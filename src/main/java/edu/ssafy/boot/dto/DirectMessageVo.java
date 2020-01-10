package edu.ssafy.boot.dto;

public class DirectMessageVo {
	private int dm_id;
	private String send_id;
	private String receive_id;
	private String message;
	private String timestamp;
	private boolean read_check;

	public DirectMessageVo() {
		super();
	}

	public DirectMessageVo(int dm_id, String send_id, String receive_id, String message, String timestamp,
			boolean read_check) {
		super();
		this.dm_id = dm_id;
		this.send_id = send_id;
		this.receive_id = receive_id;
		this.message = message;
		this.timestamp = timestamp;
		this.read_check = read_check;
	}

	public DirectMessageVo(int dm_id, String send_id, String receive_id, String message, String timestamp) {
		super();
		this.dm_id = dm_id;
		this.send_id = send_id;
		this.receive_id = receive_id;
		this.message = message;
		this.timestamp = timestamp;
	}

	public int getDm_id() {
		return dm_id;
	}

	public void setDm_id(int dm_id) {
		this.dm_id = dm_id;
	}

	public String getSend_id() {
		return send_id;
	}

	public void setSend_id(String send_id) {
		this.send_id = send_id;
	}

	public String getReceive_id() {
		return receive_id;
	}

	public void setReceive_id(String receive_id) {
		this.receive_id = receive_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isRead_check() {
		return read_check;
	}

	public void setRead_check(boolean read_check) {
		this.read_check = read_check;
	}

	@Override
	public String toString() {
		return "DirectMessageVo [dm_id=" + dm_id + ", send_id=" + send_id + ", receive_id=" + receive_id + ", message="
				+ message + ", timestamp=" + timestamp + ", read_check=" + read_check + "]";
	}

}
