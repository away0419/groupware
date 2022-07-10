package com.it.groupware.electronicAppLine.model;

import java.util.List;

import com.it.groupware.electronic.model.ElectronicDTO;

public interface ElectronicAppLineService {
	
	int deleteAppLine(ElectronicAppLineDTO vo);
	
	int insertAppLine(ElectronicAppLineDTO dto); // 결재자 추가
	List<ElectronicAppLineDTO> selectByElectronicNo(int electronicNo); //해당 문서 번호의 결재 라인
	int updateAppLine(int electronicNo, int empNo, int approvalLineCompleteFlag); //해당 결재 라인 업데이트
	int selectForCheckExist(int electronicNo, int empNo, int approvalLineCompleteFlag); //현재 문서에 해당 사용자이면서 결재 상태가 동일 한게 있는지 확인
}
