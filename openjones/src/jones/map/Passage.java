/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.map;

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
        this(pos, "passage");
    }

     public Passage (Position pos, String name) {
        super(pos, name);
    }
    
    @Override
    public boolean isPassable() {
        return true;
    }
    
}
