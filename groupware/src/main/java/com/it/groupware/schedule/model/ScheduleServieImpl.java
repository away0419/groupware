package com.it.groupware.schedule.model;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleServieImpl implements ScheduleService{
	
	private final  ScheduleDAO dao;
	
	@Override
	public int insertSchedule(ScheduleDTO vo) {
		return dao.insertSchedule(vo);
	}

	@Override
	public List<ScheduleDTO> selectAllScheduleByEmpNo(int empNo){
		return dao.selectAllScheduleByEmpNo(empNo);
	}

	@Override
	public List<ScheduleDTO> selectAllScheduleByScFolderNo(int scheduleFolderNo) {
		return dao.selectAllScheduleByScFolderNo(scheduleFolderNo);
	}

	@Override
	public int deleteScheduleByScFolderNo(int scheduleFolderNo) {
		return dao.deleteScheduleByScFolderNo(scheduleFolderNo);
	}

	@Override
	public int updateScheduleByScFolderNo(ScheduleDTO vo) {
		return dao.updateScheduleByScFolderNo(vo);
	}

	@Override
	public ScheduleDTO selectScheduleByScheduleNo(int scheduleNo) {
		return dao.selectScheduleByScheduleNo(scheduleNo);
	}

	@Override
	public int deleteScheduleByScheduleNo(int scheduleNo) {
		return dao.deleteScheduleByScheduleNo(scheduleNo);
	}

	@Override
	public int selectMaxScNoByEmpNo(int empNo) {
		return dao.selectMaxScNoByEmpNo(empNo);
	}

	@Override
	public List<ScheduleDTO> selectAllScheduleByBTNo(int bookingTargetNo) {
		return dao.selectAllScheduleByBTNo(bookingTargetNo);
	}

	@Override
	public List<ScheduleDTO> selectAllScheduleByScThemeNo(int scheduleThemeNo) {
		return dao.selectAllScheduleByScThemeNo(scheduleThemeNo);
	}

	@Override
	public int selectCntScheduleByToday(HashMap<String, Object> map) {
		return dao.selectCntScheduleByToday(map);
	}

	@Override
	public int selectCntScheduleByBookingday(ScheduleDTO vo) {
		return dao.selectCntScheduleByBookingday(vo);
	}
	
}
