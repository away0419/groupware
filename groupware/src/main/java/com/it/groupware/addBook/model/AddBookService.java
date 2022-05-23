package com.it.groupware.addBook.model;

import java.util.List;
import java.util.Map;

import com.it.groupware.common.SearchVO;

public interface AddBookService {
	int insertAddBook(AddBookDTO vo);
	List<Map<String, Object>> selectAllAddBook(SearchVO searchVo);
	int selectAllTotalRecord(SearchVO searchVo);
	AddBookDTO selectByNo(int addressBookNo);
	int updateAddBook(AddBookDTO vo);
	int deleteAddBook(int addressBookNo);
	List<Map<String, Object>> selectByFolNo(SearchVO searchVo);
	int selectByFolNoTotalRecord(SearchVO searchVo);
}
