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

    public static int manhattanDistance(PlayerPosition pos1, PlayerPosition pos2) {
        return Math.abs(pos1.getX() - pos2.getX()) + Math.abs(pos1.getY() - pos2.getY());
    }
    
    public final int HEIGTH; //rows
    public final int WIDTH;  //cols
    private GridTile [][] _grid;
   
    public  Grid ( int height, int width) {
        
        if (width <= 0 || height <=0) {
            throw new IllegalArgumentException("Dimensions must be positive");
        }
        WIDTH = width;
        HEIGTH = height;
        _grid = new GridTile[height][width];
    }
    
    public boolean checkRange(int x, int y) {
        
         
        if (x < 0 || x >= HEIGTH || y <0 || y >= HEIGTH) {
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
