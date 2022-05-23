package com.it.groupware.position.model;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService{
	private final PositionDAO positionDao;

	@Override
	public List<PositionDTO> selectAllPosition() {
		return positionDao.selectAllPosition();
	}
	
}
