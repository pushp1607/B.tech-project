package com.application.dto;

public class PingResponseDTO {

    public static class RTTDataDTO {
        private double min;
        private double avg;
        private double max;
        private double mdev;

        // getters and setters

        public double getMin() {
            return min;
        }

        public void setMin(double min) {
            this.min = min;
        }

        public double getAvg() {
            return avg;
        }

        public void setAvg(double avg) {
            this.avg = avg;
        }

        public double getMax() {
            return max;
        }

        public void setMax(double max) {
            this.max = max;
        }

        public double getMdev() {
            return mdev;
        }

        public void setMdev(double mdev) {
            this.mdev = mdev;
        }
    }
    private String timestamp;
    private String pingDestination;
    private String destinationIp;
    private int packetsTransmitted;
    private int packetsReceived;
    private String packetLoss;
    private String totalTime;
    private RTTDataDTO rtt;

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

    public String getDestinationIp() {
        return destinationIp;
    }

    public void setDestinationIp(String destinationIp) {
        this.destinationIp = destinationIp;
    }

    public int getPacketsTransmitted() {
        return packetsTransmitted;
    }

    public void setPacketsTransmitted(int packetsTransmitted) {
        this.packetsTransmitted = packetsTransmitted;
    }

    public int getPacketsReceived() {
        return packetsReceived;
    }

    public void setPacketsReceived(int packetsReceived) {
        this.packetsReceived = packetsReceived;
    }

    public String getPacketLoss() {
        return packetLoss;
    }

    public void setPacketLoss(String packetLoss) {
        this.packetLoss = packetLoss;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public RTTDataDTO getRtt() {
        return rtt;
    }

    public void setRtt(RTTDataDTO rtt) {
        this.rtt = rtt;
    }
// getters and setters
}

