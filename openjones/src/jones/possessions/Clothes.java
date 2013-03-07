/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.possessions;

/**
 *
 * @author dimid
 */
public class Clothes extends Commodity {
    
    protected int _level;
    	
    public Clothes (int value, String name, int level) {
        super(value, name);
        _level = level;
	
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

}
