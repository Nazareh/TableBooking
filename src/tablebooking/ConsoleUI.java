package tablebooking;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Nazareh
 */
public class ConsoleUI {
    
    public void welcome() {
        boolean exit = false;
        
        
        do{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println();
            System.out.println(Preferences.getFirstScreenMsg());
            try { 
                String answer = reader.readLine().substring(0, 1).toUpperCase();
                switch (answer) {
                    case "E": exit = true; break;
                    case "B": makeBooking();break;
                    case "C": cancelBooking();break;
                    case "S": printBookings();break;
                    default:  System.out.println();
                              System.out.println(Preferences.invalidAnswerMsg); 
                              

                }
                exit = (answer.equals("E"))? true : false;
            }
            catch (Exception e ){
                System.out.println(e);
            }
        }
        while (exit == false);
}
    private void makeBooking(){
        //TO BE IMPLEMENTED
    }
    private void cancelBooking(){
        //TO BE IMPLEMENTED
    }
    private void printBookings(){
        System.out.println();
        System.out.println("Bookings for today:");

        for (Table table : Restaurant.getInstance().getTables()) {
            System.out.println(table.printStatus());
            
        }
        System.out.println();
    }
}
