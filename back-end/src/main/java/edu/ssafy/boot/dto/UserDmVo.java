package edu.ssafy.boot.dto;

public class UserDmVo {
	private int dm_id;
	private String user_id;
	private String other_id;
	private String recent_message;
	private UserVo user;

	public UserDmVo() {
		super();
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

	public UserDmVo(String user_id, String other_id) {
		super();
		this.user_id = user_id;
		this.other_id = other_id;
	}

	public UserDmVo(int dm_id, String user_id, String other_id, String recent_message) {
		super();
		this.dm_id = dm_id;
		this.user_id = user_id;
		this.other_id = other_id;
		this.recent_message = recent_message;
	}

	public UserDmVo(int dm_id, String user_id, String other_id, String recent_message, UserVo user) {
		super();
		this.dm_id = dm_id;
		this.user_id = user_id;
		this.other_id = other_id;
		this.recent_message = recent_message;
		this.user = user;
	}

	public int getDm_id() {
		return dm_id;
	}

	public void setDm_id(int dm_id) {
		this.dm_id = dm_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getOther_id() {
		return other_id;
	}

	public void setOther_id(String other_id) {
		this.other_id = other_id;
	}

	public String getRecent_message() {
		return recent_message;
	}

	public void setRecent_message(String recent_message) {
		this.recent_message = recent_message;
	}

	@Override
	public String toString() {
		return "UserDmVo [dm_id=" + dm_id + ", user_id=" + user_id + ", other_id=" + other_id + ", recent_message="
				+ recent_message + "]";
	}

}
