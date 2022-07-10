package com.it.groupware.electronicDocSty.model;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ElectronicDocStyServiceImpl implements ElectronicDocStyService {
	private final ElectronicDocStyDAO eleDao;

	@Override
	public List<ElectronicDocStyDTO> selectAll() {
		return eleDao.selectAll();
	}

	@Override
	public ElectronicDocStyDTO selectByStyleNo(int styleNo) {
		return eleDao.selectByStyleNo(styleNo);
	}
	
	@Override
	public List<ElectronicDocStyDTO> selectByFolderNo(int styleNo) {
		return eleDao.selectByFolderNo(styleNo);
	}

	@Override
	public int selectByFolderStyleNo(int folderNo, int folderStyleNo) {
		return eleDao.selectByFolderStyleNo(folderNo, folderStyleNo);
	}

}
