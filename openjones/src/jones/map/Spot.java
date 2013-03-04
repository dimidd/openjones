/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.map;

import jones.general.Position;

/**
 *
 * @author dimid
 */
public class Spot extends Passage {

     /** Create a new spot
     *
     * @param pos
     */
    public Spot (Position pos) {
        super(pos);
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
