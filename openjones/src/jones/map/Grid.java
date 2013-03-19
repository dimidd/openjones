/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.map;

import jones.general.PlayerPosition;
import jones.general.Position;

/**
 *
 * @author dimid
 */
public class Grid<T extends GridTile> {

    public static int manhattanDistance(Position pos1, Position pos2) {
        return Math.abs(pos1.getX() - pos2.getX()) + Math.abs(pos1.getY() - pos2.getY());
    }
    
    public final int HEIGHT; //rows
    public final int WIDTH;  //cols
    private GridTile [][] _grid;
   
    public  Grid (int width, int height) {
        
        if (width <= 0 || height <=0) {
            throw new IllegalArgumentException("Dimensions must be positive");
        }
        WIDTH = width;
        HEIGHT = height;
        _grid = new GridTile[width][height];
    }
    
    public boolean checkRange(int x, int y) {
        
         
        if (x < 0 || x >=  WIDTH|| y <0 || y >= HEIGHT) {
            throw new IllegalArgumentException("Indices must be in range");
        }
        
        return true;
    }
    
    public GridTile get (Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        if (checkRange(x, y)) {
            return _grid[x][y];
        }
        
        return null;
    }
    
     public void set (Position pos, GridTile tile) {       
        int x = pos.getX();
        int y = pos.getY();
        if (checkRange(x, y)) {
             _grid[x][y] = tile;
        }
         
     }
    
}
