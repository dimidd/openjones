package jones.agents;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;
import jones.agents.GreedyPlanner;
import jones.agents.Plan;
import jones.agents.PlanScore;
import jones.general.Game;
import jones.general.Player;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
public class GreedyOnDemandPlanner extends GreedyPlanner {

    public GreedyOnDemandPlanner(Player p1, Game g) {
        super(p1, g);
    }
    
    
    /**
     * Same as parent, but only needed Plans
     * @return 
     */   
    @Override
    protected Plan getMinScorePlan() {
        
        List<PlanScore> neededPlanScores = getNeededPlanScores(_player.getState());
        return getMinScorePlan(neededPlanScores);
    }

}
