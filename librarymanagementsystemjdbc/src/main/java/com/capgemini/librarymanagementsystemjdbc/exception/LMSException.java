package com.capgemini.librarymanagementsystemjdbc.exception;

@SuppressWarnings("serial")
public class LMSException extends RuntimeException {
	public LMSException(String check) {
		super(check);
	}

}
