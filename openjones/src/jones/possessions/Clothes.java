/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.possessions;

import jones.general.Copyable;

/**
 *
 * @author dimid
 */
public class Clothes extends Commodity  {

    protected int _level;
    
        
    	
    public Clothes (int value, String name, int level) {
        super(value, name);
        _level = level;
	
    }

    @Override
    public  Clothes deepCopy() {                
        return new Clothes(_unitValue, _name, _level);
    }

    
     @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this._level;
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
        final Clothes other = (Clothes) obj;
        if (this._level != other._level) {
            return false;
        }
        return true;
    }
    

    
    /**
     * @return the _level
     */
    public int getLevel() {
        return _level;
    }

    /**
     * @param level the _level to set
     */
    public void setLevel(int level) {
        this._level = level;
    }

    
    /**
     * How many weeks this commodity will last
     * @return 
     */
     public int getLifeSpanWeeks () {
        return 8;
    }

    
}
