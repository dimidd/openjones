/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jones.actions.Action;
import jones.actions.ActionResponse;
import jones.actions.Movement;
import jones.general.GUI;
import jones.general.Game;
import jones.general.Player;
import jones.general.BuildingListener;
import jones.map.MapManager;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
public class AgentSimulation {

    private static int N_WEEKS = 20;
    private static GUI _gui;

    public static int simulateRandom() {

        final MapManager map = MapManager.getDefaultMap();
        final Game g = new Game(map);
        Player p1 = new Player("Player1", null, map);
        g.addPlayer(p1);
        g.startGame();

        Agent agent = new RandoAgent2(p1, g);
        return simulate(agent, null);

    }

    public static int simulatePlanner() {

        final MapManager map = MapManager.getDefaultMap();
        final Game g = new Game(map);
        Player p1 = new Player("Player1", null, map);
        g.addPlayer(p1);
        g.startGame();

        //final PlannerAgent agent = new PlannerAgent(p1, g); agent.testPlans();
        //final Agent agent = new RandomPlanner(p1, g);
        //final Agent agent = new OrderedOnDemandPlanner(p1, g);
        //final Agent agent = new RandomOnDemandPlanner(p1, g);
        final Agent agent = new GreedyPlanner(p1, g);
        //final Agent agent = new GreedyOnDemandPlanner(p1, g);
        //final Agent agent = new SearchPlanner(p1, g);
        
        final GUI gui;
        _gui = new GUI(g);
        _gui.setVisible(true);
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//       
//                
//                simulate(agent, _gui);
//            }
//        });

        return simulate(agent, _gui);


    }
    final static Object lock = new Object();

    private static int simulate(Agent agent, GUI gui) {
        Game game = agent.getGame();

        while (agent.hasNextAction() && game.getWeek() <= N_WEEKS && !game.hasEnded()) {
            ArrayList<Action> possibletActions = game.getPossibletActions();
            int choice = agent.selectAction(possibletActions);
            if (Game.NOOP_ACTION_INDEX == choice) {
                continue;
            }

            ActionResponse response;
            if (game.isInside()) {
                response = game.performBuildingAction(choice, possibletActions);
            } else {
                Movement move = (Movement) possibletActions.get(choice);
                if (move.timeEffect(agent.getPlayer().getState()) > agent.getPlayer().timeLeft()) {
                    if (null != gui) {
                        BuildingListener.updateMapPanelBeforeMoving(game, gui);
                    }
                    game.endTurn();
                    if (null != gui) {
                        gui.repaint();
                    }
                    agent.notifyOfNewTurn();
                    continue;
                }
                if (null != gui) {
                    BuildingListener.updateMapPanelBeforeMoving(game, gui);
                }
                response = game.movePlayer(move.getNewPos());

            }
            if (null != gui) {
                gui.setLastSelectedBuildingActionIndex(choice);
                gui.repaint();
                //Thread.yield();

                synchronized (lock) {
                    try {
                        lock.wait(400);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AgentSimulation.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }



            agent.notifyOfResult(response);

            if (!game.hasTime()) {
                if (null != gui) {
                    BuildingListener.updateMapPanelBeforeMoving(game, gui);
                }
                game.endTurn();
                if (null != gui) {
                    gui.repaint();
                }
                agent.notifyOfNewTurn();
            }

            
        }

        return game.getCurPlayer().getTotalScore();
    }

    public static void main(String args[]) {
        System.out.println("Simulating Planner for " + N_WEEKS + " weeks:" + simulatePlanner());
    }
}
