/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.actions;

import static jones.actions.RentHouseAction.calculateNewRentDebt;
import jones.possessions.RentPossession;
import jones.general.Player;
import jones.general.PlayerState;

/**
 *
 * @author dimid
 */
 public class PayRentAction extends PurchaseAction {



    public PayRentAction(RentPossession rent) {
        super(rent);
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
    public String toString() {
        return "Pay rent for 1 month "+_possession.worth();
    }

    @Override
    protected ActionResponse getPositiveResponse(PlayerState player) {
        return new ActionResponse(true,"I'M here for all your renting needs");
    }

    @Override
    public boolean isSubmenu() {
        return false;
    }

    @Override
    protected void purchaseEffects(PlayerState player) {
        int debt = player.getPossessions().getRentDebt();
        int newDebt = calculateNewRentDebt(debt, _possession.worth());
        player.getPossessions().setRentDebt(newDebt);
    }


    @Override
    public void clearCachedValues() {

    }


}
