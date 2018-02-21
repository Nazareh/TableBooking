package tablebooking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Nazareh
 */
public class Run {

    public static void main(String[] args) {
        Restaurant restaurant = Restaurant.getInstance();
        restaurant.loadTablesLayoutA();
        
        ConsoleUI c = new ConsoleUI();
        c.welcome();
        
        //testing BEGIN
        LocalDateTime bookingBegin = LocalDateTime.of(LocalDate.now(), LocalTime.of(22, 0));
        LocalDateTime bookingEnd = LocalDateTime.of(LocalDate.now(), LocalTime.of(22, 0));

        Restaurant.getInstance().bookTable(new Booking(4, bookingBegin, bookingEnd));
        bookingBegin = LocalDateTime.of(LocalDate.now(), LocalTime.of(18, 0));
        bookingEnd = LocalDateTime.of(LocalDate.now(), LocalTime.of(20, 0));

        // layoutA.bookTable(4,bookingEnd );
        restaurant.bookTable(new Booking(2, bookingBegin, bookingEnd));
        restaurant.bookTable(new Booking(2, bookingEnd));
        restaurant.bookTable(new Booking(5));
        restaurant.bookTable(new Booking(4));

        //testing END
        
    }
}
