package com.it.groupware.common;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CustomErrorResponse {
	private final HttpStatus status;
	private final String code;
	private final String message;
	
	@Builder
	public CustomErrorResponse(CustomErrorCode errorCode) {
		this.status = errorCode.getStatus();
		this.code = errorCode.getCode();
		this.message = errorCode.getMessage();
	}
	
}
