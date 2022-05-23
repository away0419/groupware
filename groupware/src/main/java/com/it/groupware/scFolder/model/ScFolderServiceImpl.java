package com.it.groupware.scFolder.model;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScFolderServiceImpl implements ScFolderService{
    private final ScFolderDAO dao;

    @Override
    public int insertDefaultScFolder(int empNo) {
        return dao.insertDefaultScFolder(empNo);
    }

    @Override
    public List<ScFolderDTO> selectAllScFolderByEmpNo(int empNo) {
        return dao.selectAllScFolderByEmpNo(empNo);
    }

	@Override
	public ScFolderDTO selectScFolderByScFolderNo(int scheduleFolderNo) {
		return dao.selectScFolderByScFolderNo(scheduleFolderNo);
	}

	@Override
	public int insertScFolder(ScFolderDTO vo) {
		return dao.insertScFolder(vo);
	}

	@Override
	public int deleteScFolderByScFolderNo(int scheduleFolderNo) {
		return dao.deleteScFolderByScFolderNo(scheduleFolderNo);
	}

	@Override
	public int updateScFolderByScFolderNo(ScFolderDTO vo) {
		return dao.updateScFolderByScFolderNo(vo);
	}



    
    
}