package com.application.service;

import com.application.service.URLCategorizationApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class URLCategorizationService {

    @Autowired
    private URLCategorizationApiClient categorizationApiClient;

    public Map<String, String> getCategorizationConfidence(String url) {
        return categorizationApiClient.getCategorizationConfidence(url);
    }
}
