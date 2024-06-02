package com.application.controllers;

import com.application.dto.GeoIP;
import com.application.service.GeoIPLocationSetService;
import com.application.service.SearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.HtmlEmail;
import org.jfree.data.json.impl.JSONArray;
import org.jfree.data.json.impl.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Example {
    public static void main(String[] args) throws IOException, GeoIp2Exception {
        System.out.println("ok");

        String senderEmailId = "pushp.19je0663@me.iitism.ac.in";
        String password = "Pushpraj15437@";
        String emailBody = "alert mail";
        String recipient = "pushp628700@gmail.com";
        try {
            Email email = new HtmlEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setSSLOnConnect(true);
            email.setAuthenticator(new DefaultAuthenticator(senderEmailId, password));
            email.setFrom(senderEmailId);
            email.setSubject("Alert Email");
            ((HtmlEmail) email).setHtmlMsg(emailBody);
            email.addTo(recipient);
            email.send();
           // return "Alert Mail Sent successfully";
        } catch (Exception e) {
            e.printStackTrace();
           // return "failed to send alert";
        }



       /* String jsonFilePath = "C:\\Users\\91628\\Desktop\\Project\\final year cse project\\backend-project-main\\record.json";
        // Map<String, Double> topicPercentages = SearchService.calculateTopicPercentages(jsonFilePath);
        Map<String, Double> topicPercentages = SearchService.calculateVisitedSitePercentages(jsonFilePath);

        // Print the topic percentages
        System.out.println("Topic Percentages:");
        for (Map.Entry<String, Double> entry : topicPercentages.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "%");
        }*/


       // String filePath = "C:\\Users\\91628\\Desktop\\Project\\final year cse project\\backend-project-main\\urlOutput.json"; // Change to the actual file path

        //List<String> x = getDestinationIPsFromFile(filePath);



    }
    public static List<String> getDestinationIPsFromFile(String filePath) throws IOException {
        List<String> destinationIPs = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);
        DestinationIPList destinationIPList = objectMapper.readValue(file, DestinationIPList.class);

        for (DestinationIP destinationIP : destinationIPList.getDestinationIPs()) {
            destinationIPs.add(destinationIP.getIpAddress());
        }
        System.out.println(destinationIPs);
        return destinationIPs;
    }
    public static class DestinationIPList {
        private List<DestinationIP> destinationIPs;

        public List<DestinationIP> getDestinationIPs() {
            return destinationIPs;
        }

        public void setDestinationIPs(List<DestinationIP> destinationIPs) {
            this.destinationIPs = destinationIPs;
        }
    }
    public class DestinationIP {
        private String ipAddress;

        public String getIpAddress() {
            return ipAddress;
        }

        public void setIpAddress(String ipAddress) {
            this.ipAddress = ipAddress;
        }
    }
}
