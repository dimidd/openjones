package external;

/**
 * 2D Point
 * 
 * @author Hieu Dao Trung, Antonia Schmalstieg, Joschka Fischer, August 2011
 */

public class Point extends java.awt.Point implements Comparable<Point> {

	public Point(int x, int y) {
		super(x, y);
	}

	public static final int N = 0, E = 1, S = 2, W = 3;

	/**
	 * Returns the point that is in the direction (can use N, E, S, W)
	 */
	public Point pointInDir(int dir) {
		return new Point(x + (2 - dir) % 2, y + (1 - dir) % 2);
	}

	public int directionTo(Point p) {
		if (x == p.x && y < p.y)
			return 0;
		if (x < p.x && y == p.y)
			return 1;
		if (x == p.x && y > p.y)
			return 2;
		if (x > p.x && y == p.y)
			return 3;
		return -1;
	}

	@Override
	public String toString() {
		return String.format("(%d;%d)", x, y);
	}

	@Override
	public int compareTo(Point o) {
		if (x < o.x)
			return -1;
		else if (x > o.x)
			return 1;
		else if (y < o.y)
			return -1;
		else if (y > o.y)
			return 1;
		else
			return 0;
	}

	public static int oppositeDirOf(int dir) {
		return (dir + 2) % 4;
	}

	private static final long serialVersionUID = 8576171113048076121L;
}
