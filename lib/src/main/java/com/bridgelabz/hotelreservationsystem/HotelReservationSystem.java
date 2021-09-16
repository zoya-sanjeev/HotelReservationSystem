package com.bridgelabz.hotelreservationsystem;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.bridgelabz.hotelreservationsystem.HotelReservationException.ExceptionType;


public class HotelReservationSystem {
	
	ArrayList<Hotel> listOfHotels;
	
	public HotelReservationSystem() {
		listOfHotels=new ArrayList<>();
	}
		
	public Hotel createHotel() {
		Hotel newHotel = new Hotel("Lakewood", 120.0, 90.0, 80.0, 80.0, 3);
		return newHotel;
		
	}
	
	public static boolean validateDate(String date) throws HotelReservationException {
		try {
			String dateRegex="^([0-9]{4})[\\-]((0[1-9])|1[012])[\\-]([012][0-9]|[3][01])$";
			if(Pattern.matches(dateRegex, date))
				return true;
			else
				throw new HotelReservationException("Enter date in proper format", ExceptionType.DATE_INVALID);
		}catch(NullPointerException e) {
			throw new HotelReservationException("Enter date", ExceptionType.DATE_NULL); 
		}
		
	}
	
	public static boolean validateCustomerType(String customerType) {
		String customerTypeRegex="^(REGULAR_CUSTOMER)|(REWARDS_CUSTOMER)$";
		return Pattern.matches(customerTypeRegex, customerType);
	}
	
	public boolean addHotel(Hotel newHotel) {		
		boolean added=listOfHotels.add(newHotel);
		return added;
	}
	
	public List<Hotel> findCheapestHotel(LocalDate startDate, LocalDate endDate, CustomerType type) {
		int noOfDays=startDate.compareTo(endDate);
		
		final int weekdayCount=weekdayCounter(startDate, endDate);
		final int weekendCount=weekendCounter(startDate, endDate);
		
		int cheapestPrice;
		List<Hotel> cheapestHotels;
		if(type==CustomerType.REGULAR_CUSTOMER ) {
			cheapestPrice=listOfHotels.stream()
						.mapToInt(hotel -> (int)(hotel.getWeekdayRates()*weekdayCount) + (int)(hotel.getWeekendRates()*weekendCount))
						.min().orElse(Integer.MAX_VALUE);
			cheapestHotels=listOfHotels.stream()
					.filter(hotel -> hotel.getWeekdayRates()*weekdayCount +(hotel.getWeekendRates()*weekendCount) == cheapestPrice)
					.collect(Collectors.toList());
		}
		else {
			cheapestPrice=listOfHotels.stream()
					.mapToInt(hotel -> (int)(hotel.getWeekdayRewardRates()*weekdayCount) + (int)(hotel.getWeekendRewardRates()*weekendCount))
					.min().orElse(Integer.MAX_VALUE);
			
			cheapestHotels=listOfHotels.stream()
					.filter(hotel -> hotel.getWeekdayRewardRates()*weekdayCount +(hotel.getWeekendRewardRates()*weekendCount) == cheapestPrice)
					.collect(Collectors.toList());
		}
		
		
		return cheapestHotels;
	}
	
	public Hotel findBestCheapHotel(LocalDate startDate, LocalDate endDate, CustomerType type) {
		
		List<Hotel> cheapestHotels= findCheapestHotel(startDate, endDate, type);
		
		Hotel bestHotel=cheapestHotels.stream()
						.sorted((hotel1, hotel2) -> String.valueOf(hotel2.getRating()).compareTo(String.valueOf(hotel1.getRating())))
						.findFirst()
						.orElse(null);
		
		return bestHotel;
	}
	
	public Hotel findBestHotel(LocalDate startDate, LocalDate endDate, CustomerType type) {
		int noOfDays=startDate.compareTo(endDate);
		int weekdayCount=weekdayCounter(startDate, endDate);
		int weekendCount=weekendCounter(startDate, endDate);
		
		Hotel bestHotel=listOfHotels.stream()
				.sorted((hotel1, hotel2) -> String.valueOf(hotel2.getRating()).compareTo(String.valueOf(hotel1.getRating())))
				.findFirst()
				.orElse(null);
		
		int price;
		if(type==CustomerType.REGULAR_CUSTOMER)
			price=(int)bestHotel.getWeekdayRates()*weekdayCount +(int) bestHotel.getWeekendRates()*weekendCount;
		else
			price=(int)bestHotel.getWeekdayRewardRates()*weekdayCount +(int) bestHotel.getWeekendRewardRates()*weekendCount;
		
		return bestHotel;
	}
	
	public int weekendCounter(LocalDate startDate, LocalDate endDate) {
		int weekendCounter=0;
		for(LocalDate dateCounter=startDate; dateCounter.isBefore(endDate); dateCounter=dateCounter.plusDays(1) ) 
			if(dateCounter.getDayOfWeek()==DayOfWeek.SATURDAY || dateCounter.getDayOfWeek()==DayOfWeek.SUNDAY)
				weekendCounter++;
		
		if(endDate.getDayOfWeek()==DayOfWeek.SATURDAY ||endDate.getDayOfWeek()==DayOfWeek.SUNDAY)
			weekendCounter++;
		
		return weekendCounter;
	}
	
	public int weekdayCounter(LocalDate startDate, LocalDate endDate) {
		int weekdayCounter=0;
		for(LocalDate dateCounter=startDate; dateCounter.isBefore(endDate); dateCounter=dateCounter.plusDays(1) ) 
			if(dateCounter.getDayOfWeek()!=DayOfWeek.SATURDAY && dateCounter.getDayOfWeek()!=DayOfWeek.SUNDAY)
				weekdayCounter++;
		
		if(endDate.getDayOfWeek()!=DayOfWeek.SATURDAY && endDate.getDayOfWeek()!=DayOfWeek.SUNDAY)
			weekdayCounter++;
		
		return weekdayCounter;
	}
}
