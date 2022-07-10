package com.it.groupware.breakTheme.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BreakThemeDTO {
	@ApiModelProperty(value="휴가 종류 번호")
	private int breakthemeNo; /* 휴가 종류 번호 */
	@ApiModelProperty(value="휴가 종류 이름")
	private String breakthemeName; /* 휴가 종류 이름 */
}
