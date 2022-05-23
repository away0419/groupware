package com.it.groupware.emailFile.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmailFileDAO {
	int uploadEmailFile(EmailFileDTO emailFileVo);
	List<EmailFileDTO> selectFileByMailNo(int mailNo);
	EmailFileDTO selectFileByFileNo(int fileNo);
}
