package com.it.groupware.addBookFol.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.it.groupware.addBook.model.AddBookDTO;

@Mapper
public interface AddBookFolDAO {
	int insertFol(AddBookFolDTO folVo);
	List<AddBookFolDTO> selectFol(int empNo);
	AddBookFolDTO selectByFolNo(int addressFolderNo);
	int updateFol(AddBookFolDTO folVo);
	int deleteFol(int addressFolderNo);
}
