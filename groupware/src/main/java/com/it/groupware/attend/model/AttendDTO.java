package com.it.groupware.attend.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AttendDTO {
	@ApiModelProperty(value = "근태 번호", example = "0")
	private int attendanceNo;
	@ApiModelProperty(value = "사원 번호", example = "0")
	private int empNo;
	@ApiModelProperty(value = "연차 일 수", example = "0")
	private int attendanceAnnualCount; /* 연차 갯수 */
	@ApiModelProperty(value = "연차 사용일 수", example = "0")
	private int attendanceAnnualUse; /* 연차 사용일 */
	@ApiModelProperty(value = "지각 일 수", example = "0")
	private int attendanceLateCount; /* 지각 일수 */
	@ApiModelProperty(value = "보상 휴가 일 수", example = "0")
	private int attendanceRewardCount; /* 보상 휴가 일수 */
	@ApiModelProperty(value = "보상 휴가 사용일 수", example = "0")
	private int attendanceRewardUse; 
}
