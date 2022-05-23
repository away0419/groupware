package com.it.groupware.booking.model;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookingDAO {
	List<BookingDTO> selectAllBookingByEmpNo(int empNo);
	List<BookingDTO> selectAllBookingByAppFlag(int currentPage);
	List<BookingDTO> selectAllBookingViewByEmpNo(int emoNo);
	int insertBooking(BookingDTO vo);
	int updateBokkingByBookingNo(HashMap<String, Object> map);
	int selectCntAllByAppFlag();
}
