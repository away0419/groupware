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
	
//	새로만듬----------------------------------------------------------
	void sendEmail(EmpDTO vo); // 임시 비밀번호 이메일로 발송
	Map<String, Object> login(int empNo, String empPwd); // 로그인 확인
	boolean issuePwd(EmpDTO empDto); // 비밀번호 확인 후 임시 비밀번호 랜덤 변경
	List<EmpDTO> selectByDepartmentNo(int departmentNo); // 해당 부서 번호를 가진 사원 목록
}
