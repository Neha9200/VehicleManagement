package com.perfios.vehicleManagement.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ErrorDetails {

	private String message;
	private String details;
	private Date timestampDate;
	private HttpStatus status;
	
	public ErrorDetails( Date timestampDate,HttpStatus status, String message, String details) {
		this.timestampDate = timestampDate;
		this.status = status;
		this.message = message;
		this.details = details;
		
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Date getTimestampDate() {
		return timestampDate;
	}
	public void setTimestampDate(Date timestampDate) {
		this.timestampDate = timestampDate;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	
}