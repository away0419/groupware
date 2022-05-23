package com.it.groupware.attend.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it.groupware.attend.model.AttendService;
import com.it.groupware.attendDay.model.AttendDayDTO;
import com.it.groupware.common.ConstUtil;
import com.it.groupware.schedule.controller.ScheduleController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/attend")
@RequiredArgsConstructor
@Api(tags ="근태")
public class AttendController {
	private final AttendService attendService;
	
	private static final Logger logger
	=LoggerFactory.getLogger(ScheduleController.class);

	@PostMapping
	@ApiOperation(value="지각 확인")
	public ResponseEntity<String> lateCheck(@RequestBody AttendDayDTO dto){
		
		Date comet= dto.getAttendanceDayOnHour();
		Date regt= dto.getAttendanceDayRegdate();
		
		regt.setHours(10);
		if(comet.getTime()>regt.getTime()) {// 지각이면 Attend의 지각수 증가
			attendService.updateLateAttendByEmpNo(dto.getEmpNo());
		}
		
		return new ResponseEntity<String>(ConstUtil.SUCCESS, HttpStatus.OK);
	}
}
