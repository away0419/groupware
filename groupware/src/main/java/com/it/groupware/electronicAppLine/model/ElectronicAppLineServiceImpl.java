package com.it.groupware.electronicAppLine.model;

import java.util.List;

import org.springframework.stereotype.Service;

import com.it.groupware.common.CustomErrorCode;
import com.it.groupware.common.CustomException;
import com.it.groupware.electronic.model.ElectronicDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ElectronicAppLineServiceImpl implements ElectronicAppLineService{
	private final ElectronicAppLineDAO electronicAppLineDao;


	

	@Override
	public int deleteAppLine(ElectronicAppLineDTO vo) {
		return electronicAppLineDao.deleteAppLine(vo);
	}

	//현재 문서에 해당 유저가 있으며 상태가 동일한지 확인
	@Override
	public int selectForCheckExist(int electronicNo, int empNo, int approvalLineCompleteFlag){
		return electronicAppLineDao.selectForCheckExist(electronicNo, empNo, approvalLineCompleteFlag);
	}

	// 결재자 추가
	@Override 
	public int insertAppLine(ElectronicAppLineDTO dto) {
		return electronicAppLineDao.insertAppLine(dto);
	}
	
	// 해당 문서 번호의 결재 라인
	@Override
	public List<ElectronicAppLineDTO> selectByElectronicNo(int electronicNo) {
		return electronicAppLineDao.selectByElectronicNo(electronicNo);
	}
	
	//결재 라인 수정
	@Override
	public int updateAppLine(int electronicNo, int empNo, int approvalLineCompleteFlag) {
		int res =electronicAppLineDao.updateAppLine(electronicNo, empNo, approvalLineCompleteFlag);
		if(res==0) {
			throw new CustomException(CustomErrorCode.ELECTRONIC_APPLINE_UPDATE_FAIL, "해당 결재 라인 수정 실패");
		}
		
		if(approvalLineCompleteFlag==1) {
			List<ElectronicAppLineDTO> appList = electronicAppLineDao.selectByComFalg(electronicNo, 3);
			if(appList==null || appList.size()==0) {
				res=0;
			}else {
				empNo = appList.get(0).getEmpNo();
				int res2 = electronicAppLineDao.updateAppLine(electronicNo, empNo, 0);
				
				if(res2==0) {
					throw new CustomException(CustomErrorCode.ELECTRONIC_APPLINE_UPDATE_FAIL, "해당 결재 라인 수정 실패");
				}
			}
		}
		
		return res;
	}

}
