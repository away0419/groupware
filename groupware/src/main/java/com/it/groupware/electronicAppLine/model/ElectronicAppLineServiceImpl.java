package com.it.groupware.electronicAppLine.model;

import java.util.List;

import org.springframework.stereotype.Service;

import com.it.groupware.electronic.model.ElectronicDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ElectronicAppLineServiceImpl implements ElectronicAppLineService{
	private final ElectronicAppLineDAO electronicAppLineDao;

	@Override
	public int insertAppLine(ElectronicAppLineDTO vo) {
		return electronicAppLineDao.insertAppLine(vo);
	}

	@Override
	public List<ElectronicAppLineDTO> selectByElectronicNo(int ElectronicNo) {
		return electronicAppLineDao.selectByElectronicNo(ElectronicNo);
	}

	@Override
	public int AcceptUpdateAppLine(ElectronicDTO vo) {
		return electronicAppLineDao.AcceptUpdateAppLine(vo);
	}

	@Override
	public int deleteAppLine(ElectronicAppLineDTO vo) {
		return electronicAppLineDao.deleteAppLine(vo);
	}

	@Override
	public int selectForCheckExist(ElectronicAppLineDTO vo) {
		return electronicAppLineDao.selectForCheckExist(vo);
	}

	@Override
	public ElectronicAppLineDTO selectAppLineCheck(ElectronicDTO vo) {
		return electronicAppLineDao.selectAppLineCheck(vo);
	}

}
