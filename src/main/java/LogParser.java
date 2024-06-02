import org.json.JSONObject;
import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LogParser {
    public static void main(String[] args) {
        String inputFile = "JitterLogs.txt";
        String outputFile = "JitterLogs.json";
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            JSONArray jsonArray = new JSONArray();
            int count=0;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    JSONObject logEntry = parseLogEntry(line);
                    jsonArray.put(logEntry);
                    count++;
                    //if(count >= 200) break;

                }
            }
            // Write the JSON data to an output file
            try (FileWriter fileWriter = new FileWriter(outputFile)) {
                fileWriter.write(jsonArray.toString(4)); // Indent with 4 spaces for readability
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject parseLogEntry(String line) {
        JSONObject logEntry = new JSONObject();
        String[] fieldsToAdd = {"user", "date", "time", "srcip", "sessionid", "hostname","action", "srcport", "dstport"};

        for (String field : fieldsToAdd) {
            logEntry.put(field, ""); // Initialize all fields with an empty value
        }
        String[] keyValuePairs = line.split("\\s+");
        for (String pair : keyValuePairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2 && containsField(fieldsToAdd, keyValue[0])) {
                String value = keyValue[1].replaceAll("^\"|\"$", ""); // Remove leading and trailing quotes
                logEntry.put(keyValue[0], value);
            }
        }
        if(logEntry.length() != 9) System.out.println(logEntry.length());
        return logEntry;
    }
    private static boolean containsField(String[] fields, String field) {
        for (String f : fields) {
            if (f.equals(field)) {
                return true;
            }
        }
        return false;
    }
}
