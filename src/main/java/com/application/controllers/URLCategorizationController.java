package com.application.controllers;

import com.application.service.URLCategorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class URLCategorizationController {

    @Autowired
    private URLCategorizationService categorizationService;

    @PostMapping("/categorize")
    public @ResponseBody Map<String, String> categorizeURL(@RequestBody Map<String, String> requestData) {
        String url = requestData.get("url");
        return categorizationService.getCategorizationConfidence(url);
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
