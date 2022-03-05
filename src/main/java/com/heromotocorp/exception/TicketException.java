package com.heromotocorp.exception;

public class TicketException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TicketException(String message) {
		super(message);
	}

	public TicketException(Throwable throwable) {
		super(throwable);
	}

	public TicketException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
