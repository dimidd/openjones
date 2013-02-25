/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.general;

/**
 *
 * @author dimid
 */
public class PlayerPosition extends Position {

    private boolean _isInBuilding;
    
    public boolean isIsInBuilding() {
        return _isInBuilding;
    }

    public void enterBuilding() {
        this._isInBuilding = true;
    }
    
    public void leaveBuilding() {
        this._isInBuilding = false;
    }
        
    public PlayerPosition(int x, int y, boolean inside) {
        super(x,y);
        _isInBuilding = inside;
    }
 
    public PlayerPosition(Position pos, boolean inside) {
        super(pos);
        _isInBuilding = inside;
    }
 
}
