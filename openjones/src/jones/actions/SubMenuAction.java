/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.actions;

import java.util.Objects;
import jones.general.Player;
import jones.general.PlayerState;
import jones.map.Building;
import net.vivin.GenericTreeNode;

/**
 * Used just to access a submenu, may have a duration.
 * Checks only time. Extend, if you need something else 
 * @author dimid
 */
public class SubMenuAction extends Action {

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this._duration;
        hash = 17 * hash + this._name.hashCode();
        hash = 17 * hash + this._node.hashCode();
        hash = 17 * hash + this._building.hashCode();
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
        final SubMenuAction other = (SubMenuAction) obj;
        if (this._duration != other._duration) {
            return false;
        }
        if (this._name != null && !this._name.equals(other._name)) {
            return false;
        }
        if (!(this._node == other._node)) {
            return false;
        }
        if (!(this._building == other._building)) {
            return false;
        }
        return true;
    }

    protected int _duration;
    protected String _name;
    protected GenericTreeNode<Action> _node;
    private final Building _building;
    
    public SubMenuAction(int duration, String name, GenericTreeNode<Action> node, Building build) {
        _duration = duration;
        _name = name;
        _node = node;
        _building = build;
    }

 
    @Override
    protected void doAction(PlayerState player) {
        _building.setPlayerActionsParent(_node);
    }

    @Override
    public int healthEffect(PlayerState player) {
        return 0;
    }

    @Override
    public int happinessEffect(PlayerState player) {
        return 0;
    }

    @Override
    public int careerEffect(PlayerState player) {
        return 0;
    }

    @Override
    public int cashEffect(PlayerState player) {
        return 0;
    }

    @Override
    public int timeEffect(PlayerState player) {
        return getDuration();
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    protected  ActionResponse checkConditions(PlayerState player) {
        return checkTime(player);
    }

    @Override
    protected ActionResponse getPositiveResponse(PlayerState player) {
        return new ActionResponse(true, null);
    }

    @Override
    public boolean isSubmenu() {
        return true;
    }

    /**
     * @return the _duration
     */
    public int getDuration() {
        return _duration;
    }

    /**
     * @param duration the _duration to set
     */
    public void setDuration(int duration) {
        this._duration = duration;
    }

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

    /**
     * @return the _node
     */
    public GenericTreeNode<Action> getNode() {
        return _node;
    }

    /**
     * @param node the _node to set
     */
    public void setNode(GenericTreeNode<Action> node) {
        this._node = node;
    }
    
      
    @Override
    public void clearCachedValues() {
      
    }

    
}
