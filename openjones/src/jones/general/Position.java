/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.general;

/**
 *
 * @author dimid
 */
public class Position {
    
    private int _x;
    private int _y;

    public int getX() {
        return _x;
    }

    public int getY() {
        return _y;
    }

    public Position (int x, int y) {
        _x = x;
        _y = y;
    }
   
    public Position (Position other) {
        _x = other._x;
        _y = other._y;
    }
    
    public void setPosition (Position other) {
        _x = other._x;
        _y = other._y;
    }

    public void setXY(int x, int y) {
        _x = x;
        _y = y;

    }

    public String toString () {
        return "("+_x+","+_y+")";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + this._x;
        hash = 43 * hash + this._y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
        final Position other = (Position) obj;
        if (this._x != other._x) {
            return false;
        }
        if (this._y != other._y) {
            return false;
        }
        return true;
    }
    
 
}
