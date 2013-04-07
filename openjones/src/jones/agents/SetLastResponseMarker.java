/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import jones.actions.Action;
import jones.actions.ActionResponse;
import jones.general.PlayerState;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
class SetLastResponseMarker extends PlanMarker {
    private final ActionResponse _lastResp;

    public SetLastResponseMarker(Plan plan, Action action, ActionResponse lastResp) {
        super(plan, action);
       _lastResp = lastResp;
    }

    @Override
    public void changeState(PlayerState playerState) { 
        _plan.setLastResponse(_lastResp);
    }
    
}
