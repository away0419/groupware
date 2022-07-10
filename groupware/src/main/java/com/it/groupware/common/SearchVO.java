package com.it.groupware.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 검색 정보를 담고있는 Bean 페이징 처리 관련 변수 포함
 */

@Getter
@Setter
@ToString
public class SearchVO {
	@ApiModelProperty(value = "유저 번호", example = "")
	private String empNo;
	
	/* 검색용 시작 --------------------------------------*/
	@ApiModelProperty(value = "검색 조건", example = "")
	private String searchCondition;

	@ApiModelProperty(value = "검색 키워드", example = "")
	private String searchKeyword;
	/* 검색용 끝 ----------------------------------------*/
	
	/* 페이지용 시작-------------------------------------- */
	@ApiModelProperty(value = "현재 페이지", example = "1")
	private int currentPage = 1;

	@ApiModelProperty(value = "시작 인덱스", example = "1")
	private int firstRecordIndex = 1;
	
	@ApiModelProperty(value = "끝 인덱스", example = "1")
	private int lastRecordIndex = 1;

	@ApiModelProperty(value = "페이지 하나당 글 갯수 (pageSize)", example = "0")
	private int perPage;
	/* 페이지용 끝------------------------------------------- */
	
	/* 정렬용 시작------------------------------------------ */
	@ApiModelProperty(value = "정렬 컬럼", example = "column")
	private String sortBy;
	
	@ApiModelProperty(value = "정렬 기준", example = "true")
	private boolean sortDesc;
	/* 정렬용 끝-------------------------------------------*/
	
	

	
	@ApiModelProperty(value = "동", example = "")
	private String dong = "";

	@ApiModelProperty(value = "게시판 폴더 번호", example = "0")
	private int boardFolderNo;


	@ApiModelProperty(value = "주소록 폴더 번호", example = "")
	private int addressFolderNo;


	/**
	 * 검색 조건을 설정한다.
	 * 
	 * @param bean 검색조건
	 */
	public void setSearchVO(SearchVO bean) {
		this.currentPage = bean.currentPage;
		this.searchCondition = bean.getSearchCondition();
		this.searchKeyword = bean.getSearchKeyword();
		this.firstRecordIndex = bean.firstRecordIndex;
		this.lastRecordIndex = bean.lastRecordIndex;
		this.perPage = bean.perPage;
		this.dong = bean.dong;
		this.boardFolderNo = bean.boardFolderNo;
		this.empNo = bean.empNo;
		this.sortBy = bean.sortBy;
		this.sortDesc = bean.sortDesc;
	}

	

}
