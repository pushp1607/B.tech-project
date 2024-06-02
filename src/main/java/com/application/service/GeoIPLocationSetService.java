package com.application.service;
import com.application.dto.GeoIP;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class GeoIPLocationSetService {
    private final DatabaseReader dbReader;

    public GeoIPLocationSetService() throws IOException {
        File database = new File("GeoLite2-City.mmdb");
        dbReader = new DatabaseReader.Builder(database).build();
    }

    public List<GeoIP> getGeoLocationsFromJsonFile(String jsonFilePath) throws IOException, GeoIp2Exception {
        List<GeoIP> geoIPList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(new File("urlOutput.json"));

        for (JsonNode node : rootNode) {
            String dstIP = node.get("dstip").asText();
            String action = node.get("action").asText();
            String timestamp = node.get("timestamp").asText();
            String url = node.get("url").asText();

            // Get geolocation for dstIP
            GeoIP geoIP = getLocation(dstIP);
            geoIP.setAction(action);
            geoIP.setTimestamp(timestamp);
            geoIP.setUrl(url);

            geoIPList.add(geoIP);
        }

        return geoIPList;
    }

    public GeoIP getLocation(String ip) throws IOException, GeoIp2Exception {
        InetAddress ipAddress = InetAddress.getByName(ip);
        CityResponse response = dbReader.city(ipAddress);

        String cityName = response.getCity().getName();
        String latitude = response.getLocation().getLatitude().toString();
        String longitude = response.getLocation().getLongitude().toString();
        return new GeoIP(ip, cityName, latitude, longitude);
    }
}
