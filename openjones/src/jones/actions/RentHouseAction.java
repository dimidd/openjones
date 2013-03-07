/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.actions;

import jones.general.Player;
import jones.map.House;
import possessions.RentPossesion;
import possessions.WeekOfRent;

/**
 *
 * @author dimid
 */
public class RentHouseAction extends PurchaseAction {

    public RentHouseAction(House h, int numOfWeeks) {
        WeekOfRent rent = new WeekOfRent(h.pricePerWeek(), h);
        _possession = (new RentPossesion(numOfWeeks, rent)) ;
    }

    @Override
    public int healthEffect(Player player) {
        return 0;
    }

    public House getHouse() {
        return ((WeekOfRent)_possession.getCommodity()).getHouse();
    }
    
    @Override
    public int happinessEffect(Player player) {
    	return 0;
    }

    @Override
    protected void purchaseEffects(Player player) {
    	player.setRentPossession(_possession);
    	player.getRentContract().setPossession(_possession);
    }

    @Override
    protected ActionResponse getPositiveResponse(Player player) {
    	return new ActionResponse(true, "Tell all your rich friends about us");
    }
    
        
    @Override
    protected ActionResponse checkConditions(Player player) {
        if (getHouse() == player.getRentContract().getHouse())
            return new ActionResponse(false, "You are already renting this house");
        else
            return checkCash(player);
    }

}
