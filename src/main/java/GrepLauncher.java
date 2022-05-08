package main.java;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

public class GrepLauncher {
    @Option(name = "-v", metaVar = "Find everything except word", usage = "Reverse filter")
    private final boolean inversion = false;

    @Option(name = "-i", metaVar = "Ignore case of words", usage = "Ignore case")
    private final boolean ignoring = false;

    @Option(name = "-r", metaVar = "RegexToFind", usage = "Regex filter")
    private final boolean regex = false;

    @Argument(required = true, metaVar = "word", usage = "A word to compare to")
    private String word;

    @Argument(required = true, metaVar = "InputName", index = 1, usage = "Name of the input file")
    private String inputName;

    public static void main(String[] args) {
        new GrepLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar grep.jar [-v] [-i] [-r] word InputName.txt");
            parser.printUsage(System.err);
        }
        try {
            Grep grep = new Grep(ignoring, regex, inversion);
            grep.grep(word, inputName);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
//В основном весь код был разобран на лекции по ТП создание консольного приложения на языке Java