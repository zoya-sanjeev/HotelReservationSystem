package com.bridgelabz.hotelreservationsystem;

public class HotelReservationException extends RuntimeException {
		
	enum ExceptionType{
		DATE_NULL,
		DATE_INVALID,
		CUSTOMER_TYPE_NULL,
		CUTOMER_TYPE_INVALID
	}
	
	ExceptionType type;
	
	public HotelReservationException(String message, ExceptionType type) {
		super(message);
		this.type=type;
	}
}
