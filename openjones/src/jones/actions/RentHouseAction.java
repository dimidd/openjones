/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.actions;

import jones.general.Player;
import jones.map.House;
import net.vivin.GenericTreeNode;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void purchaseEffects(Player player) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ActionResponse getPositiveResponse(Player player) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
        
    @Override
    protected ActionResponse checkConditions(Player player) {
        if (getHouse() == player.getRentContract().getHouse())
            return new ActionResponse(false, "You are already renting this house");
        else
            return checkCash(player);
    }

}
