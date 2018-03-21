package tablebooking;

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
           msg =  booking.getCustomer().getName() +" has booked table "+
                  tableNumber + " for " +
                  booking.getNumberOfPeople()+ " people from  " +
                  booking.getBookingBegin().format(ofPattern("HH:mm"));
                   
                     msg += (booking.getBookingEnd() == null) ? "hs onwards." : " to " + booking.getBookingEnd().format(ofPattern("HH:mm"));
        }
        else { msg = "Table "+ tableNumber + " is not booked yet."; }
             
        return msg;
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
    public int getTablePreference() {return tablePreference;}
    public void setTablePreference(int tablePreference) { this.tablePreference = tablePreference;}
    public Booking getBooking() {return booking;}
    public void setBooking(Booking booking) { this.booking = booking; }
}
