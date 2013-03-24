/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import jones.actions.Action;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
class GetBetterClothesMarker  extends PlanMarker{

    public GetBetterClothesMarker(Plan plan, Action action) {
        super(plan, action);
    }

    @Override
    public void changeState() {
        _plan.push(new GetBetterClothesPlan(_plan.getAgent()));
    }

    
}
