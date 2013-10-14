package de.devsurf.common.lang.secret.vault.exceptions;


public class StoreException extends VaultException {

	private static final long serialVersionUID = -950427013448600134L;

	public StoreException() {
		super();
	}

	public StoreException(String message, Throwable cause) {
		super(message, cause);
	}

	public StoreException(String message) {
		super(message);
	}

	public StoreException(Throwable cause) {
		super(cause);
	}

}
