package edu.ssafy.boot.dto;

import java.util.List;

public class UserVo {
	private String user_id;
	private String password;
	private String tel;
	private String email;
	private String interest;
	private String dislike;
	private List<String> interestList;
	private List<String> dislikeList;
	private String description;
	private String timestamp;
	private boolean status;
	private String profile_url;
	private String profile_filter;
	private ImageVo profileImage;

	public UserVo() {
		super();
	}

	public UserVo(String user_id, String password, String tel, String email, List<String> interestList,
			List<String> dislikeList, String description) {
		this.user_id = user_id;
		this.password = password;
		this.tel = tel;
		this.email = email;
		this.setInterestList(interestList);
		this.setDislikeList(dislikeList);
		this.description = description;
	}

	public UserVo(String user_id, String password, String tel, String email, List<String> interestList,
			List<String> dislikeList, String description, ImageVo profileImage) {
		this.user_id = user_id;
		this.password = password;
		this.tel = tel;
		this.email = email;
		this.setInterestList(interestList);
		this.setDislikeList(dislikeList);
		this.description = description;
		this.profileImage = profileImage;
	}

	public UserVo(String user_id, String password, String tel, String email, String interest, String dislike, String description, boolean status, String profile_url, String profile_filter) {
		super();
		this.user_id = user_id;
		this.password = password;
		this.tel = tel;
		this.email = email;
		this.interest = interest;
		this.setDislike(dislike);
		this.description = description;
		this.status = status;
		this.profile_url = profile_url;
		this.profile_filter = profile_filter;
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
	
	public String getDislike() {
		return dislike;
	}

	public void setDislike(String dislike) {
		this.dislike = dislike;
	}

	public List<String> getDislikeList() {
		return dislikeList;
	}

	public void setDislikeList(List<String> dislikeList) {
		this.dislikeList = dislikeList;
	}

	public List<String> getInterestList() {
		return interestList;
	}

	public void setInterestList(List<String> interestList) {
		this.interestList = interestList;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserVo [user_id=" + user_id + ", password=" + password + ", tel=" + tel + ", email=" + email
				+ ", interest=" + interest + ", dislike=" + dislike + ", interestList=" + interestList
				+ ", dislikeList=" + dislikeList + ", description=" + description + ", timestamp=" + timestamp
				+ ", status=" + status + "]";
	}

	public String getProfile_url() {
		return profile_url;
	}

	public void setProfile_url(String profile_url) {
		this.profile_url = profile_url;
	}

	public ImageVo getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(ImageVo profileImage) {
		this.profileImage = profileImage;
	}

	public String getProfile_filter() {
		return profile_filter;
	}

	public void setProfile_filter(String profile_filter) {
		this.profile_filter = profile_filter;
	}
	
}
