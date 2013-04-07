/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package external;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import jones.agents.Plan;
import jones.agents.PlannerAgent;
import jones.general.PlayerState;
import jones.map.MapManager;

/**
 *
 * @author dimid
 */
public class AStarPlayer {

    public static final double HEURISTIC_SCALE_FACTOR = 200.0;
    
    /**
     * Our own implementation of the A-Star algorithm, for use with our Node
     * class and custom heuristic function.
     */
    static public ArrayList<Plan> findPlan(PlayerState start, int goal, PlannerAgent agent, MapManager map) {
        Map<PlayerState, Object> closedMap = new HashMap<>();
        PriorityQueue<ScoreNode<PlayerState>> openQueue = new PriorityQueue<>();
        Map<PlayerState, PlayerState> parentMap = new HashMap<>();
        Map<PlayerState, Plan> planFromParentMap = new HashMap<>();

        Map<PlayerState, Double> gScore = new HashMap<>();
        Map<PlayerState, Double> hScore = new HashMap<>();
        Map<PlayerState, Double> fScore = new HashMap<>();

        PlayerState bestState = null;
        double highestTotalScore = -1;
        double doubleGoal = (double) goal;
        // set scores for start Node
        gScore.put(start, 0.0);
        hScore.put(start, heuristic(start, doubleGoal));
        fScore.put(start, hScore.get(start));

        // at the beginning, only the start node has to be considered
        openQueue.add(new ScoreNode(fScore.get(start), start));
//        Timer timer = new Timer(); 
        long startTime = System.currentTimeMillis();
        long curTime;
        long timeLapsed = 0;
        long TIME_LIMIT_MILISECONDS = 55000;
        
        // work through all the nodes in the openQueue, which are sorted by
        // their fScore
        while (!openQueue.isEmpty() && timeLapsed < TIME_LIMIT_MILISECONDS) {
            // move node from openQueue to closedMap
            ScoreNode<PlayerState> currentFscoreNode = openQueue.remove();
            closedMap.put(currentFscoreNode.getData(), null);

            int currentFscoreNodeTotalScore = currentFscoreNode.getData().getTotalScore();
            if (currentFscoreNodeTotalScore > highestTotalScore) {// 
                highestTotalScore = currentFscoreNodeTotalScore;
                bestState = currentFscoreNode.getData();
            }

            // arrived? if yes -> build the path from the predecessor map
            if (goal != currentFscoreNode.getData().getTotalScore()) {
            } else {

                ArrayList<Plan> path = reconstructPath(parentMap, planFromParentMap, currentFscoreNode.getData());
                path.add(planFromParentMap.get(currentFscoreNode.getData()));
                return path;
            }



            // not done yet -> check out the neighbors that we can reach and did
            // not check yet

            for (PlayerStatePlan neighbour : getPlanNeigbours(currentFscoreNode.getData(), agent, map)) {
                if (closedMap.containsKey(neighbour.getPlayerState())) {
                    continue;
                }

                // calculate temporary gScore
                int timeCostFromCurrentToNeighbour = neighbour.getPlayerState().getTotalTime() - currentFscoreNode.getData().getTotalTime();
                double tentativeGScoreNeighbour = gScore.get(currentFscoreNode.getData()) + timeCostFromCurrentToNeighbour;

                double storedGScoreNeighbour;
                if (gScore.get(neighbour.getPlayerState()) != null) {
                    storedGScoreNeighbour = gScore.get(neighbour.getPlayerState());
                } else {
                    storedGScoreNeighbour = Integer.MAX_VALUE;
                }

                boolean inOpenList = false;
                if (openQueue.contains(new ScoreNode(0, neighbour.getPlayerState()))) {
                    inOpenList = true;
                    if (tentativeGScoreNeighbour >= storedGScoreNeighbour) {
                        continue;
                    }
                }
                
                if (!inOpenList) {
                    parentMap.put((PlayerState) neighbour.getPlayerState(), currentFscoreNode.getData());
                    planFromParentMap.put((PlayerState) neighbour.getPlayerState(), neighbour.getPlan());
                    gScore.put((PlayerState) neighbour.getPlayerState(), tentativeGScoreNeighbour);
                    double heuristicScore = HEURISTIC_SCALE_FACTOR * heuristic(neighbour.getPlayerState(), goal);
                    hScore.put(neighbour.getPlayerState(), heuristicScore);
                    double funcScore = tentativeGScoreNeighbour +  heuristicScore;
                    fScore.put(neighbour.getPlayerState(),funcScore);
                    openQueue.add(new ScoreNode(funcScore, neighbour.getPlayerState()));
                }
            }
            
            curTime = System.currentTimeMillis();
            timeLapsed = curTime - startTime;
        }
        
        
        return reconstructPath(parentMap, planFromParentMap, bestState);
        
    }
        
        /**
         * Heuristic function for A-Star, using the Tie-Breaker Manhattan
         * Distance
         * <br>
         * As tie-breaker the delta y * 0.001 was chosen.
         *
         */
    
    

    static private double heuristic(PlayerState playerState, double goalScore) {       
        return goalScore - playerState.getTotalScore();       
    }

    private static Iterable<PlayerStatePlan> getPlanNeigbours(PlayerState current, PlannerAgent agent, MapManager map) {
        ArrayList<PlayerStatePlan> result = new ArrayList<>();
        List<Plan> neededPlans = agent.getNeededPlans(current);
        for(Plan plan: neededPlans) {
            PlayerState dummy = new PlayerState(current);
            dummy.simulatePlan(plan, map);
            PlayerStatePlan neighbour = new PlayerStatePlan(dummy, plan);
            result.add(neighbour);
        }
        
        return result;
    }

    private static ArrayList<Plan> reconstructPath(Map<PlayerState, PlayerState> parentMap, Map<PlayerState, Plan> planFromParentMap, PlayerState current) {
           
        if (parentMap.containsKey(current)) {
            ArrayList<Plan> path = reconstructPath(parentMap,planFromParentMap, parentMap.get(current));
            path.add(planFromParentMap.get(current));
            return path;
        } else {
            ArrayList<Plan> path = new ArrayList<>();
            path.add(planFromParentMap.get(current));
            return path;
        }

        
        
    }
}
