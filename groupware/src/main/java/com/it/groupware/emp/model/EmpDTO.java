package com.it.groupware.emp.model;

import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmpDTO {
	@ApiModelProperty(value="사원 번호", example = "999")
	private int empNo; /* 사원 번호 */
	
	@ApiModelProperty(value="사원 이름", example = "홍길동")
	private String empName; /* 사원 이름 */
	
	@ApiModelProperty(value="비밀번호", example = "123123123")
	private String empPwd; /* 비밀번호 */
	
	@ApiModelProperty(value="전화번호", example = "010-0000-0000")
	private String empTel; /* 전화번호 */
	
	@ApiModelProperty(value="이메일", example = "ggg@gmail.com")
	private String empEmail; /* 이메일 */
	
	@ApiModelProperty(value="우편번호", example = "000-000")
	private String empZipcode; /* 우편번호 */
	
	@ApiModelProperty(value="주소", example = "서울특별시 구 동")
	private String empAddress; /* 주소 */
	
	@ApiModelProperty(value="상세주소", example = "건물")
	private String empAddressdetail;  /* 상세주소 */
	
	@ApiModelProperty(value="입사 날짜", example = "2022-05-07T12:00:00")
	@DateTimeFormat(pattern = "yyyy-MM-ddThh:mm:ss")
	private Timestamp empRegdate;  /* 입사일 */
	
	@ApiModelProperty(value="퇴사 날짜", example = "2022-05-07T12:00:00")
	@DateTimeFormat(pattern = "yyyy-MM-ddThh:mm:ss")
	private Timestamp empResdate; /* 퇴사일 */
	
	@ApiModelProperty(value="사원사진", example = "사진이름")
	private String empPhoto; /* 사원사진 */
	
	@ApiModelProperty(value="봉급", example = "5000000")
	private int empSalary; /* 봉급 */
	
	@ApiModelProperty(value="계좌", example = "1111-1111-1111")
	private String empAccount; /* 계좌 */
	
	@ApiModelProperty(value="관리자 레벨", example = "1")
	private int empAdminLev;  /* 관리자 레벨번호 */
	
	@ApiModelProperty(value="생일", example = "2022-05-07T12:00:00")
	@DateTimeFormat(pattern = "yyyy-MM-ddThh:mm:ss")
	private Timestamp empBirth; /* 생일 */
	
	@ApiModelProperty(value="부서 번호", example = "1")
	private int departmentNo; /* 부서번호 */
	
	@ApiModelProperty(value="직급 번호", example = "1")
	private int positionNo; /* 직급 번호 */ 
	
	@ApiModelProperty(value="부서 이름", example = "영업부")
	private String departmentName;
	
	@ApiModelProperty(value="직급 이름", example = "대리")
	private String positionName;
	
}
