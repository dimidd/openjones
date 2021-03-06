/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import jones.actions.Action;
import jones.general.PlayerState;

/**
 * Markers contain an action and are used to change Plan state at a certain stage.
 * Markers with a null action, just change the state
 * 
 * @author dimid <dimidd@gmail.com>
 */
public abstract class PlanMarker {
    protected final Plan _plan;
    protected  Action _action;
    
    public Action getAction() {
        return _action;
    }
    
    
    public PlanMarker (Plan plan, Action action) {
        _plan = plan;
        _action = action;
    }
    
    public abstract void changeState(PlayerState playerState);
    
    @Override
    public String toString () {
        return getClass().getSimpleName()+" action:"+((_action != null)?_action.toString() : "null");
    }
}
