import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {

    public static void main(String[] args) throws IOException {

        String valuesJson = Files.readString(Path.of(args[0]));
        String testsJson = Files.readString(Path.of(args[1]));

        Map<Integer, String> values = new HashMap<>();

        Pattern valuePattern = Pattern.compile(
                "\"id\"\\s*:\\s*(\\d+)\\s*,\\s*\"value\"\\s*:\\s*\"([^\"]*)\""
        );

        Matcher valuesMatcher = valuePattern.matcher(valuesJson);

        while (valuesMatcher.find()) {
            int id = Integer.parseInt(valuesMatcher.group(1));
            String value = valuesMatcher.group(2);

            values.put(id, value);
        }


        Pattern testPattern = Pattern.compile(
                "(\"id\"\\s*:\\s*(\\d+)\\s*,(?:(?!\\{|\\}).)*?\"value\"\\s*:\\s*\")([^\"]*)(\")",
                Pattern.DOTALL
        );

        Matcher testsMatcher = testPattern.matcher(testsJson);

        StringBuffer report = new StringBuffer();

        while (testsMatcher.find()) {

            int id = Integer.parseInt(testsMatcher.group(2));

            String value = values.get(id);

            if (value != null) {
                String replacement =
                        testsMatcher.group(1)
                                + value
                                + testsMatcher.group(4);

                testsMatcher.appendReplacement(
                        report,
                        Matcher.quoteReplacement(replacement)
                );
            } else {
                testsMatcher.appendReplacement(
                        report,
                        Matcher.quoteReplacement(testsMatcher.group())
                );
            }
        }

        testsMatcher.appendTail(report);

        Files.writeString(
                Path.of(args[2]),
                report.toString()
        );
    }
}