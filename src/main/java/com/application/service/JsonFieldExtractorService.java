package com.application.service;

import com.application.dto.JsonEntryDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class JsonFieldExtractorService {

    // Provide the path to your JSON file
    private final String jsonFilePath = "JitterLogs.json";

    public List<JsonEntryDTO> getJsonEntriesByAction(String targetAction) {
        List<JsonEntryDTO> jsonEntries = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File jsonFile = new File(jsonFilePath);

            // Read the JSON file and parse it into a JSON tree
            JsonNode rootNode = objectMapper.readTree(jsonFile);

            // Check if the root node is an array
            if (rootNode.isArray()) {
                // Iterate through the JSON array elements
                for (JsonNode element : rootNode) {
                    // Check if the "action" field exists and has the target value
                    if (element.has("action") && element.get("action").asText().equals(targetAction)) {
                        // Create a DTO and add it to the list
                        JsonEntryDTO jsonEntry = createJsonEntryDTO(element);
                        jsonEntries.add(jsonEntry);
                    }
                }
            } else {
                System.out.println("Root node is not an array.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonEntries;
    }

    private JsonEntryDTO createJsonEntryDTO(JsonNode element) {
        // Extract values from JSON element and create a DTO
        return new JsonEntryDTO(
                element.get("date").asText(),
                element.get("srcip").asText(),
                element.get("hostname").asText(),
                element.get("dstport").asText(),
                element.get("action").asText(),
                element.get("srcport").asText(),
                element.get("time").asText(),
                element.get("sessionid").asText(),
                element.get("user").asText()
        );
    }
}
