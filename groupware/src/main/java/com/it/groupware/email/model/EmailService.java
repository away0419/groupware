package com.it.groupware.email.model;

import java.util.List;
import java.util.Map;

import com.it.groupware.common.SearchVO;

public interface EmailService {
	// 항목별 메일함 상수
	int TAKE_MAIL = 1;
	int SEND_MAIL = 2;
	int TEMP_MAIL = 3;
	int BOOK_MAIL = 4;
	int TRASH_MAIL = 5;
	int NOTREAD_MAIL = 6;
	int IMPORTANT_MAIL = 7;

	int sendEmail(EmailDTO vo);
	String splitName(String taker);
	List<Map<String, Object>> selectListByType(SearchVO searchVo, int type);
	//메인
	EmailDTO selectByMailNo(int mailNo);
	int totalRecordByType(SearchVO searchVo, int type);
	int deleteCheckMulti(List<EmailDTO> list);
	int deleteCompleteMail(List<EmailDTO> list);
	int updateMultiReadDate(List<EmailDTO> list);
	int updateReadDate(int mailNo);
	int updateInportant(int mailNo);
	int updateNotInportant(int mailNo);
	int updateNotReadDate(int mailNo);
	int totalCountByReadDateMain(int mailNo);
	
	// 메인에 뿌려주기
	List<EmailDTO> selectNotRead(String empNo);
	List<EmailDTO> selectImportant(String empNo);
	List<EmailDTO> selectTempSave(String empNo);
	List<EmailDTO> selectReserve(String empNo);
}
