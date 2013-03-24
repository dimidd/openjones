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
class StopPlanOnResponseMarker extends PlanMarker {
    private final boolean _responseStopValue;


    public StopPlanOnResponseMarker(Plan plan, Action action, boolean b) {
       super(plan, action);
       _responseStopValue = b;
    }

    @Override
    public void changeState() {
        if (_plan.getLastResponse()._isPositive == _responseStopValue) {
            _action = null;
            _plan.getActions().clear();
        }
    }
    
}
