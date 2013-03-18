/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.general;

import external.AStarPos;
import external.Node;
import external.Point;
import jones.actions.Movement;
import jones.map.MapManager;
import java.util.ArrayList;
import java.util.List;
import jones.actions.EnterBuildingMovement;
import jones.actions.ExitBuildingMovement;
import jones.map.Building;
import jones.map.GridTile;
import jones.map.Wall;

/**
 *
 * @author dimid
 */
public class Route {

    private ArrayList<Movement> _movements;

    private Route() {
        _movements = new ArrayList<>();
    }

    /**
     * Assumes uniform cost of 1
     *
     * @return
     */
    int getDuration() {
        int sum = 0;
        for (Movement m : _movements) {
            sum += m.timeEffect(null);
        }
        return sum;
    }

    ArrayList<Movement> getMovementSequence() {
        return _movements;
    }

    public static ArrayList<Position> getNeigbours(Position curPos, MapManager _map) {

        ArrayList<Position> result = new ArrayList<>();
        GridTile tile;
        Position test = new Position(curPos);

        //north
        test.setXY(curPos.getX(), curPos.getY() - 1);
        try {
            tile = _map.getTile(test);
            if (null != tile && tile.isPassable()) {
                result.add(new Position(test));
            }
        } catch (IllegalArgumentException iae) {
        }


        //east
        test.setXY(curPos.getX() + 1, curPos.getY());
        try {
            tile = _map.getTile(test);
            if (null != tile && tile.isPassable()) {
                result.add(new Position(test));
            }
        } catch (IllegalArgumentException iae) {
        }

        //south
        test.setXY(curPos.getX(), curPos.getY() + 1);
        try {
            tile = _map.getTile(test);
            if (null != tile && tile.isPassable()) {
                result.add(new Position(test));
            }
        } catch (IllegalArgumentException iae) {
        }

        //west
        test.setXY(curPos.getX() - 1, curPos.getY());
        try {
            tile = _map.getTile(test);
            if (null != tile && tile.isPassable()) {
                result.add(new Position(test));
            }
        } catch (IllegalArgumentException iae) {
        }

        return result;
    }

    public static Route findRoute(PlayerPosition src, PlayerPosition dest, MapManager map) {
        Route result = new Route();
        if (src.getX() == dest.getX() && src.getY() == dest.getY()) {
            return addEnterBuildingMovement(src, dest, result, map);
        }

//        Node srcNode = new Node(new Point(src.getX(), src.getY()));
//        Node destNode = new Node(new Point(dest.getX(), dest.getY()));
//        Node[] blocked = new Node[map.getWalls().size()];
//        int i = 0;
//        for (Wall w : map.getWalls()) {
//            Position pos = w.getPosition();
//            blocked[i] = new Node(new Point(pos.getX(), pos.getY()));
//            ++i;
//        }

        ArrayList<Position> foundPath = AStarPos.findPath(src, dest, map);
        //O[] path = (Node[]) foundPath.toArray();

        PlayerPosition lastPos = src;
        PlayerPosition curPos = src;

        //boolean skipFirst = ;

        // if you start inside, exit first of all
        if (src.isInBuilding()) {
            curPos = new PlayerPosition(src, false);
            Building build = (Building) map.getTile(src);
            result._movements.add(new ExitBuildingMovement(src, build));
        }

        //Movement m
        for (Position pos: foundPath) {
            lastPos = curPos;
            curPos = new PlayerPosition(pos, false);
            Movement m = new Movement(lastPos, curPos);
            result._movements.add(m);
        }

        // if you finish inside, enter at the end
        if (dest.isInBuilding()) {
            Building build = (Building) map.getTile(curPos);
            result._movements.add(new EnterBuildingMovement(curPos, build));
        }

        return result;
    }

    private static Route addEnterBuildingMovement(PlayerPosition src, PlayerPosition dest, Route result, MapManager map) {

        if (src.isInBuilding() == dest.isInBuilding()) {
            return result; //same positisions
        } else {//enter/exit building

            Building build = (Building) map.getTile(src);
            result._movements.add(new EnterBuildingMovement(src, build));
            return result;
        }

    }
}
