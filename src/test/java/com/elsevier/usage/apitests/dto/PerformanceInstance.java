package com.elsevier.usage.apitests.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PerformanceInstance {
    @JsonProperty("Metric_Type")
    private String metricType;
    @JsonProperty("Count")
    private String count;

    public String getMetricType() {
        return metricType;
    }

    public void setMetricType(String metricType) {
        this.metricType = metricType;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "PerformanceInstance{" +
                "metricType='" + metricType + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
