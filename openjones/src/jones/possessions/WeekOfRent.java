/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.possessions;

import java.util.Objects;
import jones.map.House;

/**
 *
 * @author dimid
 */
public class WeekOfRent extends Commodity {
    
    private House _house;

     public WeekOfRent (int pricePerWeek, House house) {
        super(pricePerWeek, "Week of rent");
        _house = house;
    }
     
     
    @Override
    public WeekOfRent deepCopy() {
        return new WeekOfRent(_unitValue, _house);
    }
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this._house);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WeekOfRent other = (WeekOfRent) obj;
        if (!Objects.equals(this._house, other._house)) {
            return false;
        }
        return true;
    }

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
    
    
   
}
