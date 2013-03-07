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
public class WeekOfRent extends Commodity {
    
    private House _house;

    public House getHouse() {
        return _house;
    }

    public void setHouse(House _house) {
        this._house = _house;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }
    
    
    public WeekOfRent (int pricePerWeek, House house) {
        super(pricePerWeek, "Week of rent");
        _house = house;
    }
}
