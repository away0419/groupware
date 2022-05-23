package com.it.groupware.boardFile.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardFileDTO {
	private int boardFileNo;
	private int boardNo;
	private String fileName;
	private String originalFileName;
	private long fileSize;
}
