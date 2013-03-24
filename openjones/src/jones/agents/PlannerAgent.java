/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import jones.actions.Action;
import jones.actions.ActionResponse;
import jones.general.Game;
import jones.general.Player;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
class PlannerAgent extends Agent {

    protected Queue<Plan> _schedule;
    
    public PlannerAgent(Player p1, Game g) {
        super(p1, g);
        _schedule = new LinkedList<>();
     }

    private void testPlans() {
        
        _schedule.add(new StudyAllWeekPlan(this));
        _schedule.add(new GetABetterJobPlan(this));
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
    public int selectAction(ArrayList<? extends Action> actions) {
        Action nextAction;
        nextAction = getCurPlan().getNextAction();
        if (null == nextAction)
            return Game.NOOP_ACTION_INDEX;
        
        int indexInPossibleActions = actions.indexOf(nextAction);
        if (-1 == indexInPossibleActions) {
            try {
                throw new IllegalActionException(nextAction, actions);
            } catch (IllegalActionException ex) {
                Logger.getLogger(PlannerAgent.class.getName()).log(Level.SEVERE, null, ex);
                //sex.printStackTrace();
            }
        }
        
        return indexInPossibleActions;
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
      
      return false;
    }
    
}
