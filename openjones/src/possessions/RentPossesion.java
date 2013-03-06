/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package possessions;

/**
 *
 * @author dimid
 */
public class RentPossesion extends Possession {
    public static final int WEEKS_OF_RENT = 4;
    
    public RentPossesion(Rent rent) {
        super(WEEKS_OF_RENT, rent);
    }
}
