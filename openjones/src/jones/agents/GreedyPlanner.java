/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import java.util.List;
import java.util.PriorityQueue;
import jones.general.Game;
import jones.general.Player;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
public class GreedyPlanner extends PlannerAgent {

    protected PriorityQueue<PlanScore> _priorityQueuePlans;
    protected PlanScore _lastPlanScore;
    
    public GreedyPlanner(Player p1, Game g) {
        super(p1, g);
        _priorityQueuePlans = new PriorityQueue<>();
        _lastPlanScore = null;
    }
    
     @Override
    public boolean hasNextAction() {
        if (!hasNextActionInSchedule())
            _schedule.add(getMinScorePlan());
        return true;
    }

    /**
     * Returns the Plan with the smallest score. 
     * E.g. if the education score is the lowest, returns StudyAllWeekPlan
     * @return 
     */ 
    protected Plan getMinScorePlan() {
        List<PlanScore> allPlanScores = getPlanScores(_player.getState());
        return getMinScorePlan(allPlanScores);
    }

    protected Plan getMinScorePlan( List<PlanScore> possiblePlanScores) {
               
        _priorityQueuePlans.clear();
        
        _priorityQueuePlans.addAll(possiblePlanScores);
        PlanScore minPlanScore = _priorityQueuePlans.remove();
        if (null != _lastPlanScore && _lastPlanScore.equals(minPlanScore)) {
            minPlanScore = _priorityQueuePlans.remove();
        }
        
        _lastPlanScore = minPlanScore;
        return _lastPlanScore.getPlan();

    }
}
