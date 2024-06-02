package com.application.dto;

import com.application.dto.EmailData;

public class AlertRequest {
    private String filepath;
    private Double toleranceFactor;
    private EmailData emailData;

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Double getToleranceFactor() {
        return toleranceFactor;
    }

    public void setToleranceFactor(Double toleranceFactor) {
        this.toleranceFactor = toleranceFactor;
    }

    public EmailData getEmailData() {
        return emailData;
    }

    public void setEmailData(EmailData emailData) {
        this.emailData = emailData;
    }
}