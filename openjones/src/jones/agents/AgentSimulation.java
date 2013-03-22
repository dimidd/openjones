/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import java.util.ArrayList;
import jones.actions.Action;
import jones.actions.ActionResponse;
import jones.actions.Movement;
import jones.general.GUI;
import jones.general.Game;
import jones.general.Player;
import jones.map.MapManager;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
public class AgentSimulation {
    private static int N_WEEKS = 20;
    private static GUI _gui;
            
    public static int simulateRandom () {
        
        final MapManager map = MapManager.getDefaultMap();
        final Game g = new Game(map);
        Player p1 = new Player("Player1", null, map);
        g.addPlayer(p1);
        g.startGame();
        
        Agent agent = new RandoAgent2(p1, g);
        return simulate(agent,null);

    }

        
    public static int simulatePlanner () {
        
        final MapManager map = MapManager.getDefaultMap();
        final Game g = new Game(map);
        Player p1 = new Player("Player1", null, map);
        g.addPlayer(p1);
        g.startGame();
        
        final Agent agent = new PlannerAgent(p1, g);
        final GUI gui;      
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                _gui =  new GUI(g);
                _gui.setVisible(true);
                simulate(agent, _gui);
            }
        });

        return -1;
        

    }

    private static int simulate(Agent agent, GUI gui) {
        Game game = agent.getGame();
        
        while (agent.hasNextAction() && game.getWeek() <= N_WEEKS) {
            ArrayList<? extends Action> possibletActions = game.getPossibletActions();
            int choice = agent.selectAction(possibletActions);
            ActionResponse response;
            if (game.isInside()) {
                response = game.performBuildingAction(choice);
            }
            else {
                Movement move = (Movement) possibletActions.get(choice);
                response = game.movePlayer(move.getNewPos());
            }
            if (null != gui) {
                gui.setLastSelectedBuildingActionIndex(choice);
                gui.repaint();
            }
            
            agent.notifyOfResult(response);
            
            if (!game.hasTime()) {
                game.endTurn();
                agent.notifyOfNewTurn();
            }
            
        }
        
        return game.getCurPlayer().getScore();
    }
    
      public static void main(String args[]) {
          System.out.println("Simulating Random for "+N_WEEKS+" weeks:"+simulatePlanner());
      }
    
}
