package tablebooking;


import java.time.LocalDateTime;
import static java.time.format.DateTimeFormatter.ofPattern;
/**
 * @author Nazareh nazarehturmina@gmail.com
 */
public class Table{
    //VARIABLES
    private int tableNumber;
    private int maxCapacity;
    private Booking booking;
    private int tablePreference;  //tables with lower tablePreference will be used first;
    private int customerId; //to be implemented
    
    
    //CONSTRUCTORS 
    public Table (int tableNumber, int maxCapacity){ 

        this(tableNumber,maxCapacity,99);
    }
    public Table (int tableNumber, int maxCapacity, int tablePreference){
        this(tableNumber,maxCapacity,0,null);
    }

    public Table (int tableNumber, int maxCapacity, int tablePreference,Booking booking){

        if (booking != null) {
            if ( (maxCapacity < 1) 
                 || (booking.getNumberOfPeople()> maxCapacity) )    {
                throw new RuntimeException(Preferences.invalidTableCapacityMsg);
            }
            
            this.booking = booking;
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
    @Override
    public int hashCode (){
        return tableNumber;
    }
    
    //METHODS
    public String printStatus(){
        String msg ; 
       if (booking != null){
           msg = "Table "+ tableNumber + " is booked for " +
                  booking.getNumberOfPeople()+ " people from  " +
                  booking.getBookingBegin().format(ofPattern("HH:mm"));
                   
                     msg += (booking.getBookingEnd() == null) ? "hs onwards." : " to " + booking.getBookingEnd().format(ofPattern("HH:mm"));
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
   // public boolean isBooked() {return booked; }
  //  public void setBooked (boolean booked) { this.booked = booked;}
    public int getTablePreference() {return tablePreference;}
    public void setTablePreference(int tablePreference) { this.tablePreference = tablePreference;}
    public int getCustomerID() {return customerId;}
    public void setCustomerId(int customerId) { this.customerId = customerId;}
    public Booking getBooking() {return booking;}
    public void setBooking(Booking booking) {
        validateBookingTime(booking.getBookingBegin(),booking.getBookingEnd());
        this.booking = booking;
    }
}
