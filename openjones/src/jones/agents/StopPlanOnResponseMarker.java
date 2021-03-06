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
class StopPlanOnResponseMarker extends PlanMarker {
    private final boolean _responseStopValue;


    public StopPlanOnResponseMarker(Plan plan, Action action, boolean b) {
       super(plan, action);
       _responseStopValue = b;
    }

    @Override
    public void changeState(PlayerState playerState) {
        if (null !=_plan.getLastResponse() && _plan.getLastResponse()._isPositive == _responseStopValue) {
            _action = null;
            _plan.getActions().clear();
        }
    }
    
}
