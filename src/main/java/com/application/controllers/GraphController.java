package com.application.controllers;

import com.application.dto.GraphData;
import com.application.dto.PingDataDTO;
import com.application.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GraphController {
    private final GraphService graphService;

    @Autowired
    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    @GetMapping("/ping-data")
    public GraphData getAvgRttData(@RequestParam("pingDestination") String pingDestination) {
        String filePath = "C:\\Users\\91628\\Desktop\\Project\\final year cse project\\backend-project-main\\output.json";
        return graphService.getAvgRttDataForPingDestination(filePath, pingDestination);
    }

    @GetMapping("/top-actions")
    public GraphData getTopNActions(@RequestParam(name = "jsonFilePath") String jsonFilePath,
                                    @RequestParam(name = "topN", defaultValue = "5") int topN) {
        return graphService.getActionCount(jsonFilePath, topN);
    }

    @GetMapping("/hour-count")
    public GraphData getLogCountByHour(@RequestParam(name = "jsonFilePath") String jsonFilePath) {
        String jsonFilePath2 = "C:\\Users\\91628\\Desktop\\Project\\final year cse project\\backend-project-main\\output.json";

        return graphService.getLogCountByHour(jsonFilePath2);
    }
}
