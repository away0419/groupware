package com.it.groupware.schedule.model;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScheduleDAO {
	int insertSchedule(ScheduleDTO vo);
	List<ScheduleDTO> selectAllScheduleByEmpNo(int empNo);
	List<ScheduleDTO> selectAllScheduleByBTNo(int bookingTargetNo);
	ScheduleDTO selectScheduleByScheduleNo(int scheduleNo);
	List<ScheduleDTO> selectAllScheduleByScFolderNo(int scheduleFolderNo);
	List<ScheduleDTO> selectAllScheduleByScThemeNo(int scheduleThemeNo);
	int deleteScheduleByScFolderNo(int scheduleFolderNo);
	int deleteScheduleByScheduleNo(int scheduleNo);
	int updateScheduleByScFolderNo(ScheduleDTO vo);
	int selectMaxScNoByEmpNo(int empNo);
	int selectCntScheduleByToday(HashMap<String, Object> map);
	int selectCntScheduleByBookingday(ScheduleDTO vo);
}
