package com.it.groupware.electronicAppStamp.model;

public interface ElectronicAppStampService {

	int registerStamp(ElectronicAppStampDTO elAppStampDto); // 사인 등록
	ElectronicAppStampDTO selectStamp(int empNo); // 해당 사원 사인 검색
}
