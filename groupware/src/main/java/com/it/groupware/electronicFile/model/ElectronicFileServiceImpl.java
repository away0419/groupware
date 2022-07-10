package com.it.groupware.electronicFile.model;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ElectronicFileServiceImpl implements ElectronicFileService{
	private final ElectronicFileDAO electronicFileDao;


	@Override
	public ElectronicFileDTO selectFileByFileNo(int fileNo) {
		return electronicFileDao.selectFileByFileNo(fileNo);
	}

	@Override
	public int deleteFile(int electronicNo) {
		return electronicFileDao.deleteFile(electronicNo);
	}

	//파일 등록
	@Override
	public int insertFile(ElectronicFileDTO dto) {
		return electronicFileDao.insertFile(dto);
	}
	
	//해당 문서 파일 목록
	@Override
	public List<ElectronicFileDTO> selectFileByEleNo(int electronicNo) {
		return electronicFileDao.selectFileByEleNo(electronicNo);
	}
	
}
