package com.application.controllers;

import com.application.dto.EmailData;
import com.application.service.AlertService4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlertController {


    @PostMapping("/sendAlert")
    public ResponseEntity<String> sendAlert(@RequestBody AlertRequest alertRequest) {
        String filepath = alertRequest.getFilepath();
        Double toleranceFactor = alertRequest.getToleranceFactor();
        EmailData emailData = alertRequest.getEmailData();
        AlertService4 alertService = new AlertService4();
        String response = alertService.sendAlertMail(filepath, toleranceFactor, emailData);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/sendAlert2")
    public ResponseEntity<String> sendAlert2(@RequestBody String inputURL) {

        AlertService4 alertService = new AlertService4();
        String response = alertService.sendAlertMail2(inputURL);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // Inner class to represent the request body structure
    static class AlertRequest {
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
}
