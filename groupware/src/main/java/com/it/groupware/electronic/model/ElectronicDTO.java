package com.it.groupware.electronic.model;

import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ElectronicDTO {
	
	@ApiModelProperty(value="문서 번호", example = "1")
	private int electronicNo;
	@ApiModelProperty(value="문서 생성 날짜", example = "2022-05-07T12:00:00")
	@DateTimeFormat(pattern = "yyyy-MM-ddThh:mm:ss")
	private Timestamp electronicDate;
	@ApiModelProperty(value="문서 제목", example = "제목")
	private String electronicTitle;
	@ApiModelProperty(value="문서 내용", example = "{객체}")
	private String electronicContent;
	@ApiModelProperty(value="첨부 파일 여부", example = "0")
	private int electronicFileFlag;
	@ApiModelProperty(value="문서 진행 상태", example = "0")
	private int electronicCompletFlag;
	@ApiModelProperty(value="긴급 여부", example = "0")
	private int electronicEmergencyFlag;
	@ApiModelProperty(value="기안 사원 번호", example = "102")
	private int empNo;
	@ApiModelProperty(value="양식 번호", example = "100")
	private int styleNo;
	@ApiModelProperty(value="임시 저장 여부", example = "0")
	private int electronicDraft;
	@ApiModelProperty(value="사원 이름", example = "이름")
	private String empName;
	@ApiModelProperty(value="양식 이름", example = "양식 이름")
	private String styleName;

}
