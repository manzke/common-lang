package de.devsurf.common.lang.secret.vault.exceptions;

public class VaultException extends Exception {

	private static final long serialVersionUID = -3713618734855053415L;

	public VaultException() {
		super();
	}

	public VaultException(String message, Throwable cause) {
		super(message, cause);
	}

	public VaultException(String message) {
		super(message);
	}

	public VaultException(Throwable cause) {
		super(cause);
	}

}
