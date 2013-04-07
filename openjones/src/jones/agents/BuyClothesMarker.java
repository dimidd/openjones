/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import jones.actions.Action;
import jones.actions.PurchaseClothesAction;
import jones.actions.SubMenuAction;
import jones.general.PlayerState;
import jones.map.Building;
import jones.possessions.Clothes;

/**
 * Buy better clothes than the one you currently own
 * @author dimid <dimidd@gmail.com>
 */
class BuyClothesMarker extends PlanMarker {

    public BuyClothesMarker(Plan plan, Action action) {
        super(plan, action);
    }

    @Override
    public void changeState(PlayerState playerState) {
               
        PurchaseClothesAction clothesAction;

        for (int buildingIndex = Building.LAST_INDEX_OF_SPECIAL_ACTION + 1; buildingIndex < _plan.getPossibletActions().size(); ++buildingIndex) {
            //for (Integer buildingIndex : buildingsWithProfitableJobs) {
            //assert (buildingIndex > Building.LAST_INDEX_OF_SPECIAL_ACTION);
            clothesAction = (PurchaseClothesAction) _plan.getPossibletActions().get(buildingIndex);
            Clothes clothes = (Clothes) clothesAction.getPossession().getCommodity();
            if (clothes.getLevel() > _plan.getPlayer().getClothesLevel()) {
                _plan.getActions().add(new StopPlanOnResponseMarker(_plan, clothesAction, true));
            }
           
        }

    }

    
   
}
