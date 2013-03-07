/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.actions;

import jones.general.Player;
import net.vivin.GenericTreeNode;

/**
 * Used just to access a submenu, may have a duration.
 * Checks only time. Extend, if you need something else 
 * @author dimid
 */
public class SubMenuAction extends Action {

    protected int _duration;
    protected String _name;
    protected GenericTreeNode<Action> _node;
    
    public SubMenuAction(int duration, String name, GenericTreeNode<Action> node) {
        _duration = duration;
        _name = name;
        _node = node;
    }

 
    @Override
    protected void doAction(Player player) {
        //nothing
    }

    @Override
    public int healthEffect(Player player) {
        return 0;
    }

    @Override
    public int happinessEffect(Player player) {
        return 0;
    }

    @Override
    public int careerEffect(Player player) {
        return 0;
    }

    @Override
    public int cashEffect(Player player) {
        return 0;
    }

    @Override
    public int timeEffect(Player player) {
        return getDuration();
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    protected  ActionResponse checkConditions(Player player) {
        return checkTime(player);
    }

    @Override
    protected ActionResponse getPositiveResponse(Player player) {
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
    
    
}
