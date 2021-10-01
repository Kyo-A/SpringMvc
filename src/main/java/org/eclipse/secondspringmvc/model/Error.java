package org.eclipse.secondspringmvc.model;

public class Error {

	private int errorCode;
	private String errorMessage;

	public Error() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Error(int errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
