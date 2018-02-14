package tablebooking;
/**
 * @author Nazareh
 * Class to generate table layouts/combinations
 */
public class LoadTables {
    
    public Area lowerFrontLayoutA() {
        
        Area area = new Area();
        area.addTable(new Table(1, 2));
        area.addTable(new Table(2, 4));
        area.addTable(new Table(3, 2,100));
        area.addTable(new Table(4, 2,101));
        area.addTable(new Table(5, 4));
        area.addTable(new Table(50, 2));
        
        return area ;
    }
    public Area upperFrontLayoutA() {
        
        Area area = new Area();
        
        area.addTable(new Table(7, 6));
        area.addTable(new Table(8, 4));
        area.addTable(new Table(9, 4));
        area.addTable(new Table(10, 6));
        area.addTable(new Table(11, 4));
        area.addTable(new Table(12, 2));
        area.addTable(new Table(13, 4));
        area.addTable(new Table(14, 2));
        area.addTable(new Table(15, 3));
        area.addTable(new Table(17, 6));
        area.addTable(new Table(18, 2));
        area.addTable(new Table(19, 2));
        area.addTable(new Table(20, 4));
        
        return area ;
    }
    
    public Area backRoomLayoutA() {
        
        Area area = new Area();
        
        area.addTable(new Table(21, 4));
        area.addTable(new Table(22, 4));
        area.addTable(new Table(23, 4));
        area.addTable(new Table(24, 4));
        area.addTable(new Table(25, 4));
        area.addTable(new Table(26, 4));
        
        return area ;
    }
}
