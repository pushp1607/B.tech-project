package com.application.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class URLCategorizationApiClient {

    private static final String API_ENDPOINT = "https://website-categorization.whoisxmlapi.com/api/v2";
    private static final String API_KEY = "at_wAs3nIxsj2dv1XGWe8jQUfFvwf3UM";
    //at_eLkwN8pf0IJssGuOm9S1yYrh54UiV
    //at_1P0oi6WTDpZcAESDbB6fvOVq0Qbnb (working)
//https://www.whoisxmlapi.com/whoisserver/WhoisService?apiKey=at_wAs3nIxsj2dv1XGWe8jQUfFvwf3UM&domainName=google.com
    public Map<String, String> getCategorizationConfidence(String url) {
        String requestUrl = API_ENDPOINT + "?apiKey=" + API_KEY + "&domainName=" + url;

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(requestUrl, Map.class);

        return extractConfidence(response);
    }

    private Map<String, String> extractConfidence(Map<String, Object> response) {
        Map<String, String> confidenceMap = new HashMap<>();
        if (response != null && response.containsKey("categories")) {
            List<Map<String, Object>> categories = (List<Map<String, Object>>) response.get("categories");
            for (Map<String, Object> category : categories) {
                Map<String, Object> tier1 = (Map<String, Object>) category.get("tier1");
                Map<String, Object> tier2 = (Map<String, Object>) category.get("tier2");

                String tier1Name = (String) tier1.get("name");
                Double tier1Confidence = (Double) tier1.get("confidence");
                confidenceMap.put(tier1Name, String.format("%.2f", tier1Confidence));

                if (tier2 != null) {
                    String tier2Name = (String) tier2.get("name");
                    Double tier2Confidence = (Double) tier2.get("confidence");
                    confidenceMap.put(tier2Name, String.format("%.2f", tier2Confidence));
                }
            }
        }
        return confidenceMap;
    }
}
