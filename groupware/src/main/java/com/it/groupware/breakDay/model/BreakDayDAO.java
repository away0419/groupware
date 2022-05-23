package com.it.groupware.breakDay.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BreakDayDAO {
	List<BreakDayDTO> selectAllBreakDayByEmpNo(Map<String, Object> map);
	int selectCntAllBreakDayByEmpNo(int empNo);
}
