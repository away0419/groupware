package com.it.groupware.electronicDocFol.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ElectronicDocFolDTO {
	@ApiModelProperty(value="폴더 번호", example="1")
	private int folderNo;
	
	@ApiModelProperty(value="폴더 이름", example="휴가")
	private String folderName;
}
