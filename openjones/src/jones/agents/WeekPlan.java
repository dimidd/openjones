/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

/**
 *
 * @author dimid
 */
public abstract class WeekPlan extends Plan {

    public WeekPlan(Agent agent) {
        super(agent);
    }

   
    @Override
    public void notifyOfNewTurn() {
        switch (_actions.size()) {
            case 0: break; //nothing left to do
            case 1: if (_isRepetetive) { //most of plan executed
                        _actions.clear();
                        break;
            }
            default: _actions.clear(); rebuild(); break; ////most of plan not executed yet
        }
    }
    
}
