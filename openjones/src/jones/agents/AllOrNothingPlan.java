/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

/**
 *
 * @author dimid
 */
public abstract class AllOrNothingPlan extends Plan {

    public AllOrNothingPlan(Agent agent) {
        super(agent);
    }
     
    @Override
    public void notifyOfNewTurn() {
         switch (_actions.size()) {
            case 0: break; //nothing left to do           
            default: _actions.clear(); rebuild(); break; ////most of plan not executed yet
        }
    }
}
