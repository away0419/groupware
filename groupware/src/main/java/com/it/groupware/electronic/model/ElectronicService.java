package com.it.groupware.electronic.model;

import java.util.List;
import java.util.Map;

import com.it.groupware.common.SearchVO;

public interface ElectronicService {

	
	List<ElectronicDTO> selectByEmpNo(int empNo);
	List<Map<String, Object>> selectListByEmpNo(SearchVO searchVo, String no);
	int TotalSelectListByEmpNo(SearchVO searchVo, String no);
	int updateEleReturn(int ElectronicNo);
	List<Map<String, Object>> selectTopSty(int empNo);
	int upToDate(int ElectronicNo);
	List<ElectronicDTO> selectUpdateToday(int empNo);
	//==================================================새로만듬
	Map<String,Object> sendList(Map<String,Object> map); // 보낸 결재 목록(조건) + 총 갯수(전체)
	Map<String,Object> receiveList(Map<String,Object> map); // 받은 결재 목록(조건) + 총 갯수(전체)
	Map<String,Object> readList(Map<String,Object> map); // 받은 수신용 결재 목록(조건) + 총 갯수(전체)
	List<ElectronicRecentlyDTO> selectRecentlyList(int empNo); // 최근 사용 목록
	int insertEle(ElectronicDTO dto); // 결재 등록
	int selectMaxEleNo(int empNo); // 최근 결재 번호
	int insertRecently(ElectronicRecentlyDTO dto); //최근 사용 양식 저장
	int updateEle(ElectronicDTO dto); // 결재 업데이트 (임시저장한 결재 수정 시)
	ElectronicDTO selectByElectronicNo(int electronicNo); // 해당 문서 번호 찾기
	int updateEleComplete(int electronicNo,int electronicCompletFlag); //진행 상태 수정
}
