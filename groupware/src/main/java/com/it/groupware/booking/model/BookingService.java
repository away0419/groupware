package com.it.groupware.booking.model;

import java.util.HashMap;
import java.util.List;

public interface BookingService {
	List<BookingDTO> selectAllBookingByEmpNo(int empNo);
	List<BookingDTO> selectAllBookingByAppFlag(int currentPage);
	int insertBooking(BookingDTO vo);
	int updateBokkingByBookingNo(HashMap<String, Object> map);
	int selectCntAllByAppFlag();
	List<BookingDTO> selectAllBookingViewByEmpNo(int emoNo);


}
