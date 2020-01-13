package edu.ssafy.boot.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.stereotype.Service;

@Service("SecurityService")
public class SecurityService implements ISecurityService {

	public String computePw(String pw) {

		String pwTohash = "" + pw;

		MessageDigest digest;
		String securitypw = null;

		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(pwTohash.getBytes(StandardCharsets.UTF_8));
			securitypw = Base64.getEncoder().encodeToString(hash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return securitypw;

	}
}
