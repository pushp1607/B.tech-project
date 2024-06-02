package com.application.service;

import com.application.dto.GraphData;
import com.application.dto.PingDataDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GraphService {


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
    public GraphData getAvgRttDataForPingDestination(String filePath, String pingDestination) {

        List<String> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(new File(filePath));

            for (JsonNode entry : jsonNode) {
                String entryPingDestination = entry.get("ping_destination").asText();
                if (pingDestination.equals(entryPingDestination)) {
                    String timestamp = entry.get("timestamp").asText();
                    double avgRtt = entry.get("rtt").get(0).get("avg").asDouble();
                    xValues.add(timestamp);
                    yValues.add(avgRtt);

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        GraphData graphData = new GraphData();
        graphData.setxValues(xValues);
        graphData.setyValues(yValues);
        System.out.println(graphData.getxValues());
        System.out.println(graphData.getyValues());
        return graphData;
    }

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

    public GraphData getActionCount(String jsonFilePath, int topN) {
        List<String> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();
        System.out.println(jsonFilePath);
       // jsonFilePath = "C:\\Users\\91628\\Desktop\\Project\\final year cse project\\backend-project-main\\JitterLogs.json";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File jsonFile = new File(jsonFilePath);

            JsonNode rootNode = objectMapper.readTree(jsonFile);

            // Create a map to store the count of each action
            Map<String, Integer> actionCountMap = new HashMap<>();

            if (rootNode.isArray()) {
                for (JsonNode element : rootNode) {
                    if (element.has("action")) {
                        String action = element.get("action").asText();
                        // Update the count for the action in the map
                        actionCountMap.put(action, actionCountMap.getOrDefault(action, 0) + 1);
                    }
                }
            }

            // Sort the map by values in descending order
            Map<String, Integer> sortedActionCountMap = actionCountMap.entrySet()
                    .stream()
                    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            LinkedHashMap::new
                    ));

            // Take the top 5 entries
            int count = 0;
            for (Map.Entry<String, Integer> entry : sortedActionCountMap.entrySet()) {
                if (count < topN) {
                    if(Objects.equals(entry.getKey(), "")) continue;
                    xValues.add(entry.getKey());
                    yValues.add((double) entry.getValue());
                    count++;
                } else {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create and return GraphData object
        GraphData graphData = new GraphData();
        graphData.setxValues(xValues);
        graphData.setyValues(yValues);
        System.out.println(xValues);
        System.out.println(yValues);
        return graphData;
    }
}
