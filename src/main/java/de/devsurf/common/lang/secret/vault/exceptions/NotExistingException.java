package de.devsurf.common.lang.secret.vault.exceptions;


public class NotExistingException extends VaultException {

	private static final long serialVersionUID = 3160439247856402985L;

	public NotExistingException() {
		super();
	}

	public NotExistingException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotExistingException(String message) {
		super(message);
	}

	public NotExistingException(Throwable cause) {
		super(cause);
	}

}
