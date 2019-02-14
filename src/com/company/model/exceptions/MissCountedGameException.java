package com.company.model.exceptions;

public class MissCountedGameException extends RuntimeException {
	public MissCountedGameException() {
		super();
	}

	public MissCountedGameException(String msg) {
		super(msg);
	}
}