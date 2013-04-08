/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import java.util.ArrayList;
import java.util.List;
import jones.general.Game;
import jones.general.Player;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
class OrderedPlanner extends PlannerAgent {

    public OrderedPlanner(Player p1, Game g) {
        super(p1, g);
    }

        
    @Override
    public boolean hasNextAction() {
      if (!hasNextActionInSchedule())     
        _schedule.addAll(getOrderedPlans());
      return true;
    }

    protected List<Plan> getOrderedPlans() {                
        
        ArrayList<Plan> result = new ArrayList<>();
        result.add(new StudyAllWeekPlan(this));                       
        result.add(new RestAllWeekPlan(this));
        result.add(new GetABetterJobPlan(this, _player.getState()));
//        _player.setJob(_game.getMap().getBuildingByName("Monolith Burgers").getJobs().get(0));
        result.add(new WorkAllWeekPlan(this, _player.getState()));
                        
        return result;
    }

}
