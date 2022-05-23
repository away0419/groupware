package com.it.groupware.electronicReLine.model;

import java.util.List;

import org.springframework.stereotype.Service;

import com.it.groupware.electronic.model.ElectronicDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ElectronicReLineServiceImpl implements ElectronicReLineService{
	private final ElectronicReLineDAO electronicReLineDao;

	@Override
	public int insertReLine(ElectronicReLineDTO vo) {
		return electronicReLineDao.insertReLine(vo);
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

	@Override
	public int selectForCheckExist(ElectronicReLineDTO vo) {
		return electronicReLineDao.selectForCheckExist(vo);
	}

}
