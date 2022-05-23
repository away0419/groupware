package com.it.groupware.attend.model;

public interface AttendService {
	int updateLateAttendByEmpNo(int empNo);
	AttendDTO selectAttendByEmpNo(int empNo);

}
