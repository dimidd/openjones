/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import jones.actions.WorkAction;
import jones.general.Player;
import jones.general.PlayerPosition;
import jones.general.PlayerState;
import jones.jobs.Job;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
public class WorkAllWeekPlan extends WeekPlan {
    private final PlayerState _playerState;

    public WorkAllWeekPlan(Agent agent, PlayerState playerState) {
        super(agent);
        _playerState = playerState;
        build();
    }

    @Override
    public void build() {
        
        
         _actions.add( new GoToWorkMarker(this, null));
//         _actions.add(new UpdateJobMarker(this, null));       
//        Player player = _agent.getPlayer();
//        ///Job job; 
//        
//        if(_lastJob.getRank() == 0) {
//            //unemployed
//            return;
//        }
//        
// 
//        PlayerPosition work = new PlayerPosition(_lastJob.getBuilding().getPosition(), true);
//        _actions.add(new MoveMarker(this, null, work));
//        Plan move = new MoveToPlan(_agent, work);
//        setPlan(move);
//        
        
        //after the last movement (i.e. enter building), we work repetetively
        _actions.add(new SetRepetetiveMarker(this, null, true)); 
        //GetBetterClothesMarker betterClothesMarker = new GetBetterClothesMarker(this, null, _playerState);
        //_actions.add(new PushMarkerOnResponseMarker(this, new WorkAction(_lastJob), betterClothesMarker, false, "dressed properly"));
         _actions.add(new NoOpMarker(this, new WorkAction(_lastJob)));
    }
    
        
//    public static Plan workAllWeek(Agent agent) {
//        Player player = agent.getPlayer();
//        Job job = player.getLastJob();
//        
//        if(job.getRank() == 0) {
//            //unemployed
//            return null;
//        }
// 
//        PlayerPosition work = new PlayerPosition(job.getBuilding().getPosition(), true);
//        Plan result = moveTo(agent, work);
//        
//        //after the last movement (i.e. enter building), we work repetetively
//        result._actions.add(new SetRepetetiveMarker(result, null, true));        
//        result._actions.add(new NoOpMarker(result, new WorkAction(job)));
//        
//        return result;       
//    }

    
}
