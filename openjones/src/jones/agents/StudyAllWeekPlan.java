/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import jones.actions.StudyAction;
import jones.general.Game;
import jones.general.Player;
import jones.general.PlayerPosition;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
public class StudyAllWeekPlan extends WeekPlan {

    public StudyAllWeekPlan(Agent agent) {
        super(agent, PlanType.STUDY_ALL_WEEK);
        build();
    }

    @Override
    public void build() {
                
        Player player = _agent.getPlayer();
        Game game = _agent.getGame();
        
        PlayerPosition college = new PlayerPosition(game.getMap().getBuildingPositionByName("HI-TECH U"), true);
        //Plan move = new MoveToPlan(_agent, work);
        _actions.add(new MoveMarker(this, null, college));
        //setPlan(move);
        
        //after the last movement (i.e. enter building), we study repetetively
        //until we run out of cash or time
        _actions.add(new SetRepetetiveMarker(this, null, true));                        
        _actions.add(new StopPlanOnResponseMarker(this, new StudyAction(), false));

    }
    
//    
//       
//    public static Plan studyAllWeek(Agent agent) {
//        Player player = agent.getPlayer();
//        Game game = agent.getGame();
//        
//        PlayerPosition work = new PlayerPosition(game.getMap().getBuildingPositionByName("HI-TECH U"), true);
//        Plan result = moveTo(agent, work);
//        
//        //after the last movement (i.e. enter building), we study repetetively
//        result._actions.add(new SetRepetetiveMarker(result, null, true));        
//        result._actions.add(new NoOpMarker(result, new StudyAction()));
//        
//        return result;       
//    }

}
