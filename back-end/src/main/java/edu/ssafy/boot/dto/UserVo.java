package edu.ssafy.boot.dto;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class UserVo {
	private String user_id;
	private String password;
	private String tel;
	private String email;
	private String interest;

	public UserVo() {
		super();
	}

	public UserVo(String user_id, String password, String tel, String email, String interest) {
		super();
		this.user_id = user_id;
		this.password = password;
		this.tel = tel;
		this.email = email;
		this.interest = interest;
	}

	public UserVo(String user_id, String password) {
		super();
		this.user_id = user_id;
		this.password = password;
	}

	

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	@Override
	public String toString() {
		return "UserVo [user_id=" + user_id + ", password=" + password + ", tel=" + tel + ", email=" + email
				+ ", interest=" + interest + "]";
	}

}
