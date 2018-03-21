package tablebooking;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/**
 * @author Nazareh
 *  This class uses SINGLETON DESIGN PATTERN
 */
public class Restaurant {
    //VARIABLES
    private List<Table> tables = new ArrayList<>();
    private static final Restaurant instance = new Restaurant();
    
    //CONSTRUCTORS
    private Restaurant() {}
            
    //METHODS
    public synchronized void addTable (Table table) {
        addTable(getTables().size(), table);
    }
    
    public synchronized  void addTable (int index,Table table) {
        tables.add(index,table); 
    }

    public synchronized boolean cancelBooking (Booking booking) {
        List<Table> bookedTables = getBookedTables();
        for(Table table:bookedTables){
            if (table.getBooking().equals(booking)){
                tables.remove(table);
                return true;
            }
        }
            return false;
    }

    public synchronized List<Table> getBookedTables (){
        List<Table> bookedTables = new ArrayList();
        for (Table table:tables){
            if (table.getBooking() != null)
                bookedTables.add(table);
        }
        return bookedTables;
    }
    public synchronized boolean bookTable(Booking booking){
        
        int numberOfPeople = booking.getNumberOfPeople();
        LocalDateTime bookingBegin = booking.getBookingBegin();
        LocalDateTime bookingEnd = booking.getBookingEnd();

        if (!validBookingTime(bookingBegin,bookingEnd)){
            return false;
        }
       //Lambda expression for sorting by table maxCapacity and tablePreference
        Comparator<Table> comparator = Comparator.comparing(Table::getMaxCapacity)
                                                 .thenComparing(Table::getTablePreference);
        tables.sort(comparator);

      //Find the first available table
      for(int i =0; i < getTables().size();i++ ){
          Table t =  getTables().get(i);
          if (t.getMaxCapacity() >=  numberOfPeople) {
              //if table does not have a booking, make one.
              if (t.getBooking() == null ) {
                  t.setBooking(booking);
                return true;
              }
              /*if the table is already booked, try to double book the same table
               * by creating a new table in the list, with same table number and capacity
               */
              else if( (t.getBooking() != null) &&
                      (bookingBegin.isAfter(t.getBooking().getBookingEnd()) ||
                       bookingBegin.equals(t.getBooking().getBookingEnd())))
              {
                  Table DoubleBook = new Table(t.getTableNumber(),t.getMaxCapacity());
                  addTable(i+1, DoubleBook );
              }
          }
      }
        return false;
    }
    private boolean validBookingTime( LocalDateTime bookingBeginTime,LocalDateTime bookingEndTime ){
        if ((bookingBeginTime == null && bookingEndTime == null) || //both parameters are null
                (bookingBeginTime != null && (bookingBeginTime.isAfter(Preferences.kitchenClosingTime) ||
                                              bookingBeginTime.isEqual(Preferences.kitchenClosingTime))) || //kitchen is closed
                ((bookingBeginTime != null && bookingEndTime != null) && bookingEndTime.isBefore(bookingBeginTime))) // EndTime is before beginTime
        {
            return false;
        }
        return true;
    }

    public synchronized void loadTablesLayoutA() {
        //lower section
        tables = new ArrayList<>();
        addTable(new Table(1,2,11));
        addTable(new Table(2,4,10));
        addTable(new Table(3,2,11));
        addTable(new Table(4,2,10));
        addTable(new Table(5,4,10));
        addTable(new Table(50, 2));
        //upper section
        addTable(new Table(7,6,1));
        addTable(new Table(8,4,2));
        addTable(new Table(9,4,2));
        addTable(new Table(10,5,2));
        addTable(new Table(11,4,2));
        addTable(new Table(12,2,3));
        addTable(new Table(13,4,3));
        addTable(new Table(14,2,3));
        addTable(new Table(15,3,4));
        addTable(new Table(17,6,2));
        addTable(new Table(18,2,1));
        addTable(new Table(19,4,1));
        //backroom  section
        addTable(new Table(21, 6,20));
        addTable(new Table(22, 8,20));
        addTable(new Table(23, 4,20));
        addTable(new Table(24, 2,20));
        addTable(new Table(25, 5,20));
    }
    
    //GETTERS and SETTERS
    public static Restaurant getInstance (){return instance; }
    public synchronized List<Table> getTables(){ return tables;}
    public synchronized void setTables(List<Table> tables) {this.tables = tables;}

}
