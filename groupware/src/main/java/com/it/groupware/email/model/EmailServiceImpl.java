package com.it.groupware.email.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.it.groupware.common.SearchVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{
	private final EmailDAO emailDao;
	
	@Override
	public int sendEmail(EmailDTO vo) {
		return emailDao.sendEmail(vo);
	}
	
	// 101(이름) 에서 이름 분리 
	@Override
	public String splitName(String taker) {
		String[] splitTaker = taker.split("@");
		String emailTake = "";
		for(int i=0;i<splitTaker.length;i++) {
			emailTake = splitTaker[0];
		}
		
		return emailTake;
	}


	@Override
	public EmailDTO selectByMailNo(int mailNo) {
		return emailDao.selectByMailNo(mailNo);
	}

	@Override
	public List<Map<String, Object>> selectListByType(SearchVO searchVo, int type) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(type==EmailService.TAKE_MAIL) {
			list = emailDao.selectTakeMailList(searchVo);
		}else if(type==EmailService.SEND_MAIL) {
			list= emailDao.selectSendMailList(searchVo);
		}else if(type==EmailService.TEMP_MAIL) {
			list= emailDao.selectTempsaveMailList(searchVo);
		}else if(type==EmailService.BOOK_MAIL) {
			list= emailDao.selectReservMailList(searchVo);
		}else if(type==EmailService.TRASH_MAIL) {
			list= emailDao.selectTrashMailList(searchVo);
		}else if(type==EmailService.NOTREAD_MAIL) {
			list= emailDao.selectNotReadMailList(searchVo);
		}else if(type==EmailService.IMPORTANT_MAIL) {
			list= emailDao.selectImportantMailList(searchVo);
		}
		
		return list;
	}

	@Override
	public int totalRecordByType(SearchVO searchVo, int type) {
		int totalRecord = 0;
		if(type==EmailService.TAKE_MAIL) {
			totalRecord = emailDao.totalRecordByEmailTake(searchVo);
		}else if(type==EmailService.SEND_MAIL) {
			totalRecord = emailDao.totalRecordByEmailSend(searchVo);
		}else if(type==EmailService.TEMP_MAIL) {
			totalRecord = emailDao.totalRecordByTempsave(searchVo);
		}else if(type==EmailService.BOOK_MAIL) {
			totalRecord = emailDao.totalRecordByReserv(searchVo);
		}else if(type==EmailService.TRASH_MAIL) {
			totalRecord = emailDao.totalRecordByTrash(searchVo);
		}else if(type==EmailService.NOTREAD_MAIL) {
			totalRecord = emailDao.totalCountByReadDate(searchVo);
		}else if(type==EmailService.IMPORTANT_MAIL) {
			totalRecord = emailDao.totalCountByImportant(searchVo);
		}
		return totalRecord;
	}

	@Override
	@Transactional
	public int deleteCheckMulti(List<EmailDTO> list) {
		int cnt = 0;		
		for(EmailDTO vo : list) {
			int mailNo = vo.getMailNo();
			
			if(mailNo!=0) {
				cnt = emailDao.deleteCheckMail(mailNo);
			}
		}//
		
		return cnt;
	}

	@Override
	@Transactional
	public int deleteCompleteMail(List<EmailDTO> list) {
		int cnt = 0;		
		for(EmailDTO vo : list) {
			int mailNo = vo.getMailNo();
			
			if(mailNo!=0) {
				cnt = emailDao.deleteCompleteMail(mailNo);
			}
		}//
		
		return cnt;
	}

	@Override
	@Transactional
	public int updateMultiReadDate(List<EmailDTO> list) {
		int cnt = 0;
		for(EmailDTO vo : list) {
			int mailNo = vo.getMailNo();
			
			if(mailNo!=0) {
				cnt=emailDao.updateReadDate(mailNo);
			}
		}
		return cnt;
	}
	
	@Override
	public int updateReadDate(int mailNo) {
		return emailDao.updateReadDate(mailNo);
	}

	@Override
	public int updateInportant(int mailNo) {
		return emailDao.updateInportant(mailNo);
	}

	@Override
	public int updateNotInportant(int mailNo) {
		return emailDao.updateNotInportant(mailNo);
	}

	@Override
	public int updateNotReadDate(int mailNo) {
		return emailDao.updateNotReadDate(mailNo);
	}

	@Override
	public List<EmailDTO> selectNotRead(String empNo) {
		return emailDao.selectNotRead(empNo);
	}

	@Override
	public List<EmailDTO> selectImportant(String empNo) {
		return emailDao.selectImportant(empNo);
	}

	@Override
	public List<EmailDTO> selectTempSave(String empNo) {
		return emailDao.selectTempSave(empNo);
	}

	@Override
	public List<EmailDTO> selectReserve(String empNo) {
		return emailDao.selectReserve(empNo);
	}

	@Override
	public int totalCountByReadDateMain(int mailNo) {
		return emailDao.totalCountByReadDateMain(mailNo);
	}




	
}
