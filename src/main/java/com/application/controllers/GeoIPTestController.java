package com.application.controllers;

import com.application.dto.GeoIP;
import com.application.service.RawDBDemoGeoIPLocationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class GeoIPTestController {
    private final RawDBDemoGeoIPLocationService locationService;

    public GeoIPTestController() throws IOException {
        locationService = new RawDBDemoGeoIPLocationService();
    }

    @GetMapping("/GeoIPTest")
    public GeoIP getLocation(
            @RequestParam(value="ipAddress", required=true) String ipAddress
    ) throws Exception {

        System.out.println(ipAddress);
        System.out.println(locationService.getLocation(ipAddress));
        return locationService.getLocation(ipAddress);
    }
}