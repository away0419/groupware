package com.it.groupware.emailFile.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmailFileDTO {
	private int fileNo;
	private int mailNo;
	private int fileCheckNo;
	private String fileName;
	private String fileOriginName;
	private long fileSize;
}
