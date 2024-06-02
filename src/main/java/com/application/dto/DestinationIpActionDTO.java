package com.application.dto;

public class DestinationIpActionDTO {
    private String destinationIp;
    private String action;
    private String city;
    private String latitude;
    private String longitude;

    public DestinationIpActionDTO(String dstip, String action) {
        this.destinationIp = dstip;
        this.action = action;
    }

    // Constructor, getters, and setters

    public String getDestinationIp() {
        return destinationIp;
    }

    public void setDestinationIp(String destinationIp) {
        this.destinationIp = destinationIp;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


}
