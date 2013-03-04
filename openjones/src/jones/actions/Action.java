/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.actions;

import jones.general.Player;

/**
 *
 * @author dimid
 */
public abstract class Action {

    
    protected abstract String checkConditions(Player player);

    protected abstract void doAction(Player player);

    /**
     * If possible, performs the action.
     * Otherwise, returns an explanation why it can't be performed 
     * @param player
     * @return Returns null iff the action performed.
     *          otherwise returns a String with an explanation
     */
    public final String perform(Player player) {
        String preConditions = checkConditions(player);
        if (null != preConditions) {
            return preConditions;
        }
        doAction(player);
        player.getState().affectHealth(healthEffect(player));
        player.getState().affectHappiness(happinessEffect(player));
        player.getState().affectCareer(careerEffect(player));
        player.getState().affectCash(cashEffect(player));
        player.getState().affectTime(timeEffect(player));
            
        return null;
    }

    public abstract int healthEffect(Player player); 

    public abstract int happinessEffect(Player player);

    //public abstract int WealthEffect(Player player);

    public abstract int careerEffect(Player player);
     
    public abstract int cashEffect(Player player);
    
    public abstract int timeEffect(Player player);
   
    @Override
    public abstract String toString();

    protected String checkTime(Player player) {
        if (player.getState().timeLeft() < timeEffect(player)) {
            return null;
        }
        else {
            return "Not enough time";
        }
    }
}
