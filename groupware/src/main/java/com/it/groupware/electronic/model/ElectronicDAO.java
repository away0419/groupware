package com.it.groupware.electronic.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.it.groupware.common.SearchVO;

@Mapper
public interface ElectronicDAO {

	

	

	List<ElectronicDTO> selectByEmpNo(int empNo);

	List<Map<String, Object>> selectListByEmpNo1(SearchVO searchVo);

	List<Map<String, Object>> selectListByEmpNo3(SearchVO searchVo);

	List<Map<String, Object>> selectListByEmpNo6(SearchVO searchVo);

	List<Map<String, Object>> selectListByEmpNo2(SearchVO searchVo);

	List<Map<String, Object>> selectListByEmpNo4(SearchVO searchVo);

	List<Map<String, Object>> selectListByEmpNo5(SearchVO searchVo);

	List<Map<String, Object>> selectListByEmpNo7(SearchVO searchVo);

	int TotalSelectListByEmpNo1(SearchVO searchVo);

	int TotalSelectListByEmpNo2(SearchVO searchVo);

	int TotalSelectListByEmpNo3(SearchVO searchVo);

	int TotalSelectListByEmpNo4(SearchVO searchVo);

	int TotalSelectListByEmpNo5(SearchVO searchVo);

	int TotalSelectListByEmpNo6(SearchVO searchVo);

	int TotalSelectListByEmpNo7(SearchVO searchVo);

	

	int updateEleReturn(int ElectronicNo);


	List<Map<String, Object>> selectTopSty(int empNo);

	int upToDate(int ElectronicNo);

	List<ElectronicDTO> selectUpdateToday(int empNo);
	// =====================================================================새로만듬

	List<ElectronicDTO> selectSendList(Map<String, Object> map); // 보낸 결재 목록

	int selectSendListCnt(Map<String, Object> map); // 보낸 결재 목록 전체 갯수

	List<ElectronicDTO> selectReceiveList(Map<String, Object> map); // 받은 결재 목록

	int selectReceiveListCnt(Map<String, Object> map); // 받은 결재 목록 전체 갯수
	
	List<ElectronicDTO> selectReadList(Map<String, Object> map); // 받은 수신용 결재 목록
	
	int selectReadListCnt(Map<String, Object> map); // 받은 수신용 결재 목록 전체 갯수

	List<ElectronicRecentlyDTO> selectRecentlyList(int empNo); // 최근 사용 양식 목록

	int insertEle(ElectronicDTO dto); // 결재 등록
	
	int selectMaxEleNo(int empNo); // 최근 만든 결재 번호
	
	int insertRecently(ElectronicRecentlyDTO dto); //최근 사용 양식 저장
	
	int updateEle(ElectronicDTO dto); // 결재 업데이트 (임시저장한 결재 수정 시)
	
	ElectronicDTO selectByElectronicNo(int electronicNo); // 해당 번호의 문서 찾기
	
	int updateEleComplete(int electronicNo,int electronicCompletFlag); //진행 상태 수정
}
