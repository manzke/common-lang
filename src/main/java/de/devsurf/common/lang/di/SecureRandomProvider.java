package de.devsurf.common.lang.di;

import java.security.NoSuchAlgorithmException;

import javax.inject.Provider;
import javax.xml.bind.DatatypeConverter;

import de.devsurf.common.lang.formatter.ExceptionMessage;

public class SecureRandomProvider implements Provider<String> {
	private final static java.security.SecureRandom INSTANCE;

	static {
		try {
			INSTANCE = java.security.SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(ExceptionMessage
					.format("Algorithm doesn't exists.")
					.addParameter("algo", "SHA1PRNG").build());
		}
	}

	@Override
	public String get() {
		byte bytes[] = new byte[20];
		INSTANCE.nextBytes(bytes);
		//TODO should we switch to google guava
		return DatatypeConverter.printBase64Binary(bytes);
	}
}
