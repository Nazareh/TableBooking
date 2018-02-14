package tablebooking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import static java.time.format.DateTimeFormatter.ofPattern;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/**
 * @author Nazareh
 */
public class Area {
    //VARIABLES
    private List<Table> tables = new ArrayList<>();
    private int areaMaxCapacity ;
    private int bookingPreference;  //areas with lower bookingPreferencen will be filled first;
    
    //GETTERS and SETTERS
    public List<Table> getTables(){ return tables;}
    public void setTables(List<Table> tables) {this.tables = tables;}
    public int getAreaMaxCapacity () {return areaMaxCapacity;}
    
    //TABLE MANAGEMENT METHODS
    public void addTable (Table table) {
        addTable(getTables().size(), table);
    }
    public void addTable (int index,Table table) {
        tables.add(index,table); 
        areaMaxCapacity += table.getMaxCapacity();
    }

    public void cancelBooking (Table table) {/*still to be implemented*/};

    public boolean bookTable(int people){
        return bookTable(people,Preferences.standardBookingTime,Preferences.kitchenClosingTime);
    }
    public boolean bookTable(int people,LocalDateTime time){
       
        return bookTable(people,time,Preferences.kitchenClosingTime);
    }

    public boolean bookTable(int people,LocalDateTime beginTime,LocalDateTime endTime){
        
        if (beginTime.isAfter(Preferences.kitchenClosingTime) ){
            System.out.println(Preferences.kitchenClosedMsg) ; 
            return false;
        }
       //Lambda expression for sorting by table maxCapacity 
        Comparator<Table> comparator = Comparator.comparing(Table::getMaxCapacity).thenComparing(Table::getTablePreference);      
        tables.sort(comparator);

      //Find the first available table
      for(int i =0; i < getTables().size();i++ ){
          Table t =  getTables().get(i);
          if (t.getMaxCapacity() >= people) {
              if (!t.isBooked()) {
                t.setCurrentBooking(people);
                t.setBookingBeginTime(beginTime);
                t.setBookingEndTime(endTime);
                t.setBooked(true);
                System.out.println("Table "+ t.getTableNumber() + " was successfully booked for "+ beginTime.format(ofPattern("HH:mm")));
                return true;
              }
              /**if the table is already booked if it possible to double book the same table
               * by create a new table in the list, with same table number and capacity
               */
              else if(t.isBooked() && (beginTime.isAfter(t.getBookingEndTime()) || beginTime ==  t.getBookingEndTime())){
                  Table DoubleBook = new Table(t.getTableNumber(),t.getMaxCapacity());
                  addTable(i+1, DoubleBook );
              }
          }
      }
        System.out.println("Sorry, we do not have a table available for "+people+ " people at " +beginTime.format(ofPattern("HH:mm"))) ; 
        return false;
    }
}
