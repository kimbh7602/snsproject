package edu.ssafy.boot.dto;

public class NoticeVo {
    private String calendarId;
    private String category;
    private String id;
    private String title;
    private String location;
    private boolean isAllDay;
    private Start start;
    private End end;

    public NoticeVo() {
    }

    public NoticeVo(String calendarId, String category, String id, String title, String location, boolean isAllDay,
            Start start, End end) {
        this.calendarId = calendarId;
        this.category = category;
        this.id = id;
        this.title = title;
        this.location = location;
        this.isAllDay = isAllDay;
        this.start = start;
        this.end = end;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Start getStart() {
        return start;
    }

    public void setStart(Start start) {
        this.start = start;
    }

    public End getEnd() {
        return end;
    }

    public void setEnd(End end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "NoticeVo [calendarId=" + calendarId + ", category=" + category + ", end=" + end + ", id=" + id
                + ", isAllDay=" + isAllDay + ", location=" + location + ", start=" + start + ", title=" + title + "]";
    }

}