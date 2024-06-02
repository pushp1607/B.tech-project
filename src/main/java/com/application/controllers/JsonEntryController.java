package com.application.controllers;

import com.application.dto.JsonEntryDTO;
import com.application.service.JsonFieldExtractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/json-entries")
public class JsonEntryController {

    private final JsonFieldExtractorService jsonFieldExtractorService;

    @Autowired
    public JsonEntryController(JsonFieldExtractorService jsonFieldExtractorService) {
        this.jsonFieldExtractorService = jsonFieldExtractorService;
    }

    @GetMapping("/get-by-action/{targetAction}")
    public List<JsonEntryDTO> getJsonEntriesByAction(@PathVariable String targetAction) {
        return jsonFieldExtractorService.getJsonEntriesByAction(targetAction);
    }
}
