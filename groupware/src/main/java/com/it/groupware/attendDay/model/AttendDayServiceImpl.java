package com.it.groupware.attendDay.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendDayServiceImpl implements AttendDayService {
	private final AttendDayDAO dao;

	@Override
	public AttendDayDTO selectAttendDayByRegdate(AttendDayDTO dto) {
		return dao.selectAttendDayByRegdate(dto);
	}

	@Override
	public int insertAttendDay(AttendDayDTO dto) {
		return dao.insertAttendDay(dto);
	}

	@Override
	public int updateAttendDayByOffHour(AttendDayDTO dto) {
		return dao.updateAttendDayByOffHour(dto);
	}

	@Override
	public int selectSumWeekWork(int empNo) {
		return dao.selectSumWeekWork(empNo);
	}

	@Override
	public int selectSumMonthWork(Map<String, Object> map) {
		return dao.selectSumMonthWork(map);
	}

	@Override
	public int selectSumWeekWorkByMonth(Map<String, Object> map) {
		return dao.selectSumWeekWorkByMonth(map);
	}

	@Override
	public List<AttendDayDTO> selectAttendMonth(Map<String, Object> map) {
		return dao.selectAttendMonth(map);
	}

	@Override
	public int selectSumWeekWorkEx(int empNo) {
		return dao.selectSumWeekWorkEx(empNo);
	}

	@Override
	public int selectSumMonthWorkEx(Map<String, Object> map) {
		return dao.selectSumMonthWorkEx(map);
	}

	@Override
	public int selectLeftTimeWeek(int empNo) {
		return dao.selectLeftTimeWeek(empNo);
	}

	@Override
	public int selectDayWorkEx(Map<String, Object> map) {
		return dao.selectDayWorkEx(map);
	}

	@Override
	public int updateAttendDay(AttendDayDTO dto) {
		return dao.updateAttendDay(dto);
	}

	@Override
	public List<Map<String, Object>> selectSumConditionByGroup(Map<String, Object> map) {
		return dao.selectSumConditionByGroup(map);
	}

	@Override
	public List<ConditionViewDTO> selectAllConditionByDepartMent(Map<String, Object> map) {
		return dao.selectAllConditionByDepartMent(map);
	}

	@Override
	public List<ConditionViewDTO> selectAllConditionByMonth(Map<String, Object> map) {
		return dao.selectAllConditionByMonth(map);
	}

	@Override
	public int selectCntConditionByMonth(Map<String, Object> map) {
		return dao.selectCntConditionByMonth(map);
	}

	@Override
	public int selectCntBreakDayByMonth(Map<String, Object> map) {
		return dao.selectCntBreakDayByMonth(map);
	}

	@Override
	public int selectAllCntConditionByMonth(Map<String, Object> map) {
		return dao.selectAllCntConditionByMonth(map);
	}

	@Override
	public List<ConditionViewDTO> selectAttendDayByFlag(int currentPage) {
		return dao.selectAttendDayByFlag(currentPage);
	}

	@Override
	public int selecCnttAttendDayByFlag() {
		return dao.selecCnttAttendDayByFlag();
	}

	@Override
	public int updateAttendDayByFlag(Map<String, Object> map) {
		return dao.updateAttendDayByFlag(map);
	}

	@Override
	public int updateAutoOffHour() {
		return dao.updateAutoOffHour();
	}

	@Override
	@Transactional
	public Map<String, Object> conditionMonthView(Map<String, Object> map) {
		Map<String, Object> data = new HashMap<>();
		List<Map<String, Object>> conditionSumList = dao.selectSumConditionByGroup(map);
		List<ConditionViewDTO> conditionList = dao.selectAllConditionByDepartMent(map);

		data.put("conditionSumList", conditionSumList);
		data.put("conditionList", conditionList);

		return data;
	}

	@Override
	@Transactional
	public Map<String, Object> statsView(Map<String, Object> map) {
		HashMap<String, Object> data = new HashMap<>();
		List<ConditionViewDTO> conditionList2 = dao.selectAllConditionByMonth(map);// 엑셀 다운용 List
		map.remove("currentPage");

		List<ConditionViewDTO> conditionList = dao.selectAllConditionByMonth(map);// 검색 view용 List
		int empCnt = dao.selectCntConditionByMonth(map);
		int breakCnt = dao.selectCntBreakDayByMonth(map);
		int TotalRecord = dao.selectAllCntConditionByMonth(map);

		data.put("conditionList2", conditionList2);
		data.put("conditionList", conditionList);
		data.put("empCnt", empCnt);
		data.put("breakCnt", breakCnt);
		data.put("TotalRecord", TotalRecord);

		return data;
	}

	@Override
	public AttendDayDTO toDayAttendDay(int empNo) {
		Date today = new Date();

		AttendDayDTO attendDayDto = new AttendDayDTO();
		attendDayDto.setAttendanceDayRegdate(today);
		attendDayDto.setEmpNo(empNo);

		AttendDayDTO attendDayDto2 = dao.selectAttendDayByRegdate(attendDayDto);

		return attendDayDto2;
	}

	public Map<String, Object> toDayAllData(int empNo) {
		Map<String, Object> data = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cal = new GregorianCalendar();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int maxday = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		List<Integer> weekTimelist = new ArrayList<>();
		Calendar startDate = new GregorianCalendar(year, month, 1);
		int firstDay = startDate.get(Calendar.DAY_OF_WEEK) - 1;
		int weekCheck = 1;
		for (int i = 1; i <= maxday; i++) {
			Calendar date = new GregorianCalendar(year, month, i);
			int weekNum = (int) Math.ceil((i + firstDay) / 7.0);

			if (weekCheck < weekNum) {
				date.add(Calendar.DATE, -1);
				HashMap<String, Object> map = new HashMap<>();

				map.put("startDate", sdf.format(startDate.getTime()));
				map.put("endDate", sdf.format(date.getTime()));
				map.put("empNo", empNo);

				weekTimelist.add(dao.selectSumWeekWorkByMonth(map));
				startDate = new GregorianCalendar(year, month, i);
				weekCheck++;
			} else if (i == maxday) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("startDate", sdf.format(startDate.getTime()));
				map.put("endDate", sdf.format(date.getTime()));
				map.put("empNo", empNo);

				weekTimelist.add(dao.selectSumWeekWorkByMonth(map));
			}
		}

		Calendar firstDate = new GregorianCalendar(year, month, 1);
		Calendar lastDate = new GregorianCalendar(year, month, maxday);

		int pday = firstDate.get(Calendar.DAY_OF_WEEK) - 1;
		int fday = 7 - lastDate.get(Calendar.DAY_OF_WEEK);

		firstDate.add(Calendar.DATE, -pday);
		lastDate.add(Calendar.DATE, fday);
		HashMap<String, Object> map = new HashMap<>();
		map.put("empNo", empNo);
		map.put("firstDate", sdf.format(firstDate.getTime()));
		map.put("lastDate", sdf.format(lastDate.getTime()));

		List<AttendDayDTO> aMList = dao.selectAttendMonth(map);
		int aMListSize = aMList.size();
		int aMListNum = 0;

		long distinction = (lastDate.getTimeInMillis() - firstDate.getTimeInMillis()) / 1000;
		distinction /= 24 * 60 * 60;

		List<AttendDayDTO> attendMonthList = new ArrayList<AttendDayDTO>();
		for (int i = 0; i <= distinction; i++) {
			AttendDayDTO emptyAttendVo = new AttendDayDTO();

			if (aMListNum < aMListSize && sdf.format(firstDate.getTime())
					.equals(sdf.format(aMList.get(aMListNum).getAttendanceDayRegdate()))) {
				attendMonthList.add(aMList.get(aMListNum));
				aMListNum++;
			} else {
				attendMonthList.add(emptyAttendVo);
			}
			firstDate.add(Calendar.DATE, 1);

		}

		HashMap<String, Object> map2 = new HashMap<>();
		map2.put("empNo", empNo);
		Date nowDate = new Date();
		map2.put("nowDate", sdf.format(nowDate));

		int selectSumWeekWork = dao.selectSumWeekWork(empNo);
		int selectSumMonthWork = dao.selectSumMonthWork(map2);
		int selectSumWeekWorkEx = dao.selectSumWeekWorkEx(empNo);
		int selectSumMonthWorkEx = dao.selectSumMonthWorkEx(map2);
		int selectLeftTimeWeek = dao.selectLeftTimeWeek(empNo);

		data.put("attendMonthList", attendMonthList);
		data.put("weekTimelist", weekTimelist);
		data.put("selectSumMonthWork", selectSumMonthWork);
		data.put("selectSumWeekWork", selectSumWeekWork);
		data.put("selectSumMonthWorkEx", selectSumMonthWorkEx);
		data.put("selectSumWeekWorkEx", selectSumWeekWorkEx);
		data.put("selectLeftTimeWeek", selectLeftTimeWeek);

		return data;
	}

	@Override
	public Map<String, Object> condition(int empNo, int departmentNo) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> data = new HashMap<>();

		AttendDayDTO attendDayDTO = toDayAttendDay(empNo);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String selectDate = sdf.format(new Date());
		map.put("departmentNo", departmentNo);
		map.put("selectDate", selectDate);
		map.put("selectNum", 0);
		map.put("timeNum", 0);
		map.put("searchKeyword", 0);

		Calendar cal = new GregorianCalendar();
		int nowDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
		cal.add(Calendar.DATE, -nowDay);
		Date now = cal.getTime();
		now.setHours(0);
		now.setMinutes(0);
		now.setSeconds(0);
		long nowMili = now.getTime() / 1000;

		List<Map<String, Object>> conditionSumList = dao.selectSumConditionByGroup(map);
		List<ConditionViewDTO> conditionList = dao.selectAllConditionByDepartMent(map);
		data.put("conditionList", conditionList);
		data.put("conditionSumList", conditionSumList);
		data.put("nowMili", nowMili);
		data.put("attendDayDTO", attendDayDTO);

		return data;
	}

	@Override
	public List<Integer> selectMonthWorkTime(String selectDate, int empNo) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		int ym = selectDate.indexOf("-");

		int year = Integer.parseInt(selectDate.substring(0, ym));
		int month = Integer.parseInt(selectDate.substring(ym + 1)) - 1;
		Calendar firstDate = new GregorianCalendar(year, month, 1);
		int maxday = firstDate.getActualMaximum(Calendar.DAY_OF_MONTH);

		List<Integer> weekTimelist = new ArrayList<>();
		int firstDay = firstDate.get(Calendar.DAY_OF_WEEK) - 1;
		int weekCheck = 1;
		for (int i = 1; i <= maxday; i++) {// 선택한 달의 첫날 부터 마지막날까지 반복
			Calendar date = new GregorianCalendar(year, month, i);
			int weekNum = (int) Math.ceil((i + firstDay) / 7.0);

			if (weekCheck < weekNum) {// 주차 검사
				date.add(Calendar.DATE, -1);
				HashMap<String, Object> map = new HashMap<>();

				map.put("startDate", sdf.format(firstDate.getTime()));
				map.put("endDate", sdf.format(date.getTime()));
				map.put("empNo", empNo);

				weekTimelist.add(dao.selectSumWeekWorkByMonth(map));
				firstDate = new GregorianCalendar(year, month, i);
				weekCheck++;
			} else if (i == maxday) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("startDate", sdf.format(firstDate.getTime()));
				map.put("endDate", sdf.format(date.getTime()));
				map.put("empNo", empNo);

				weekTimelist.add(dao.selectSumWeekWorkByMonth(map));
			}
		}
		return weekTimelist;
	}

	@Override
	public List<AttendDayDTO> selectMonth(String selectDate, int empNo) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int ym = selectDate.indexOf("-");

		int year = Integer.parseInt(selectDate.substring(0, ym));
		int month = Integer.parseInt(selectDate.substring(ym+1))-1;
		Calendar firstDate = new GregorianCalendar(year, month, 1);
		int maxday = firstDate.getActualMaximum(Calendar.DAY_OF_MONTH);
		Calendar lastDate = new GregorianCalendar(year,month,maxday);
		
		int pday = firstDate.get(Calendar.DAY_OF_WEEK)-1;
		int fday = 7-lastDate.get(Calendar.DAY_OF_WEEK);
		
		firstDate.add(Calendar.DATE, -pday);
		lastDate.add(Calendar.DATE, fday);
		HashMap<String, Object> map = new HashMap<>();
		map.put("empNo",empNo);
		map.put("firstDate", sdf.format(firstDate.getTime()));
		map.put("lastDate", sdf.format(lastDate.getTime()));
		
		List<AttendDayDTO> aMList= dao.selectAttendMonth(map);
		int aMListSize = aMList.size();
		int aMListNum=0;
		
		long  distinction = (lastDate.getTimeInMillis()-firstDate.getTimeInMillis())/1000;
		distinction /= 24*60*60;
		
		List<AttendDayDTO> attendMonthList = new ArrayList<AttendDayDTO>();
		for(int i=0; i<=distinction; i++) {
			AttendDayDTO emptyAttendVo = new AttendDayDTO();
			
			
			if(aMListNum<aMListSize && sdf.format(firstDate.getTime()).equals(sdf.format(aMList.get(aMListNum).getAttendanceDayRegdate()))) {
				attendMonthList.add(aMList.get(aMListNum));
				aMListNum++;
			}else{
				attendMonthList.add(emptyAttendVo);
			}
			firstDate.add(Calendar.DATE, 1);
			
		}
		
		return attendMonthList;
	}

}
