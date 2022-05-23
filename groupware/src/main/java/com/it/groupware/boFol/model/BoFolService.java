package com.it.groupware.boFol.model;

import java.util.List;

public interface BoFolService {
	List<BoFolDTO> selectAllBoFol(); 
	int insertBoFol(BoFolDTO vo);
	int updateBoFol(BoFolDTO vo);
	int deleteBoFol(int bookingFolderNo);
}
