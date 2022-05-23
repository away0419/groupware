package com.it.groupware.electronic.model;

import java.util.List;
import java.util.Map;

import com.it.groupware.common.SearchVO;

public interface ElectronicService {

	int insertEle(ElectronicDTO vo);
	ElectronicDTO selectByElectronicNo(int ElectronicNo);
	int selectMaxEleNo(int empNo);
	List<ElectronicDTO> selectByEmpNo(int empNo);
	List<Map<String, Object>> selectListByEmpNo(SearchVO searchVo, String no);
	int TotalSelectListByEmpNo(SearchVO searchVo, String no);
	int updateEle(ElectronicDTO vo);
	int updateEleReturn(int ElectronicNo);
	int updateEleComplete(int ElectronicNo);
	List<Map<String, Object>> selectTopSty(int empNo);
	int upToDate(int ElectronicNo);
	List<ElectronicDTO> selectUpdateToday(int empNo);
}
