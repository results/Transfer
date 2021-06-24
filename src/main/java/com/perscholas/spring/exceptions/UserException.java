package com.perscholas.spring.exceptions;

import java.io.Serializable;

public class UserException extends Exception implements Serializable {
	
	//private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 510465848970912384L;

	public UserException(String msg) {
		super(msg);
	}
	
	public UserException() {
		super("Exception occured due to user being null.");
	}

}
