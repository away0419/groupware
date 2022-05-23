package com.it.groupware.electronic.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.it.groupware.common.SearchVO;

@Mapper
public interface ElectronicDAO {
	
	int insertEle(ElectronicDTO vo);
	ElectronicDTO selectByElectronicNo(int ElectronicNo);
	int selectMaxEleNo(int empNo);
	List<ElectronicDTO> selectByEmpNo(int empNo);
	List<Map<String, Object>> selectListByEmpNo1(SearchVO searchVo);
	List<Map<String, Object>> selectListByEmpNo3(SearchVO searchVo);
	List<Map<String, Object>> selectListByEmpNo6(SearchVO searchVo);
	List<Map<String, Object>> selectListByEmpNo2(SearchVO searchVo);
	List<Map<String, Object>> selectListByEmpNo4(SearchVO searchVo);
	List<Map<String, Object>> selectListByEmpNo5(SearchVO searchVo);
	List<Map<String, Object>> selectListByEmpNo7(SearchVO searchVo);
	int TotalSelectListByEmpNo1(SearchVO searchVo);
	int TotalSelectListByEmpNo2(SearchVO searchVo);
	int TotalSelectListByEmpNo3(SearchVO searchVo);
	int TotalSelectListByEmpNo4(SearchVO searchVo);
	int TotalSelectListByEmpNo5(SearchVO searchVo);
	int TotalSelectListByEmpNo6(SearchVO searchVo);
	int TotalSelectListByEmpNo7(SearchVO searchVo);
	int updateEle(ElectronicDTO vo);
	int updateEleReturn(int ElectronicNo);
	int updateEleComplete(int ElectronicNo);
	List<Map<String, Object>> selectTopSty(int empNo);
	int upToDate(int ElectronicNo);
	List<ElectronicDTO> selectUpdateToday(int empNo);
	
}
