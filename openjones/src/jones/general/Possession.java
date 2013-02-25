/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.general;

/**
 *
 * @author dimid
 */
public class Possession {
    
    private int _units;
    private Commodity _commod;
    //private int _unitValue;
    
    
    public Possession (int units, Commodity commod) {
        _units = units;
        _commod = commod;
    }
    
    public int worth () {
        return getUnits() * _commod.getUnitValue();
    }

    /**
     * @return the _units
     */
    public int getUnits() {
        return _units;
    }

    /**
     * @param units the _units to set
     */
    public void setUnits(int units) {
        this._units = units;
    }

   
}
