package main.java;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Grep(boolean ignoring, boolean regex, boolean inversion) {

    public void grep(String word, String inputName) throws IOException {
        PrintStream consoleStream = System.out;
        try {
            PrintStream stream = new PrintStream(System.out);
            File file = new File(inputName);
            BufferedReader xxx = new BufferedReader(new FileReader(file));
            String line;
            String ig = ignoreCase(word);
            Pattern pattern = Pattern.compile(ig);
            while ((line = xxx.readLine()) != null) {
                Matcher matcher = pattern.matcher(ignoreCase(line));
                if (inversion) {
                    if (regex) {
                        if (!matcher.find()) stream.println(line);
                    } else if (!ignoreCase(line).contains(ig)) stream.println(line);
                } else if (regex) {
                        if (matcher.find()) stream.println(line);
                    } else if (ignoreCase(line).contains(ig)) stream.println(line);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        System.setOut(consoleStream);
    }
    public String ignoreCase(String text) throws IOException {
        return ignoring ? text.toLowerCase() : text;
    }
}