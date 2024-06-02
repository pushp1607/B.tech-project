package com.application.dto;

public class JsonEntryDTO {
    private String date;
    private String srcip;
    private String hostname;
    private String dstport;
    private String action;
    private String srcport;
    private String time;
    private String sessionid;
    private String user;

    // Constructors, getters, and setters

    public JsonEntryDTO() {
        // Default constructor
    }

    public JsonEntryDTO(String date, String srcip, String hostname, String dstport,
                        String action, String srcport, String time, String sessionid, String user) {
        this.date = date;
        this.srcip = srcip;
        this.hostname = hostname;
        this.dstport = dstport;
        this.action = action;
        this.srcport = srcport;
        this.time = time;
        this.sessionid = sessionid;
        this.user = user;
    }

    // Getters and setters for each field

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSrcip() {
        return srcip;
    }

    public void setSrcip(String srcip) {
        this.srcip = srcip;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getDstport() {
        return dstport;
    }

    public void setDstport(String dstport) {
        this.dstport = dstport;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSrcport() {
        return srcport;
    }

    public void setSrcport(String srcport) {
        this.srcport = srcport;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
