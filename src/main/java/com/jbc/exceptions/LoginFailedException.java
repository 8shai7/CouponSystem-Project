package com.jbc.exceptions;
//login fail exception
public class LoginFailedException extends Exception {
	/**
	 * Failed To Login To DataBase
	 */
	private static final long serialVersionUID = 1L;
	public LoginFailedException(String message) {
		super(message);
	}
	//customized exception for failed login
	public LoginFailedException() {
		super("Login Exception: Cannot Find User With The Specific Email/Password.");
	}
}
