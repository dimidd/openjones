/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.actions;

import jones.general.Player;
import jones.general.PlayerPosition;
import jones.general.Position;
import jones.map.Building;

/**
 *
 * @author dimid
 */
public class ExitBuildingMovement extends Movement{
    /**
     * The time it takes to exit a building
     */
    public static final int EXIT_BUILDING_DURATION = 0;
    
      
    private Building _build;
    
    
    public ExitBuildingMovement (PlayerPosition oldpos, Building build) {
        super(new PlayerPosition(oldpos), new PlayerPosition(oldpos));
        _newPos.exitBuilding();
        _build = build;
    }
 
    public ExitBuildingMovement (Position oldpos, Building build) {
        super(new PlayerPosition(oldpos,true), new PlayerPosition(oldpos,false));     
        _build = build;
    }
   
    @Override
    public int timeEffect(Player player) {
            return EXIT_BUILDING_DURATION;
        
    }

     
    @Override
    protected void doAction(Player player) {
        player.getState().setPos(getNewPos());
        _build.prepareForPlayerExit(player);
        
    }

}
