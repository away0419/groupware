package com.it.groupware.addBookFol.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddBookFolDTO {
	@ApiModelProperty(value = "카테고리 번호", example = "0")
	private int addressFolderNo;
	@ApiModelProperty(value = "회원 번호", example = "0")
	private int empNo;
	@ApiModelProperty(value = "카테고리 이름", example = "example")
	private String addressFolderName;
}
