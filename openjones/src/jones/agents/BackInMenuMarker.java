/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import jones.actions.Action;
import jones.actions.SubMenuAction;
import jones.general.PlayerPosition;
import jones.general.PlayerState;
import jones.map.Building;
import net.vivin.GenericTreeNode;

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

        PlayerPosition src = playerState.getPos();
        //MoveToPlan move = new MoveToPlan(_plan.getAgent(), src, _dest);

 
        if (src.isInBuilding()) {
            Building build = (Building) _plan.getAgent().getGame().getMap().getTile(src);
            GenericTreeNode<Action> root = playerState.getActionsTree().getRoot();
            if (playerState.getPlayerActionsParent() != root) {
                _plan.getActions().push(new BackInMenuMarker(_plan, new SubMenuAction(0, "back", playerState.getPlayerActionsParent().getParent(), build)));
            } else {
                _action = null;
            }
        }




    }
}
