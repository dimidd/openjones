/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.actions;

import java.util.Objects;
import jones.map.Grid;
import jones.general.Player;
import jones.general.PlayerPosition;
import jones.general.PlayerState;
import jones.map.Building;
import jones.map.MapManager;

/**
 *
 * @author dimid
 */
public class Movement extends Action {
    
    protected final PlayerPosition _oldPos;
    protected final PlayerPosition _newPos;
    protected int _duration;
    
    /**
     *  The time it takes to move to an adjacent tile on the map 
     */
    public static final int SINGLE_TILE_MOVEMENT_DURATION = 10;
    
    public Movement (PlayerPosition oldpos, PlayerPosition newpos) {
        _oldPos = oldpos;
        _newPos = newpos;
        _duration = calcDuration();
    }

    @Override
    protected void doAction(PlayerState playerState) {
        playerState.setPos(getNewPos());
        playerState.affectTime(_duration);
                     
    }
    

    @Override
    public int healthEffect(PlayerState playerState) {
        return 0;
    }

    @Override
    public int happinessEffect(PlayerState playerState) {
        return 0;
    }

    @Override
    protected ActionResponse checkConditions(PlayerState playerState) {
        return checkTime(playerState);
            
    
    }

    @Override
    public int careerEffect(PlayerState playerState) {
        return 0;
    }

    @Override
    public int cashEffect(PlayerState playerState) {
        return 0;
    }

//    @Override
//    public int WealthEffect(Player player) {
//        return 0;
//    }

    @Override
    public int timeEffect(PlayerState playerState) {
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

//    /**
//     * @param oldPos the _oldPos to set
//     */
//    public void setOldPos(PlayerPosition oldPos) {
//        this._oldPos = oldPos;
//    }

    /**
     * @return the _newPos
     */
    public PlayerPosition getNewPos() {
        return _newPos;
    }

//    /**
//     * @param newPos the _newPos to set
//     */
//    public void setNewPos(PlayerPosition newPos) {
//        this._newPos = newPos;
//    }

 
    @Override
    protected ActionResponse getPositiveResponse(PlayerState playerState) {
        return new ActionResponse(true, null);
    }


    @Override
    public boolean isSubmenu() {
        return false;
    }
     
      
    @Override
    public void clearCachedValues() {
      
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this._oldPos);
        hash = 83 * hash + Objects.hashCode(this._newPos);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Movement other = (Movement) obj;
        if (!Objects.equals(this._oldPos, other._oldPos)) {
            return false;
        }
        if (!Objects.equals(this._newPos, other._newPos)) {
            return false;
        }
        return true;
    }

}
