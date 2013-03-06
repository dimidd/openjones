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

    
    protected abstract ActionResponse checkConditions(Player player);

    protected abstract void doAction(Player player);

    /**
     * If possible, performs the action.
     * Otherwise, returns an explanation why it can't be performed 
     * @param player
     * @return Returns null iff the action performed.
     *          otherwise returns a String with an explanation
     */
    public final ActionResponse perform(Player player) {
        ActionResponse preConditions = checkConditions(player);
        if (!preConditions._wasPerformed) {
            return preConditions;
        }
        doAction(player);
        player.getState().affectHealth(healthEffect(player));
        player.getState().affectHappiness(happinessEffect(player));
        player.getState().affectCareer(careerEffect(player));
        player.getState().affectCash(cashEffect(player));
        player.getState().affectTime(timeEffect(player));
            
        return getPositiveResponse();
    }

    public abstract int healthEffect(Player player); 

    public abstract int happinessEffect(Player player);

    //public abstract int WealthEffect(Player player);

    public abstract int careerEffect(Player player);
     
    public abstract int cashEffect(Player player);
    
    public abstract int timeEffect(Player player);
   
    @Override
    public abstract String toString();

    protected ActionResponse checkTime(Player player) {
        if (player.getState().timeLeft() < timeEffect(player)) {
            return new ActionResponse(true, null);
        }
        else {
            return new ActionResponse(false,"Not enough time");
        }
    }

    protected abstract ActionResponse getPositiveResponse();
    
    /**
     * Return true iff this action leads to a menu of other actions
     * @return
     */
    public abstract boolean isSubmenu();

    /**
     * Return the max period a player has upto a limit.
     * @param limit The max period
     * @param player
     * @return Iff the player, doesn't have time at all, return Integer.MAX_VALUE.
     *          Otherwise, return the max period the player has upto a limit
     *
     */
    protected int getAvailiableTimeUpto(int limit, Player player) {
        if (!player.hasTime()) {
            return Integer.MAX_VALUE;
        }
        return Math.min(limit, player.timeLeft());
    }

       
}
