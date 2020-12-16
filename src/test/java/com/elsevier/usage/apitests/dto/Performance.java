package com.elsevier.usage.apitests.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Performance {
    @JsonProperty("Period")
    private PerformancePeriod period;
    @JsonProperty("Instance")
    private List<PerformanceInstance> instance;

    public PerformancePeriod getPeriod() {
        return period;
    }

    public void setPeriod(PerformancePeriod period) {
        this.period = period;
    }

    public List<PerformanceInstance> getInstance() {
        return instance;
    }

    public void setInstance(List<PerformanceInstance> instance) {
        this.instance = instance;
    }

    @Override
    public String toString() {
        return "Performance{" +
                "period=" + period +
                ", instance=" + instance +
                '}';
    }
}
