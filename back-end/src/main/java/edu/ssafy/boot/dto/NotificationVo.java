package edu.ssafy.boot.dto;

public class NotificationVo {
    private int notification_id;
    private String user_id;
    private String target_user_id;
    private String category;
    private String timestamp;
    private boolean is_checked;
    private int target_event_id;
    private ContentVo content;
    private String profile_url;
	private String profile_filter;

    public NotificationVo() {
    }

    public NotificationVo(int notification_id, String user_id, String target_user_id, String category, String timestamp,
            boolean is_checked, int target_event_id) {
        this.notification_id = notification_id;
        this.user_id = user_id;
        this.target_user_id = target_user_id;
        this.category = category;
        this.timestamp = timestamp;
        this.is_checked = is_checked;
        this.target_event_id = target_event_id;
    }

    public NotificationVo(int notification_id, String user_id, String target_user_id, String category, String timestamp,
            boolean is_checked, int target_event_id, String profile_url, String profile_filter) {
        this.notification_id = notification_id;
        this.user_id = user_id;
        this.target_user_id = target_user_id;
        this.category = category;
        this.timestamp = timestamp;
        this.is_checked = is_checked;
        this.target_event_id = target_event_id;
        this.profile_url = profile_url;
        this.profile_filter = profile_filter;
    }

    public int getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(int notification_id) {
        this.notification_id = notification_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTarget_user_id() {
        return target_user_id;
    }

    public void setTarget_user_id(String target_user_id) {
        this.target_user_id = target_user_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isIs_checked() {
        return is_checked;
    }

    public void setIs_checked(boolean is_checked) {
        this.is_checked = is_checked;
    }

    public int getTarget_event_id() {
        return target_event_id;
    }

    public void setTarget_event_id(int target_event_id) {
        this.target_event_id = target_event_id;
    }

    public ContentVo getContent() {
        return content;
    }

    public void setContent(ContentVo content) {
        this.content = content;
    }

    

    public String getProfile_url() {
        return profile_url;
    }

    public void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }

    public String getProfile_filter() {
        return profile_filter;
    }

    public void setProfile_filter(String profile_filter) {
        this.profile_filter = profile_filter;
    }

    @Override
    public String toString() {
        return "NotificationVo [category=" + category + ", content=" + content + ", is_checked=" + is_checked
                + ", notification_id=" + notification_id + ", profile_filter=" + profile_filter + ", profile_url="
                + profile_url + ", target_event_id=" + target_event_id + ", target_user_id=" + target_user_id
                + ", timestamp=" + timestamp + ", user_id=" + user_id + "]";
    }

    
}