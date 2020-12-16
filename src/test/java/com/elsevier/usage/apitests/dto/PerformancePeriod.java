package com.elsevier.usage.apitests.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PerformancePeriod {
    @JsonProperty("Begin_Date")
    private String beginDate;
    @JsonProperty("End_Date")
    private String endDate;

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "PerformancePeriod{" +
                "beginDate='" + beginDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
