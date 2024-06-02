package com.application.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProcessorService {
    public String processPingFile(String logFilePath) {
        try {
            System.out.println(logFilePath);
            //String logFilePath = "C:\\Users\\91628\\Downloads\\ping_21082023 (1)";  // Replace with your actual log file path
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
        return "successfully processed the given ping file to json";
    }

    public String processJitterFile(String inputFile, String[] fieldsToAdd) {
        inputFile = "JitterLogs.txt";
        String outputFile = "JitterLogs.json";
        System.out.println("called here 45");
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
        return "successfully processed the given Jitter file to json";
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
