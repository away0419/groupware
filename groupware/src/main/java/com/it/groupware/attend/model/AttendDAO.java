package com.it.groupware.attend.model;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttendDAO {
	int updateLateAttendByEmpNo(int empNo);
	AttendDTO selectAttendByEmpNo(int empNo);
}
