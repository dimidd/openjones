/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import jones.actions.Action;
import jones.general.Player;
import jones.general.PlayerPosition;
import jones.general.PlayerState;
import jones.jobs.Job;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
class GoToWorkMarker extends PlanMarker {

    public GoToWorkMarker(Plan plan, Action action) {
        super(plan, action);
    }

    @Override
    public void changeState(PlayerState playerState) {
       
        //Player player = _plan.getAgent().getPlayer();
        Job job = playerState.getJob(); 
        _plan.setLastJob(job);
        if(job.getRank() == 0) {
            //unemployed
            _plan._actions.clear();
            //_plan._actions.remove();
            return;
        }
        
 
        PlayerPosition work = new PlayerPosition(job.getBuilding().getPosition(), true);
        _plan.getActions().push(new MoveMarker(this._plan, null, work));

    }
    
}
