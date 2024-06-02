package com.application.controllers;

import com.application.service.ProcessorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonProcessorController {

    @PostMapping("/processPingFile")
    public String processPingFile(@RequestParam("filePath") String filePath) {
        ProcessorService processorService = new ProcessorService();
        filePath = "C:\\Users\\91628\\Downloads\\ping_21082023 (1)";
        return processorService.processPingFile(filePath);
    }

    @PostMapping("/processJitterFile")
    public String processFile(@RequestParam String inputFile, @RequestParam String[] fieldsToAdd) {
        ProcessorService processorService = new ProcessorService();
        return processorService.processJitterFile(inputFile, fieldsToAdd);
    }

}
