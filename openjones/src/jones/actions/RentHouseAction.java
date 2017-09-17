/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.actions;

import jones.general.Player;
import jones.general.PlayerState;
import jones.map.House;
import jones.possessions.Possession;
import jones.possessions.PossessionManager;
import jones.possessions.RentContract;
import jones.possessions.RentPossession;
import jones.possessions.WeekOfRent;

/**
 *
 * @author dimid
 */
public class RentHouseAction extends PurchaseAction {

    public RentHouseAction(House h, int numOfWeeks) {
        super (new RentPossession(numOfWeeks, new WeekOfRent(h.pricePerWeek(), h)));
    }

    @Override
    public int healthEffect(PlayerState player) {
        return 0;
    }

    public House getHouse() {
        return ((WeekOfRent)_possession.getCommodity()).getHouse();
    }
    
    @Override
    public int happinessEffect(PlayerState player) {
    	return 0;
    }

    @Override
    protected void purchaseEffects(PlayerState player) {
        PossessionManager pss = player.getPossessions();
        pss.remove(pss.getRentPossession());

        player.setRentContract(new RentContract(new RentPossession (_possession)));
        int debt = pss.getRentDebt();
        if (debt <= _possession.worth()) {
            player.getPossessions().setRentDebt(0);
        }
        else {
            player.getPossessions().setRentDebt(debt - _possession.worth());
        }
    }

    @Override
    protected ActionResponse getPositiveResponse(PlayerState player) {
    	return new ActionResponse(true, "Tell all your rich friends about us");
    }
    
        
    @Override
    protected ActionResponse checkConditions(PlayerState player) {
        if (getHouse() == player.getRentContract().getHouse()) {
            return new ActionResponse(false, "You are already renting this house");
        }
        else {
            return checkCash(player);
        }
    }
    
     
    @Override
    public String toString () {
        return "Rent "+getHouse().getName()+" "+getHouse().getPricePerMonth();
    }

      
    @Override
    public void clearCachedValues() {
        
      
    }
    
     
    @Override
    public boolean shouldRebuildActions () {
        return true;
    }
  


}
