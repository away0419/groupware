package com.it.groupware.common;

import io.swagger.annotations.ApiModelProperty;

/**
 * 검색 정보를 담고있는 Bean
 * 페이징 처리 관련 변수 포함
*/

public class SearchVO {
	@ApiModelProperty(value = "검색 조건", example = "")
	private String searchCondition;
	
	@ApiModelProperty(value = "검색 키워드", example = "")
	private String searchKeyword;
    
	@ApiModelProperty(value = "검색 사용여부", example = "")
	private String searchUseYn;
	
	@ApiModelProperty(value = "현재 페이지", example = "1")
	private int currentPage = 1;
	
	@ApiModelProperty(value = "동", example = "")
	private String dong = "";
	
	@ApiModelProperty(value = "게시판 폴더 번호", example = "0")
	private int boardFolderNo;
	
	@ApiModelProperty(value = "유저 번호", example = "")
//	@ApiParam(value = "전자결재 결재문서의 유저 번호") 원래 String
	private String empNo;
	
	@ApiModelProperty(value = "주소록 폴더 번호", example = "")
	private int addressFolderNo;
	
	@ApiModelProperty(value = "블럭당 보여질 페이지 수,  페이지 사이즈", example = "0")
    private int blockSize; // properties에서 설정
    
	@ApiModelProperty(value = "시작 인덱스", example = "1")
    private int firstRecordIndex = 1;
    
	@ApiModelProperty(value = "끝 인덱스", example = "1")
    private int lastRecordIndex = 1;
    
	@ApiModelProperty(value = "페이지 별 레코드 갯수 (pageSize)", example = "0")
    private int recordCountPerPage; 
    
   /**
     * 검색 조건을  설정한다.
     * @param bean 검색조건
     */
    public void setSearchVO(SearchVO bean){
    	this.currentPage = bean.currentPage;
    	this.searchCondition = bean.getSearchCondition();
    	this.searchKeyword = bean.getSearchKeyword();
    	this.searchUseYn = bean.getSearchUseYn();
    	this.blockSize = bean.blockSize;
    	this.firstRecordIndex = bean.firstRecordIndex;
    	this.lastRecordIndex = bean.lastRecordIndex;
    	this.recordCountPerPage = bean.recordCountPerPage;
    	this.dong=bean.dong;
    	this.boardFolderNo=bean.boardFolderNo;
    	this.empNo=bean.empNo;
    }

    public String getSearchCondition() {
    	return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
    	this.searchCondition = searchCondition;
    }

    public String getSearchKeyword() {
    	return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
    	this.searchKeyword = searchKeyword;
    }

    public String getSearchUseYn() {
    	return searchUseYn;
    }

    public void setSearchUseYn(String searchUseYn) {
    	this.searchUseYn = searchUseYn;
    }

    public int getCurrentPage() {
    	return currentPage;
    }

    public void setCurrentPage(int currentPage) {
    	this.currentPage = currentPage;
    }

    public int getBlockSize() {
    	return blockSize;
    }

    public void setBlockSize(int blockSize) {
    	this.blockSize = blockSize;
    }

    public int getFirstRecordIndex() {
    	return firstRecordIndex;
    }

    public void setFirstRecordIndex(int firstRecordIndex) {
    	this.firstRecordIndex = firstRecordIndex;
    }

    public int getLastRecordIndex() {
    	return lastRecordIndex;
    }

    public void setLastRecordIndex(int lastRecordIndex) {
    	this.lastRecordIndex = lastRecordIndex;
    }

    
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public int getBoardFolderNo() {
		return boardFolderNo;
	}

	public void setBoardFolderNo(int boardFolderNo) {
		this.boardFolderNo = boardFolderNo;
	}
	

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public int getAddressFolderNo() {
		return addressFolderNo;
	}

	public void setAddressFolderNo(int addressFolderNo) {
		this.addressFolderNo = addressFolderNo;
	}

	@Override
	public String toString() {
		return "SearchVO [searchCondition=" + searchCondition + ", searchKeyword=" + searchKeyword + ", searchUseYn="
				+ searchUseYn + ", currentPage=" + currentPage + ", dong=" + dong + ", boardFolderNo=" + boardFolderNo
				+ ", empNo=" + empNo + ", addressFolderNo=" + addressFolderNo + ", blockSize=" + blockSize
				+ ", firstRecordIndex=" + firstRecordIndex + ", lastRecordIndex=" + lastRecordIndex
				+ ", recordCountPerPage=" + recordCountPerPage + "]";
	}

}
 