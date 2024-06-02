package com.application.dto;

public class PingDataDTO {
    private String timestamp;
    private String pingDestination;
    private double avgRtt;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPingDestination() {
        return pingDestination;
    }

    public void setPingDestination(String pingDestination) {
        this.pingDestination = pingDestination;
    }

    public double getAvgRtt() {
        return avgRtt;
    }

    public void setAvgRtt(double avgRtt) {
        this.avgRtt = avgRtt;
    }
// Getters and setters
}
