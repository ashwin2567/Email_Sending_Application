package com.email.model;

public class EmailResponse {
	String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public EmailResponse(String status) {
		super();
		this.status = status;
	}
}
