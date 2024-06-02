package com.application.config;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LogToJsonConverter {

    public static void main(String[] args) {
        // Path to the log file
        String filePath = "C:\\Users\\91628\\Desktop\\Project\\final year cse project\\backend-project-main\\url.log";

        // Read the log file
        List<String> logLines = readLogFile(filePath);

        // Convert log lines to JSON array
        JSONArray jsonArray = convertToJSONArray(logLines);

        // Write JSON array to a file
        writeJsonToFile(jsonArray, "urlOutput.json");
    }

    private static List<String> readLogFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private static JSONArray convertToJSONArray(List<String> logLines) {
        JSONArray jsonArray = new JSONArray();
        for (String line : logLines) {
            JSONObject jsonObject = new JSONObject();
            String[] parts = line.split("\\s+");
            for (String part : parts) {
                String[] keyValue = part.split("=");
                if (keyValue.length == 2) {
                    jsonObject.put(keyValue[0], keyValue[1].replace("\"", ""));
                }
            }
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }

    private static void writeJsonToFile(JSONArray jsonArray, String fileName) {
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(jsonArray.toString(4)); // 4 is the number of spaces for indentation
            System.out.println("JSON file has been created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
