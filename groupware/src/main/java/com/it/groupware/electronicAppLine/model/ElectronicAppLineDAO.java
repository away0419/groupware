package com.it.groupware.electronicAppLine.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.it.groupware.electronic.model.ElectronicDTO;

@Mapper
public interface ElectronicAppLineDAO {



	int deleteAppLine(ElectronicAppLineDTO dto);

//===================================================================================
	int insertAppLine(ElectronicAppLineDTO dto); // 결재 라인 등록
	
	List<ElectronicAppLineDTO> selectByElectronicNo(int electronicNo); //해당 문서 번호의 결재 라인 찾기

	int updateAppLine(int electronicNo, int empNo, int approvalLineCompleteFlag); //해당 결재 라인 업데이트

	List<ElectronicAppLineDTO> selectByComFalg(int electronicNo, int approvalLineCompleteFlag); //해당 문서 번호의 결재 라인 중 대기 상태인 것 찾기

	int selectForCheckExist(int electronicNo, int empNo, int approvalLineCompleteFlag); //현재 문서에 해당 사용자이면서 결재 상태가 동일 한게 있는지 확인
}
