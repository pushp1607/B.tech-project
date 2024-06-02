package com.application.controllers;

import com.application.dto.DestinationIpActionDTO;
import com.application.service.DestinationIpActionService;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DestinationIpActionController {
    @GetMapping("/destination-ip-actions")
    public ResponseEntity<List<DestinationIpActionDTO>> getDestinationIpActions() {
        try {
            DestinationIpActionService destinationIpActionService = new DestinationIpActionService();

            List<DestinationIpActionDTO> destinationIpActions = destinationIpActionService.getDestinationIpActions("C:\\Users\\91628\\Desktop\\Project\\final year cse project\\backend-project-main\\urlOutput.json");
            return ResponseEntity.ok(destinationIpActions);
        } catch (IOException | GeoIp2Exception e) {
            // Handle IOException
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
