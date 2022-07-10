package com.it.groupware.emp.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.it.groupware.common.SearchVO;

@Mapper
public interface EmpDAO {
	int insertEmp(EmpDTO empDto);
	List<EmpDTO> selectAllEmp();
	List<EmpDTO> selectAllEmpList(SearchVO searchVo);
	Map<String, Object> selectstamp(String userNo);
	Map<String, Object> selectstampList(String empNo);
	int selectTotalEmp(SearchVO searchVo);
	int updateEmp(EmpDTO empDto);
	int deleteEmp(int empNo);
	String checkEmail(int empNo);
	List<EmpDTO>  selectAllEmpForEle();
	List<EmpDTO> selectSearchNum(int searcNo);
	int selectCountByEmpNo(int empNo);
	int selectTotalEmpCount();
	int selectTotalAdmin();
	List<HashMap<String, Object>> selectAge();
	List<HashMap<String, Object>> selectAgeGrop();
	List<HashMap<String, Object>> selectJoinLeave();
	
	//새로만듬-------------------------------------------------
	String selectPwd(int empNo);
	EmpDTO selectByEmpNo(int empNo);
	int updateTempPwd(EmpDTO empDto);
	List<EmpDTO> selectByDepartmentNo(int departmentNo);

}
