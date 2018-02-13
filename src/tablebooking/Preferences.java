/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablebooking;

import java.time.LocalTime;

/**
 *
 * @author Nazareh
 */
public class Preferences {
    //Time when the kitchen usually closes.
    public static LocalTime KitchenClosingTime = LocalTime.of(21, 0);
    public static String InvalidBookingTimeMsg = "The booking time informed is invalid.";
    public static String InvalidTableCapacity = "The value for the table capacity is invalid.";
}