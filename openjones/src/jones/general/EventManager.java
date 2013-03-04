/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.general;

import jones.actions.Action;

/**
 *
 * @author dimid
 */
class EventManager {

    /**
     * Weekend events happen when the player has finished his turn
     * @param _curPlayer current player
     * @return a random event (e.g. bills, lottery)
     */
    Action getRandomWeekendEvent(Player _curPlayer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Road event happen when a player leaves a building 
     * @param _curPlayer current player
     * @return a random event (e.g. robbery)
     */
    Action getRandomRoadEvent(Player _curPlayer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
