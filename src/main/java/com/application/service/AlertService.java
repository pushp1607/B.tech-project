package com.application.service;
import com.application.dto.EmailData;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class AlertService4 {
    public String sendAlertMail(String filepath, Double toleranceFactor, EmailData emailData) {
        ServerAnalysis serverAnalysis = new ServerAnalysis();
        Set<String> uniqueDestinations = serverAnalysis.UniquePingDestinations(filepath);
        StringBuilder emailBody = new StringBuilder();

        emailBody.append("<p style='margin-top: 20px; font-family: Arial, sans-serif; font-size: 16px;'>");
        emailBody.append(" Our network monitoring systems have detected that certain destinations are currently experiencing higher than usual round-trip times. This may indicate potential network issues that require attention. \n" +
                "    Higher round-trip times can result in degraded network performance, leading to delays in data transmission and service disruptions. \n" +
                "    To ensure the optimal performance and reliability of our network infrastructure, it's essential to investigate and address these issues promptly. \n" +
                "    Below is a detailed summary of the affected destinations along with their associated round-trip times. ");
        emailBody.append("</p>");

        // Start table header
        emailBody.append("<table style='border-collapse: collapse;'>");
        emailBody.append("<tr>");
        emailBody.append("<th style='border: 1px solid black; padding: 8px;'>Destination Name</th>");
        emailBody.append("<th style='border: 1px solid black; padding: 8px;'>Destination IP</th>");
        emailBody.append("<th style='border: 1px solid black; padding: 8px;'>Timestamp</th>");
        emailBody.append("<th style='border: 1px solid black; padding: 8px;'>RTT (Round-Trip Time) Average</th>");
        emailBody.append("</tr>");

        for (String destination : uniqueDestinations) {
            List<Double> rttAverages = serverAnalysis.findRTTAveragesForDestination(filepath, destination);
            Double toleranceRttAvg = serverAnalysis.findToleranceOfRTTAveragesForDestination(rttAverages);

            // Append each row for the destination
            for (ServerAnalysis.RTTAverageInfo entry : serverAnalysis.getcompleteRTTAveragesForDestination(filepath, destination)) {
                if (entry.getRttAverage() > toleranceRttAvg * toleranceFactor) {
                    emailBody.append("<tr>");
                    emailBody.append("<td style='border: 1px solid black; padding: 8px;'>").append(destination).append("</td>");
                    emailBody.append("<td style='border: 1px solid black; padding: 8px;'>").append(entry.getDestinationIp()).append("</td>");
                    emailBody.append("<td style='border: 1px solid black; padding: 8px;'>").append(entry.getTimestamp()).append("</td>");
                    emailBody.append("<td style='border: 1px solid black; padding: 8px;'>").append(entry.getRttAverage()).append("</td>");
                    emailBody.append("</tr>");
                }
            }
        }

        // End table
        emailBody.append("</table>");

        // Adding a descriptive message about the alert


        emailData.setEmailBody(String.valueOf(emailBody));
        EmailService emailService = new EmailService();
        return emailService.setAlertMail(emailData);
    }

    public String sendAlertMailReal(String filepath, Double toleranceFactor, EmailData emailData) {
        ServerAnalysis serverAnalysis = new ServerAnalysis();
        Set<String> uniqueDestinations = serverAnalysis.UniquePingDestinations(filepath);
        StringBuilder emailBody = new StringBuilder();

        // Start table header
        emailBody.append("<table style='border-collapse: collapse;'>");
        emailBody.append("<tr>");
        emailBody.append("<th style='border: 1px solid black; padding: 8px;'>Destination IP</th>");
        emailBody.append("<th style='border: 1px solid black; padding: 8px;'>Timestamp</th>");
        emailBody.append("<th style='border: 1px solid black; padding: 8px;'>RTT</th>");
        emailBody.append("</tr>");

        for (String destination : uniqueDestinations) {
            List<Double> rttAverages = serverAnalysis.findRTTAveragesForDestination(filepath, destination);
            Double toleranceRttAvg = serverAnalysis.findToleranceOfRTTAveragesForDestination(rttAverages);

            // Append each row for the destination
            for (ServerAnalysis.RTTAverageInfo entry : serverAnalysis.getcompleteRTTAveragesForDestination(filepath, destination)) {
                if (entry.getRttAverage() > toleranceRttAvg * toleranceFactor) {
                    emailBody.append("<tr>");
                    emailBody.append("<td style='border: 1px solid black; padding: 8px;'>").append(entry.getDestinationIp()).append("</td>");
                    emailBody.append("<td style='border: 1px solid black; padding: 8px;'>").append(entry.getTimestamp()).append("</td>");
                    emailBody.append("<td style='border: 1px solid black; padding: 8px;'>").append(entry.getRttAverage()).append("</td>");
                    emailBody.append("</tr>");
                }
            }
        }

        // End table
        emailBody.append("</table>");
        emailData.setEmailBody(String.valueOf(emailBody));
        EmailService emailService = new EmailService();
        return emailService.setAlertMail(emailData);
    }
    public String sendAlertMail2(String inputURL) {
        EmailService emailService = new EmailService();
        return emailService.setAlertMail2(inputURL);
    }


}
