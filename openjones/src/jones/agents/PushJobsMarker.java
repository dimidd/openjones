/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import java.util.ArrayList;
import jones.actions.Action;
import jones.actions.ActionResponse;
import jones.actions.ApplyForJobAction;
import jones.actions.SubMenuAction;
import jones.general.Game;
import jones.general.PlayerState;
import jones.map.Building;

/**
 * Push all building's job to the head of the queue
 * @author dimid <dimidd@gmail.com>
 */
class PushJobsMarker extends PlanMarker {

    public PushJobsMarker(Plan plan, SubMenuAction buildingJobs) {
        super(plan, buildingJobs);
    }

    @Override
    public void changeState(PlayerState playerState) {
        Agent agent = _plan.getAgent();
        Game game = agent.getGame();
         ArrayList<? extends Action> possibletActions = playerState.getPossibleActions(game.getMap());
         
         //we set the last response to false (currently it is true because of the submenu action of the building),
         //so it would change to true only after being hired
         ActionResponse dummy = new ActionResponse(false, null);
//         _plan.getActions().push(new SetLastResponseMarker(_plan, null, dummy));       
         _plan.setLastResponse(dummy);
         
         //the first action is "back"             
        _plan.getActions().push(new StopPlanOnResponseMarker(_plan, possibletActions.get(0), true));
        
        
        //we push all actions with StopPlan, save the last. 
        //it's because the last one would be executed (i.e. remove-ed) first.
        //only then we need to check the response and stop if positive (i.e. got the job)
        ApplyForJobAction apply;
        for (int i= Building.LAST_INDEX_OF_SPECIAL_ACTION + 1; i< possibletActions.size() - 1; ++i) {
            apply = (ApplyForJobAction) possibletActions.get(i);
            if (apply.getJob().getWagePerTimeUnit() > playerState.getJob().getWagePerTimeUnit()) {
                _plan.getActions().push(new StopPlanOnResponseMarker(_plan, apply, true));
            }
        }
        apply = (ApplyForJobAction) possibletActions.get(possibletActions.size() - 1);
        if (apply.getJob().getWagePerTimeUnit() > playerState.getJob().getWagePerTimeUnit())
            _plan.getActions().push(new NoOpMarker(_plan, apply));
                                
    }
    
}
