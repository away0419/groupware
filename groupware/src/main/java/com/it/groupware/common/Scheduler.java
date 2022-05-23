package com.it.groupware.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.it.groupware.attendDay.model.AttendDayService;
import com.it.groupware.email.controller.EmailController;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Scheduler {
	private static final Logger logger = LoggerFactory.getLogger(EmailController.class);
	private final AttendDayService attendDayService;
	
	/* 23시 59분 30초 자동 퇴근 */
	@Scheduled(cron= "30 59 23 * * *")
	public void updateAttendDayOffHour(){
		attendDayService.updateAutoOffHour();
	}
}
