/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.actions;

import jones.general.Player;
import jones.possessions.Possession;

/**
 *
 * @author dimid
 */
public abstract class PurchaseAction extends Action {
    
    protected PurchaseAction (Possession poss) {
        _possession = poss;
    }
    
    protected Possession _possession;

    @Override
    protected ActionResponse checkConditions(Player player) {
        return checkCash(player);
    }

    @Override
    protected void doAction(Player player) {
        player.getState().affectCash(-_possession.worth());
        player.getState().getPossessions().add(_possession);
        purchaseEffects(player);
    }

    @Override
    public abstract int healthEffect(Player player);

    @Override
    public abstract int happinessEffect(Player player);

//    @Override
//    public int WealthEffect(Player player) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public int careerEffect(Player player) {
        return 0;
    }

    @Override
    public int cashEffect(Player player) {
        return -_possession.worth();
    }

    @Override
    public int timeEffect(Player player) {
        return 0;
    }

    @Override
    public String toString() {
        return _possession.toString()+" "+_possession.worth()+"$";
    }

    /**
     * Adds purchase-specific effects (besides cash and possession).
     * e.g. Rent may change player`s home. Clothes may change player`s clothes
     * @param player 
     */
    protected abstract void purchaseEffects(Player player); 
    
    
    @Override
    public boolean isSubmenu() {
        return false;
    }

    protected ActionResponse checkCash(Player player) {
        if (player.getState().getCash() < _possession.worth()) {
            return new ActionResponse(false, "Not enough money");
        } else {
            return new ActionResponse(true, null);
        }
    }

    
}
