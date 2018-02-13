/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablebooking;


/**
 *
 * @author Nazareh
 */
public class GenerateTables {
    
    public Area lowerFrontLayoutA() {
       Area area = new Area();
       //tables 
       new Table(1, 2);
        area.addTable(new Table(1, 2));
     
        area.addTable(new Table(3, 2));
        area.addTable(new Table(4, 2));
        area.addTable(new Table(2, 4));
        area.addTable(new Table(5, 4,1));
        area.addTable(new Table(50, 2));
        
        return area ;
    }
    
    
}
