/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.actions;

import java.util.Objects;
import jones.general.Player;
import jones.possessions.Possession;

/**
 *
 * @author dimid
 */
public abstract class PurchaseAction extends Action {

    public Possession getPossession() {
        return _possession;
    }

    public void setPossession(Possession _possession) {
        this._possession = _possession;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this._possession.hashCode();
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
        final PurchaseAction other = (PurchaseAction) obj;
        if (!this._possession.equals(other._possession)) {
            return false;
        }
        return true;
    }

    static ActionResponse checkCash(Player player, int cashEffect) {
                
        if (player.getState().getCash() < cashEffect) {
            return new ActionResponse(false, "Not enough money");
        } else {
            return new ActionResponse(true, null);
        }

    }
    
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
        return checkCash(player, _possession.worth());
    }

    
}
