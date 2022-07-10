package com.it.groupware.common;

public class PaginationInfo {
	/**
	 * Required Fields
	  	currentPage : 현재 페이지
		recordCountPerPage : 페이지당 보여질 레코드수
		totalRecord : totalRecord 총 레코드 수
	 * */
	private int currentPage; //현재 페이지
	private int recordCountPerPage;  //pageSize 페이지당 보여질 레코드수
	private int totalRecord; //총 레코드 수
	
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	
	public int getTotalRecord() {
		return totalRecord;
	}
	
	/**
	 * Not Required Fields
	 * 
	 */	
	private int totalPage;  //총 페이지수
	private int firstRecordIndex; //페이지당 시작 인덱스 0, 5, 10, 15 ...
	private int lastRecordIndex;  //페이지당 마지막 인덱스	5,10,15,20....
	  
	public int getTotalPage() {
		totalPage=(int)Math.ceil((float)totalRecord/recordCountPerPage);
		//totalPage = ((getTotalRecord()-1)/getRecordCountPerPage()) + 1;
		
		return totalPage;
	}
		
	public int getFirstRecordIndex() {
		//curPos=(currentPage-1)*pageSize;
		firstRecordIndex = (getCurrentPage() - 1) * getRecordCountPerPage();
		return firstRecordIndex;
	}

	public int getLastRecordIndex() {
		lastRecordIndex = getCurrentPage() * getRecordCountPerPage();
		return lastRecordIndex;
	}	
}
