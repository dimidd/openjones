/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import java.util.ArrayList;
import jones.actions.Action;
import jones.general.Game;
import jones.general.PlayerPosition;
import jones.general.PlayerState;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
class BackInMenuMarker extends PlanMarker {

    public BackInMenuMarker(Plan plan, Action action) {
        super(plan, action);
    }

    @Override
    public void changeState(PlayerState playerState) {
                PlayerPosition src = _plan.getAgent().getPlayer().getPos();
        //MoveToPlan move = new MoveToPlan(_plan.getAgent(), src, _dest);
        
        Game game = _plan.getAgent().getGame();
        ArrayList<? extends Action> possibletActions =  playerState.getPossibleActions(game.getMap());
        int i = 0;
        Action firstPossibleAction = possibletActions.get(0);
        
        if (!firstPossibleAction.toString().equals("Exit")) {
                //game.performBuildingAction(0);
                _plan.getActions().push(new BackInMenuMarker(_plan,firstPossibleAction));
//                possibletActions = game.getPossibletActions();
//                index = possibletActions.indexOf(move);
            
        }
        
        else 
            _action = null;

    }

    
}
