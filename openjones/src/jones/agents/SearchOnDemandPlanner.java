/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import external.AStarPlayer;
import java.util.List;
import jones.agents.Plan.PlanType;
import jones.general.Game;
import jones.general.Player;
import jones.measures.Goals;

/**
 *
 * @author dimid
 */
public class SearchOnDemandPlanner extends SearchPlanner {

    public SearchOnDemandPlanner(Player p1, Game g) {
        super(p1, g);
    }
    
       
    @Override
    public boolean hasNextAction() {
      while (_schedule.size() > 0) {
          if (getCurPlan().size() > 0) {
              return true;
          }
          else {
              _schedule.remove();
          }
      }
        
      List<PlanType> foundPlansTypes = AStarPlayer.findPlan(_player.getState(), Goals.MAX_TOTAL_SCORE, this, _game.getMap(), true);
//      Plan first = foundPlans.get(0);
//      for (int i =1; i < foundPlans.size(); ++i)
//          first.concatenate(foundPlans.get(i));
//      _schedule.add(first);
      for (PlanType type: foundPlansTypes) {
          Plan plan = createPlanFromType(type);
          if (plan == null) {
              int fuck = -1;
          }
          _schedule.add(plan);
      }
      return true;
    }

    
}
