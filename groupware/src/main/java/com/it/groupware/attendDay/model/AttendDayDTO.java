package com.it.groupware.attendDay.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AttendDayDTO {
	@ApiModelProperty(value = "날짜 근태 번호", example = "0")
	private int attendanceDayNo; 
	
	@ApiModelProperty(value = "사원 번호", example = "0")
	private int empNo;
	
	@ApiModelProperty(value = "출근 시간", example = "2022-05-05T12:00:00")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date attendanceDayOnHour; 

	@ApiModelProperty(value = "퇴근 시간", example = "2022-05-05T12:00:00")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date attendanceDayOffHour;
	
	@ApiModelProperty(value = "근무 시간", example = "2022-05-05T12:00:00")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date attendanceDayWorkHour;
	
	@ApiModelProperty(value="휴일 여부", example = "0")
	private String attendanceDayHolidayFlag;/* 휴일 여부 */
	
	@ApiModelProperty(value = "근무 시간", example = "2022-05-05")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date attendanceDayRegdate;
}
