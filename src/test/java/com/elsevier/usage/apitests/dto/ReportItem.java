package com.elsevier.usage.apitests.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ReportItem {
    @JsonProperty("Platform")
    private String platform;
    @JsonProperty("Database")
    private String database;
    @JsonProperty("Publisher")
    private String publsiher;
    @JsonProperty("Performance")
    private List<Performance> performances;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getPublsiher() {
        return publsiher;
    }

    public void setPublsiher(String publsiher) {
        this.publsiher = publsiher;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }

    @Override
    public String toString() {
        return "ReportItem{" +
                "platform='" + platform + '\'' +
                ", database='" + database + '\'' +
                ", publsiher='" + publsiher + '\'' +
                ", performances=" + performances +
                '}';
    }
}
