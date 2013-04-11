/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import external.AStarPlayer;
import java.util.ArrayList;
import java.util.List;
import jones.agents.Plan.PlanType;
import jones.general.Game;
import jones.general.Player;
import jones.measures.Goals;

/**
 *
 * @author dimid
 */
public class SearchPlanner extends PlannerAgent {

    public SearchPlanner(Player p1, Game g) {
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
        
      List<PlanType> foundPlansTypes = AStarPlayer.findPlan(_player.getState(), Goals.MAX_TOTAL_SCORE, this, _game.getMap());
//      Plan first = foundPlans.get(0);
//      for (int i =1; i < foundPlans.size(); ++i)
//          first.concatenate(foundPlans.get(i));
//      _schedule.add(first);
      for (PlanType type: foundPlansTypes) {
          Plan plan = createPlanFromType(type);
          _schedule.add(plan);
      }
      return true;
    }

    private Plan createPlanFromType(PlanType type) {
        Plan result = null;
        switch (type) {
            case BETTER_CLOTHES:   result = new GetBetterClothesPlan(this); break;
            case BETTER_JOB:       result = new GetABetterJobPlan(this); break;
            //case MOVE_TO:          result = new MoveToPlan(this, null, null);  break;
            case REST_ALL_WEEK:    result = new RestAllWeekPlan(this);  break;
            case STUDY_ALL_WEEK:   result = new StudyAllWeekPlan(this);  break;
            case WORK_ALL_WEEK:    result = new WorkAllWeekPlan(this);  break;
        }
        
        return result;
    }

    
}
