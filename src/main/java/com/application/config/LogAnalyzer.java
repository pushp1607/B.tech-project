package com.application.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogAnalyzer {

    public GraphData getLogCountByHour(String filePath) {
        List<String> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(new File(filePath));

            // Initialize a map to hold the count of logs for each hour
            Map<Integer, Integer> hourCounts = new HashMap<>();
            for (int hour = 0; hour < 24; hour++) {
                hourCounts.put(hour, 0);
            }

            // Iterate through each entry in the JSON file
            for (JsonNode entry : jsonNode) {
                String timestamp = entry.get("timestamp").asText();
                int hour = extractHourFromTimestamp(timestamp);
                // Increment the count for the corresponding hour
                hourCounts.put(hour, hourCounts.get(hour) + 1);
            }

            // Populate x and y values for the graph
            for (int hour = 0; hour < 24; hour++) {
                xValues.add(hour + ":00"); // Assuming hours are in 24-hour format
                yValues.add(Double.valueOf(hourCounts.get(hour)));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        GraphData graphData = new GraphData();
        graphData.setxValues(xValues);
        graphData.setyValues(yValues);
        return graphData;
    }

    // Extracts hour from the timestamp string (format: "Mon Aug 21 22:32:11 IST 2023")
    private int extractHourFromTimestamp(String timestamp) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
            Date date = format.parse(timestamp);
            return date.getHours();
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void main(String[] args) {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        GraphData graphData = logAnalyzer.getLogCountByHour("C:\\Users\\91628\\Desktop\\Project\\final year cse project\\backend-project-main\\output.json");
        System.out.println("x values: " + graphData.getxValues());
        System.out.println("y values: " + graphData.getyValues());
    }
}

class GraphData {
    private List<String> xValues;
    private List<Double> yValues;

    public List<String> getxValues() {
        return xValues;
    }

    public void setxValues(List<String> xValues) {
        this.xValues = xValues;
    }

    public List<Double> getyValues() {
        return yValues;
    }

    public void setyValues(List<Double> yValues) {
        this.yValues = yValues;
    }
}
