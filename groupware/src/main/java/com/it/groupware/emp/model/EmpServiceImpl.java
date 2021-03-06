package com.it.groupware.emp.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.it.groupware.common.CustomErrorCode;
import com.it.groupware.common.CustomException;
import com.it.groupware.common.MailHandler;
import com.it.groupware.common.RandomPwd;
import com.it.groupware.common.SearchVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService{
	private final EmpDAO empDao;
	private final PasswordEncoder passwordEncoder;
	private final JavaMailSender javaMailSender;
	private static final Logger logger = LoggerFactory.getLogger(EmpServiceImpl.class);

	 
	@Transactional
	@Override
	public int insertEmp(EmpDTO empDto) {
		empDto.setEmpPwd(passwordEncoder.encode(empDto.getEmpPwd()));
		return empDao.insertEmp(empDto);
	}

	

	@Override
	public EmpDTO selectByEmpNo(int empNo) {
		return empDao.selectByEmpNo(empNo);
	}

	@Override
	public Map<String, Object> selectstamp(String userNo) {
		return empDao.selectstamp(userNo);
	}

	@Override
	public Map<String, Object> selectstampList(String empNo) {
		Map<String, Object> map = empDao.selectstampList(empNo);
		return map;
		
	}

	@Override
	public int selectTotalEmp(SearchVO searchVo) {
		return empDao.selectTotalEmp(searchVo);
	}

	@Override
	public List<EmpDTO> selectAllEmpList(SearchVO searchVo) {
		return empDao.selectAllEmpList(searchVo);
	}

	@Override
	public List<EmpDTO> selectAllEmp() {
		return empDao.selectAllEmp();
	}

	@Override
	public int updateEmp(EmpDTO empDto) {
		return empDao.updateEmp(empDto);
	}

	@Override
	public int deleteEmp(int empNo) {
		return empDao.deleteEmp(empNo);
	}

	@Override
	public String checkEmail(int empNo) {
		return empDao.checkEmail(empNo);
	}

	@Override
	public int updateTempPwd(EmpDTO empDto) {
		empDto.setEmpPwd(passwordEncoder.encode(empDto.getEmpPwd()));
		return empDao.updateTempPwd(empDto);
	}

	

	@Override
	public String selectPwd(int empNo) {
		return empDao.selectPwd(empNo);
	}

	@Override
	public List<EmpDTO>  selectAllEmpForEle() {
		return empDao.selectAllEmpForEle();
	}

	@Override
	public List<EmpDTO> selectSearchNum(int searcNo) {
		return empDao.selectSearchNum(searcNo);
	}

	@Override
	public int selectCountByEmpNo(int empNo) {
		return empDao.selectCountByEmpNo(empNo);
	}

	@Override
	public List<HashMap<String, Object>> selectAge() {
		return empDao.selectAge();
	}

	@Override
	public List<HashMap<String, Object>> selectAgeGrop() {
		return empDao.selectAgeGrop();
	}

	@Override
	public List<HashMap<String, Object>> selectJoinLeave() {
		return empDao.selectJoinLeave();
	}

	@Override
	public int selectTotalEmpCount() {
		return empDao.selectTotalEmpCount();
	}

	@Override
	public int selectTotalAdmin() {
		return empDao.selectTotalAdmin();
	}

//	????????????-------------------------------------------------------------------
	
	//???????????? ?????? ??? ?????? ???????????? ?????? ??????
	@Transactional
	@Override
	public boolean issuePwd(EmpDTO empDto) {
		int empNo = empDto.getEmpNo();
		String empEmail = empDto.getEmpEmail();
		String dbEmail = empDao.checkEmail(empNo);
		
//		logger.info("dbEmail : {}",dbEmail);
//		logger.info("empEmail : {}",empEmail);
		
		if(empEmail.equals(dbEmail)) {
			String tempPwd =  RandomPwd.getRandomPwd(8);
			empDto.setEmpPwd(tempPwd);
			
			sendEmail(empDto);
			
			empDao.updateTempPwd(empDto);
			
			return true;
		}
		return false;
	}
	
	//????????? ??????
	@Override
	public Map<String, Object> login(int empNo, String empPwd) {
		Map<String, Object> data = new HashMap<String, Object>();
		boolean result = true;
		EmpDTO dto = null;
		String dbPwd = empDao.selectPwd(empNo);
		
//		logger.info("?????? {}",dbPwd);
//		logger.info("???????????? {}",empPwd);
//		logger.info("???????????? ????????? {}",passwordEncoder.encode(empPwd));
		
		if(dbPwd==null || dbPwd.isEmpty() || !passwordEncoder.matches(empPwd, dbPwd)) {
			result = false;
		}else {
			dto = empDao.selectByEmpNo(empNo);
		}
		
		data.put("result", result);
		data.put("emp", dto);
		
		return data;
	}
	
	//?????? ???????????? ???????????? ??????
	@Override
	public void sendEmail(EmpDTO empDto) {
		
		try {
			MailHandler mailHandler = new MailHandler(javaMailSender);
			
			
			String fromEmail = "groupwaretest456@gmail.com"; 
			String fromName = "IU????????????";
			String subject = "";
			String msg = "";
			
			subject = "IU???????????? ?????? ???????????? ?????????.";
			msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
			msg += "<h3 style='color: blue;'>";
			msg += empDto.getEmpNo()+"?????? ?????? ???????????? ?????????. ??????????????? ???????????? ???????????????.</h3>";
			msg += "<p>?????? ???????????? : ";
			msg += empDto.getEmpPwd()+"</p>";
			
			String mail = empDto.getEmpEmail();
			
			mailHandler.setFrom(fromEmail,fromName);
			mailHandler.setSubject(subject);
			mailHandler.setText(msg, true);
			mailHandler.setTo(mail);
			
			mailHandler.send();
			
		} catch (Exception e) {
			System.out.println("???????????? ?????? : " + e);
			e.printStackTrace();
		}
		
		
	}

	//?????? ?????? ????????? ?????? ?????? ??????
	@Override
	public List<EmpDTO> selectByDepartmentNo(int departmentNo) {
		return empDao.selectByDepartmentNo(departmentNo);
	}


	
	
}

