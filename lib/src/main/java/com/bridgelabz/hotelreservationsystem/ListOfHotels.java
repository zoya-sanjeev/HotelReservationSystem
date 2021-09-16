package com.bridgelabz.hotelreservationsystem;

import java.util.LinkedList;

public class ListOfHotels {
	
	LinkedList<Hotel> listOfHotels= new LinkedList<>();
	
	public void addHotel() {
		Hotel newHotel = new Hotel("Lakewood", 3000.0,5000.0);
		listOfHotels.add(newHotel);
		
	}

}
