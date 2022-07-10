package com.it.groupware.electronic.model;

import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ElectronicRecentlyDTO {
	@ApiModelProperty(value="최근 사용 양식 번호", example = "1")
	private int recentlyNo; /* 최근사용양식번호 */
	@ApiModelProperty(value="양식 이름", example = "휴가신청서")
	private String styleName; /* 양식이름 */
	@ApiModelProperty(value="사원 번호", example = "999")
	private int empNo; /* 사원 번호 */
	@ApiModelProperty(value="양식 번호", example = "100")
	private int styleNo; /* 양식번호 */
	@ApiModelProperty(value="최근 사용 날짜", example = "2022-05-07T12:00:00")
	@DateTimeFormat(pattern = "yyyy-MM-ddThh:mm:ss")
	private Timestamp useDate; /* 최근 사용 날짜*/
}
