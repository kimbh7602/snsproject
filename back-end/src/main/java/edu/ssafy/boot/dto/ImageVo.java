package edu.ssafy.boot.dto;

public class ImageVo {
	private int content_id;
	private String image_name;
	private String image_url;
	private String base64;
	private String filter;
	private String user_id;

	public ImageVo() {
		super();
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public ImageVo(int content_id, String image_name, String image_url, String filter) {
		super();
		this.content_id = content_id;
		this.image_name = image_name;
		this.image_url = image_url;
		this.filter = filter;
	}
	
	public ImageVo(String base64, String filter) {
		super();
		this.base64 = base64;
		this.filter = filter;
	}
	
	public ImageVo(String base64, String filter, String user_id) {
		super();
		this.base64 = base64;
		this.filter = filter;
		this.user_id = user_id;
	}

	public int getContent_id() {
		return content_id;
	}

	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}

	public String getImage_name() {
		return image_name;
	}

	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	@Override
	public String toString() {
		return "ImageVo [content_id=" + content_id + ", image_name=" + image_name + ", image_url=" + image_url + "]";
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

}
