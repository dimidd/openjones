/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.actions;

import jones.general.Player;
import jones.general.PlayerPosition;
import jones.map.Building;

/**
 *
 * @author dimid
 */
public class EnterBuildingMovement extends Movement {
    
    /**
     * The time it takes to enter a building
     */
    public static final int ENTER_BUILDING_DURATION = 2;

    private Building _build;
    
    
    public EnterBuildingMovement (PlayerPosition oldpos, Building build) {
        super(new PlayerPosition(oldpos), new PlayerPosition(oldpos));
        _newPos.enterBuilding();
        _build = build;
    }
              
    @Override
    public int timeEffect(Player player) {
            return ENTER_BUILDING_DURATION;
        
    }

     
    @Override
    protected void doAction(Player player) {
        player.getState().setPos(getNewPos());
        _build.prepareForPlayerEntrance(player);
        
    }

}
