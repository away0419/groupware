package com.it.groupware.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionAdvice {
	@ExceptionHandler({ CustomException.class })
	public ResponseEntity<CustomErrorResponse> exceptionHandler(CustomException e) {

//		return ResponseEntity.status(e.getError().getStatus())
//                .body(CustomErrorResponse.builder()
//                        .errorCode(e.getError).build());
		return new ResponseEntity<>(new CustomErrorResponse(e.getError()), e.getError().getStatus());
	}

//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler({CustomException.class})
//	public CustomErrorResponse exceptionHandler(CustomException e){
//		
//		return new CustomErrorResponse(e.getError());
//	}

	@ExceptionHandler({ IllegalStateException.class, IOException.class })
	public ResponseEntity<String> exceptionHandler(RuntimeException e) {

//		return ResponseEntity.status(e.getError().getStatus())
//                .body(CustomErrorResponse.builder()
//                        .errorCode(e.getError).build());
		return new ResponseEntity<>("에러", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
