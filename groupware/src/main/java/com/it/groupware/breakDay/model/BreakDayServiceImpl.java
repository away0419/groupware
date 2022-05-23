package com.it.groupware.breakDay.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BreakDayServiceImpl implements BreakDayService{
	private final BreakDayDAO dao;


	@Override
	public List<BreakDayDTO> selectAllBreakDayByEmpNo(Map<String, Object> map) {
		return dao.selectAllBreakDayByEmpNo(map);
	}


	@Override
	public int selectCntAllBreakDayByEmpNo(int empNo) {
		return dao.selectCntAllBreakDayByEmpNo(empNo);
	}


	@Override
	public Map<String, Object> currentList(Map<String, Object> map) {
		Map<String, Object> data = new HashMap<>();
		int totalRecord = dao.selectCntAllBreakDayByEmpNo((int)map.get("empNo"));
		List<BreakDayDTO> breakdayList = dao.selectAllBreakDayByEmpNo(map);
		
		data.put("totalRecord", totalRecord);
		data.put("breakdayList", breakdayList);
		
		return data;
	}
	
}
