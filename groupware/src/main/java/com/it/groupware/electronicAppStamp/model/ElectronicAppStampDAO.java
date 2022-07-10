package com.it.groupware.electronicAppStamp.model;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ElectronicAppStampDAO {
	int insertStamp(ElectronicAppStampDTO elAppStampDto); // 사인 추가
	ElectronicAppStampDTO selectByEmpNo(int empNo); // 해당 사원의 사인 
	int updateStamp(ElectronicAppStampDTO elAppStampDto); // 해당 사원의 사인 수정
}
