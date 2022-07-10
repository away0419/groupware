package com.it.groupware.electronicAppStamp.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ElectronicAppStampDTO {
	@ApiModelProperty(value="사인 번호", example = "999")
	private int stampNo;
	@ApiModelProperty(value="사인 번호", example = "999")
	private int empNo;
	@ApiModelProperty(value="사인 이름", example = "이름")
	private String stampName;
}
