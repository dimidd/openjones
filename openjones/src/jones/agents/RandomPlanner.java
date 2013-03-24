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

    public static final int N_PLANS = 4;
    public static final int STUDY_PLAN_INDEX = 0;
    public static final int WORK_PLAN_INDEX = 1;
    public static final int JOB_PLAN_INDEX = 2;
    public static final int REST_PLAN_INDEX = 3;
 
    
    public RandomPlanner(Player p1, Game g) {
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
      
      _schedule.add(getRandomPlan());
      return true;
    }

    private Plan getRandomPlan() {
                
        Random random = new Random();
        int choice = random.nextInt(N_PLANS);
        switch (choice) {
                
                case STUDY_PLAN_INDEX: return new StudyAllWeekPlan(this);            
                case JOB_PLAN_INDEX:   return new GetABetterJobPlan(this);
                case WORK_PLAN_INDEX:  return new WorkAllWeekPlan(this);
                case REST_PLAN_INDEX:  return new RestAllWeekPlan(this);
                default:               return new RestAllWeekPlan(this);                                
                        
        }
    }

    
}
