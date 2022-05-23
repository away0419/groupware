package com.it.groupware.breakDay.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BreakDayDTO {
	@ApiModelProperty(value="휴가 번호", example ="0")
	private int breakdayNo;/* 휴가 번호 */

	@ApiModelProperty(value="시작일", example ="2022-05-25T12:00:00")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date breakdayStart;/* 시작일 */

	@ApiModelProperty(value="마지막일", example ="2022-05-27T12:00:00")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date breakdayEnd;/* 마지막일 */
	
	@ApiModelProperty(value="사원 번호", example ="999")
	private int empNo;/* 사원 번호 */
	
	@ApiModelProperty(value="휴가 종류 번호", example ="0")
	private int breakthemeNo;/* 휴가 종류 번호 */
	
	@ApiModelProperty(value="휴가 종류 이름", example ="연가")
	private String breakthemeName;
	
	@ApiModelProperty(value="사원 이름", example ="홍길동")
	private String empName;
	
	@ApiModelProperty(value="휴가 사용 일 수", example ="0")
	private int breakdayUse;
}

