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
class UpdateJobMarker extends PlanMarker {

    public UpdateJobMarker(Plan plan, Action action) {
        super(plan, action);
    }

    @Override
    public void changeState() {
        _plan.setLastJob(_plan.getAgent().getPlayer().getJob());
    }

    
}
