package de.devsurf.common.lang.secret;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigests {
	private static final String DIGEST_MD5 = "MD5";

	public static MessageDigest md5() {
		try {
			return MessageDigest.getInstance(DIGEST_MD5);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
