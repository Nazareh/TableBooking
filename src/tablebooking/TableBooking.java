package tablebooking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Nazareh
 */
public class TableBooking {

    public static void main(String[] args) {
       GenerateTables gt = new GenerateTables();
       Area area = gt.lowerFrontLayoutA();
        
       
       LocalDate d = LocalDate.now();
       LocalTime t = LocalTime.of(19, 0);
       LocalDateTime dt = LocalDateTime.of(d, t);
       area.bookTable(4,dt);
         
        for(Table table : area.getTables() ){
           System.out.println(table);
        }
          System.out.println("Area capacity: "+ area.getAreaMaxCapacity());
                  
        
    }
}
