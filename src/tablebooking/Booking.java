package tablebooking;

import java.time.LocalDateTime;

/**
 * @author Nazareh
 */
public class Booking {



    //VARIABLES
    private Customer customer;
    private int numberOfPeople;
    private LocalDateTime bookingBegin;
    private LocalDateTime bookingEnd;

    @Override
    public boolean equals (Object obj){
        if (!(obj instanceof Booking))
                return false;

        Booking booking = (Booking) obj;
        if (this == obj)
                return true;
        else if (    this.numberOfPeople == booking.getNumberOfPeople() &&
                this.customer.equals(booking.customer) &&
                this.bookingBegin.equals(booking.getBookingBegin()) &&
                this.bookingEnd.equals(booking.getBookingEnd()))
                    return true;

        else return false;
    }
    
    //CONSTRUCTORS
    public Booking (int numberOfPeople) {
        this(   numberOfPeople,
                Preferences.unkownCustomer,
                Preferences.standardBookingTime,
                Preferences.kitchenClosingTime);
    }
    public Booking(int numberOfPeople,
                   Customer customer){
        this(numberOfPeople,customer, Preferences.standardBookingTime,Preferences.kitchenClosingTime);
    }
    public Booking(int numberOfPeople,
                   Customer customer,
                   LocalDateTime bookingBegin){
       
        this(numberOfPeople,customer, bookingBegin,Preferences.kitchenClosingTime);
    }
    public Booking(int numberOfPeople,Customer customer,LocalDateTime bookingBegin,LocalDateTime bookingEnd){
        this.numberOfPeople = numberOfPeople;
        this.customer = customer;
        this.bookingBegin   = bookingBegin;
        this.bookingEnd     = bookingEnd;
    }

    //GETTERS AND SETTERS
    public int getNumberOfPeople() {return numberOfPeople;}
    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }
    public LocalDateTime getBookingBegin() {return bookingBegin;}
    public void setBookingBegin(LocalDateTime bookingBegin) {
        this.bookingBegin = bookingBegin;
    }
    public LocalDateTime getBookingEnd() {return bookingEnd;}
    public void setBookingEnd(LocalDateTime bookingEnd) {
        this.bookingEnd = bookingEnd;
    }
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
