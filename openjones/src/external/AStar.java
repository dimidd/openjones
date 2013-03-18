package external;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author Hieu Dao Trung, Antonia Schmalstieg, Joschka Fischer, August 2011
 */
public class AStar {
	/**
	 * Our own implementation of the A-Star algorithm, for use with our Node
	 * class and custom heuristic function.
	 */
	static public List<Node> findPath(Node start, Node goal, Node[] blockedNodes) {
		// create all needed maps
		Map<Node, Object> closedMap = new HashMap<Node, Object>();
		PriorityQueue<FScoreNode> openQueue = new PriorityQueue<FScoreNode>();
		Map<Node, Node> predMap = new HashMap<Node, Node>();

		Map<Node, Double> gScore = new HashMap<Node, Double>();
		Map<Node, Double> hScore = new HashMap<Node, Double>();
		Map<Node, Double> fScore = new HashMap<Node, Double>();

		// add blocked positions to closedMap to ignore those positions
		for (Node node : blockedNodes) {
			closedMap.put(node, null);
		}

		// set scores for start Node
		gScore.put(start, 0.0);
		hScore.put(start, heuristic(start, goal));
		fScore.put(start, hScore.get(start));

		// at the beginning, only the start node has to be considered
		openQueue.add(new FScoreNode(fScore.get(start), start));

		// work through all the nodes in the openQueue, which are sorted by
		// their fScore
		while (!openQueue.isEmpty()) {
			// move node from openQueue to closedMap
			Node current = openQueue.remove().node;
			closedMap.put(current, null);

			// arrived? if yes -> build the path from the predecessor map
			if (current.equals(goal)) {
				List<Node> path = reconstructPath(predMap, predMap.get(goal));
				path.add(goal);
				return path;
			}

			// not done yet -> check out the neighbors that we can reach and did
			// not check yet
			for (Node neighbor : current.e) {
				if (neighbor == null || closedMap.containsKey(neighbor))
					continue;

				 // calculate temporary gScore
				double g = 1000.0;
				if (gScore.get(neighbor) != null)
					g = gScore.get(neighbor);
				double tempGScore = g + 1;

				// check if its better to go this way
				boolean neighborNotInQueue = false;
				boolean tempScoreBetter = false;

				if (!openQueue.contains(neighbor)) {
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
					openQueue
							.add(new FScoreNode(fScore.get(neighbor), neighbor));
				}
			}

		}

		return null;
	}

	/**
	 * Heuristic function for A-Star, using the Tie-Breaker Manhattan Distance <br>
	 * As tie-breaker the delta y * 0.001 was chosen.
	 */
	static private double heuristic(Node n, Node m) {
		return Math.abs(n.p.x - m.p.x) + Math.abs(n.p.y - m.p.y) * 0.001;
	}

	/**
	 * Reconstructs path recursively
	 */
	static private List<Node> reconstructPath(Map<Node, Node> predMap,
			Node current) {
		if (predMap.containsKey(current)) {
			List<Node> path = reconstructPath(predMap, predMap.get(current));
			path.add(current);
			return path;
		} else {
			List<Node> path = new LinkedList<Node>();
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
	public Node node;

	public FScoreNode(double d, Node n) {
		this.fscore = d;
		this.node = n;
	}

	@Override
	public int compareTo(FScoreNode o) {
		if (fscore < o.fscore)
			return -1;
		else if (fscore > o.fscore)
			return 1;
		return 0;
	}

}
