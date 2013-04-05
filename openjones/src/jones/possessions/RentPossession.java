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

    public RentPossession (RentPossession other) {
        super(other);
    }
  
    
    public RentPossession(int numOfWeeks, WeekOfRent rent) {
        super(numOfWeeks, rent, 1.00);
    }

    public RentPossession(Possession possession) {
        this(possession._units, (WeekOfRent)possession._commodity);
    }
}
