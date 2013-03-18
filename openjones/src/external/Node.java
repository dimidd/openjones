package external;

import java.util.List;

/**
 * Nodes in the Graph that represents the Sokoban map
 * 
 * @author Hieu Dao Trung, Antonia Schmalstieg, Joschka Fischer, August 2011
 */
public class Node {

	/** Edges from this Node. Order is North, East, South, West */
	public Node[] e;

	/** Normalized coordinates of this node in the map */
	public final Point p;

	public Node(Point p) {
		e = new Node[4];
		this.p = p;
	}

	@Override
	public String toString() {
		return "<" + p.toString() + "|" + (e[0] == null ? 0 : 1) + (e[1] == null ? 0 : 1) + (e[2] == null ? 0 : 1) + (e[3] == null ? 0 : 1) + ">";
	}

	/**
	 * simple function to print List of Nodes as simple to read printout
	 */
	public static String printNodeList(List<Node> list) {
		String s = "";
		if (list != null) {
			for (Node n : list) {
				s += n + " ";
			}
		}
		return s;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Node) {
			Node n = (Node) obj;
			return (p.equals(n.p));
		}
		return super.equals(obj);
	}
}
