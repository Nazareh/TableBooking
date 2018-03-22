/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablebooking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static java.time.format.DateTimeFormatter.ofPattern;

/**
 * @author Nazareh nazarehturmina@gmail.com
 */
class Preferences {

    public static Customer unkownCustomer = new Customer("John Doe");
    public static LocalDateTime standardBookingTime = LocalDateTime.of(LocalDate.now(),LocalTime.of(19,0)) ;
    //Time when the kitchen closes.
    public static LocalDateTime kitchenClosingTime =  LocalDateTime.of(LocalDate.now(),LocalTime.of(21,0)) ;
    public static String kitchenClosedMsg= "Sorry, the kitchen is already closed at this time.";
    public static String invalidBookingTimeMsg = "The booking time informed is invalid.";
    public static String invalidTableCapacityMsg= "The value for the table capacity is invalid.";
    public static String invalidAnswerMsg = "Sorry, that was not a valid answer. Please try again.";
    //make booking messages
    public static String numberOfPeopleMsg = "Please inform HOW MANY PEOPLE would you like to make the booking for: ";
    public static String valueInformedInvalid = "The value you informed is NOT valid. Please try again.";
    public static String bookingForTodayMsg = "Is the booking for today?  YES(Y) or NO(N):";
    public static String bookingDateTimeMsg = "Please inform the DATE and TIME you like to make the booking for in the format DD/MM/YYYY HH24:mm: ";
    public static String informBookingBeginTimeMsg = "Please inform the TIME you like to make the booking for in the format HH24:mm: ";
    public static String informBookingEndMsg = "Would like to inform the time when the booking will end? YES(Y) or NO(N):";
    public static String bookingConfirmedMsg = "Booking Confirmed!";
    public static String bookingCanceledMsg = "Booking Canceled!";
    public static String tableNotAvailableMsg = "Sorry, we do not have a table available for this number of people at this time";

    //cancelBooking messages
    public static String tableToCancelMsg = "What table would like to cancel? To exit please write EXIT(E).";
    
    public static String getFirstScreenMsg(){
        return ("What would like to do?" + "\n") +
                "To make a booking please write BOOKING (B)." + "\n" +
                "To cancel a booking please write CANCEL(C)." + "\n" +
                "To print the bookings for today please write PRINT(P)." + "\n" +
                "To exit please write EXIT(E).";
    }
    public static String getConfirmBookingMsg(Booking booking){
        return "Would you like to confirm the booking for "+ booking.getNumberOfPeople() +
                " on "+ booking.getBookingBegin().toLocalDate().format(ofPattern("dd/MM/YYY")) +
                " at " + booking.getBookingBegin().toLocalTime().toString() +"?";
    }


}