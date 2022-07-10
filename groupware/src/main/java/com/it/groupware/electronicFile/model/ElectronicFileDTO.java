package com.it.groupware.electronicFile.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ElectronicFileDTO {
	@ApiModelProperty(value = "파일 번호")
	private int fileNo;
	@ApiModelProperty(value = "파일 저장 이름")
	private String fileName;
	@ApiModelProperty(value = "파일 원본 이름")
	private String fileOriginalname;
	@ApiModelProperty(value = "파일 크기")
	private long fileSize;
	@ApiModelProperty(value = "문서 번호")
	private int electronicNo;
}
