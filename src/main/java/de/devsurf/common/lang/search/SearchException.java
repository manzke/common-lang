package de.devsurf.common.lang.search;

public class SearchException extends Exception {

	private static final long serialVersionUID = 8920350044547358611L;

	public SearchException(String message) {
		super(message);
	}

	public SearchException(String message, Throwable cause) {
		super(message, cause);
	}
}
