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
    private List<Table> tablesLayout = new ArrayList<>();
    private static final Restaurant instance = new Restaurant();

    //CONSTRUCTORS
    private Restaurant() {}
            
    //METHODS
    public synchronized boolean cancelBooking (Booking booking) {
        List<Table> bookedTables = getBookedTables();
        for(Table table:bookedTables){
            if (table.getBooking().equals(booking)){
                tablesLayout.remove(table);
                return true;
            }
        }
            return false;
    }
    public synchronized List<Table> getBookedTables (){
        List<Table> bookedTables = new ArrayList();
        for (Table table:tablesLayout){
            if (table.getBooking() != null)
                bookedTables.add(table);
        }
        return bookedTables;
    }
    /*
   This method extract all previous bookings  PLUS a new booking
   and try to fit them in the same table layout. If it does not work,
   try a different a layout. If still doesnt work, returns the original one.
    */
    public synchronized boolean tryToBookTable(Booking newBooking){
        List<Table> currentLayout = getCurrentTableLayout();
        boolean foundBetterLayout;

        //List containing all possible table layouts
        List<List<Table>> allLayouts = new ArrayList<>();
        allLayouts.add(loadTablesLayoutA());
        allLayouts.add(loadTablesLayoutB());

        //adds previous bookings + new booking to a List<Booking>
        List<Booking> previousBookings = new ArrayList<>();
        for (Table table:getBookedTables() ){
            previousBookings.add(table.getBooking());
        }
        previousBookings.add(newBooking);

        /*try to place the new booking in the current layout,
        if it does not work, tries a different one.
         */
        for(List<Table> newTableLayout: allLayouts){
            tablesLayout = newTableLayout;
            foundBetterLayout = true;
            bookings:
            for (Booking b:previousBookings){
                if (!bookTable(b)) {
                    tablesLayout = currentLayout;
                    foundBetterLayout = false;
                    break bookings;
                }

            }
            if (foundBetterLayout) {
                return true;
            }
        }
        return false;
    }
    private boolean bookTable(Booking booking){

        int numberOfPeople = booking.getNumberOfPeople();
        LocalDateTime bookingBegin = booking.getBookingBegin();
        LocalDateTime bookingEnd = booking.getBookingEnd();

        if (!validBookingTime(bookingBegin,bookingEnd)){
            return false;
        }
       //Lambda expression for sorting by table maxCapacity and tablePreference
        Comparator<Table> comparator = Comparator.comparing(Table::getMaxCapacity)
                                                 .thenComparing(Table::getTablePreference);
        tablesLayout.sort(comparator);

      //Find the first available table
      for(int i =0; i < getCurrentTableLayout().size();i++ ){
          Table t =  getCurrentTableLayout().get(i);
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
                  tablesLayout.add(i + 1, DoubleBook);
              }
          }
      }
            return false;
    }
    private boolean validBookingTime( LocalDateTime bookingBeginTime,LocalDateTime bookingEndTime ){
        return (bookingBeginTime != null || bookingEndTime != null) && //both parameters are null
                (bookingBeginTime == null || (!bookingBeginTime.isAfter(Preferences.kitchenClosingTime) &&
                        !bookingBeginTime.isEqual(Preferences.kitchenClosingTime))) && //kitchen is closed
                ((bookingBeginTime == null || bookingEndTime == null) || !bookingEndTime.isBefore(bookingBeginTime));
    }

    public List<Table> loadTablesLayoutA() {
        //lower section
        List<Table> layout = new ArrayList<>();
        layout.add(new Table(1,2,11));
        layout.add(new Table(2,4,10));
        layout.add(new Table(3,2,11));
        layout.add(new Table(4,2,10));
        layout.add(new Table(5,4,10));
        layout.add(new Table(50, 2));
        //upper section
        layout.add(new Table(7,6,1));
        layout.add(new Table(8,4,2));
        layout.add(new Table(9,4,2));
        layout.add(new Table(10,5,2));
        layout.add(new Table(11,4,2));
        layout.add(new Table(12,2,3));
        layout.add(new Table(13,4,3));
        layout.add(new Table(14,2,3));
        layout.add(new Table(15,3,4));
        layout.add(new Table(17,6,2));
        layout.add(new Table(18,2,1));
        layout.add(new Table(19,4,1));
        //backroom  section
        layout.add(new Table(21, 6,20));
        layout.add(new Table(22, 8,20));
        layout.add(new Table(23, 4,20));
        layout.add(new Table(24, 2,20));
        layout.add(new Table(25, 5,20));
        return layout;
    }
    public List<Table> loadTablesLayoutB() {
        //lower section
        List<Table> layout = new ArrayList<>();
        layout.add(new Table(1,2,11));
        layout.add(new Table(2,4,10));
        layout.add(new Table(3,2,11));
        layout.add(new Table(4,2,10));
        layout.add(new Table(5,4,10));
        layout.add(new Table(50, 2));
        //upper section
        layout.add(new Table(7,6,1));
        layout.add(new Table(8,4,2));
        layout.add(new Table(9,4,2));
        layout.add(new Table(10,5,2));
        layout.add(new Table(11,4,2));
        layout.add(new Table(12,2,3));
        layout.add(new Table(13,4,3));
        layout.add(new Table(14,2,3));
        layout.add(new Table(15,3,4));
        layout.add(new Table(17,10,2));
        layout.add(new Table(19,2,1));
        //backroom  section
        layout.add(new Table(21, 6,20));
        layout.add(new Table(22, 8,20));
        layout.add(new Table(23, 4,20));
        layout.add(new Table(24, 2,20));
        layout.add(new Table(25, 5,20));
        return layout;
    }
    //GETTERS and SETTERS
    public static Restaurant getInstance (){return instance; }
    public synchronized List<Table> getCurrentTableLayout(){ return tablesLayout;}
    public synchronized void setTablesLayout(List<Table> tables) {this.tablesLayout = tables;}
    public synchronized Table getTable(int tableNumber){
        for(Table table : getInstance().getCurrentTableLayout()){
            if (table.getTableNumber() == tableNumber)
               return table;
        }
        return null;
    }
}
