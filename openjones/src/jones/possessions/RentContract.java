/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.possessions;

import jones.map.House;

/**
 *
 * @author dimid
 */
public class RentContract extends Contract {
    public RentContract (RentPossesion rentPoss) {
        super(rentPoss);
    }
    
    @Override
    public RentPossesion getPossession() {
        return (RentPossesion) _possession;
    }

    public House getHouse() {
        return ((WeekOfRent)_possession.getCommodity()).getHouse();       
    }
}
