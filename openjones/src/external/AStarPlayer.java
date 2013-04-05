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

/**
 *
 * @author dimid
 */
public class AStarPlayer {

    /**
     * Our own implementation of the A-Star algorithm, for use with our Node
     * class and custom heuristic function.
     */
    static public ArrayList<Plan> findPlan(PlayerState start, int goal, PlannerAgent agent) {
        Map<PlayerState, Object> closedMap = new HashMap<>();
        PriorityQueue<ScoreNode<PlayerState>> openQueue = new PriorityQueue<>();
        Map<PlayerState, PlayerState> parentMap = new HashMap<>();
        Map<PlayerState, Plan> planFromParentMap = new HashMap<>();

        Map<PlayerState, Integer> gScore = new HashMap<>();
        Map<PlayerState, Integer> hScore = new HashMap<>();
        Map<PlayerState, Integer> fScore = new HashMap<>();

        PlayerState bestState = null;
        int bestScore = -1;

        // set scores for start Node
        gScore.put(start, 0);
        hScore.put(start, heuristic(start, goal));
        fScore.put(start, hScore.get(start));

        // at the beginning, only the start node has to be considered
        openQueue.add(new ScoreNode(fScore.get(start), start));

        // work through all the nodes in the openQueue, which are sorted by
        // their fScore
        while (!openQueue.isEmpty()) {
            // move node from openQueue to closedMap
            PlayerState current = openQueue.remove().getData();
            closedMap.put(current, null);

            if (current.getScore() > bestScore) {
                bestScore = current.getScore();
                bestState = current;
            }

            // arrived? if yes -> build the path from the predecessor map
            if (goal != current.getScore()) {
            } else {

                ArrayList<Plan> path = reconstructPath(parentMap, planFromParentMap, current);
                path.add(planFromParentMap.get(current));
                return path;
            }



            // not done yet -> check out the neighbors that we can reach and did
            // not check yet

            for (PlayerStatePlan neighbour : getPlanNeigbours(current, agent)) {
                if (closedMap.containsKey(neighbour.getPlayerState())) {
                    continue;
                }

                // calculate temporary gScore
                int timeCostFromCurrentToNeighbour = neighbour.getPlayerState().getTotalTime() - current.getTotalTime();
                int tentativeGScoreNeighbour = gScore.get(current) + timeCostFromCurrentToNeighbour;

                int storedGScoreNeighbour;
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
                    parentMap.put((PlayerState) neighbour.getPlayerState(), current);
                    planFromParentMap.put((PlayerState) neighbour.getPlayerState(), neighbour.getPlan());
                    gScore.put((PlayerState) neighbour.getPlayerState(), tentativeGScoreNeighbour);
                    int heuristicScore = heuristic(neighbour.getPlayerState(), goal);
                    hScore.put(neighbour.getPlayerState(), heuristicScore);
                    int funcScore = tentativeGScoreNeighbour + heuristicScore;
                    fScore.put(neighbour.getPlayerState(),funcScore);
                    openQueue.add(new ScoreNode(funcScore, neighbour.getPlayerState()));
                }
            }
            
            
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
    
    

    static private int heuristic(PlayerState playerState, int goalScore) {       
        return goalScore - playerState.getScore();       
    }

    private static Iterable<PlayerStatePlan> getPlanNeigbours(PlayerState current, PlannerAgent agent) {
        ArrayList<PlayerStatePlan> result = new ArrayList<>();
        List<Plan> neededPlans = agent.getNeededPlans(current);
        for(Plan plan: neededPlans) {
            PlayerState dummy = new PlayerState(current);
            dummy.simulatePlan(plan);
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
