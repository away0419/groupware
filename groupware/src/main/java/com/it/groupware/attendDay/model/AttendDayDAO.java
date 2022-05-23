package com.it.groupware.attendDay.model;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttendDayDAO {
	AttendDayDTO selectAttendDayByRegdate(AttendDayDTO vo);
	int insertAttendDay(AttendDayDTO vo);
	int updateAttendDayByOffHour(AttendDayDTO vo);
	int selectSumWeekWork(int empNo);
	int selectSumMonthWork(Map<String, Object> map);
	int selectSumWeekWorkEx(int empNo);
	int selectSumMonthWorkEx(Map<String, Object> map);
	int selectLeftTimeWeek(int empNo);
	int selectDayWorkEx(Map<String, Object> map);
	int selectSumWeekWorkByMonth(Map<String, Object> map);
	List<AttendDayDTO> selectAttendMonth(Map<String, Object> map);
	int updateAttendDay(AttendDayDTO vo);
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
}
