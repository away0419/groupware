package com.it.groupware.emailFile.model;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailFileServiceImpl implements EmailFileService{
	
	private final EmailFileDAO emailFileDao;
	
	@Override
	public int uploadEmailFile(EmailFileDTO emailFileVo) {
		return emailFileDao.uploadEmailFile(emailFileVo);
	}

	@Override
	public List<EmailFileDTO> selectFileByMailNo(int mailNo) {
		return emailFileDao.selectFileByMailNo(mailNo);
	}

	@Override
	public EmailFileDTO selectFileByFileNo(int fileNo) {
		return emailFileDao.selectFileByFileNo(fileNo);
	}

}
