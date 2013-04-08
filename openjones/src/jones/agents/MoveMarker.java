/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import java.util.ArrayList;
import java.util.LinkedList;
import jones.actions.Action;
import jones.actions.ExitBuildingMovement;
import jones.actions.SubMenuAction;
import jones.general.Game;
import jones.general.PlayerPosition;
import jones.general.PlayerState;
import jones.map.Building;
import net.vivin.GenericTreeNode;

/**
 * Pushes MoveTo plan from player`s current position to _dest
 *
 * @author dimid <dimidd@gmail.com>
 */
public class MoveMarker extends PlanMarker {

    private final PlayerPosition _dest;

    public MoveMarker(Plan plan, Action action, PlayerPosition dest) {
        super(plan, action);
        _dest = dest;
    }

    @Override
    public void changeState(PlayerState playerState) {
        PlayerPosition src = playerState.getPos();



//        if (src.isInBuilding()) {
//            Game game = _plan.getAgent().getGame();
//            ArrayList<? extends Action> possibletActions = playerState.getPossibleActions(game.getMap());
//
//            if (!possibletActions.get(0).toString().equals("Exit")) {
//                move.getActions().push(new BackInMenuMarker(_plan, possibletActions.get(0)));
//            }
//
//        }

        LinkedList<PlanMarker> gotoMainMenu = new LinkedList<>();
        if (src.isInBuilding()) {
            Building build = (Building) _plan.getAgent().getGame().getMap().getTile(src);
            GenericTreeNode<Action> root = build.getActionsTree().getRoot();
            if (build.getPlayerActionsParent() != root) {
                gotoMainMenu.add(new BackInMenuMarker(_plan, null));
            }

        }

        //MoveToPlan move;
        if (!src.equals(_dest)) {
            MoveToPlan move = new MoveToPlan(_plan.getAgent(), src, _dest);
            gotoMainMenu.addAll(move.getActions());
        }

        _plan.getActions().addAll(0, gotoMainMenu);

    }
}
