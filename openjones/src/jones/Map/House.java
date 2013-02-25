/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.Map;

import jones.general.Action;
import jones.general.Position;

/**
 *
 * @author dimid
 */
public abstract class House extends Building {

    /** Create a new Building
    *
    * @param pos
    */
    public House (Position pos, String name) {
        super(pos,name);
        
    }
    
    /**
     *
     * @return
     */
    public abstract Action relax();

    
}
