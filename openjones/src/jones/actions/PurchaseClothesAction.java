/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.actions;

import jones.general.Player;
import jones.general.PlayerState;
import jones.possessions.ConsumablePossession;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
public class PurchaseClothesAction extends PurchaseAction {

    public PurchaseClothesAction(ConsumablePossession clothesPoss) {
       super(clothesPoss);
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
    protected void purchaseEffects(PlayerState player) {
        
    }

    @Override
    protected ActionResponse getPositiveResponse(PlayerState player) {
        return new ActionResponse(true, "Great outfit!");
    }

    @Override
    public void clearCachedValues() {
        
    }
    
    @Override
    public String toString() {
        return _possession.getCommodity().toString()+" "+_possession.worth()+"$";
    }

}
