import java.io.*;
import java.util.Objects;
import java.util.regex.*;
import org.json.*;

public class PingLogToJson {
    public static void main(String[] args) {
        try {
            String logFilePath = "C:\\Users\\91628\\Downloads\\ping_21082023 (1)";  // Replace with your actual log file path
            BufferedReader br = new BufferedReader(new FileReader(logFilePath));

            JSONArray jsonArray = new JSONArray(); // Create an array to store JSON objects
            JSONObject json = null;
            JSONArray rttArray = null;
            String line;
            String timestamp = "";
            boolean ipFound = false;

            while((line = br.readLine()) != null) {
                if (line.startsWith("Mon ")) {
                    timestamp = line;
                } else if (line.startsWith("PINGING")) {
                    // create a new JSON object
                    if (json != null) {
                        jsonArray.put(json); // Add the previous JSON object to the array
                    }
                    ipFound = false;
                    json = new JSONObject();
                    rttArray = new JSONArray();
                    json.put("timestamp", timestamp);

                    // Extract destination information
                    String[] parts = line.split(" ");
                    StringBuilder destination = new StringBuilder();

                    for (int i = 1; i < parts.length; i++) {
                        if (parts[i].matches("\\d+(\\.\\d+){3}")) {
                            ipFound = true;
                            break;
                        }
                        destination.append(parts[i]).append(" ");
                    }

                    json.put("ping_destination", destination.toString().trim());
                    if (ipFound) {
                        json.put("destination_ip", parts[parts.length - 1]);
                    }
                } else if (Objects.equals(line.split(" ")[0], "PING") && !ipFound) {
                    String[] parts = line.split(" ");
                    StringBuilder destination = new StringBuilder();

                    for (int i = 1; i < parts.length; i++) {
                        if (parts[i].matches("\\d+(\\.\\d+){3}")) {
                            ipFound = true;
                            json.put("destination_ip", parts[i]);
                            break;
                        } else if (parts[i].startsWith("(") && parts[i].endsWith(")")) {
                            // Extract IP address from parentheses
                            String ipInParentheses = parts[i].substring(1, parts[i].length() - 1);
                            json.put("destination_ip", ipInParentheses);
                            ipFound = true;
                            break;
                        } else {
                            destination.append(parts[i]).append(" ");
                        }
                    }
                } else if (line.contains("packets transmitted")) {
                    // Use regex to match lines with relevant information
                    Pattern pattern = Pattern.compile("(\\d+) packets transmitted, (\\d+) received, (.+), time (\\d+)");
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {
                        json.put("packets_transmitted", Integer.parseInt(matcher.group(1)));
                        json.put("packets_received", Integer.parseInt(matcher.group(2)));
                        json.put("packet_loss", matcher.group(3));
                        json.put("total_time", matcher.group(4) + "ms");
                    }
                } else if (line.contains("rtt min/avg/max/mdev")) {
                    // This line contains the RTT information
                    String[] rttInfo = line.split(" ")[3].split("/");
                    JSONObject rttObj = new JSONObject();
                    rttObj.put("min", Double.parseDouble(rttInfo[0]));
                    rttObj.put("avg", Double.parseDouble(rttInfo[1]));
                    rttObj.put("max", Double.parseDouble(rttInfo[2]));
                    rttObj.put("mdev", Double.parseDouble(rttInfo[3]));
                    rttArray.put(rttObj);
                    json.put("rtt", rttArray);
                }
            }

            // Add the last JSON object to the array
            if (json != null) {
                jsonArray.put(json);
            }

            br.close();

            // Write the JSON array to a file
            try (FileWriter file = new FileWriter("output.json")) {
                file.write(jsonArray.toString(4));
                System.out.println("JSON objects written to output.json");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
