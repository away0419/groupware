package com.it.groupware.addBook.model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddBookDTO {
	@ApiModelProperty(value = "주소록 정보 번호", required = true, example="0")
	private int addressBookNo;

	@ApiModelProperty(value = "로그인 회원 번호", required = true, example = "999")
	private int empNo;
	
	@ApiModelProperty(value = "이름", example = "홍길동")
	private String addressBookName;
	
	@ApiModelProperty(value = "번호", example = "01000000000")
	private String addressBookTel;
	
	@ApiModelProperty(value = "메일", example = "test@test.com")
	private String addressBookMaile;
	
	@ApiModelProperty(value = "직급", example = "사장")
	private String addressBookPosition;
	
	@ApiModelProperty(value = "회사 이름", example = "GroupWare")
	private String addressBookOfficeName;
	
	@ApiModelProperty(value = "회사 번호", example = "020000000")
	private String addressBookOfficeTel;
	
	@ApiModelProperty(value = "성별", example = "남")
	private String addressBookGender;
	
	@ApiModelProperty(value = "주소록 폴더 번호", required = true, example = "0")
	private int addressFolderNo;
}
