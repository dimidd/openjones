/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.Map;

/**
 * A tile is defined by a hierarchy of 3 binary state-variables. 
 * Passable: The player may only pass it,
 *           i.e. only pass there when moving to another tile, not "land" there
 * Visitable: The player may pass and visit.
 * Enterable: The player may pass, visit and enter.
 *
 * @author dimid
 */
public interface GridTile {
    
    public boolean isPassable();
    public boolean isVisitable();
    public boolean isEnterable();
}
