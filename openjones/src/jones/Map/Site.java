/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.Map;

import jones.general.Position;

/**
 * A Site is a Location that can be visited
 * 
 * @author dimid
 */
abstract class Site extends Passage {
    
    /** Create a new Site
    *
    * @param pos
    */
    public Site (Position pos) {
        super(pos);
    }

    
    @Override
    public boolean isVisitable() {
        return true;
    }
}
