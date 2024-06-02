package com.application.service;

import com.application.dto.DestinationIpActionDTO;
import com.application.dto.GeoIP;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class DestinationIpActionService {

    public List<DestinationIpActionDTO> getDestinationIpActions(String filePath) throws IOException, GeoIp2Exception {
        RawDBDemoGeoIPLocationService rawDBDemoGeoIPLocationService = new RawDBDemoGeoIPLocationService();
        List<DestinationIpActionDTO> destinationIpActions = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(new File(filePath));

        // Iterate over each JSON object in the array
        Iterator<JsonNode> elements = rootNode.elements();
        while (elements.hasNext()) {
            JsonNode node = elements.next();
            String dstip = node.get("dstip").asText(); // Extract dstip field
            String action = node.get("action").asText(); // Extract action field

            // Fetch geolocation information for the destination IP address
            GeoIP geoIP = rawDBDemoGeoIPLocationService.getLocation(dstip);

            // Create a DestinationIpActionDTO with geolocation information
            DestinationIpActionDTO destinationIpActionDTO = new DestinationIpActionDTO(dstip, action);
            destinationIpActionDTO.setCity(geoIP.getCity());
            destinationIpActionDTO.setLatitude(geoIP.getLatitude());
            destinationIpActionDTO.setLongitude(geoIP.getLongitude());

            destinationIpActions.add(destinationIpActionDTO);
        }

        return destinationIpActions;
    }
}
