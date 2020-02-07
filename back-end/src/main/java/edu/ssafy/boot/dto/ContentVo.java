package edu.ssafy.boot.dto;

import java.util.List;

public class ContentVo {
	private int content_id;
	private int share_cid;
	private String content_title;
	private String content_val;
	private String user_id;
	private String timestamp;
	private int content_like;
	private String hashtag;
	private List<String> hashtagList;
	private boolean user_like;
	private int dislike;
	private List<ImageVo> imageList;
	private String location_name;
	private double lat;
	private double lng;
	private String profile_url;
	private String profile_filter;

	public ContentVo() {
		super();
	}

	public int getDislike() {
		return dislike;
	}

	public void setDislike(int dislike) {
		this.dislike = dislike;
	}

	public ContentVo(int content_id, int share_cid, String content_val, String user_id,
			String timestamp, int content_like, List<String> hashtagList, List<ImageVo> imageList, boolean user_like, int dislike) {
		super();
		this.content_id = content_id;
		this.share_cid = share_cid;
		this.content_val = content_val;
		this.user_id = user_id;
		this.timestamp = timestamp;
		this.content_like = content_like;
		this.hashtagList = hashtagList;
		this.imageList = imageList;
		this.user_like = user_like;
		this.dislike = dislike;
	}

	public ContentVo(int content_id, String content_title, String content_val, String user_id, String timestamp,
			String hashtag, int dislike) {
		super();
		this.content_id = content_id;
		this.content_title = content_title;
		this.content_val = content_val;
		this.user_id = user_id;
		this.timestamp = timestamp;
		this.hashtag = hashtag;
		this.dislike = dislike;
	}
	
	public ContentVo(int content_id, String content_val, String user_id, String timestamp,
			List<String> hashtagList, List<ImageVo> imageList, int dislike) {
		super();
		this.content_id = content_id;
		this.content_val = content_val;
		this.user_id = user_id;
		this.timestamp = timestamp;
		this.hashtagList = hashtagList;
		this.imageList = imageList;
		this.dislike = dislike;
	}

	public ContentVo(String content_val, String user_id, List<String> hashtagList, List<ImageVo> imageList, int dislike) {
		super();
		this.content_val = content_val;
		this.user_id = user_id;
		this.hashtagList = hashtagList;
		this.imageList = imageList;
		this.dislike = dislike;
	}

	public ContentVo(String content_val, String user_id, List<String> hashtagList, int dislike, List<ImageVo> imageList, String location_name, double lat, double lng) {
		super();
		this.content_val = content_val;
		this.user_id = user_id;
		this.hashtagList = hashtagList;
		this.dislike = dislike;
		this.imageList = imageList;
		this.location_name = location_name;
		this.lat = lat;
		this.lng = lng;
	}

	public ContentVo(int content_id, int share_cid, String content_val, String user_id, String timestamp,
			int content_like, String hashtag, int dislike) {
		this.content_id = content_id;
		this.share_cid = share_cid;
		this.content_val = content_val;
		this.user_id = user_id;
		this.timestamp = timestamp;
		this.content_like = content_like;
		this.hashtag = hashtag;
		this.dislike = dislike;
	}

	public int getContent_id() {
		return content_id;
	}

	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}

	public int getShare_cid() {
		return share_cid;
	}

	public void setShare_cid(int share_cid) {
		this.share_cid = share_cid;
	}

	public String getContent_title() {
		return content_title;
	}

	public void setContent_title(String content_title) {
		this.content_title = content_title;
	}

	public String getContent_val() {
		return content_val;
	}

	public void setContent_val(String content_val) {
		this.content_val = content_val;
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

	public int getContent_like() {
		return content_like;
	}

	public void setContent_like(int content_like) {
		this.content_like = content_like;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public boolean isUser_like() {
		return user_like;
	}

	public void setUser_like(boolean user_like) {
		this.user_like = user_like;
	}

	public List<ImageVo> getImageList() {
		return imageList;
	}

	public void setImageList(List<ImageVo> imageList) {
		this.imageList = imageList;
	}

	public List<String> getHashtagList() {
		return hashtagList;
	}

	public void setHashtagList(List<String> hashtagList) {
		this.hashtagList = hashtagList;
	}

	@Override
	public String toString() {
		return "ContentVo [content_id=" + content_id + ", share_cid=" + share_cid + ", content_title=" + content_title
				+ ", content_val=" + content_val + ", user_id=" + user_id + ", timestamp=" + timestamp
				+ ", content_like=" + content_like + ", hashtag=" + hashtag + ", user_like=" + user_like + "]";
	}

	public String getLocation_name() {
		return location_name;
	}

	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
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

}
