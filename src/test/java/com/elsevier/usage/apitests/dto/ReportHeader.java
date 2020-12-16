package com.elsevier.usage.apitests.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ReportHeader {
     @JsonProperty("Created")
    private String created;
     @JsonProperty("Created_By")
    private String createdBy;
     @JsonProperty("Customer_ID")
    private String customerId;
     @JsonProperty("Report_ID")
    private String reportId;
     @JsonProperty("Release")
    private String release;
     @JsonProperty("Report_Name")
    private String reportName;
     @JsonProperty("Institution_Name")
    private String institutionName;
     @JsonProperty("Report_Filters")
    private List<ReportFilter> reportFilters;

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public List<ReportFilter> getReportFilters() {
        return reportFilters;
    }

    public void setReportFilters(List<ReportFilter> reportFilters) {
        this.reportFilters = reportFilters;
    }

    @Override
    public String toString() {
        return "ReportHeader{" +
                "created='" + created + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", customerId='" + customerId + '\'' +
                ", reportId='" + reportId + '\'' +
                ", release='" + release + '\'' +
                ", reportName='" + reportName + '\'' +
                ", institutionName='" + institutionName + '\'' +
                ", reportFilters=" + reportFilters +
                '}';
    }
}
