/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.possessions;

/**
 * A Possession is an amount of units of a Commodity
 *
 * @author dimid
 */
public class Possession {
    
    protected int _units;
    protected Commodity _commodity;
    //private int _unitValue;

    public Commodity getCommodity() {
        return _commodity;
    }

    public void setCommodity(Commodity _commodity) {
        this._commodity = _commodity;
    }
    
    
    public Possession (int units, Commodity commod) {
        _units = units;
        _commodity = commod;
    }
    
    public int worth () {
        return getUnits() * _commodity.getUnitValue();
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

    /**
     * @param units the units to be added
     */
    public void addUnits(int units) {
        this._units += units;
    }

    /**
     * Consume, if this Possession is consumable.
     * Otherwise, do nothing
     * 
     */
    void consume() {
    }

 
    public int getRestHealthEffect() {
        if (_commodity.hasAccumulativeEffect()) {
            return _units * _commodity.getRestHealthEffect();
        }
        else {
            return _commodity.getRestHealthEffect();
        }
    }
   
    public int getRestHappinessEffect() {       
        if (_commodity.hasAccumulativeEffect())
            return _units * _commodity.getRestHappinessEffect();
        else
            return _commodity.getRestHappinessEffect();
    }
    
        
    @Override
    public String toString () {
        return _units +" X "+_commodity.toString();
    }
}
