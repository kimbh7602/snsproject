package edu.ssafy.boot.dto;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

public class BlockVo {

	private String hash;
	private String prehash;
	private LogVo data;
	private Date Timestamp;

	public BlockVo(LogVo data, Date Timestamp) {
		this.hash = computeHash();
		this.data = data;
		this.Timestamp = Timestamp;
	}
	
	//블록데이터 해쉬함수변환
	public String computeHash() {

		String dataToHash = "" + this.Timestamp + this.prehash + this.data;

		MessageDigest digest;
		String encoded = null;

		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
			encoded = Base64.getEncoder().encodeToString(hash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		this.hash = encoded;
		return encoded;

	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getPrehash() {
		return prehash;
	}

	public void setPrehash(String prehash) {
		this.prehash = prehash;
	}

	public LogVo getData() {
		return data;
	}

	public void setData(LogVo data) {
		this.data = data;
	}

	public Date getTimestamp() {
		return Timestamp;
	}

	public void setTimestamp(Date timestamp) {
		Timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "BlockVo [hash=" + hash + ", prehash=" + prehash + ", data=" + data + ", Timestamp=" + Timestamp + "]";
	}

}
