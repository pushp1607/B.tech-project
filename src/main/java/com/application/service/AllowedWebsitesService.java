package com.application.service;

import com.application.dto.AllowedWebsiteDTO;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AllowedWebsitesService {

    private final List<AllowedWebsiteDTO> allowedWebsites = new ArrayList<>();

    public List<AllowedWebsiteDTO> getAllAllowedWebsites() {
        return allowedWebsites;
    }

    public void addAllowedWebsite(AllowedWebsiteDTO websiteDTO) {
        allowedWebsites.add(websiteDTO);
    }

    public void deleteAllowedWebsite(String url) {
        allowedWebsites.removeIf(websiteDTO -> websiteDTO.getUrl().equals(url));
    }
}
