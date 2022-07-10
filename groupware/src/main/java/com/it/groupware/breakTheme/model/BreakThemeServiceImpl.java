package com.it.groupware.breakTheme.model;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BreakThemeServiceImpl implements BreakThemeService{

	private final BreakThemeDAO themeDao;
	
	@Override
	public List<BreakThemeDTO> selectAll(){
		
		return themeDao.selectAll();
	}

}
