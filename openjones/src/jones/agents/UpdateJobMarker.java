/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import jones.actions.Action;
import jones.general.PlayerState;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
class UpdateJobMarker extends PlanMarker {

    public UpdateJobMarker(Plan plan, Action action) {
        super(plan, action);
    }

    @Override
    public void changeState(PlayerState playerState) {
        _plan.setLastJob(playerState.getJob());
    }

    
}
