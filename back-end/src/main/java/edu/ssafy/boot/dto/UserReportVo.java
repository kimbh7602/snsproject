package edu.ssafy.boot.dto;

public class UserReportVo {
    private int content_id;
    private String user_id;
    private String report_category;
    private String report_val;
    private String timestamp;

    public UserReportVo() {
    }

    public String getReport_category() {
        return report_category;
    }

    public void setReport_category(String report_category) {
        this.report_category = report_category;
    }

    public UserReportVo(int content_id, String user_id, String report_category, String report_val, String timestamp) {
        this.content_id = content_id;
        this.user_id = user_id;
        this.report_category = report_category;
        this.report_val = report_val;
        this.timestamp = timestamp;
    }

    public int getContent_id() {
        return content_id;
    }

    public void setContent_id(int content_id) {
        this.content_id = content_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getReport_val() {
        return report_val;
    }

    public void setReport_val(String report_val) {
        this.report_val = report_val;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "UserReportVo [content_id=" + content_id + ", report_val=" + report_val + ", timestamp=" + timestamp
                + ", user_id=" + user_id + "]";
    }
}