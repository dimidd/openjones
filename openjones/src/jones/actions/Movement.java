/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.actions;

import jones.map.Grid;
import jones.general.Player;
import jones.general.PlayerPosition;
import jones.general.Position;

/**
 *
 * @author dimid
 */
public class Movement extends Action {
    
    protected PlayerPosition _oldPos;
    protected PlayerPosition _newPos;
    protected int _duration;
    
    /**
     *  The time it takes to move to an adjacent tile on the map 
     */
    public final int SINGLE_TILE_MOVEMENT_DURATION = 10;
    
    public Movement (PlayerPosition oldpos, PlayerPosition newpos) {
        _oldPos = oldpos;
        _newPos = newpos;
        _duration = calcDuration();
    }

    @Override
    protected void doAction(Player player) {
        player.getState().setPos(getNewPos());
    }

    @Override
    public int healthEffect(Player player) {
        return 0;
    }

    @Override
    public int happinessEffect(Player player) {
        return 0;
    }

    @Override
    protected ActionResponse checkConditions(Player player) {
        return checkTime(player);
            
    
    }

    @Override
    public int careerEffect(Player player) {
        return 0;
    }

    @Override
    public int cashEffect(Player player) {
        return 0;
    }

//    @Override
//    public int WealthEffect(Player player) {
//        return 0;
//    }

    @Override
    public int timeEffect(Player player) {
            return _duration;
        
    }

    @Override
    public String toString() {
        return "Move from " + getOldPos() + " to " + getNewPos();
    }

    private int calcDuration() {
                
        int dist = Grid.manhattanDistance(getOldPos(), getNewPos());
        int buildingDuration;
        
        if (0 == dist) {
            
            if (getOldPos().isInBuilding() == getNewPos().isInBuilding()) {
                buildingDuration = 0;
            }
            else
                if (getOldPos().isInBuilding() == true && getNewPos().isInBuilding() == false) {
                buildingDuration = ExitBuildingMovement.EXIT_BUILDING_DURATION;
            }
            else {
                buildingDuration = EnterBuildingMovement.ENTER_BUILDING_DURATION;
            }
            
            return buildingDuration;
        }
        
        else {
            buildingDuration = 0;
            if (getOldPos().isInBuilding() == true) {
                buildingDuration += ExitBuildingMovement.EXIT_BUILDING_DURATION;
            }
            
            if (getNewPos().isInBuilding() == true) {
                buildingDuration += EnterBuildingMovement.ENTER_BUILDING_DURATION;
            }
            
            return SINGLE_TILE_MOVEMENT_DURATION * dist + buildingDuration;
        }

    }

       
    
//        public static Movement getExitMovement(Position position) {
//            PlayerPosition oldP = new PlayerPosition(position, true);
//            PlayerPosition newP = new PlayerPosition(position, false);
//            
//            return new Movement(oldP, newP);
//        }
//    

    /**
     * @return the _oldPos
     */
    public PlayerPosition getOldPos() {
        return _oldPos;
    }

    /**
     * @param oldPos the _oldPos to set
     */
    public void setOldPos(PlayerPosition oldPos) {
        this._oldPos = oldPos;
    }

    /**
     * @return the _newPos
     */
    public PlayerPosition getNewPos() {
        return _newPos;
    }

    /**
     * @param newPos the _newPos to set
     */
    public void setNewPos(PlayerPosition newPos) {
        this._newPos = newPos;
    }

 
    @Override
    protected ActionResponse getPositiveResponse() {
        return new ActionResponse(true, null);
    }


    @Override
    public boolean isSubmenu() {
        return false;
    }
     
    
}
