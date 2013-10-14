package de.devsurf.common.lang.secret;

import java.security.InvalidKeyException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Ciphers {
	public final static String AES_ENCRYPT_ALGORITHM = "AES";
	
	public static Cipher aes256(byte[] key, boolean encrypt) throws InvalidKeyException {
		try {
			SecretKeySpec spec = new SecretKeySpec(key, AES_ENCRYPT_ALGORITHM);
			Cipher cipher = Cipher.getInstance(AES_ENCRYPT_ALGORITHM);
			cipher.init((encrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE), spec);
			return cipher;
		} catch (InvalidKeyException e) {
			throw e;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} 
	}
	
	public static Cipher cipher(String algorithm, Key key, boolean encrypt) {
		try {
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init((encrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE), key);
			return cipher;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} 
	}
}
