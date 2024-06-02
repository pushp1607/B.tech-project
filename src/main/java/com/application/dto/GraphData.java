    package com.application.dto;

    import java.util.Arrays;
    import java.util.List;

    public class GraphData {
        private List<String> xValues;
        private List<Double> yValues;

        public List<String> getxValues() {
            return xValues;
        }

        public void setxValues(List<String> xValues) {
            this.xValues = xValues;
        }

        public List<Double> getyValues() {
            return yValues;
        }

        public void setyValues(List<Double> yValues) {
            this.yValues = yValues;
        }
    }