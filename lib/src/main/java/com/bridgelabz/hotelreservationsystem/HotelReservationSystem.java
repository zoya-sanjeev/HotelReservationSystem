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
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class HotelReservationSystem {
	
	ArrayList<Hotel> listOfHotels;
	
	public HotelReservationSystem() {
		listOfHotels=new ArrayList<>();
	}
		
	public Hotel createHotel() {
		Hotel newHotel = new Hotel("Lakewood", 120.0, 90.0, 80.0, 80.0, 3);
		return newHotel;
		
	}
	
	public boolean addHotel(Hotel newHotel) {		
		boolean added=listOfHotels.add(newHotel);
		return added;
	}
	
	public List<Hotel> findCheapestHotel(LocalDate startDate, LocalDate endDate) {
		int noOfDays=startDate.compareTo(endDate);
		
		final int weekdayCount=weekdayCounter(startDate, endDate);
		final int weekendCount=weekendCounter(startDate, endDate);
		
		int cheapestPrice=listOfHotels.stream()
						.mapToInt(hotel -> (int)(hotel.getWeekdayRates()*weekdayCount) + (int)(hotel.getWeekendRates()*weekendCount))
						.min().orElse(Integer.MAX_VALUE);
		
		List<Hotel> cheapestHotels=listOfHotels.stream()
									.filter(hotel -> hotel.getWeekdayRates()*weekdayCount +(hotel.getWeekendRates()*weekendCount) == cheapestPrice)
									.collect(Collectors.toList());
		return cheapestHotels;
	}
	
	public Hotel findBestCheapHotel(LocalDate startDate, LocalDate endDate) {
		
		List<Hotel> cheapestHotels= findCheapestHotel(startDate, endDate);
		
		Hotel bestHotel=cheapestHotels.stream()
						.sorted((hotel1, hotel2) -> String.valueOf(hotel2.getRating()).compareTo(String.valueOf(hotel1.getRating())))
						.findFirst()
						.orElse(null);
		
		return bestHotel;
	}
	
	public Hotel findBestHotel(LocalDate startDate, LocalDate endDate) {
		int noOfDays=startDate.compareTo(endDate);
		int weekdayCount=weekdayCounter(startDate, endDate);
		int weekendCount=weekendCounter(startDate, endDate);
		
		Hotel bestHotel=listOfHotels.stream()
				.sorted((hotel1, hotel2) -> String.valueOf(hotel2.getRating()).compareTo(String.valueOf(hotel1.getRating())))
				.findFirst()
				.orElse(null);
		
		int price=(int)bestHotel.getWeekdayRates()*weekdayCount +(int) bestHotel.getWeekendRates()*weekendCount;
		
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
