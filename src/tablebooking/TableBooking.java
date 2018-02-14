package tablebooking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Nazareh
 */
public class TableBooking {

    public static void main(String[] args) {
        
       LoadTables load = new LoadTables();
       
       Area lowerFront = load.lowerFrontLayoutA();
        
       //testing BEGIN
       LocalDateTime bookingBegin = LocalDateTime.of(LocalDate.now(), LocalTime.of(22, 0));
       LocalDateTime bookingEnd = LocalDateTime.of(LocalDate.now(), LocalTime.of(22, 0));
      
       lowerFront.bookTable(4,bookingBegin,bookingEnd );
      // lowerFront.bookTable(4,bookingEnd );
       lowerFront.bookTable(2);
       lowerFront.bookTable(2);
       lowerFront.bookTable(5);
       lowerFront.bookTable(4);
       
       //testing END
       
         System.out.println();
         System.out.println("Bookings for today:");
         
        for(Table table : lowerFront.getTables() ){
            
           System.out.println(table);
        }
                  
        
    }
}
