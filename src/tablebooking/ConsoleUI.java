package tablebooking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Nazareh nazarehturmina@gmail.com
 */
public class ConsoleUI {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private int numberOfPeople = 0;
    private LocalDate bookingDate = null;
    private LocalTime bookingBeginTime = null;
    private LocalTime bookingEndTime = null;
    private LocalDateTime bookingBegin = Preferences.standardBookingTime;
    private LocalDateTime bookingEnd = Preferences.kitchenClosingTime;
    
    public void welcome() {
        boolean exit = false;
        String answer;
        do{
            System.out.println();
            System.out.println(Preferences.getFirstScreenMsg());
            try { 
                answer = reader.readLine().substring(0, 1).toUpperCase();
                switch (answer) {
                    case "E": exit = true; break;
                    case "B": makeBooking();break;
                    case "C": cancelBooking();break;
                    case "P": printBookings();break;
                    default:  System.out.println();
                              System.out.println(Preferences.invalidAnswerMsg); 
                }
                exit = answer.equals("E");
            }
            catch (Exception e ){
                System.out.println(e.getStackTrace());
            }
        }
        while (!exit);
    }
    private void cleanVariables(){
        numberOfPeople = 0;
        bookingDate = null;
        bookingBeginTime = null;
        bookingEndTime = null;
        bookingBegin = Preferences.standardBookingTime;
        bookingEnd = Preferences.kitchenClosingTime;
    }
    private void makeBooking() {
        makeBooking(0);
    }
    private void makeBooking(int startPoint) {
        startPoint = (startPoint < 0) ? 0 : startPoint;

        try {
            String answer;
            boolean exit = false;

            //part 1, or startPoint = 0
            while (!exit && startPoint == 0) {
                startPoint = 0;
                System.out.println();
                System.out.println(Preferences.numberOfPeopleMsg);
                numberOfPeople = Integer.parseInt(reader.readLine());
                exit = true;
            }

            //part 2, or startPoint = 1
            exit = false;
            while (!exit && startPoint <= 1) {
                startPoint = 1;
                System.out.println(Preferences.bookingForTodayMsg);
                answer = reader.readLine().substring(0, 1).toUpperCase();
                switch (answer) {
                    case "Y":
                        bookingDate = LocalDate.now();
                        exit = true;
                        break;
                    case "N":
                        System.out.println(Preferences.bookingDateTimeMsg);
                        answer = reader.readLine();
                        bookingDate = LocalDate.of(
                                Integer.parseInt(answer.substring(0, 2)), //day DD
                                Integer.parseInt(answer.substring(3, 5)), //month MM
                                Integer.parseInt(answer.substring(6, 10))); //year YYYY
                        bookingBeginTime = LocalTime.of(
                                Integer.parseInt(answer.substring(11, 13)), //day DD
                                Integer.parseInt(answer.substring(14, 16))); //month MM
                        exit = true;
                        break;
                }
            }
            //part 3, or startPoint = 2
            exit = false;
            while (!exit && startPoint <= 2) {
                startPoint = 2;
                System.out.println(Preferences.informBookingBeginTimeMsg);
                answer = reader.readLine();
                switch (answer) {
                    case "Y":
                        bookingBeginTime = LocalTime.of(
                                Integer.parseInt(answer.substring(0,2)), //day DD
                                Integer.parseInt(answer.substring(3,5))); //month MM
                        exit = true;
                        break;
                    case "N":
                        bookingBeginTime = Preferences.standardBookingTime.toLocalTime();
                        exit = true;
                        break;
                }
            }
            //part 4, or startPoint = 3
            exit = false;
            while (!exit && startPoint <= 3) {
                startPoint = 3;
                System.out.println(Preferences.informBookingEndMsg);
                answer = reader.readLine().substring(0, 1).toUpperCase();
                switch (answer) {
                    case "Y":
                        bookingDate = LocalDate.now();
                        bookingEndTime = LocalTime.of(
                                Integer.parseInt(answer.substring(0,2)), //day DD
                                Integer.parseInt(answer.substring(3,5))); //month MM
                        exit = true;
                        break;
                    case "N":
                        exit = true;
                        break;
                }
            }

            //part 5, or startPoint = 4
            exit = false;
            while (!exit && startPoint <=4){
                startPoint = 4;
                Booking booking = new Booking(numberOfPeople, Preferences.unkownCustomer,bookingBegin, bookingEnd);
                System.out.println(Preferences.getConfirmBookingMsg(booking));
                answer = reader.readLine().substring(0, 1).toUpperCase();
                switch (answer) {
                    case "Y":
                        bookingBegin = LocalDateTime.of(bookingDate, bookingBeginTime);
                        if (Restaurant.getInstance().bookTable(booking)) {
                            System.out.println(Preferences.bookingConfirmedMsg);
                        }
                        else {
                            /*in a case there is no table available at this time, a method to re-organize the tables
                            and try to make a table available should be called here
                             */
                            System.out.println(Preferences.tableNotAvailableMsg) ;
                        }
                        booking = null;
                        exit = true;
                        break;
                    case "N":
                        System.out.println(Preferences.bookingCanceledMsg);
                        exit = true;
                        break;
                }
            }
        }
        catch(NumberFormatException e){
                System.out.println(Preferences.valueInformedInvalid);
                makeBooking(startPoint);
        }
        catch(IOException e){
                e.printStackTrace();
        }
        finally {
            cleanVariables();
        }
    }
    private void cancelBooking(){
       try {
           System.out.println();
           System.out.println(Preferences.tableToCancelMsg);
           String answer = reader.readLine();
           if (answer.substring(0,1).toUpperCase().equals("E") ) {
               return;
           }
           numberOfPeople = Integer.parseInt(answer);
           System.out.println("Sorry, cancelations have not been implemented yet.");
       }
       catch (NumberFormatException e){
           System.out.println(Preferences.valueInformedInvalid);
           cancelBooking();
       }
       catch (IOException e) {
           e.printStackTrace();
       }

    }
    private void printBookings(){
        System.out.println();
        System.out.println("Bookings for today:");
        Restaurant.getInstance().getTables().forEach(t -> System.out.println(t.printStatus()));
        System.out.println();
    }
}
