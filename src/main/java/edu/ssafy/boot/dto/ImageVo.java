package edu.ssafy.boot.dto;

public class ImageVo {
	private int content_id;
	private String image_name;
	private String image_url;

	public ImageVo() {
		super();
	}

	public ImageVo(int content_id, String image_name, String image_url) {
		super();
		this.content_id = content_id;
		this.image_name = image_name;
		this.image_url = image_url;
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

}
