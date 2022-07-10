package com.it.groupware.electronicAppLine.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ElectronicAppLineDTO {

	@ApiModelProperty(value="결재 라인 번호")
	private int approvalLineNo;
	@ApiModelProperty(value="문서 번호")
	private int electronicNo;
	@ApiModelProperty(value="사원 번호")
	private int empNo;
	@ApiModelProperty(value="결재 진행 여부")
	private int approvalLineCompleteFlag;
	@ApiModelProperty(value="결재 순서")
	private int approvalLineOrder;
}
