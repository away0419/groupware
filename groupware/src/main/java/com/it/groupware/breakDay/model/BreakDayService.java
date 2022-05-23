package com.it.groupware.breakDay.model;

import java.util.List;
import java.util.Map;

public interface BreakDayService {
	List<BreakDayDTO> selectAllBreakDayByEmpNo(Map<String, Object> map);
	int selectCntAllBreakDayByEmpNo(int empNo);
	Map<String,Object> currentList(Map<String, Object> map);

}
