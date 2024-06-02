package com.application.controllers;

import com.application.dto.AllowedWebsiteDTO;
import com.application.service.AllowedWebsitesService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/allowed-websites")
public class AllowedWebsitesController {

    private final AllowedWebsitesService allowedWebsitesService;

    public AllowedWebsitesController(AllowedWebsitesService allowedWebsitesService) {
        this.allowedWebsitesService = allowedWebsitesService;
    }

    @GetMapping
    public List<AllowedWebsiteDTO> getAllAllowedWebsites() {
        return allowedWebsitesService.getAllAllowedWebsites();
    }

    @PostMapping
    public void addAllowedWebsite(@RequestBody AllowedWebsiteDTO websiteDTO) {
        allowedWebsitesService.addAllowedWebsite(websiteDTO);
    }

    @DeleteMapping("/{url}")
    public void deleteAllowedWebsite(@PathVariable String url) {
        allowedWebsitesService.deleteAllowedWebsite(url);
    }
}
