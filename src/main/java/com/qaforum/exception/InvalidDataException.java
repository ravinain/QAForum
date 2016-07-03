package com.qaforum.exception;

import java.util.List;

/**
 * 
 * @author cdacr
 *
 */
public class InvalidDataException extends Exception {

	private static final long serialVersionUID = 8005590240862791952L;

	/**
	 * 
	 */
	public InvalidDataException() {
		super();
	}

	/**
	 * 
	 * @param errorMessage 
	 */
	public InvalidDataException(final List<String> errorMessage) {
		super(errorMessage.toString());
	}

}
