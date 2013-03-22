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
    
    public boolean isInBuilding() {
        return _isInBuilding;
    }

    public void enterBuilding() {
        this._isInBuilding = true;
    }
    
    public void exitBuilding() {
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
  
      public PlayerPosition(PlayerPosition other) {
        super(other);
        _isInBuilding = other._isInBuilding;
    }
 
        
    public void setPosition (PlayerPosition other) {
        super.setPosition(other);
        _isInBuilding = other._isInBuilding;
    }

    
    @Override
    public String toString () {
        String inout = (_isInBuilding)? "in" : "out";
        return "("+super.toString()+","+inout+")";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this._isInBuilding ? 1 : 0);
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
 
             
        final Position otherPos = (Position) obj;
        if (!super.equals(otherPos))
            return false;
        
        final PlayerPosition otherPpos = (PlayerPosition) obj;
        if (this._isInBuilding != otherPpos._isInBuilding) {
            return false;
        }
        return true;
    }

}
