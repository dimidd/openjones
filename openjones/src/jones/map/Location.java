/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.map;

import jones.general.Position;

/**
 *
 * @author dimid
 */
public abstract class Location implements GridTile {
    private Position _pos;
    protected String _name;

    /**
     * @return the Position
     */
    public Position getPosition() {
        return _pos;
    }
    
    public Location (Position pos, String name) {
        _pos = new Position(pos);
        _name = name;
    }
    
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    //    private Collection<? extends Action> getMenuActions(PlayerState player) {
    //        assert (null != getPlayerActionsParent());
    //        return getPlayerActionsParent().getDataOfChildren();
    //    }
    /**
     * @return the _name
     */
    public String getName() {
        return _name;
    }

    /**
     * @param name the _name to set
     */
    public void setName(String name) {
        this._name = name;
    }
}
