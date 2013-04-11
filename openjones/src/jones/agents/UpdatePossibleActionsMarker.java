/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import java.util.ArrayList;
import jones.actions.Action;
import jones.general.Game;
import jones.general.PlayerState;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
class UpdatePossibleActionsMarker extends PlanMarker {
    //private final PlayerState _playerState;

    public UpdatePossibleActionsMarker(Plan plan, Action action) {
        super(plan, action);
        //_playerState = playerState;
        
    }

    @Override
    public void changeState(PlayerState playerState) {
        //Game game = _plan.getAgent().getGame();
        ArrayList<? extends Action> possibleActions = playerState.getPossibleActions(_plan.getAgent().getGame().getMap());
        _plan.setPossibletActions(possibleActions);
    }
    
}
