package edu.ssafy.boot.dto;

public class LogVo {
	private String user_id;
	private String user_ip;
	private String user_status;
	
	public LogVo() {
		super();
	}
	
	public LogVo(String user_id, String user_ip, String user_status) {
		super();
		this.user_id = user_id;
		this.user_ip = user_ip;
		this.user_status = user_status;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_ip() {
		return user_ip;
	}

	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}

	public String getUser_status() {
		return user_status;
	}

	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}
	
	@Override
	public String toString() {
		return "LogVo [user_id=" + user_id + ", user_ip=" + user_ip + ", user_status=" + user_status + "]";
	}
	
}
