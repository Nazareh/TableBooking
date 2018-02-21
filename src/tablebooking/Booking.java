/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablebooking;

import java.time.LocalDateTime;

/**
 *
 * @author Nazareh
 */
public class Booking {
    //VARIABLES
    private int numberOfPeople;
    private LocalDateTime bookingBegin;
    private LocalDateTime bookingEnd;
    
    //CONSTRUCTORS
    public Booking (int numberOfPeople) {
        this(numberOfPeople,Preferences.standardBookingTime,Preferences.kitchenClosingTime);
    }
    public Booking(int numberOfPeople,LocalDateTime bookingBegin){
       
        this(numberOfPeople,bookingBegin,Preferences.kitchenClosingTime);
    }
    public Booking(int numberOfPeople,LocalDateTime bookingBegin,LocalDateTime bookingEnd){
        this.numberOfPeople = numberOfPeople;
        this.bookingBegin   = bookingBegin;
        this.bookingEnd     = bookingEnd;
    }

    //GETTERS AND SETTERS
    public int getNumberOfPeople() {
        return numberOfPeople;
    }
    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }
    public LocalDateTime getBookingBegin() {
        return bookingBegin;
    }
    public void setBookingBegin(LocalDateTime bookingBegin) {
        this.bookingBegin = bookingBegin;
    }
    public LocalDateTime getBookingEnd() {
        return bookingEnd;
    }
    public void setBookingEnd(LocalDateTime bookingEnd) {
        this.bookingEnd = bookingEnd;
    }
}
