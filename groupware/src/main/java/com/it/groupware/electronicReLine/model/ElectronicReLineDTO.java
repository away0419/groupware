package com.it.groupware.electronicReLine.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ElectronicReLineDTO {
	@ApiModelProperty(value="수신 라인 번호")
	private int receiveLineNo;
	@ApiModelProperty(value="문서 번호")
	private int electronicNo;
	@ApiModelProperty(value="사원 번호")
	private int empNo;
	@ApiModelProperty(value="수신 확인 여부")
	private int receiveLineFlag;
}
