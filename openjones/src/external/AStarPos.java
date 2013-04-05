package external;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import jones.general.Position;
import jones.general.Route;
import jones.map.Grid;
import jones.map.MapManager;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dimid <dimidd@gmail.com>
 */
public class AStarPos {

    /**
     * Our own implementation of the A-Star algorithm, for use with our Node
     * class and custom heuristic function.
     */
    static public ArrayList<Position> findPath(Position start, Position goal, MapManager _map) {
        // create all needed maps
        Map<Position, Object> closedMap = new HashMap<>();
        PriorityQueue<FScoreNode> openQueue = new PriorityQueue<>();
        Map<Position, Position> predMap = new HashMap<>();

        Map<Position, Integer> gScore = new HashMap<>();
        Map<Position, Integer> hScore = new HashMap<>();
        Map<Position, Integer> fScore = new HashMap<>();

//		// add blocked positions to closedMap to ignore those positions
//		for (Node node : blockedNodes) {
//			closedMap.put(node, null);
//		}

        // set scores for start Node
        gScore.put(start, 0);
        hScore.put(start, heuristic(start, goal));
        fScore.put(start, hScore.get(start));

        // at the beginning, only the start node has to be considered
        openQueue.add(new FScoreNode(fScore.get(start), start));

        // work through all the nodes in the openQueue, which are sorted by
        // their fScore
        while (!openQueue.isEmpty()) {
            // move node from openQueue to closedMap
            Position current = openQueue.remove().pos;
            closedMap.put(current, null);

            // arrived? if yes -> build the path from the predecessor map
            if (current.equals(goal)) {
                ArrayList<Position> path = reconstructPath(predMap, predMap.get(goal));
                path.add(goal);
                return path;
            }



            // not done yet -> check out the neighbors that we can reach and did
            // not check yet



            for (Position neighbor : Route.getNeigbours(current, _map)) {
                if (closedMap.containsKey(neighbor)) {
                    continue;
                }

                // calculate temporary gScore
                int g = 1000;
                if (gScore.get(neighbor) != null) {
                    g = gScore.get(neighbor);
                }
                int tempGScore = g + 1;

                // check if its better to go this way
                boolean neighborNotInQueue = false;
                boolean tempScoreBetter;

                if (!openQueue.contains(new FScoreNode(0, neighbor))) {
                    neighborNotInQueue = true;
                    tempScoreBetter = true;
                } else if (tempGScore < g) {
                    tempScoreBetter = true;
                } else {
                    tempScoreBetter = false;

                }

                if (tempScoreBetter) {
                    predMap.put(neighbor, current);
                    gScore.put(neighbor, tempGScore);
                    hScore.put(neighbor, heuristic(neighbor, goal));
                    fScore.put(neighbor,
                            gScore.get(neighbor) + hScore.get(neighbor));
                }

                if (neighborNotInQueue) {
                    openQueue.add(new FScoreNode(fScore.get(neighbor), neighbor));
                }
            }

        }

        return null;
    }

    /**
     * Heuristic function for A-Star, using the Tie-Breaker Manhattan Distance
     * <br>
     * As tie-breaker the delta y * 0.001 was chosen.
     */
    static private int heuristic(Position n, Position m) {
        return Grid.manhattanDistance(n, m);
        //Math.abs(n.p.x - m.p.x) + Math.abs(n.p.y - m.p.y) * 0.001;
    }

    /**
     * Reconstructs path recursively
     */
    static private ArrayList<Position> reconstructPath(Map<Position, Position> predMap, Position current) {
        if (predMap.containsKey(current)) {
            ArrayList<Position> path = reconstructPath(predMap, predMap.get(current));
            path.add(current);
            return path;
        } else {
            ArrayList<Position> path = new ArrayList<>();
            path.add(current);
            return path;
        }
    }
}

/**
 * Data structure to bundle a double fScore with a Node object to create a
 * sorted Queue with Nodes, sorted by a the given temporary fScore value
 */
class FScoreNode implements Comparable<FScoreNode> {

    public double fscore;
    public Position pos;

    public FScoreNode(double d, Position p) {
        this.fscore = d;
        this.pos = p;
    }

    @Override
    public int compareTo(FScoreNode o) {
        if (fscore < o.fscore) {
            return -1;
        } else if (fscore > o.fscore) {
            return 1;
        }
        return 0;
    }

    public boolean equals(FScoreNode o) {
        return pos.equals(o.pos);
    }

    @Override
    public int hashCode() {
        return pos.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FScoreNode other = (FScoreNode) obj;
        if (!pos.equals(other.pos)) {
            return false;
        }
        return true;
    }
}

