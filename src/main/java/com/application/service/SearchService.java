package com.application.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class SearchService {

    public static Map<String, Double> calculateTopicPercentages(String jsonFilePath) throws IOException {
        Map<String, Integer> topicCounts = new HashMap<>();

        try (FileInputStream fileInputStream = new FileInputStream(jsonFilePath)) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(fileInputStream);

            if (rootNode.isArray()) {
                for (JsonNode node : rootNode) {
                    JsonNode searchQueryNode = node.get("searchQuery");
                    if (searchQueryNode != null) {
                        String searchQuery = searchQueryNode.asText();
                        String topic = extractTopic(searchQuery);
                        topicCounts.put(topic, topicCounts.getOrDefault(topic, 0) + 1);
                    }
                }
            }

        }


        int totalSearches = topicCounts.values().stream().mapToInt(Integer::intValue).sum();

        Map<String, Double> topicPercentages = new HashMap<>();
        for (Map.Entry<String, Integer> entry : topicCounts.entrySet()) {
            double percentage = (double) entry.getValue() / totalSearches * 100;
            topicPercentages.put(entry.getKey(), percentage);
        }

        return topicPercentages;
    }


    public static Map<String, Double> calculateVisitedSitePercentages(String jsonFilePath) throws IOException {
        Map<String, Integer> topicCounts = new HashMap<>();

        try (FileInputStream fileInputStream = new FileInputStream(jsonFilePath)) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(fileInputStream);

            if (rootNode.isArray()) {
                for (JsonNode node : rootNode) {
                    JsonNode searchQueryNode = node.get("visitedWebsite");
                    if (searchQueryNode != null) {
                        String searchQuery = searchQueryNode.asText();
                        String topic = extractTopic(searchQuery);
                        topicCounts.put(topic, topicCounts.getOrDefault(topic, 0) + 1);
                    }
                }
            }

        }


        int totalSearches = topicCounts.values().stream().mapToInt(Integer::intValue).sum();

        Map<String, Double> topicPercentages = new HashMap<>();
        for (Map.Entry<String, Integer> entry : topicCounts.entrySet()) {
            double percentage = (double) entry.getValue() / totalSearches * 100;
            topicPercentages.put(entry.getKey(), percentage);
        }

        return topicPercentages;
    }
    private static String extractTopic(String searchQuery) {
        // Simplified extraction, you might want to enhance it
        String[] parts = searchQuery.split("\\s+");
        return parts.length > 0 ? parts[0] : "Unknown";
    }
}
