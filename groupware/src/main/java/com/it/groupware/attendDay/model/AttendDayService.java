package com.it.groupware.attendDay.model;

import java.util.List;
import java.util.Map;

public interface AttendDayService {
	AttendDayDTO selectAttendDayByRegdate(AttendDayDTO dto);
	int insertAttendDay(AttendDayDTO dto);
	int updateAttendDayByOffHour(AttendDayDTO dto);
	int selectSumWeekWork(int empNo);
	int selectSumMonthWork(Map<String, Object> map);
	int selectSumWeekWorkEx(int empNo);
	int selectSumMonthWorkEx(Map<String, Object> map);
	int selectLeftTimeWeek(int empNo);
	int selectDayWorkEx(Map<String, Object> map);
	int selectSumWeekWorkByMonth(Map<String, Object> map);
	List<AttendDayDTO> selectAttendMonth(Map<String, Object> map);
	int updateAttendDay(AttendDayDTO dto);
	List<Map<String,Object>> selectSumConditionByGroup(Map<String, Object> map);
	List<ConditionViewDTO> selectAllConditionByDepartMent(Map<String, Object> map);
	List<ConditionViewDTO> selectAllConditionByMonth(Map<String, Object> map);
	int selectCntConditionByMonth(Map<String, Object> map);
	int selectCntBreakDayByMonth(Map<String, Object> map);
	int selectAllCntConditionByMonth(Map<String, Object> map);
	List<ConditionViewDTO> selectAttendDayByFlag(int currentPage);
	int selecCnttAttendDayByFlag();
	int updateAttendDayByFlag(Map<String, Object> map);
	int updateAutoOffHour();
	Map<String,Object> conditionMonthView(Map<String, Object> map);
	Map<String,Object> statsView(Map<String, Object> map);
	AttendDayDTO toDayAttendDay(int empNo);
	Map<String, Object> toDayAllData(int empNo);
	Map<String, Object> condition(int empNo, int departmentNo);
	List<Integer> selectMonthWorkTime(String selectDate, int empNo);
	List<AttendDayDTO> selectMonth(String selectDate, int empNo);
}
