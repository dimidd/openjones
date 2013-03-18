/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.general;

import java.util.ArrayList;
import jones.actions.Action;
import jones.map.Building;
import jones.map.MapManager;
import jones.map.RentAgency;

/**
 *
 * @author dimid
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        MapManager map = MapManager.getDefaultMap();
        Game g = new Game(map);
        Player p1 = new Player("Player1", null, map);
        g.addPlayer(p1);
        g.startGame();
         
        Player curPlayer = g.getCurPlayer();
        System.out.println("player:"+curPlayer.getName()+" cash:"+curPlayer.getState().getCash());
  
        Position homePos =map.getLowestHousing().getPosition();
        PlayerPosition homePosInside = new PlayerPosition(homePos, true);
        g.movePlayer(homePosInside);
        ArrayList<? extends Action> gePossibletActions = g.getPossibletActions();
        g.performBuildingAction(Building.RELAX_ACTION_INDEX);
        
        Position rentAgencyPos = new Position(1, 0);
        PlayerPosition rentAgencyPosInside = new PlayerPosition(rentAgencyPos, true);
        RentAgency rentAgency = (RentAgency) map.getGrid().get(rentAgencyPos);
        g.getCurPlayer().getState().setJob(rentAgency.getJobs().get(0)); //groundkeeper
        g.movePlayer(rentAgencyPosInside);
        gePossibletActions = g.getPossibletActions();
        
        //while (g.hasTime())
        g.performBuildingAction(Building.WORK_ACTION_INDEX);
        
             
        System.out.println("player:"+curPlayer.getName()+" cash:"+curPlayer.getState().getCash());

        g.performBuildingAction(Building.DONE_ACTION_INDEX);
    }
}
