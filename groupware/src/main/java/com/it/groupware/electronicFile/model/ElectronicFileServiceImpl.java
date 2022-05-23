package com.it.groupware.electronicFile.model;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ElectronicFileServiceImpl implements ElectronicFileService{
	private final ElectronicFileDAO electronicFileDao;

	@Override
	public int insertFile(ElectronicFileDTO vo) {
		return electronicFileDao.insertFile(vo);
	}

	@Override
	public List<ElectronicFileDTO> selectFileByEleNo(int electronicNo) {
		return electronicFileDao.selectFileByEleNo(electronicNo);
	}

	@Override
	public ElectronicFileDTO selectFileByFileNo(int fileNo) {
		return electronicFileDao.selectFileByFileNo(fileNo);
	}

	@Override
	public int deleteFile(int electronicNo) {
		return electronicFileDao.deleteFile(electronicNo);
	}
	
	
}
