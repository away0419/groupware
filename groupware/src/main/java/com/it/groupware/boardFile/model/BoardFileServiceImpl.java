package com.it.groupware.boardFile.model;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.it.groupware.common.ConstUtil;
import com.it.groupware.common.FileUploadUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardFileServiceImpl implements BoardFileService{
	private final BoardFileDAO boardFileDao;
	
	@Override
	public int insertFile(BoardFileDTO fileDto) {
		return boardFileDao.insertFile(fileDto);
	}

	@Override
	public List<BoardFileDTO> selectByNo(int boardNo) {
		return boardFileDao.selectByNo(boardNo);
	}

	@Override
	public BoardFileDTO selectByFileNo(int boardFileNo) {
		return boardFileDao.selectByFileNo(boardFileNo);
	}

	@Override
	public int deleteFile(int boardFileNo) {
		return boardFileDao.deleteFile(boardFileNo);
	}

	@Override
	public void insertFileAll(List<MultipartFile> fileList, BoardFileDTO fileDto, int boardNo) {
		String fileName="", originalFileName="";
		long fileSize=0;
		
		for(MultipartFile mf : fileList) {
			if(mf.getOriginalFilename() != "") {
				originalFileName = mf.getOriginalFilename();
				fileSize = mf.getSize();
				fileName = FileUploadUtil.getUniqueFileName(mf.getOriginalFilename());
				
				try {
					mf.transferTo(new File(ConstUtil.BOARD_UPLOAD_PATH_TEST+"\\"+fileName));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				
				fileDto.setBoardNo(boardNo);
				fileDto.setFileName(fileName);
				fileDto.setOriginalFileName(originalFileName);
				fileDto.setFileSize(fileSize);
				
				boardFileDao.insertFile(fileDto);
			}//if
		}//for
		
		return;
	}

}
