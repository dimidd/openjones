/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import java.util.ArrayList;
import jones.actions.Action;
import jones.actions.ExitBuildingMovement;
import jones.general.Game;
import jones.general.PlayerPosition;

/**
 * Pushes MoveTo plan from player`s current position to _dest
 * @author dimid <dimidd@gmail.com>
 */
public class MoveMarker extends PlanMarker {
    private final PlayerPosition _dest;

    public MoveMarker(Plan plan, Action action, PlayerPosition dest) {
        super(plan, action);
        _dest = dest;
    }

    @Override
    public void changeState() {
        PlayerPosition src = _plan.getAgent().getPlayer().getPos();
        MoveToPlan move = new MoveToPlan(_plan.getAgent(), src, _dest);
        
        Game game = _plan.getAgent().getGame();
        ArrayList<? extends Action> possibletActions = game.getPossibletActions();
        int i = 0;
//        Action firstMovement = move.getActions().get(i++).getAction();
//        while (firstMovement == null && i < move.getActions().size())
//            firstMovement =move.getActions().get(i++).getAction();
//        
//        if (null != firstMovement && firstMovement.toString().equals("Exit")) {
//           int index = possibletActions.indexOf(firstMovement);
//           if (index != 0) {
          if (src.isInBuilding() && !possibletActions.get(0).toString().equals("Exit"))      {
                //game.performBuildingAction(0);
                move.getActions().push(new BackInMenuMarker(_plan,possibletActions.get(0)));
                //_plan.setIsRepetetive(true);
//                possibletActions = game.getPossibletActions();
//                index = possibletActions.indexOf(move);
//            }
        }
        
        _plan.push(move);
    }
    
}
