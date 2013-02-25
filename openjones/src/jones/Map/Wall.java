/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.Map;

import jones.general.Position;

/**
 *
 * @author dimid
 */
public class Wall extends Location {

    /** Create a new wall
     *
     * @param pos
     */
    public Wall (Position pos) {
        super(pos);
    }

    
    @Override
    public boolean isPassable() {
        return false;
    }

    @Override
    public boolean isVisitable() {
        return false;
    }

    @Override
    public boolean isEnterable() {
        return false;
    }
    
    
}
