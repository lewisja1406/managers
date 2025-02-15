/*
 * Copyright contributors to the Galasa project
 */
package dev.galasa.zossecurity;

public class ProfileNotFoundException extends ZosSecurityManagerException {
	private static final long serialVersionUID = 1L;

	public ProfileNotFoundException() {
	}

	public ProfileNotFoundException(String message) {
		super(message);
	}

	public ProfileNotFoundException(Throwable cause) {
		super(cause);
	}

	public ProfileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
