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
            
            setCurrentBooking(currentBooking);
            setBookingBeginTime(bookingBeginTime);
            setBookingEndTime(bookingEndTime);
            setBooked(false);
        }
        setTableNumber(tableNumber);
        setMaxCapacity(maxCapacity);
        setTablePreference(tablePreference);
    }
    
    //OVERRIDES
    @Override
    public String toString(){
        //for now, toString will simply print the current table status.
       return printStatus();
    }
    
    //METHODS
    public String printStatus(){
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
    
    private void validateBookingTime( LocalDateTime bookingBeginTime,LocalDateTime bookingEndTime ){
        //validate bookingBeginTime
        
        if ((bookingBeginTime == null && bookingEndTime == null) || //both parameters are null
            (bookingBeginTime != null && bookingBeginTime.isAfter(Preferences.kitchenClosingTime)) || //kitchen is closed
            ((bookingBeginTime != null && bookingEndTime != null) && bookingEndTime.isBefore(bookingBeginTime))) // EndTime is before beginTime
        {
            throw new IllegalArgumentException(Preferences.invalidBookingTimeMsg);
        }
    }
    
    //GETTERS and SETTERS
    public int getTableNumber (){ return tableNumber; }
    public void setTableNumber (int tableNumber){ this.tableNumber = tableNumber;}
    public int getMaxCapacity(){ return maxCapacity; }
    public void setMaxCapacity (int maxCapacity) { 
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException(Preferences.invalidTableCapacityMsg);
        }
        this.maxCapacity = maxCapacity;
    }
    public boolean isBooked() {return booked; }
    public void setBooked (boolean booked) { this.booked = booked;}
    public int getCurrentBooking(){ return currentBooking; }
    public void setCurrentBooking (int currentBooking) { this.currentBooking = currentBooking;
    }
    public LocalDateTime getBookingBeginTime() {return bookingBeginTime;}
    public void setBookingBeginTime(LocalDateTime bookingBeginTime) {
        validateBookingTime(bookingBeginTime,null);
        this.bookingBeginTime = bookingBeginTime;
    }
    public LocalDateTime getBookingEndTime() {return bookingEndTime;}
    public void setBookingEndTime(LocalDateTime bookingEndTime) { 
        validateBookingTime(null,bookingEndTime);
        this.bookingEndTime = bookingEndTime;}
    public int getTablePreference() {return tablePreference;}
    public void setTablePreference(int tablePreference) { this.tablePreference = tablePreference;}
    public int getCustomerID() {return customerId;}
    public void setCustomerId(int customerId) { this.customerId = customerId;}
    
}
