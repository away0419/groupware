package com.it.groupware.electronicReLine.model;

import java.util.List;

import org.springframework.stereotype.Service;

import com.it.groupware.electronic.model.ElectronicDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ElectronicReLineServiceImpl implements ElectronicReLineService{
	private final ElectronicReLineDAO electronicReLineDao;

	// 수신 라인 추가
	@Override
	public int insertReLine(ElectronicReLineDTO dto) {
		return electronicReLineDao.insertReLine(dto);
	}
	
	//수신자 확인
	@Override
	public int selectForCheckExist(int electronicNo, int empNo) {
		return electronicReLineDao.selectForCheckExist(electronicNo, empNo);
	}

	@Override
	public List<ElectronicReLineDTO> selectByElectronicNo(int ElectronicNo) {
		return electronicReLineDao.selectByElectronicNo(ElectronicNo);
	}

	@Override
	public int AcceptUpdateReLine(ElectronicDTO vo) {
		return electronicReLineDao.AcceptUpdateReLine(vo);
	}

	@Override
	public int deleteReLine(ElectronicReLineDTO vo) {
		return electronicReLineDao.deleteReLine(vo);
	}


}
