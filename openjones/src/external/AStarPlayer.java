/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package external;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import jones.agents.Plan;
import jones.agents.Plan.PlanType;
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
    static public List<PlanType> findPlan(PlayerState start, int goal, PlannerAgent agent, MapManager map, boolean isOnDemand) {
        Map<PlayerState, Object> closedMap = new HashMap<>();
        PriorityQueue<ScoreNode<PlayerStateNode>> openQueue = new PriorityQueue<>();

        Map<PlayerState, Double> gScore = new HashMap<>();
        Map<PlayerState, Double> hScore = new HashMap<>();
        Map<PlayerState, Double> fScore = new HashMap<>();

        PlayerStateNode bestState = null;
        double highestSumScore = -1;
        double doubleGoal = (double) goal;
        // set scores for start Node
        gScore.put(start, 0.0);
        hScore.put(start, heuristic(start, doubleGoal));
        fScore.put(start, hScore.get(start));

        // at the beginning, only the start node has to be considered
        PlayerStateNode startNode = new PlayerStateNode(start, null, null);
        openQueue.add(new ScoreNode<>(fScore.get(start), startNode));
        long startTime = System.currentTimeMillis();
        long curTime;
        long timeLapsed = 0;
        long TIME_LIMIT_MILISECONDS = 125000;

        // work through all the nodes in the openQueue, which are sorted by
        // their fScore
        while (!openQueue.isEmpty() && timeLapsed < TIME_LIMIT_MILISECONDS) {
            // move node from openQueue to closedMap
            ScoreNode<PlayerStateNode> currentFscoreNode = openQueue.remove();
            closedMap.put(currentFscoreNode.getData().getState(), null);

            double currentFscoreNodeSumScore = currentFscoreNode.getData().getState().getSumScore();
            if (currentFscoreNodeSumScore > highestSumScore) {
                highestSumScore = currentFscoreNodeSumScore;
                bestState = currentFscoreNode.getData();
            }

            // arrived? if yes -> build the path from the predecessor map
            if (goal != currentFscoreNode.getData().getState().getTotalScore()) {

            }

            else {

                List<PlanType> path = reconstructPath(currentFscoreNode.getData());
                return path;
            }

            Iterable<PlayerStateNode> planNeigbours = isOnDemand? getNeededPlanNeigbours(currentFscoreNode.getData(), agent, map) : getPlanNeigbours(currentFscoreNode.getData(), agent, map);
            for (PlayerStateNode neighbour : planNeigbours) {

                if (closedMap.containsKey(neighbour.getState())) {
                    continue;
                }

                int timeCostFromCurrentToNeighbour = neighbour.getState().getTotalTime() - currentFscoreNode.getData().getState().getTotalTime();
                double tentativeGScoreNeighbour = gScore.get(currentFscoreNode.getData().getState()) + timeCostFromCurrentToNeighbour;
                double storedGScoreNeighbour;
                if (gScore.get(neighbour.getState()) != null) {
                    storedGScoreNeighbour = gScore.get(neighbour.getState());
                } else {
                    storedGScoreNeighbour = Integer.MAX_VALUE;
                }
                boolean inOpenList = false;
                if (openQueue.contains(new ScoreNode<>(0, neighbour))) {
                    inOpenList = true;
                    if (tentativeGScoreNeighbour >= storedGScoreNeighbour) {
                        continue;
                    }
                }
                if (!inOpenList) {

                    gScore.put((PlayerState) neighbour.getState(), tentativeGScoreNeighbour);
                    double heuristicScore = HEURISTIC_SCALE_FACTOR * heuristic(neighbour.getState(), goal);
                    hScore.put(neighbour.getState(), heuristicScore);
                    double funcScore = tentativeGScoreNeighbour + heuristicScore;
                    fScore.put(neighbour.getState(), funcScore);

                    openQueue.add(new ScoreNode<>(funcScore, neighbour));
                }
            }

            curTime = System.currentTimeMillis();
            timeLapsed = curTime - startTime;
        }


        return reconstructPath(bestState);

    }

    /**
     * Heuristic function for A-Star, using the Tie-Breaker Manhattan Distance
     * <br>
     * As tie-breaker the delta y * 0.001 was chosen.
     *
     */
    static private double heuristic(PlayerState playerState, double goalScore) {
        return goalScore - playerState.getTotalScore();
    }

    private static Iterable<PlayerStateNode> getNeededPlanNeigbours(PlayerStateNode current, PlannerAgent agent, MapManager map) {
        ArrayList<PlayerStateNode> result = new ArrayList<>();
        List<Plan> neededPlans = agent.getNeededPlans(current.getState());
        for (Plan plan : neededPlans) {
            PlayerState dummy = new PlayerState(current.getState());
            if (current.getEdge() != null && current.getEdge().getType() == Plan.PlanType.BETTER_JOB) {
                boolean b = false;
            }
            dummy.simulatePlan(plan, map);
            PlayerStateNode neighbour = new PlayerStateNode(dummy, current, plan);
            result.add(neighbour);
        }

        return result;
    }

       private static Iterable<PlayerStateNode> getPlanNeigbours(PlayerStateNode current, PlannerAgent agent, MapManager map) {
        ArrayList<PlayerStateNode> result = new ArrayList<>();
        List<Plan> neededPlans = agent.getPlans(current.getState());
        neededPlans.stream().map((plan) -> {
            PlayerState dummy = new PlayerState(current.getState());
            if (current.getEdge() != null && current.getEdge().getType() == Plan.PlanType.BETTER_JOB) {
                boolean b = false;
            }
            dummy.simulatePlan(plan, map);
            PlayerStateNode neighbour = new PlayerStateNode(dummy, current, plan);
            return neighbour;
        }).forEachOrdered((neighbour) -> {
            result.add(neighbour);
        });

        return result;
    }


    private static List<PlanType> reconstructPath(PlayerStateNode node) {
        LinkedList<PlanType> path = new LinkedList<>();
        Plan plan = node.getEdge();
        while (plan != null) {
            path.push(plan.getType());
            node = node.getParent();
            plan = node.getEdge();
        }

        return path;
    }
}
