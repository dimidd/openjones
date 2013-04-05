/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import jones.actions.Action;
import jones.actions.ActionResponse;
import jones.general.AbstractPlayerState;
import jones.general.Game;
import jones.general.Player;
import jones.general.PlayerState;
import jones.measures.Goals;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
public class PlannerAgent extends Agent {

    public static final int JOB_PLAN_INDEX = 2;
    public static final int N_PLANS = 4;
    public static final int REST_PLAN_INDEX = 3;
    public static final int STUDY_PLAN_INDEX = 0;
    public static final int WORK_PLAN_INDEX = 1;
    protected Queue<Plan> _schedule;

    public PlannerAgent(Player p1, Game g) {
        super(p1, g);
        _schedule = new LinkedList<>();
        //testPlans();
    }

    private void testPlans() {

        //_schedule.add(new StudyAllWeekPlan(this));
        _schedule.add(new GetABetterJobPlan(this));
        //_schedule.add(new GetABetterJobPlan(this));
        _schedule.add(new WorkAllWeekPlan(this));
        _schedule.add(new RestAllWeekPlan(this));

    }

    protected Plan getCurPlan() {
        return _schedule.peek();
    }

    @Override
    public void notifyOfNewTurn() {
        if (getCurPlan().size() > 0) {
            getCurPlan().rebuild();
        }
    }

    @Override
    public void notifyOfResult(ActionResponse response) {
        getCurPlan().setLastResponse(response);
    }

    @Override
    public int selectAction(ArrayList<? extends Action> possibleActions) {
        Action nextAction;
        nextAction = getCurPlan().getNextAction();
        int indexInPossibleActions;
        try {
            indexInPossibleActions = getActionIndex(possibleActions, nextAction);           
        } 
        
        catch (IllegalActionException ex) {
            Logger.getLogger(PlannerAgent.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(PlannerAgent.class.getName()).log(Level.SEVERE, "schedule:{0}", _schedule);
            return -10;
        }


        return indexInPossibleActions;

    }

    public static int getActionIndex(ArrayList<? extends Action> possibleActions, Action nextAction) throws IllegalActionException {

        if (null == nextAction) {
            return Game.NOOP_ACTION_INDEX;
        }

        int indexInPossibleActions = possibleActions.indexOf(nextAction);
        if (-1 == indexInPossibleActions) {
            throw new IllegalActionException(nextAction, possibleActions);
        }

        return indexInPossibleActions;

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

        return false;
    }

    /**
     * Return a list of needed (by score) plans. E.g. if Happiness is too low,
     * the result would include a ResTAllWeekPlan
     *
     * @return
     */
    protected List<Plan> getNeededPlans() {

        boolean hasAddedWork = false;
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
            int rank = _player.getJob().getRank();
            if (rank > 0 && rank <= PlayerState.MAX_JOB_RANK) {
                int experienceLevel = _player.getCareer().getExperienceLevel(rank);
                int cap = _player.getCareer().getExp().getCapByRank(rank);
                if (experienceLevel >= cap) {
                    result.add(new GetABetterJobPlan(this));
                } else {
                    result.add(new WorkAllWeekPlan(this));
                    hasAddedWork = true;
                }
            } else if (0 == rank) {
                result.add(new GetABetterJobPlan(this));
            }
        }

        int wealthScore = _player.getWealthscore();
        if (!hasAddedWork && wealthScore < Goals.MAX_MEASURE_SCORE) {
            result.add(new WorkAllWeekPlan(this));
        }

        return result;
    }

    public List<Plan> getNeededPlans(AbstractPlayerState state) {


        ArrayList<Plan> result = new ArrayList<>();
        int eduScore = state.getEducationScore();
        if (eduScore < Goals.MAX_MEASURE_SCORE) {
            result.add(new StudyAllWeekPlan(this));
        }

        int healthScore = state.getHealthScore();
        if (healthScore < Goals.MAX_MEASURE_SCORE) {
            result.add(new RestAllWeekPlan(this));
        }

        boolean hasAddedWork = false;
        int careerScore = state.getCareerScore();
        if (careerScore < Goals.MAX_MEASURE_SCORE) {
            int rank = state.getJob().getRank();
            if (rank > 0 && rank <= PlayerState.MAX_JOB_RANK) {
                int experienceLevel = state.getCareer().getExperienceLevel(rank);
                int cap = state.getCareer().getExp().getCapByRank(rank);
                if (experienceLevel >= cap) {
                    result.add(new GetABetterJobPlan(this));
                } else {
                    result.add(new WorkAllWeekPlan(this));
                    hasAddedWork = true;
                }
            } else if (0 == rank) {
                result.add(new GetABetterJobPlan(this));
            }
        }

        int wealthScore = state.getWealthscore();
        if (!hasAddedWork && wealthScore < Goals.MAX_MEASURE_SCORE) {
            result.add(new WorkAllWeekPlan(this));
        }

        return result;

    }

    /**
     * Return all possible PlanScores
     *
     * @return
     */
    protected List<PlanScore> getPlanScores() {
        ArrayList<PlanScore> result = new ArrayList<>();

        int eduScore = _player.getEducationScore();
        result.add(new PlanScore(new StudyAllWeekPlan(this), eduScore));
        int healthScore = _player.getHealthScore();
        result.add(new PlanScore(new RestAllWeekPlan(this), healthScore));
        int careerScore = _player.getCareerScore();
        result.add(new PlanScore(new GetABetterJobPlan(this), careerScore));
        int wealthScore = _player.getWealthscore();
        result.add(new PlanScore(new WorkAllWeekPlan(this), wealthScore));

        return result;

    }

    /**
     * Return a list of needed (by score) PlanScores. E.g. if Happiness is too
     * low, the result would include a ResTAllWeekPlan
     *
     * @return
     */
    protected List<PlanScore> getNeededPlanScores() {

        boolean hasAddedWork = false;
        ArrayList<PlanScore> result = new ArrayList<>();


        int eduScore = _player.getEducationScore();
        if (eduScore < Goals.MAX_MEASURE_SCORE) {
            result.add(new PlanScore(new StudyAllWeekPlan(this), eduScore));
        }

        int healthScore = _player.getHealthScore();
        if (healthScore < Goals.MAX_MEASURE_SCORE) {
            result.add(new PlanScore(new RestAllWeekPlan(this), healthScore));
        }

        int careerScore = _player.getCareerScore();
        if (careerScore < Goals.MAX_MEASURE_SCORE) {
            int rank = _player.getJob().getRank();
            if (rank > 0 && rank <= PlayerState.MAX_JOB_RANK) {
                int experienceLevel = _player.getCareer().getExperienceLevel(rank);
                int cap = _player.getCareer().getExp().getCapByRank(rank);
                if (experienceLevel >= cap) {
                    result.add(new PlanScore(new GetABetterJobPlan(this), careerScore));
                } else {
                    result.add(new PlanScore(new WorkAllWeekPlan(this), careerScore));
                    hasAddedWork = true;
                }
            } else if (0 == rank) {
                result.add(new PlanScore(new GetABetterJobPlan(this), careerScore));
            }
        }


//        _player.setJob(_game.getMap().getBuildingByName("Monolith Burgers").getJobs().get(0));

        int wealthScore = _player.getWealthscore();
        if (!hasAddedWork && wealthScore < Goals.MAX_MEASURE_SCORE) {
            result.add(new PlanScore(new WorkAllWeekPlan(this), wealthScore));
        }

        return result;
    }

    public String scheduleToString() {
        StringBuilder result = new StringBuilder();
        for (Plan p : _schedule) {
            result.append(p.toString()).append(", ");
        }
        return result.toString();
    }
}
