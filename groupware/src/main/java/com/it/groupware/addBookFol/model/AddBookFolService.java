package com.it.groupware.addBookFol.model;

import java.util.List;

public interface AddBookFolService {
	int insertFol(AddBookFolDTO folVo);
	List<AddBookFolDTO> selectFol(int empNo);
	AddBookFolDTO selectByFolNo(int addressFolderNo);
	int updateFol(AddBookFolDTO booFolVo);
	int deleteFol(int addressFolderNo);
}
