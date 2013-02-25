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
public abstract class Location implements GridTile {
    private Position _pos;

    /**
     * @return the Position
     */
    public Position getPosition() {
        return _pos;
    }
    
    public Location (Position pos) {
        _pos = pos;
    }
    
}
