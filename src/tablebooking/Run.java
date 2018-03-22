package tablebooking;

/**
 * @author Nazareh nazarehturmina@gmail.com
 */
class Run {

    public static void main(String[] args) {
        Restaurant restaurant = Restaurant.getInstance();
        restaurant.setTablesLayout(restaurant.loadTablesLayoutA ());

        ConsoleUI c = new ConsoleUI();
        c.welcome();
    }
}
