package com.application.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class ServerAnalysis {
    public Set<String> UniquePingDestinations(String jsonFilePath) {

            // Provide the path to your JSON file
            jsonFilePath = "output.json";
        // Create a set to store unique ping destinations
        Set<String> uniqueDestinations = new HashSet<>();
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                File jsonFile = new File(jsonFilePath);

                // Read the JSON file and parse it into a JSON tree
                JsonNode rootNode = objectMapper.readTree(jsonFile);



                // Iterate through the JSON elements and extract the ping destinations
                if (rootNode.isArray()) {
                    for (JsonNode element : rootNode) {
                        if (element.has("ping_destination")) {
                            String destination = element.get("ping_destination").asText();
                            uniqueDestinations.add(destination);
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return uniqueDestinations;
        }
    public static class RTTAverageInfo {
        private String timestamp;
        private String destinationIp;
        private Double rttAverage;

        public RTTAverageInfo(String timestamp, String destinationIp, Double rttAverage) {
            this.timestamp = timestamp;
            this.destinationIp = destinationIp;
            this.rttAverage = rttAverage;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public String getDestinationIp() {
            return destinationIp;
        }

        public Double getRttAverage() {
            return rttAverage;
        }
    }

    public List<RTTAverageInfo> getcompleteRTTAveragesForDestination(String jsonFilePath, String destinationToSearch) {
        jsonFilePath = "output.json";

        List<RTTAverageInfo> rttAverages = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File jsonFile = new File(jsonFilePath);
            JsonNode rootNode = objectMapper.readTree(jsonFile);

            if (rootNode.isArray()) {
                for (JsonNode element : rootNode) {
                    if (element.has("ping_destination") && element.get("ping_destination").asText().equals(destinationToSearch)) {
                        if (element.has("rtt") && element.get("rtt").isArray()) {
                            for (JsonNode rttNode : element.get("rtt")) {
                                if (rttNode.has("mdev")) {
                                    String timestamp = element.get("timestamp").asText();
                                    String destinationIp = element.get("destination_ip").asText();
                                    Double rttAverage = rttNode.get("avg").asDouble();

                                    RTTAverageInfo info = new RTTAverageInfo(timestamp, destinationIp, rttAverage);
                                    rttAverages.add(info);
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rttAverages;
    }



    public Double findToleranceOfRTTAveragesForDestination(List<Double> rttAverages) {
        Double toleranceRttAvg = 0.0;
        Double sum = 0.0;
        for (Double rttAverage : rttAverages) {
            sum += rttAverage;
        }
        toleranceRttAvg = sum/ rttAverages.size();
        return toleranceRttAvg;
    }

    public Double failurePercentage(List<Double> rttAverages, Double tolerance){

        Double failPercentage = 0.0;
        Double failureCount = 0.0;
        for(Double rttAverage : rttAverages){
            if(rttAverage >= tolerance) failureCount++;
        }
        failPercentage = failureCount / rttAverages.size();
        return failPercentage;
    }
    public List<Double> findRTTAveragesForDestination(String jsonFilePath, String destinationToSearch) {
        jsonFilePath = "output.json";

        List<Double> rttAverages = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File jsonFile = new File(jsonFilePath);
            JsonNode rootNode = objectMapper.readTree(jsonFile);

            if (rootNode.isArray()) {
                for (JsonNode element : rootNode) {
                    if (element.has("ping_destination") && element.get("ping_destination").asText().equals(destinationToSearch)) {
                        if (element.has("rtt") && element.get("rtt").isArray()) {
                            for (JsonNode rttNode : element.get("rtt")) {
                                if (rttNode.has("mdev")) {
                                    rttAverages.add(rttNode.get("avg").asDouble());
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rttAverages;
    }

}
