package com.it.groupware.electronicAppStamp.model;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ElectronicAppStampDAO {
	int insertStamp(ElectronicAppStampDTO stampVo);
	ElectronicAppStampDTO selectByEmpNo(int empNo);
}
