/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.Map;

import jones.general.Position;

/**
 * A Passage is a Location that can be passed
 * @author dimid
 */
public abstract class Passage extends Location {

    /** Create a new Passage
    *
    * @param pos
    */
    public Passage (Position pos) {
        super(pos);
    }

    @Override
    public boolean isPassable() {
        return true;
    }
    
}
