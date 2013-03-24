/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import java.util.ArrayList;
import java.util.List;
import jones.general.Game;
import jones.general.Player;
import jones.measures.Goals;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
class OrderedOnDemandPlanner extends OrderedPlanner {

    public OrderedOnDemandPlanner(Player p1, Game g) {
        super(p1, g);
    }

    @Override
    public boolean hasNextAction() {
        while (_schedule.size() > 0) {
            if (getCurPlan().size() > 0) {
                return true;
            } else {
                _schedule.remove();
            }
        }

        _schedule.addAll(getOrderedPlans());
        return true;
    }

    @Override
    protected List<Plan> getOrderedPlans() {


        ArrayList<Plan> result = new ArrayList<>();
        int eduScore = _player.getEducationScore();
        if (eduScore < Goals.MAX_MEASURE_SCORE) {
            result.add(new StudyAllWeekPlan(this));
        }

        int healthScore = _player.getHealthScore();
        if (healthScore < Goals.MAX_MEASURE_SCORE) {
            result.add(new RestAllWeekPlan(this));
        }

        int careerScore = _player.getCareerScore();
        if (careerScore < Goals.MAX_MEASURE_SCORE) {
            result.add(new GetABetterJobPlan(this));
        }
        
//        _player.setJob(_game.getMap().getBuildingByName("Monolith Burgers").getJobs().get(0));
        
        int wealthScore = _player.getWealthscore();
        if (wealthScore < Goals.MAX_MEASURE_SCORE) {
            result.add(new WorkAllWeekPlan(this));
        }

        return result;
    }
}
