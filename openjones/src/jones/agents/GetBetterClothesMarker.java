/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import jones.actions.Action;
import jones.general.PlayerState;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
class GetBetterClothesMarker  extends PlanMarker{
    private final PlayerState _playerState;

    public GetBetterClothesMarker(Plan plan, Action action, PlayerState playerState) {
        super(plan, action);
        _playerState = playerState;
    }

    @Override
    public void changeState(PlayerState playerState) {
        _plan.push(new GetBetterClothesPlan(_plan.getAgent()));
    }

    
}
