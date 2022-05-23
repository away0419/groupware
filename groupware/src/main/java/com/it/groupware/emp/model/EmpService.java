package com.it.groupware.emp.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.it.groupware.common.SearchVO;

public interface EmpService {
	//로그인처리 상수
	int LOGIN_OK=1;
	int PWD_DISAGREE=2;
	int ID_NONE=3;
	
	//adminLevel
	int ADMIN=1;
	int LEADER=2;
	int EMP=3;
	
	int insertEmp(EmpDTO vo);
	int loginProc(int empNo, String empPwd);
	EmpDTO selectByEmpNo(int empNo);
	List<EmpDTO> selectAllEmp();
	List<EmpDTO> selectAllEmpList(SearchVO searchVo);
	Map<String, Object> selectstamp(String userNo);
	Map<String, Object> selectstampList(String empNo);
	int selectTotalEmp(SearchVO searchVo);
	int updateEmp(EmpDTO vo);
	int deleteEmp(int empNo);
	String checkEmail(int empNo);
	int updateTempPwd(EmpDTO vo);
	String selectPwd(int empNo);
	List<EmpDTO> selectAllEmpForEle();
	List<EmpDTO> selectSearchNum(int searcNo);
	int selectCountByEmpNo(int empNo);
	List<HashMap<String, Object>> selectAge();
	List<HashMap<String, Object>> selectAgeGrop();
	List<HashMap<String, Object>> selectJoinLeave();
	
	int selectTotalEmpCount();
	int selectTotalAdmin();
	
	//비밀번호이메일발송
	void sendEmail(EmpDTO vo);
}
