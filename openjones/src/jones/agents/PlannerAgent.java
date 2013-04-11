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
import jones.actions.ExitBuildingMovement;
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

    public void testPlans() {

        _schedule.add(new StudyAllWeekPlan(this));
        _schedule.add(new GetABetterJobPlan(this, _player.getState()));
        _schedule.add(new GetABetterJobPlan(this, _player.getState()));
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
        nextAction = getCurPlan().getNextAction(getPlayer().getState());
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
    
    protected boolean  hasNextActionInSchedule() {
                
        while (_schedule.size() > 0) {
            if (getCurPlan().size() > 0) {
                return true;
            } else {
                _schedule.remove();
            }
        }
        
        return false;

    }

    @Override
    public boolean hasNextAction() {
        
        return hasNextActionInSchedule();
    }

    /**
     * Return a list of needed (by score) plans. E.g. if Happiness is too low,
     * the result would include a ResTAllWeekPlan
     *
     * @return
     */
    public List<Plan> getNeededPlans(PlayerState playerState) {

        boolean hasAddedWork = false;
        ArrayList<Plan> result = new ArrayList<>();
        int eduScore = playerState.getEducationScore();
        if (eduScore < Goals.MAX_MEASURE_SCORE) {
            result.add(new StudyAllWeekPlan(this));
        }

        int healthScore = playerState.getHealthScore();
        if (healthScore < Goals.MAX_MEASURE_SCORE) {
            result.add(new RestAllWeekPlan(this));
        }

        int careerScore = playerState.getCareerScore();
        if (careerScore < Goals.MAX_MEASURE_SCORE) {
            int rank = _player.getJob().getRank();
            if (rank > 0 && rank <= PlayerState.MAX_JOB_RANK) {
                int experienceLevel = _player.getCareer().getExperienceLevel(rank);
                int cap = _player.getCareer().getExp().getCapByRank(rank);
                if (experienceLevel >= cap) {
                    result.add(new GetABetterJobPlan(this, playerState));
                } else {
                    result.add(new WorkAllWeekPlan(this));
                    hasAddedWork = true;
                }
            } else if (0 == rank) {
                result.add(new GetABetterJobPlan(this, playerState));
            }
        }

        int wealthScore = playerState.getWealthscore();
        if (!hasAddedWork && wealthScore < Goals.MAX_MEASURE_SCORE) {
            result.add(new WorkAllWeekPlan(this));
        }

        return result;
    }

//    public List<Plan> getNeededPlans(AbstractPlayerState state) {
//
//
//        ArrayList<Plan> result = new ArrayList<>();
//        int eduScore = state.getEducationScore();
//        if (eduScore < Goals.MAX_MEASURE_SCORE) {
//            result.add(new StudyAllWeekPlan(this));
//        }
//
//        int healthScore = state.getHealthScore();
//        if (healthScore < Goals.MAX_MEASURE_SCORE) {
//            result.add(new RestAllWeekPlan(this));
//        }
//
//        boolean hasAddedWork = false;
//        int careerScore = state.getCareerScore();
//        if (careerScore < Goals.MAX_MEASURE_SCORE) {
//            int rank = state.getJob().getRank();
//            if (rank > 0 && rank <= PlayerState.MAX_JOB_RANK) {
//                int experienceLevel = state.getCareer().getExperienceLevel(rank);
//                int cap = state.getCareer().getExp().getCapByRank(rank);
//                if (experienceLevel >= cap) {
//                    result.add(new GetABetterJobPlan(this));
//                } else {
//                    result.add(new WorkAllWeekPlan(this));
//                    hasAddedWork = true;
//                }
//            } else if (0 == rank) {
//                result.add(new GetABetterJobPlan(this));
//            }
//        }
//
//        int wealthScore = state.getWealthscore();
//        if (!hasAddedWork && wealthScore < Goals.MAX_MEASURE_SCORE) {
//            result.add(new WorkAllWeekPlan(this));
//        }
//
//        return result;
//
//    }

    /**
     * Return all possible PlanScores
     *
     * @return
     */
    protected List<PlanScore> getPlanScores(PlayerState playerState) {
        ArrayList<PlanScore> result = new ArrayList<>();

        int eduScore = playerState.getEducationScore();
        result.add(new PlanScore(new StudyAllWeekPlan(this), eduScore));
        int healthScore = playerState.getHealthScore();
        result.add(new PlanScore(new RestAllWeekPlan(this), healthScore));
        int careerScore = playerState.getCareerScore();
        result.add(new PlanScore(new GetABetterJobPlan(this,playerState), careerScore));
        int wealthScore = playerState.getWealthscore();
        result.add(new PlanScore(new WorkAllWeekPlan(this), wealthScore));

        return result;

    }

    /**
     * Return a list of needed (by score) PlanScores. E.g. if Happiness is too
     * low, the result would include a ResTAllWeekPlan
     *
     * @return
     */
    protected List<PlanScore> getNeededPlanScores(PlayerState playerState) {

        boolean hasAddedWork = false;
        ArrayList<PlanScore> result = new ArrayList<>();


        int eduScore = playerState.getEducationScore();
        if (eduScore < Goals.MAX_MEASURE_SCORE) {
            result.add(new PlanScore(new StudyAllWeekPlan(this), eduScore));
        }

        int healthScore = playerState.getHealthScore();
        if (healthScore < Goals.MAX_MEASURE_SCORE) {
            result.add(new PlanScore(new RestAllWeekPlan(this), healthScore));
        }

        int careerScore = playerState.getCareerScore();
        if (careerScore < Goals.MAX_MEASURE_SCORE) {
            int rank = playerState.getJob().getRank();
            if (rank > 0 && rank <= PlayerState.MAX_JOB_RANK) {
                int experienceLevel = playerState.getCareer().getExperienceLevel(rank);
                int cap = playerState.getCareer().getExp().getCapByRank(rank);
                if (experienceLevel >= cap) {
                    result.add(new PlanScore(new GetABetterJobPlan(this, playerState), careerScore));
                } else {
                    result.add(new PlanScore(new WorkAllWeekPlan(this), careerScore));
                    hasAddedWork = true;
                }
            } else if (0 == rank) {
                result.add(new PlanScore(new GetABetterJobPlan(this, playerState), careerScore));
            }
        }


//        _player.setJob(_game.getMap().getBuildingByName("Monolith Burgers").getJobs().get(0));

        int wealthScore = playerState.getWealthscore();
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
