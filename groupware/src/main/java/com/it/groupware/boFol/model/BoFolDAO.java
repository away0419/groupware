package com.it.groupware.boFol.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.it.groupware.boTarget.model.BoTargetDTO;

@Mapper
public interface BoFolDAO {
	List<BoFolDTO> selectAllBoFol(); 
	int insertBoFol(BoFolDTO vo);
	int updateBoFol(BoFolDTO vo);
	int deleteBoFol(int bookingFolderNo);
}
