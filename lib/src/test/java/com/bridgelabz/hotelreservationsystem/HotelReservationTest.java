/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.bridgelabz.hotelreservationsystem;

import org.junit.Test;

import com.bridgelabz.hotelreservationsystem.Hotel;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;

public class HotelReservationTest {
    
	@Test public void addHotel_correctDetailsOfHotel_True(){
    	HotelReservationSystem listOfHotels=new HotelReservationSystem();
    	Hotel hotel=new Hotel("Lakewood", 110.0, 90.0, 3);
    	boolean added=listOfHotels.addHotel(hotel);
    	Assert.assertEquals(added,true);
    	
    }
    
    @Test public void findCheapestHotel_differentHotelsWithDifferentRates_CheapestHotel() {
    	HotelReservationSystem listOfHotels=new HotelReservationSystem();
    	
    	Hotel hotel1=new Hotel("Lakewood", 110.0, 90.0, 3);
    	Hotel hotel2=new Hotel("Bridgewood", 150.0, 50.0, 4);
    	Hotel hotel3=new Hotel("Ridgewood", 220.0, 150.0, 5);
    	
    	listOfHotels.addHotel(hotel1);
    	listOfHotels.addHotel(hotel2);
    	listOfHotels.addHotel(hotel3);
    	
    	LocalDate date1=LocalDate.of(2020, Month.SEPTEMBER, 11);
    	LocalDate date2=LocalDate.of(2020, Month.SEPTEMBER, 12);
    	
    	List<Hotel> cheapestHotels=listOfHotels.findCheapestHotel(date1,date2);	
    	
    	//Assert.assertEquals("Lakewood",cheapestHotels.get(0).getNameOfHotel());
    	Assert.assertEquals("Ridgewood",cheapestHotels.get(1).getNameOfHotel());  		
    	
    }
}
