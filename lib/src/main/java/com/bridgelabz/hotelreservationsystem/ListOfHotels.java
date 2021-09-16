package com.bridgelabz.hotelreservationsystem;

import java.util.ArrayList;
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

}
