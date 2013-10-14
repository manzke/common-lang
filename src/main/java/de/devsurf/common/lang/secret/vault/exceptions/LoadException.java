package de.devsurf.common.lang.secret.vault.exceptions;


public class LoadException extends VaultException {

	private static final long serialVersionUID = 7985693186060872428L;

	public LoadException() {
		super();
	}

	public LoadException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoadException(String message) {
		super(message);
	}

	public LoadException(Throwable cause) {
		super(cause);
	}

}
