/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.general;

import jones.Map.Grid;

/**
 *
 * @author dimid
 */
public class Movement extends Action {
    
    private PlayerPosition _oldPos;
    private PlayerPosition _newPos;
    //int _duration;
    
    /**
     *  The time it takes to move to an adjacent tile on the map 
     */
    public final int SINGLE_TILE_MOVEMENT_DURATION = 10;
    
    
    /**
     * The time it takes to exit a building
     */
    public final int EXIT_BUILDING_DURATION = 0;
    
    
    /**
     * The time it takes to enter a building
     */
    public final int ENTER_BUILDING_DURATION = 2;
    
    public Movement (PlayerPosition oldpos, PlayerPosition newpos) {
        _oldPos = oldpos;
        _newPos = newpos;
        
    }

    @Override
    public void doAction(Player player) {
        player.getState().setPos(_newPos);
    }

    @Override
    public int healthEffect() {
        return 0;
    }

    @Override
    public int happinessEffect() {
        return 0;
    }

    @Override
    public boolean checkConditions() {
        return true;
    }

    @Override
    public int careerEffect() {
        return 0;
    }

    @Override
    public int cashEffect() {
        return 0;
    }

    @Override
    public int WealthEffect() {
        return 0;
    }

    @Override
    public int timeEffect() {
        int dist = Grid.manhattanDistance(_oldPos, _newPos);
        int buildingDuration;
        
        if (0 == dist) {
            
            if (_oldPos.isInBuilding() == _newPos.isInBuilding()) {
                buildingDuration = 0;
            }
            else
                if (_oldPos.isInBuilding() == true && _newPos.isInBuilding() == false) {
                buildingDuration = EXIT_BUILDING_DURATION;
            }
            else {
                buildingDuration = ENTER_BUILDING_DURATION;
            }
            
            return buildingDuration;
        }
        
        else {
            buildingDuration = 0;
            if (_oldPos.isInBuilding() == true) {
                buildingDuration += EXIT_BUILDING_DURATION;
            }
            
            if (_newPos.isInBuilding() == true) {
                buildingDuration += ENTER_BUILDING_DURATION;
            }
            
            return SINGLE_TILE_MOVEMENT_DURATION * dist + buildingDuration;
        }
            
        
    }
    
    
    
    
}
