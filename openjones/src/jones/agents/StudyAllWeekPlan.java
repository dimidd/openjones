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
public class StudyAllWeekPlan extends Plan {

    public StudyAllWeekPlan(Agent agent) {
        super(agent);
        build();
    }

    @Override
    public void build() {
                
        Player player = _agent.getPlayer();
        Game game = _agent.getGame();
        
        PlayerPosition work = new PlayerPosition(game.getMap().getBuildingPositionByName("HI-TECH U"), true);
        Plan move = new MoveToPlan(_agent, work);
        setPlan(move);
        
        //after the last movement (i.e. enter building), we study repetetively
        _actions.add(new SetRepetetiveMarker(this, null, true));        
        _actions.add(new NoOpMarker(this, new StudyAction()));

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
