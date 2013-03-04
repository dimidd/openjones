/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.actions;

import jones.general.Player;

/**
 * Used just to access a submenu, may have a duration.
 * @author dimid
 */
public class SubMenuAction extends Action {

    private int _duration;
    private String _name;
    
    public SubMenuAction(int duration, String name) {
        _duration = duration;
        _name = name;
    }

    @Override
    protected String checkConditions(Player player) {
        return checkTime(player);
    }

    @Override
    protected void doAction(Player player) {
        
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
        return _duration;
    }

    @Override
    public String toString() {
        return _name;
    }
    
    
}
