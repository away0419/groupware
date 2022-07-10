package com.it.groupware.common;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
	private CustomErrorCode error;
	
	public CustomException(CustomErrorCode e,String message) {
		super(message); //spring console에 보이는 에러 메세지
		this.error=e;
	}

}
