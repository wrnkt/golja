package golja.console;

import java.io.IOException;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import golja.rule.Rules;
import golja.rule.Rule;

public class ConsoleApp
{
    public static String availableRules()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Rules:\n");
        for (Rule r : Rules.AVAILABLE_RULES) {
            sb.append(r.title());
            sb.append("\n");
            sb.append(r.description());
        }
        return sb.toString();
    }

    public static String introduce()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Welcome to Golja.");
        return sb.toString();
    }

    public static void main(String[] args) throws IOException
    {
        Terminal term = TerminalBuilder.terminal();
        LineReader reader = LineReaderBuilder.builder()
                                .terminal(term)
                                .completer(new StringsCompleter("option1", "option2"))
                                .build();

        while(true)
        {
            String line = reader.readLine("> ");
            if (line == null || line.equalsIgnoreCase("exit")) break;
            reader.getHistory().add(line);
            System.out.println(introduce());
            System.out.println(availableRules());
            //System.out.println("Input was: \"" + line + "\"");
        }

        term.close();
    }
}
