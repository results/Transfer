package com.perscholas.spring.exceptions;

import java.io.Serializable;

public class BalanceException extends Exception implements Serializable {
	
	//private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 510465848970912384L;

	public BalanceException(String msg) {
		super(msg);
	}
	
	public BalanceException() {
		super("Balance too low.");
	}

}
