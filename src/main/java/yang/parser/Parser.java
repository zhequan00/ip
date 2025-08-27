package yang.parser;

import yang.YangException;
import yang.task.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parses raw user input strings into executable commands.
 * <p>
 * The {@code Parser} interprets command keywords such as {@code todo}, {@code deadline},
 * {@code event}, {@code mark}, {@code unmark}, {@code delete}, and {@code list},
 * then returns a corresponding {@link CommandResult}. Parsing errors or malformed
 * arguments are signaled via {@link YangException}.
 * </p>
 */
public class Parser {

    public static CommandResult apply(String input, TaskList tasks) throws YangException {
        input = input.trim();
        if (input.equals("list")) {
            return CommandResult.list();

        } else if (input.startsWith("todo")) {
            String desc = requireNonEmpty(input.substring(4), "☹ Ohno!! The description of a todo cannot be empty.");
            Task t = new Todo(desc);
            tasks.add(t);
            return CommandResult.added(t);

        } else if (input.startsWith("deadline")) {
            String[] p = input.substring(8).trim().split("/by", 2);
            if (p.length < 2) throw new YangException("Usage: deadline <desc> /by <yyyy-mm-dd>");

            String desc = requireNonEmpty(p[0], "☹ Ohno!! The description of a deadline cannot be empty.");
            String dateStr = requireNonEmpty(p[1], "☹ Ohno!!! Deadline date is missing (use yyyy-mm-dd).");

            LocalDate by = parseIsoDate(dateStr);
            Task t = new Deadline(desc, by);
            tasks.add(t);
            return CommandResult.added(t);

        } else if (input.startsWith("event")) {
            String[] p = input.substring(5).trim().split("/at", 2);
            if (p.length < 2) throw new YangException("Usage: event <desc> /at <yyyy-mm-dd>");

            String desc = requireNonEmpty(p[0], "☹ Ohno!!! The description of an event cannot be empty.");
            String dateStr = requireNonEmpty(p[1], "☹ Ohno!!! Event date is missing (use yyyy-mm-dd).");

            LocalDate at = parseIsoDate(dateStr);
            Task t = new Event(desc, at);
            tasks.add(t);
            return CommandResult.added(t);

        } else if (input.startsWith("mark")) {
            int idx = parseIndex(input, tasks.size());
            tasks.mark(idx);
            return CommandResult.marked(tasks.get(idx));

        } else if (input.startsWith("unmark")) {
            int idx = parseIndex(input, tasks.size());
            tasks.unmark(idx);
            return CommandResult.unmarked(tasks.get(idx));

        } else if (input.startsWith("delete")) {
            int idx = parseIndex(input, tasks.size());
            Task removed = tasks.remove(idx);
            return CommandResult.deleted(removed);

        } else if (!input.equals("bye")) {
            throw new YangException("Invalid command: " + input);
        }

        return CommandResult.none();
    }

    private static String requireNonEmpty(String s, String msg) throws YangException {
        if (s == null || s.trim().isEmpty()) throw new YangException(msg);
        return s.trim();
    }

    private static LocalDate parseIsoDate(String s) throws YangException {
        try {
            return LocalDate.parse(s.trim());
        } catch (DateTimeParseException e) {
            throw new YangException("☹ OOPS!!! Use date format yyyy-mm-dd.");
        }
    }

    private static int parseIndex(String input, int size) throws YangException {
        String[] parts = input.split("\\s+");
        if (parts.length < 2) throw new YangException("☹ OOPS!!! Please provide an item number.");
        try {
            int idx = Integer.parseInt(parts[1]) - 1;
            if (idx < 0 || idx >= size) throw new YangException("☹ OOPS!!! That task number is out of range.");
            return idx;
        } catch (NumberFormatException e) {
            throw new YangException("☹ OOPS!!! Task number must be an integer.");
        }
    }
}
