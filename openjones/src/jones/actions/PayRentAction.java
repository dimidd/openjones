/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.actions;

import jones.possessions.RentPossession;
import jones.general.Player;

/**
 *
 * @author dimid
 */
 public class PayRentAction extends PurchaseAction {
     
	 

    public PayRentAction(RentPossession rent) {
        super(rent);
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
    public String toString() {
        return "Pay rent for 1 month "+_possession.worth();
    }

    @Override
    protected ActionResponse getPositiveResponse(Player player) {
        return new ActionResponse(true,"I'M here for all your renting needs");
    }

    @Override
    public boolean isSubmenu() {
        return false;
    }

    @Override
    protected void purchaseEffects(Player player) {
        
    }
    
      
    @Override
    public void clearCachedValues() {
      
    }

    
}
