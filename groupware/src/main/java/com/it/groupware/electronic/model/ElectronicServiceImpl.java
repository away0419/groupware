package com.it.groupware.electronic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.stereotype.Service;

import com.it.groupware.common.PaginationInfo;
import com.it.groupware.common.SearchVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ElectronicServiceImpl implements ElectronicService {
	private final ElectronicDAO electronicDao;


	@Override
	public List<ElectronicDTO> selectByEmpNo(int empNo) {
		return electronicDao.selectByEmpNo(empNo);
	}

	@Override
	public List<Map<String, Object>> selectListByEmpNo(SearchVO searchVo, String no) {
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(no.equals("1")) {
			list = electronicDao.selectListByEmpNo1(searchVo);
		}else if(no.equals("2")) {
			list = electronicDao.selectListByEmpNo2(searchVo);
		}else if(no.equals("3")) {
			list = electronicDao.selectListByEmpNo3(searchVo);
		}else if(no.equals("4")) {
			list = electronicDao.selectListByEmpNo4(searchVo);
		}else if(no.equals("5")) {
			list = electronicDao.selectListByEmpNo5(searchVo);
		}else if(no.equals("6")) {
			list = electronicDao.selectListByEmpNo6(searchVo);
		}else if(no.equals("7")) {
			list = electronicDao.selectListByEmpNo7(searchVo);
		}
		
		return list;
		
	}
	
	@Override
	public int TotalSelectListByEmpNo(SearchVO searchVo, String no) {
		
		int cnt = 0;
		if(no.equals("1")) {
			cnt = electronicDao.TotalSelectListByEmpNo1(searchVo);
		}else if(no.equals("2")) {
			cnt = electronicDao.TotalSelectListByEmpNo2(searchVo);
		}else if(no.equals("3")) {
			cnt = electronicDao.TotalSelectListByEmpNo3(searchVo);
		}else if(no.equals("4")) {
			cnt = electronicDao.TotalSelectListByEmpNo4(searchVo);
		}else if(no.equals("5")) {
			cnt = electronicDao.TotalSelectListByEmpNo5(searchVo);
		}else if(no.equals("6")) {
			cnt = electronicDao.TotalSelectListByEmpNo6(searchVo);
		}else if(no.equals("7")) {
			cnt = electronicDao.TotalSelectListByEmpNo7(searchVo);
		}
		
		return cnt;
		
	}

	

	@Override
	public int updateEleReturn(int ElectronicNo) {
		return electronicDao.updateEleReturn(ElectronicNo);
	}



	@Override
	public int upToDate(int ElectronicNo) {
		return electronicDao.upToDate(ElectronicNo);
	}

	@Override
	public List<ElectronicDTO> selectUpdateToday(int empNo) {
		return electronicDao.selectUpdateToday(empNo);
	}

	@Override
	public List<Map<String, Object>> selectTopSty(int empNo) {
		return electronicDao.selectTopSty(empNo);
	}

	//============================================================새로만듬
	//보낸 결재 목록과 총 레코드 수
	@Override
	public Map<String,Object> sendList(Map<String, Object> map) {
		SearchVO searchVo = (SearchVO)map.get("searchVo");
		int currentPage = searchVo.getCurrentPage();
		int perPage = searchVo.getPerPage();
		
		int totalRecord = electronicDao.selectSendListCnt(map);
		PaginationInfo pageInfo = new PaginationInfo();
		pageInfo.setCurrentPage(currentPage);
		pageInfo.setRecordCountPerPage(perPage);
		pageInfo.setTotalRecord(totalRecord);
		int firstRecordIndex = pageInfo.getFirstRecordIndex();
		searchVo.setFirstRecordIndex(firstRecordIndex);
		
		List<ElectronicDTO> list = electronicDao.selectSendList(map);
		Map<String,Object> data = new HashedMap<String, Object>();
		data.put("list", list);
		data.put("totalRecord", totalRecord);
		return data; 
	}
	
	//받은 결재 목록과 총 레코드수
	@Override
	public Map<String, Object> receiveList(Map<String, Object> map) {
		SearchVO searchVo = (SearchVO)map.get("searchVo");
		int currentPage = searchVo.getCurrentPage();
		int perPage = searchVo.getPerPage();
		
		int totalRecord = electronicDao.selectReceiveListCnt(map);
		PaginationInfo pageInfo = new PaginationInfo();
		pageInfo.setCurrentPage(currentPage);
		pageInfo.setRecordCountPerPage(perPage);
		pageInfo.setTotalRecord(totalRecord);
		int firstRecordIndex = pageInfo.getFirstRecordIndex();
		searchVo.setFirstRecordIndex(firstRecordIndex);
		
		List<ElectronicDTO> list = electronicDao.selectReceiveList(map);
		Map<String,Object> data = new HashedMap<String, Object>();
		data.put("list", list);
		data.put("totalRecord", totalRecord);
		return data;
	}
	
	//받은 수신용 결재 목록과 총 레코드 수
	@Override
	public Map<String, Object> readList(Map<String, Object> map) {
		SearchVO searchVo = (SearchVO)map.get("searchVo");
		int currentPage = searchVo.getCurrentPage();
		int perPage = searchVo.getPerPage();
		
		int totalRecord = electronicDao.selectReadListCnt(map);
		PaginationInfo pageInfo = new PaginationInfo();
		pageInfo.setCurrentPage(currentPage);
		pageInfo.setRecordCountPerPage(perPage);
		pageInfo.setTotalRecord(totalRecord);
		int firstRecordIndex = pageInfo.getFirstRecordIndex();
		searchVo.setFirstRecordIndex(firstRecordIndex);
		
		List<ElectronicDTO> list = electronicDao.selectReadList(map);
		Map<String,Object> data = new HashedMap<String, Object>();
		data.put("list", list);
		data.put("totalRecord", totalRecord);
		return data;
	}
	
	//최근 사용 양식
	@Override
	public List<ElectronicRecentlyDTO> selectRecentlyList(int empNo) {
		List<ElectronicRecentlyDTO> list = electronicDao.selectRecentlyList(empNo);
		return list;
	}
	
	//결재 등록
	@Override
	public int insertEle(ElectronicDTO dto) {
		return electronicDao.insertEle(dto);
	}
	
	//최근 만든 결재 번호
	@Override
	public int selectMaxEleNo(int empNo) {
		return electronicDao.selectMaxEleNo(empNo);
	}

	//최근 사용 양식 추가
	@Override
	public int insertRecently(ElectronicRecentlyDTO dto) {
		return electronicDao.insertRecently(dto);
	}

	//결재 수정
	@Override
	public int updateEle(ElectronicDTO dto) {
		return electronicDao.updateEle(dto);
	}
	
	//해당 번호 문서 찾기
	@Override
	public ElectronicDTO selectByElectronicNo(int electronicNo) {
		return electronicDao.selectByElectronicNo(electronicNo);
	}
	
	//해당 번호 문서의 진행 상태 수정
	@Override
	public int updateEleComplete(int electronicNo,int electronicCompletFlag) {
		return electronicDao.updateEleComplete(electronicNo,electronicCompletFlag);
	}
	
}
