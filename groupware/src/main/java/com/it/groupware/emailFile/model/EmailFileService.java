package com.it.groupware.emailFile.model;

import java.util.List;

public interface EmailFileService {
	int uploadEmailFile(EmailFileDTO emailFileVo);
	List<EmailFileDTO> selectFileByMailNo(int mailNo);
	EmailFileDTO selectFileByFileNo(int fileNo);
}
