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
import org.junit.Before;

public class HotelReservationTest {
	static HotelReservationSystem listOfHotels=new HotelReservationSystem();
	static Hotel hotel1;
	static Hotel hotel2;
	static Hotel hotel3;
	@Before
	public void initialize() {
			
		hotel1=new Hotel("Lakewood", 110.0, 90.0, 80.0, 80.0, 3);
		hotel2=new Hotel("Bridgewood", 150.0, 50.0, 110, 50, 4);
		hotel3=new Hotel("Ridgewood", 220.0, 150.0, 100, 40, 5);
		
		listOfHotels.addHotel(hotel1);
    	listOfHotels.addHotel(hotel2);
    	listOfHotels.addHotel(hotel3);
	}
    
	@Test public void addHotel_correctDetailsOfHotel_True(){

    	Hotel hotel=new Hotel("Lakewood", 110.0, 90.0, 80.0, 80.0, 3);
    	boolean added=listOfHotels.addHotel(hotel);
    	Assert.assertEquals(added,true);
    	
    }
    
    @Test public void findCheapestHotel_startDateAndEndDateOfHotelStay_CheapestHotelInGivenRane() {
    	
    	LocalDate date1=LocalDate.of(2020, Month.SEPTEMBER, 11);
    	LocalDate date2=LocalDate.of(2020, Month.SEPTEMBER, 12);
    	
    	List<Hotel> cheapestHotels=listOfHotels.findCheapestHotel(date1,date2, CustomerType.REGULAR_CUSTOMER);	
    	
    	Assert.assertEquals(null, hotel1, cheapestHotels.get(0));
    	Assert.assertEquals(null, hotel2, cheapestHotels.get(1));
    	
    }
  
    @Test public void findBestCheapHotel_startDateAndEndDateAndCustomerType_highestRatedCheapHotelInGivenDateRange() {
    	
    	LocalDate date1=LocalDate.of(2020, Month.SEPTEMBER, 11);
    	LocalDate date2=LocalDate.of(2020, Month.SEPTEMBER, 12);
    	
    	Hotel bestHotel=listOfHotels.findBestCheapHotel(date1,date2,CustomerType.REGULAR_CUSTOMER);
    	Assert.assertEquals("Bridgewood",bestHotel.getNameOfHotel());
    	
    }
    
    @Test public void findBestHotel_startDateAndEndDateAndCustomerType_highestRatedHotelInGivenDateRange() {
    	
    	LocalDate date1=LocalDate.of(2020, Month.SEPTEMBER, 11);
    	LocalDate date2=LocalDate.of(2020, Month.SEPTEMBER, 12);	
    	
    	Hotel bestHotel=listOfHotels.findBestHotel(date1,date2, CustomerType.REGULAR_CUSTOMER);
    	Assert.assertEquals("Ridgewood",bestHotel.getNameOfHotel());
    	
    }
    
    @Test public void findBestCheapHotel_startDateAndEndDateAndCustomerType_cheapestHotelForRewardCustomer(){
    	
    	LocalDate date1=LocalDate.of(2020, Month.SEPTEMBER, 11);
    	LocalDate date2=LocalDate.of(2020, Month.SEPTEMBER, 12);
    	
    	Hotel bestHotel=listOfHotels.findBestCheapHotel(date1,date2,CustomerType.REWARD_CUSTOMER);
    	Assert.assertEquals("Ridgewood",bestHotel.getNameOfHotel());
    }
    
    @Test public void validateDate_correctDate_True(){
    	String date="2020-09-11";
    	try {
    		boolean result=HotelReservationSystem.validateDate(date);
    		Assert.assertEquals(true,result);
    	}catch(HotelReservationException e) {
    		
    	}
    }
    @Test public void validateDate_nullDate_True(){
    	String date=null;
    	try {
    		boolean result=HotelReservationSystem.validateDate(date);
    	}catch(HotelReservationException e) {
    		Assert.assertEquals(HotelReservationException.ExceptionType.DATE_NULL, e.type);
    	}
    }
    @Test public void validateDate_invalidDate_False(){
    	String date="12-1234-22";
    	try {
    		boolean result=HotelReservationSystem.validateDate(date);
    	}catch(HotelReservationException e) {
    		Assert.assertEquals(HotelReservationException.ExceptionType.DATE_INVALID, e.type);
    	}
    }
    
    @Test public void validateCustomerType_validCustomerType_True() {
    	String customerType="REWARDS_CUSTOMER";
    	try {
    		boolean result=HotelReservationSystem.validateCustomerType(customerType);
    		Assert.assertEquals(true, result);
    	}catch(HotelReservationException e) {
    		
    	}
    }
    
    @Test public void validateCustomerType_nullCustomerType_True() {
    	String customerType=null;
    	try {
    		boolean result=HotelReservationSystem.validateCustomerType(customerType);
    		
    	}catch(HotelReservationException e) {
    		Assert.assertEquals(HotelReservationException.ExceptionType.CUSTOMER_TYPE_NULL, e.type);
    	}
    }
    
    @Test public void validateCustomerType_invalidCustomerType_True() {
    	String customerType="hduhuas";
    	try {
    		boolean result=HotelReservationSystem.validateCustomerType(customerType);
    		
    	}catch(HotelReservationException e) {
    		Assert.assertEquals(HotelReservationException.ExceptionType.CUTOMER_TYPE_INVALID, e.type);
    	}
    }
    
}
