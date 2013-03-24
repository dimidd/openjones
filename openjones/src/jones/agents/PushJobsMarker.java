/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import java.util.ArrayList;
import jones.actions.Action;
import jones.actions.ApplyForJobAction;
import jones.actions.SubMenuAction;
import jones.general.Game;

/**
 * Push all building's job to the head of the queue
 * @author dimid <dimidd@gmail.com>
 */
class PushJobsMarker extends PlanMarker {

    public PushJobsMarker(Plan plan, SubMenuAction buildingJobs) {
        super(plan, buildingJobs);
    }

    @Override
    public void changeState() {
        Agent agent = _plan.getAgent();
        Game game = agent.getGame();
         ArrayList<? extends Action> possibletActions = game.getPossibletActions();
        
         //the first action is "back"             
        _plan.getActions().push(new StopPlanOnResponseMarker(_plan, possibletActions.get(0), true));
        
        
        //we push all actions with StopPlan, save the last. 
        //it's because the last one would be executed (i.e. remove-ed) first.
        //only then we need to check the response and stop if positive (i.e. got the job)
        for (int i= 1; i< possibletActions.size() - 1; ++i) {
            ApplyForJobAction apply = (ApplyForJobAction) possibletActions.get(i);
            if (apply.getJob().getWagePerTimeUnit() > agent.getPlayer().getJob().getWagePerTimeUnit()) {
                _plan.getActions().push(new StopPlanOnResponseMarker(_plan, apply, true));
            }
        }                
        _plan.getActions().push(new NoOpMarker(_plan, possibletActions.get(possibletActions.size() - 1)));
                                
    }
    
}
