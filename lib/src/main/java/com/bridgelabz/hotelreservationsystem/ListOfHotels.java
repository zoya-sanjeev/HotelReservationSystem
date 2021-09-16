package com.bridgelabz.hotelreservationsystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;

public class ListOfHotels {
	
	ArrayList<Hotel> listOfHotels= new ArrayList<>();
	
	public Hotel createHotel() {
		Hotel newHotel = new Hotel("Lakewood", 120.0);
		return newHotel;
		
	}
	
	public boolean addHotel(Hotel newHotel) {		
		boolean added=listOfHotels.add(newHotel);
		return added;
	}
	
	public Hotel findCheapestHotel(Date startDate, Date endDate) {
		double noOfDays=endDate.compareTo(startDate);
		double min=Double.MAX_VALUE;
		Hotel cheapest=null;
		cheapest=listOfHotels.stream().min((hotel1, hotel2) -> (int)hotel1.getRates()-(int)hotel2.getRates()).orElse(null);
	
		return cheapest;
	}

}
