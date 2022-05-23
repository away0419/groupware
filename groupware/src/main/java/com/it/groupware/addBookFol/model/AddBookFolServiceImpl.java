package com.it.groupware.addBookFol.model;

import java.util.List;

import org.springframework.stereotype.Service;

import com.it.groupware.addBook.model.AddBookDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddBookFolServiceImpl implements AddBookFolService{
	private final AddBookFolDAO addBookFolDao;

	@Override
	public int insertFol(AddBookFolDTO folVo) {
		return addBookFolDao.insertFol(folVo);
	}

	@Override
	public List<AddBookFolDTO> selectFol(int empNo) {
		return addBookFolDao.selectFol(empNo);
	}

	@Override
	public AddBookFolDTO selectByFolNo(int addressFolderNo) {
		return addBookFolDao.selectByFolNo(addressFolderNo);
	}
	
	@Override
	public int updateFol(AddBookFolDTO folVo) {
		return addBookFolDao.updateFol(folVo);
	}

	@Override
	public int deleteFol(int addressFolderNo) {
		return addBookFolDao.deleteFol(addressFolderNo);
	}

	


}
