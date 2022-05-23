package com.it.groupware.scFolder.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScFolderDAO {
    public int insertDefaultScFolder(int empNo);
    public int insertScFolder(ScFolderDTO vo);
    public List<ScFolderDTO> selectAllScFolderByEmpNo(int empNo);
    public ScFolderDTO selectScFolderByScFolderNo(int scheduleFolderNo);
    public int deleteScFolderByScFolderNo(int scheduleFolderNo);
    public int updateScFolderByScFolderNo(ScFolderDTO vo);
}
