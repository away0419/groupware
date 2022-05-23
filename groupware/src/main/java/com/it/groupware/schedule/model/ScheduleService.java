package com.it.groupware.schedule.model;

import java.util.HashMap;
import java.util.List;

public interface ScheduleService {
	
	int insertSchedule(ScheduleDTO vo);
	List<ScheduleDTO> selectAllScheduleByEmpNo(int empNo);
	List<ScheduleDTO> selectAllScheduleByScFolderNo(int scheduleFolderNo);
	int deleteScheduleByScFolderNo(int scheduleFolderNo);
	int updateScheduleByScFolderNo(ScheduleDTO vo);
	ScheduleDTO selectScheduleByScheduleNo(int scheduleNo);
	int deleteScheduleByScheduleNo(int scheduleNo);
	int selectMaxScNoByEmpNo(int empNo);
	List<ScheduleDTO> selectAllScheduleByBTNo(int bookingTargetNo);
	List<ScheduleDTO> selectAllScheduleByScThemeNo(int scheduleThemeNo);
	int selectCntScheduleByToday(HashMap<String, Object> map);
	int selectCntScheduleByBookingday(ScheduleDTO vo);

}
