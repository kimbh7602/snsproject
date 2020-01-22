package edu.ssafy.boot.dto;

public class NoticeDBVo {
    private String calendarId;
    private String category;
    private String id;
    private String title;
    private String location;
    private boolean isAllDay;
    private String start;
    private String end;
    private String start_date;
    private String end_date;

    public NoticeDBVo() {
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public NoticeDBVo(String calendarId, String category, String id, String title, String location, boolean isAllDay,
            String start, String end, String start_date, String end_date) {
        this.calendarId = calendarId;
        this.category = category;
        this.id = id;
        this.setTitle(title);
        this.location = location;
        this.isAllDay = isAllDay;
        this.start = start;
        this.end = end;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public String getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isAllDay() {
        return isAllDay;
    }

    public void setAllDay(boolean isAllDay) {
        this.isAllDay = isAllDay;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "NoticeVo [calendarId=" + calendarId + ", category=" + category + ", end=" + end + ", id=" + id
                + ", isAllDay=" + isAllDay + ", location=" + location + ", start=" + start + "]";
    }

    
}