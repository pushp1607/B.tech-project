package com.application.config;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

public class LogGenerator {

    // Method to generate a random timestamp
    private static String getRandomTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    // Method to generate a random action
    private static String getRandomAction() {
        String[] actions = {"allowed", "blocked", "redirected"};
        return actions[new Random().nextInt(actions.length)];
    }

    // Method to generate a random URL
    private static String getRandomURL() {
        String[] urls = {"https://www.example.com/", "https://www.google.com/", "https://www.github.com/"};
        return urls[new Random().nextInt(urls.length)];
    }

    // Method to generate a random destination IP
    private static String getRandomDstIP() {
        // Generate a random IP in the format xxx.xxx.xxx.xxx
        Random rand = new Random();
        return rand.nextInt(256) + "." + rand.nextInt(256) + "." + rand.nextInt(256) + "." + rand.nextInt(256);
    }

    // Method to generate a random direction
    private static String getRandomDirection() {
        String[] directions = {"outgoing", "incoming"};
        return directions[new Random().nextInt(directions.length)];
    }

    // Method to generate logs as JSON objects
    private static JSONObject generateLog() {
        JSONObject log = new JSONObject();
        log.put("timestamp", getRandomTimestamp());
        log.put("action", getRandomAction());
        log.put("url", getRandomURL());
        log.put("dstIP", getRandomDstIP());
        log.put("direction", getRandomDirection());
        return log;
    }

    // Method to generate and write logs to a file
    public static void generateLogs(int n, String filePath) {
        List<JSONObject> logs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            logs.add(generateLog());
        }
        JSONArray jsonArray = new JSONArray(logs);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(jsonArray.toString(2));
            System.out.println("Logs generated successfully!");
        } catch (IOException e) {
            System.err.println("Error writing logs to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        int numberOfLogs = 50; // Specify the number of logs to generate
        String filePath = "C:\\Users\\91628\\Desktop\\Project\\final year cse project\\backend-project-main\\testjsion.json"; // Specify the file path

        // Generate logs and write them to the file
        generateLogs(numberOfLogs, filePath);
    }
}
