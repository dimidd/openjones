/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import jones.actions.WorkAction;
import jones.general.Player;
import jones.general.PlayerPosition;
import jones.jobs.Job;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
public class WorkAllWeekPlan extends Plan {

    public WorkAllWeekPlan(Agent agent) {
        super(agent);
        build();
    }

    @Override
    public void build() {
                
        Player player = _agent.getPlayer();
        Job job = player.getJob();
        
        if(job.getRank() == 0) {
            //unemployed
            return;
        }
 
        PlayerPosition work = new PlayerPosition(job.getBuilding().getPosition(), true);
        Plan move = new MoveToPlan(_agent, work);
        setPlan(move);
        
        //after the last movement (i.e. enter building), we work repetetively
        _actions.add(new SetRepetetiveMarker(this, null, true));        
        _actions.add(new NoOpMarker(this, new WorkAction(job)));

    }
    
        
//    public static Plan workAllWeek(Agent agent) {
//        Player player = agent.getPlayer();
//        Job job = player.getJob();
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
