package com.it.groupware.board.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDTO {
	private int boardNo;
	private int empNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private int boardHits;
	private Timestamp boardDate;
	private String boardDelFlag;
	private int boardTheme;
	private int boardFolderNo;
	private int commentCnt;
	private int likeCnt;
	
	private int newImgTerm;
}
