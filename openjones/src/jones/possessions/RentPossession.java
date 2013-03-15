/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.possessions;

/**
 *
 * @author dimid
 */
public class RentPossession extends ConsumablePossession {
    
    public RentPossession(int numOfWeeks, WeekOfRent rent) {
        super(numOfWeeks, rent, 1.00);
    }
}
