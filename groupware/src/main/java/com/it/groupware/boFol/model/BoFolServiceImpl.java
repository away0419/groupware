package com.it.groupware.boFol.model;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoFolServiceImpl implements BoFolService{
	private final BoFolDAO dao;
	
	@Override
	public List<BoFolDTO> selectAllBoFol() {
		return dao.selectAllBoFol();
	}

	@Override
	public int insertBoFol(BoFolDTO vo) {
		return dao.insertBoFol(vo);
	}

	@Override
	public int updateBoFol(BoFolDTO vo) {
		return dao.updateBoFol(vo);
	}

	@Override
	public int deleteBoFol(int bookingFolderNo) {
		return dao.deleteBoFol(bookingFolderNo);
	}
}
