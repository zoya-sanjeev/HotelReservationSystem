package com.bridgelabz.hotelreservationsystem;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
		Hotel newHotel = new Hotel("Lakewood", 120.0, 90.0,3);
		return newHotel;
		
	}
	
	public boolean addHotel(Hotel newHotel) {		
		boolean added=listOfHotels.add(newHotel);
		return added;
	}
	
	public List<Hotel> findCheapestHotel(LocalDate startDate, LocalDate endDate) {
		int noOfDays=startDate.compareTo(endDate);
		int weekdayCounter=0;
		int weekendCounter=0;
		
		for(LocalDate dateCounter=startDate; startDate.isBefore(endDate); dateCounter.plusDays(1) ) {
			if(dateCounter.getDayOfWeek()==DayOfWeek.SATURDAY || dateCounter.getDayOfWeek()==DayOfWeek.SUNDAY)
				weekendCounter++;
			else
				weekdayCounter++;
		}
		
		final int weekdayCount=weekdayCounter;
		final int weekendCount=weekendCounter;
		int cheapestPrice=listOfHotels.stream()
						.mapToInt(hotel -> (int)(hotel.getWeekdayRates()*weekdayCount) + (int)(hotel.getWeekendRates()*weekendCount))
						.min().orElse(Integer.MAX_VALUE);
		
		List<Hotel> cheapestHotels=listOfHotels.stream()
									.filter(hotel -> hotel.getWeekdayRates()*weekdayCount +(hotel.getWeekendRates()*weekendCount) == cheapestPrice)
									.collect(Collectors.toList());
		return cheapestHotels;
	}

}
