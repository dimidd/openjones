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
 
}
