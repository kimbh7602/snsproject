package edu.ssafy.boot.dto;

public class UserLikeVo {
	private int content_id;
	private String user_id;
	private String timestamp;

	public UserLikeVo() {
		super();
	}

	public UserLikeVo(int content_id, String user_id, String timestamp) {
		super();
		this.content_id = content_id;
		this.user_id = user_id;
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

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "UserLikeVo [content_id=" + content_id + ", user_id=" + user_id + ", timestamp=" + timestamp + "]";
	}

}
