package tablebooking;

import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.hamcrest.core. IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

public class RestaurantTest {
    private Restaurant restaurant;
    private LocalDateTime bookingBegin;
    private LocalDateTime bookingEnd;
    private Booking intendedBooking;
    private boolean result;
    private Customer customer;
    @Before
    public void before(){
        restaurant = Restaurant.getInstance();
        restaurant.setTablesLayout(restaurant.loadTablesLayoutA());
        customer = Preferences.unkownCustomer;
        bookingBegin = Preferences.standardBookingTime;
        bookingEnd  = Preferences.kitchenClosingTime;

    }

    @Test
    public void cancelBooking() {
        intendedBooking = new Booking(4,new Customer("Nazareh"));
        restaurant.tryToBookTable(intendedBooking);
        restaurant.cancelBooking(intendedBooking);
        assertThat(restaurant.getBookedTables().size(),equalTo(0) );
    }

    @Test
    public void bookTableTooBig() {
        intendedBooking = new Booking(100);
        restaurant.tryToBookTable(intendedBooking);
        assertThat(restaurant.getBookedTables().size(),equalTo(0) );
    }

    @Test
    public void bookTableWhenKitchenIsClosed() {
        bookingBegin = Preferences.kitchenClosingTime;
        intendedBooking = new Booking(4,customer,bookingBegin);
        restaurant.tryToBookTable(intendedBooking);
        assertThat(restaurant.getBookedTables().size(),equalTo(0) );
    }
    @Test
    public void testBookingEqualsMethodSameCustomerSameTime(){
        Booking b1 = new Booking(4);
        Booking b2 = new Booking(4);
        assertTrue(b1.equals(b2));
    }
    @Test
    public void testBookingEqualsMethodSameCustomerDifferentTime(){
        Booking b1 = new Booking(4,customer,Preferences.standardBookingTime);
        Booking b2 = new Booking(4,customer,Preferences.kitchenClosingTime);
        assertFalse(b1.equals(b2));
    }
    @Test
    public void testBookingEqualsMethodDifferentCustomerSameTime(){
        Booking b1 = new Booking(4,new Customer("Customer1"));
        Booking b2 = new Booking(4,new Customer("Customer2"));
        assertFalse(b1.equals(b2));
    }

    @Test
    public void bookTable() {
        intendedBooking = new Booking(7);
        restaurant.tryToBookTable(intendedBooking);
        assertThat(restaurant.getBookedTables().get(0).getBooking(),equalTo(intendedBooking));
    }
    @Test
    public void doubleBookSameTable (){

        //booking 1
        bookingBegin = LocalDateTime.of(LocalDate.now(), LocalTime.of(18, 0));
        bookingEnd = LocalDateTime.of(LocalDate.now(), LocalTime.of(20, 0));
        intendedBooking = new Booking(4,customer,bookingBegin,bookingEnd);
        restaurant.tryToBookTable(intendedBooking);

        //booking2
        bookingBegin = LocalDateTime.of(LocalDate.now(), LocalTime.of(20, 0));
        bookingEnd = LocalDateTime.of(LocalDate.now(), LocalTime.of(22, 0));
        intendedBooking = new Booking(4,customer,bookingBegin,bookingEnd);
        restaurant.tryToBookTable(intendedBooking);

        List<Table> bookedTables = restaurant.getBookedTables();
        assertTrue ( bookedTables.size() == 2 &&
                     (bookedTables.get(0).getTableNumber() == bookedTables.get(1).getTableNumber()) &&
                     (bookedTables.get(0).getBooking().getBookingEnd().isEqual( bookedTables.get(1).
                                                                                        getBooking().
                                                                                        getBookingBegin())));
    }
    @Test
    public void bookMoreThanOneTable(){
        //booking 1
        intendedBooking = new Booking(6,customer,bookingBegin,bookingEnd);
        restaurant.tryToBookTable(intendedBooking);
        //booking2
        intendedBooking = new Booking(6,customer,bookingBegin,bookingEnd);
        restaurant.tryToBookTable(intendedBooking);
        //booking3
        intendedBooking = new Booking(4,customer,bookingBegin,bookingEnd);
        restaurant.tryToBookTable(intendedBooking);
        //booking4
        intendedBooking = new Booking(2,customer,bookingBegin,bookingEnd);
        restaurant.tryToBookTable(intendedBooking);

        assertThat(restaurant.getBookedTables().size(),equalTo(4) );
    }

    @Test
    public void bookTooManyTables(){
        for (int i = 0; i < 30; i++){
            intendedBooking = new Booking(2);
            restaurant.tryToBookTable(intendedBooking);
        }
        assertThat(restaurant.getBookedTables().size(), not(equalTo(30)));
    }
    @Test
    public void bookBiggerTablesForSmallerBooking(){
        for (int i = 0; i < 10; i++){
            intendedBooking = new Booking(2);
            restaurant.tryToBookTable(intendedBooking);
        }
        assertThat(restaurant.getBookedTables().size(), equalTo(10));
    }
    @Test
    public void rearrangeTablesForBiggerBooking(){
        intendedBooking = new Booking(6);
        restaurant.tryToBookTable(intendedBooking);

        intendedBooking = new Booking(6);
        restaurant.tryToBookTable(intendedBooking);
        assertThat(restaurant.getTable(17).getBooking().getNumberOfPeople(),equalTo(6));

        intendedBooking = new Booking(10);
        restaurant.tryToBookTable(intendedBooking);
        assertThat(restaurant.getTable(17).getBooking().getNumberOfPeople(),equalTo(10));

    }
}