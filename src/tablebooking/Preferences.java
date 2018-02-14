/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablebooking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Nazareh
 */
public class Preferences {
    //Standard time to make the booking in case it was not informed by the user.
    public static LocalDateTime standardBookingTime = LocalDateTime.of(LocalDate.now(),LocalTime.of(19,0)) ;
    //Time when the kitchen closes.
    public static LocalDateTime kitchenClosingTime =  LocalDateTime.of(LocalDate.now(),LocalTime.of(21,0)) ;
    public static String kitchenClosedMsg= "Sorry, the kitchen is already closed at this time.";
    public static String invalidBookingTimeMsg = "The booking time informed is invalid.";
    public static String invalidTableCapacityMsg= "The value for the table capacity is invalid.";
}