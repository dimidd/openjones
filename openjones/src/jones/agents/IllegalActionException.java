/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import java.util.ArrayList;
import jones.actions.Action;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
class IllegalActionException extends Exception {
    private static final long serialVersionUID = 1L;

    public IllegalActionException(Action nextAction, ArrayList<? extends Action> actions) {
        System.err.println("Action:"+nextAction+" is not in possible actions:"+actions);
    }
    
}
