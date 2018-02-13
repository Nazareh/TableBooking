
package tablebooking;

import java.time.LocalDateTime;
import static java.time.format.DateTimeFormatter.ofPattern;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/**
 * @author Nazareh
 */
public class Area {
    private List<Table> tables = new ArrayList<Table>();
    private int areaMaxCapacity ;
    //areas with lower bookingPreferencen will be filled first;
    private int bookingPreference;
    
    //GETTERS
    public List<Table> getTables(){ return tables;}
    public int getAreaMaxCapacity () {return areaMaxCapacity;}
    
    //SETTERS
    public void setTables(List<Table> tables) {this.tables = tables;}
    
    
    //TABLE MANAGEMENT
    public void addTable (Table table) {
        tables.add(table); 
        areaMaxCapacity += table.getMaxCapacity();
    }
    public void cancelBooking (Table table) {/*still to be implemented*/};
   
    public boolean bookTable(int people,LocalDateTime time){
            
       //Lambda expression for sorting by table maxCapacity 
        Comparator<Table> comparator = Comparator.comparing(Table::getMaxCapacity).thenComparing(Table::getTablePreference);      
        tables.sort(comparator);

      //java 8 forEach for printing the list
      for(Table t: getTables() ){
          if(!t.isBooked() && (t.getMaxCapacity() >= people )  ){
          
              t.setCurrentBooking(people);
              t.setBookingBeginTime(time );
              t.setBooked(true);
              System.out.println("Table "+ t.getTableNumber() + " was successfully booked for "+ time.format(ofPattern("HH:mm")));
              return true;
          }
      }
        return false;
    }
}
