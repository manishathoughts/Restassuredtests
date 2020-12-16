package com.elsevier.usage.apitests.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DRD1Report {

    @JsonProperty("Report_Header")
    private ReportHeader reportHeader;

    @JsonProperty("Report_Items")
    private List<ReportItem> reportItem;

    public ReportHeader getReportHeader() {
        return reportHeader;
    }

    public void setReportHeader(ReportHeader reportHeader) {
        this.reportHeader = reportHeader;
    }

    public List<ReportItem> getReportItem() {
        return reportItem;
    }

    public void setReportItems(List<ReportItem> reportItem) {
        this.reportItem = reportItem;
    }

    @Override
    public String toString() {
        return "DRD1Report{" +
                "reportHeader=" + reportHeader +
                ", reportItem=" + reportItem +
                '}';
    }
}
