/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import jones.actions.Action;
import jones.actions.Movement;
import jones.general.PlayerState;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
class NoOpMarker extends PlanMarker {

    public NoOpMarker(Plan p, Action m) {
        super(p, m);
    }

    @Override
    public void changeState(PlayerState playerState) {
        //noop
    }
    
    
    
}
