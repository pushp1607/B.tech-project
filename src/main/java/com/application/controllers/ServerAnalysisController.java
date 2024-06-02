package com.application.controllers;

import com.application.service.ServerAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class ServerAnalysisController {

   /* private final ServerAnalysis analysisService;

    @Autowired
    public ServerAnalysisController(ServerAnalysis analysisService) {
        this.analysisService = analysisService;
    }*/

    @GetMapping("/unique-destinations")
    public Set<String> getUniquePingDestinations(@RequestParam("jsonFilePath") String jsonFilePath) {
        ServerAnalysis serverAnalysis = new ServerAnalysis();
        System.out.println(serverAnalysis.UniquePingDestinations(jsonFilePath));
        return serverAnalysis.UniquePingDestinations(jsonFilePath);
    }

    @GetMapping("/rtt-averages")
    public List<ServerAnalysis.RTTAverageInfo> getcompleteRTTAveragesForDestination(
            @RequestParam("jsonFilePath") String jsonFilePath,
            @RequestParam("destinationToSearch") String destinationToSearch) {
        ServerAnalysis jsonAnalyzer = new ServerAnalysis();
        return jsonAnalyzer.getcompleteRTTAveragesForDestination(jsonFilePath, destinationToSearch);
    }

    @GetMapping("/tolerance-rtt-average")
    public Double getToleranceOfRTTAveragesForDestination(
            @RequestParam("jsonFilePath") String jsonFilePath,
            @RequestParam("destinationToSearch") String destinationToSearch) {
        ServerAnalysis serverAnalysis = new ServerAnalysis();

        List<Double> rttAverages = serverAnalysis.findRTTAveragesForDestination(jsonFilePath, destinationToSearch);
        System.out.println(serverAnalysis.findToleranceOfRTTAveragesForDestination(rttAverages));;
        return serverAnalysis.findToleranceOfRTTAveragesForDestination(rttAverages);
    }

    @GetMapping("/failure-percentage")
    public Double getFailurePercentageForDestination(
            @RequestParam("jsonFilePath") String jsonFilePath,
            @RequestParam("destinationToSearch") String destinationToSearch) {
        ServerAnalysis serverAnalysis = new ServerAnalysis();

        List<Double> rttAverages = serverAnalysis.findRTTAveragesForDestination(jsonFilePath, destinationToSearch);
        Double tolerance = serverAnalysis.findToleranceOfRTTAveragesForDestination(rttAverages);
        return serverAnalysis.failurePercentage(rttAverages, tolerance);
    }
}
