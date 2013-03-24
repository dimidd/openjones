/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import jones.actions.Action;
import jones.general.Game;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
class UpdatePossibleActionsMarker extends PlanMarker {

    public UpdatePossibleActionsMarker(Plan plan, Action action) {
        super(plan, action);       
        
    }

    @Override
    public void changeState() {
        Game game = _plan.getAgent().getGame();
        _plan.setPossibletActions(game.getPossibletActions());
    }
    
}
