package golja.console;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

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
        sb.append("Rules:\n\n");
        int num = 1;
        for (Rule r : Rules.AVAILABLE_RULES) {
            sb.append((num++) + ": " + r.title());
            sb.append("\n");
            sb.append(r.description());
            sb.append("\n\n");
        }
        return sb.toString();
    }

    public static void closeOut(Terminal term) {
        try {
            term.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static Rule pickRule(Terminal term) {
        List<String> ruleInputs = Rules.AVAILABLE_RULES.stream().map((rule) -> rule.title()).toList();
        List<String> ruleInputsNum = IntStream.range(1, Rules.AVAILABLE_RULES.size() + 1)
                                        .boxed().map((i) -> i.toString()).toList();
        System.out.println("Pick a rule from the list (select by typing number). Selecting nothing will select the default.");
        System.out.println(availableRules());
        LineReader reader = LineReaderBuilder.builder()
                                .terminal(term)
                                .completer(new StringsCompleter(ruleInputsNum))
                                .build();
        while(true)
        {
            String line = reader.readLine("> ");
            if (line == null) {
                return Rules.AVAILABLE_RULES.get(0);
            };
            if (line.equalsIgnoreCase("exit")) {
                closeOut(term);
            }
            for(String num : ruleInputsNum) {
                if(num.equals(line)) {
                    return Rules.AVAILABLE_RULES.get(Integer.parseInt(num));
                }
            }
            reader.getHistory().add(line);
        }
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
            //System.out.println(availableRules());
            pickRule(term);
        }

        term.close();
    }
}
