package com.it.groupware.boardFile.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardFileDTO {
	@ApiModelProperty(value = "게시글 파일 번호", example = "0")
	private int boardFileNo;
	@ApiModelProperty(value = "게시글 번호", example = "0")
	private int boardNo;
	@ApiModelProperty(value = "파일 이름", example = "파일 이름")
	private String fileName;
	@ApiModelProperty(value = "오리지널 파일 이름", example = "오리지널 파일 이름")
	private String originalFileName;
	@ApiModelProperty(value = "파일 크기", example = "파일 크기")
	private long fileSize;
}
