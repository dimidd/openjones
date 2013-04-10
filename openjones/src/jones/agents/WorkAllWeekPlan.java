/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import jones.actions.WorkAction;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
public class WorkAllWeekPlan extends WeekPlan {
    //private final PlayerState _playerState;

    public WorkAllWeekPlan(Agent agent) {
        super(agent, PlanType.WORK_ALL_WEEK);
        //_playerState = playerState;
        build();
    }

    @Override
    public void build() {
                
         _actions.add( new GoToWorkMarker(this, null));
        
        //after the last movement (i.e. enter building), we work repetetively
        _actions.add(new SetRepetetiveMarker(this, null, true)); 
        //GetBetterClothesMarker betterClothesMarker = new GetBetterClothesMarker(this, null, _playerState);
        //_actions.add(new PushMarkerOnResponseMarker(this, new WorkAction(_lastJob), betterClothesMarker, false, "dressed properly"));
         _actions.add(new NoOpMarker(this, new WorkAction(_lastJob)));
    }
    
    
}
