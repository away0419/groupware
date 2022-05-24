package com.it.groupware.board.model;

import java.sql.Timestamp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDTO {
	@ApiModelProperty(value = "게시글 번호", example = "0")
	private int boardNo;
	
	@ApiModelProperty(value = "사원 번호", example = "0")
	private int empNo;
	
	@ApiModelProperty(value = "제목", example = "제목")
	private String boardTitle;
	
	@ApiModelProperty(value = "내용", example = "내용")
	private String boardContent;
	
	@ApiModelProperty(value = "글쓴이", example = "글쓴이")
	private String boardWriter;
	
	@ApiModelProperty(value = "조회수", example = "0")
	private int boardHits;
	
	@ApiModelProperty(value = "작성일", example = "2022-05-05T12:00:00")
	private Timestamp boardDate;
	
	@ApiModelProperty(value = "게시글 삭제 여부", example = "0")
	private String boardDelFlag;
	
	@ApiModelProperty(value = "게시판 이름", example = "공지")
	private int boardTheme;
	
	@ApiModelProperty(value = "게시판 폴더 번호", example = "0")
	private int boardFolderNo;
	
	@ApiModelProperty(value = "댓글 갯수", example = "0")
	private int commentCnt;
	
	@ApiModelProperty(value = "좋아요 갯수", example = "0")
	private int likeCnt;
	
	@ApiModelProperty(value = "뭐지", example = "0")
	private int newImgTerm;
}
