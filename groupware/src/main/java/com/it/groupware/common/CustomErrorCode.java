package com.it.groupware.common;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum CustomErrorCode {
	UserNotFound(HttpStatus.NO_CONTENT,"U0001","아이디 및 비밀번호와 맞는 유저를 찾지 못했습니다."),
	
	ELECTRINIC_NOT_FIND(HttpStatus.NO_CONTENT,"E0100","해당 문서 찾지 못함."),
	ELECTRINIC_INSERT_FAIL(HttpStatus.BAD_REQUEST,"E0101","결재 등록 실패"),
	ELECTRINIC_UPDATE_FAIL(HttpStatus.BAD_REQUEST,"E0102","결재 수정 실패"),
	ELECTRINIC_EMPNO_NOT_MATCH(HttpStatus.UNAUTHORIZED,"E0120","결재 정보의 사용자 번호 불일치"),
	ELECTRINIC_IS_NOT_DRATF(HttpStatus.UNAUTHORIZED,"E0121","결재 정보가 임시 파일 아님"),
	ELECTRINIC_STATUS_DISCORD(HttpStatus.UNAUTHORIZED,"E0122","결재 정보의 상태가 불러오려는 상태와 불일치"),
	
	ELECTRONIC_FILE_INESERT_FAIL(HttpStatus.BAD_REQUEST,"E0201","결재 파일 등록 실패"),
	BREAKTHEME_LIST_NULL(HttpStatus.NO_CONTENT,"B0210","휴가 종류 데이터가 없습니다."),
	
	ELECTRONIC_APPLINE_NOT_FIND(HttpStatus.NO_CONTENT,"E0300","결재 라인 검색 결과 없음"),
	ELECTRONIC_APPLINE_INESERT_FAIL(HttpStatus.BAD_REQUEST,"E0301","결재자 등록 실패"),
	ELECTRONIC_APPLINE_UPDATE_FAIL(HttpStatus.BAD_REQUEST,"E0302","결재자 수정 실패"),
	
	ELECTRONIC_RELINE_INESERT_FAIL(HttpStatus.BAD_REQUEST,"E0401","수신자 등록 실패"),
	
	ELECTRONIC_RECENTLY_INESERT_FAIL(HttpStatus.BAD_REQUEST,"E0501","최근 양식 등록 실패"),
	
	ELECTRONIC_STYLE_NOT_FIND(HttpStatus.NO_CONTENT,"E0600","양식 찾지 못함"),
	
	ELECTRONIC_FOLDER_NOT_FIND(HttpStatus.NO_CONTENT,"E0700","양식 폴더 찾지 못함"),
	
	ELECTRONIC_STAMP_NOT_FIND(HttpStatus.NO_CONTENT,"E0800","사인 검색 결과 없음"),
	ELECTRONIC_STAMP_INSERT_FAIL(HttpStatus.BAD_REQUEST,"E0801","사인 등록 실패"),
	
	ELECTRONIC_FILE_NOT_FIND(HttpStatus.NO_CONTENT,"E0900","해당 파일 없음");
	
	
	private final HttpStatus status;
	private final String code;
	private final String message;
//	CustomErrorCode(HttpStatus status, String code,String message){
//		this.status=status;
//		this.code =code;
//		this.message=message;
//	}
	
}
