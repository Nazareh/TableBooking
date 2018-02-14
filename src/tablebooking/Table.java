package tablebooking;

import java.time.LocalDateTime;
import static java.time.format.DateTimeFormatter.ofPattern;

public class Table{
    //VARIABLES
    private int tableNumber;
    private int maxCapacity;
    private boolean booked;
    private int currentBooking;
    private LocalDateTime bookingBeginTime;
    private LocalDateTime bookingEndTime;   
    private int tablePreference;  //tables with lower tablePreference will be used first;
    private int customerId; //to be implemented
    
    
    //CONSTRUCTORS 
    public Table (int tableNumber, int maxCapacity){ 
         this(tableNumber,maxCapacity,99); 
    }
    public Table (int tableNumber, int maxCapacity, int tablePreference){
        this(tableNumber,maxCapacity,0,null,null,tablePreference);
    }
    //create table with booking
    public Table (int tableNumber, int maxCapacity, int currentBooking, LocalDateTime bookingBeginTime, LocalDateTime bookingEndTime){
          /**
           * if the table does not have any specific preference to be used first, 
           * it gets a very low preference number
          */
          this(tableNumber,maxCapacity,currentBooking,bookingBeginTime,bookingEndTime,99);
          
     }
    public Table (int tableNumber, int maxCapacity, int currentBooking, LocalDateTime bookingBeginTime, LocalDateTime bookingEndTime, int tablePreference){
        if (bookingBeginTime != null) {
            if ( (maxCapacity < 1) 
                 || (currentBooking > maxCapacity) )    {
                throw new RuntimeException(Preferences.invalidTableCapacityMsg);
            }
             validateBookingTime(bookingBeginTime,bookingEndTime);
            this.currentBooking = currentBooking;
            this.bookingBeginTime = bookingBeginTime;
            this.bookingEndTime = bookingEndTime; 
            this.booked = false;
        }
        this.tableNumber = tableNumber;
        this.maxCapacity = maxCapacity;
        this.tablePreference = tablePreference;
    }
   
    //OVERRIDES
    @Override
    public String toString(){
        String msg ; 
       if (isBooked()){ 
           msg = "Table "+ tableNumber + " is booked for " +
                  getCurrentBooking()  + " people from  " +
                  getBookingBeginTime().format(ofPattern("HH:mm"));
                   
                     msg += (getBookingEndTime() == null) ? "hs onwards." : " to " + getBookingEndTime().format(ofPattern("HH:mm"));
        }
        else { msg = "Table "+tableNumber + " is not booked yet."; }
             
        return msg;
    }
    //GETTERS  and SETTERS
    public int getTableNumber (){ return tableNumber; }
    public void setTableNumber (int tableNumber){ this.tableNumber = tableNumber;}
    public int getMaxCapacity(){ return maxCapacity; }
    public void setMaxCapacity (int maxCapacity) { this.maxCapacity = maxCapacity;}
    public boolean isBooked() {return booked; }
    public void setBooked (boolean booked) { this.booked = booked;}
    public int getCurrentBooking(){ return currentBooking; }
    public void setCurrentBooking (int currentBooking) { this.currentBooking = currentBooking;}
    public LocalDateTime getBookingBeginTime() {return bookingBeginTime;}
    public void setBookingBeginTime(LocalDateTime bookingBeginTime) {this.bookingBeginTime = bookingBeginTime;}
    public LocalDateTime getBookingEndTime() {return bookingEndTime;}
    public void setBookingEndTime(LocalDateTime bookingEndTime) { this.bookingEndTime = bookingEndTime;}
    public int getTablePreference() {return tablePreference;}
    public void setTablePreference(int tablePreference) { this.tablePreference = tablePreference;}
    public int getCustomerID() {return customerId;}
    public void setCustomerId(int customerId) { this.customerId = customerId;}

    //METHODS
    private void validateBookingTime( LocalDateTime bookingBeginTime,LocalDateTime bookingEndTime ){
        //booking has to begin before the kitchen closes abnd the end time cannot be after begin time
         if ( bookingBeginTime.isAfter(Preferences.kitchenClosingTime) ||  
              bookingEndTime.isAfter(bookingBeginTime)){
            throw new IllegalArgumentException(Preferences.invalidBookingTimeMsg);
        }
    }
}
