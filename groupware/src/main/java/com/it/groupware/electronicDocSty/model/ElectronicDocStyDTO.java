package com.it.groupware.electronicDocSty.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ElectronicDocStyDTO {
	@ApiModelProperty(value="양식 번호", example="100")
	private int styleNo;
	@ApiModelProperty(value="양식 이름", example="휴가신청서")
	private String styleName;
	@ApiModelProperty(value="양식 설명", example="휴가 신청서입니다.")
	private String styleContent;
	@ApiModelProperty(value="폴더 번호", example="1")
	private int folderNo;
	@ApiModelProperty(value="폴더 번호", example="1")
	private int folderStyleNo;
	
}
