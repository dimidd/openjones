/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package possessions;

/**
 *
 * @author dimid
 */
public class RentPossesion extends Consumable {
    
    public RentPossesion(int numOfWeeks, WeekOfRent rent) {
        super(numOfWeeks, rent, 1.00);
    }
}
