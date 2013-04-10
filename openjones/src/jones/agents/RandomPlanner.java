/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import java.util.Random;
import jones.general.Game;
import jones.general.Player;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
public class RandomPlanner extends PlannerAgent {
 
    
    public RandomPlanner(Player p1, Game g) {
        super(p1, g);
    }
    
        
    @Override
    public boolean hasNextAction() {
      if (!hasNextActionInSchedule())          
        _schedule.add(getRandomPlan());
      return true;
    }

    protected Plan getRandomPlan() {
                
        Random random = new Random();
        int choice = random.nextInt(N_PLANS);
        switch (choice) {
                
                case STUDY_PLAN_INDEX: return new StudyAllWeekPlan(this);            
                case JOB_PLAN_INDEX:   return new GetABetterJobPlan(this, _player.getState());
                case WORK_PLAN_INDEX:  return new WorkAllWeekPlan(this);
                case REST_PLAN_INDEX:  return new RestAllWeekPlan(this);
                default:               return new RestAllWeekPlan(this);                                
                        
        }
    }

    
}
