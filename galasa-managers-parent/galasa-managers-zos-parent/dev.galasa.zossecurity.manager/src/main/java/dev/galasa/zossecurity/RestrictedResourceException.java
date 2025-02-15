/*
 * Copyright contributors to the Galasa project
 */
package dev.galasa.zossecurity;

public class RestrictedResourceException extends ZosSecurityManagerException {
	private static final long serialVersionUID = 1L;

	public RestrictedResourceException() {
	}

	public RestrictedResourceException(String message) {
		super(message);
	}

	public RestrictedResourceException(Throwable cause) {
		super(cause);
	}

	public RestrictedResourceException(String message, Throwable cause) {
		super(message, cause);
	}

}
