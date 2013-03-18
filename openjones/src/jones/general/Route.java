/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.general;

import external.AStar;
import external.Node;
import external.Point;
import jones.actions.Movement;
import jones.map.MapManager;
import java.util.ArrayList;
import java.util.List;
import jones.map.Wall;

/**
 *
 * @author dimid
 */
public class Route {

    private  ArrayList<Movement> _movements;
    
    private Route () {
        _movements = new ArrayList<>();
    }
    
 
    /**
     * Assumes uniform cost of 1
     * @return 
     */
    int getDuration() {
        int sum = 0;
        for (Movement m: _movements) {
            sum += m.timeEffect(null);
        }
        return sum;
    }

    ArrayList<Movement> getMovementSequence() {
        return _movements;
    }

    	
	   
    public static Route findRoute(PlayerPosition src, PlayerPosition dest, MapManager map) {
         Route result = new Route();
         if (src.getX() == dest.getX() && src.getY() == dest.getY()) {
             return addBuildingMovements(src, dest, result);         
         }
         
         Node srcNode = new Node(new Point(src.getX(),src.getY()));
         Node destNode = new Node(new Point(dest.getX(),dest.getY()));
         Node[] blocked = new Node[map.getWalls().size()];
         int i = 0;
         for (Wall w: map.getWalls()) {
             Position pos = w.getPosition();
             blocked[i] = new Node(new Point(pos.getX(),pos.getY()));
             ++i;
         }               
         List<Node> foundPath = AStar.findPath(srcNode, destNode,  blocked);
         Node[] path = (Node[]) foundPath.toArray();
         
         PlayerPosition lastPos = src;         
         PlayerPosition curPos = src;         
          
         //boolean skipFirst = ;
                 
         // if you start inside, exit first of all
         if (src.isInBuilding()) {
             curPos = new PlayerPosition(src, false);
             result._movements.add(new Movement(src, lastPos));
         } 
         
         //Movement m
         for (i = 1; i < path.length; ++i) {
             lastPos = curPos;
             curPos = new PlayerPosition(path[i].p.x, path[i].p.y, false);
             Movement m = new Movement(lastPos, curPos);
             result._movements.add(m);
         }
             
          // if you finish inside, enter at the end
         if (dest.isInBuilding()) {             
             result._movements.add(new Movement(curPos, dest));
         } 
        
         return result;
    }

   private static Route addBuildingMovements (PlayerPosition src, PlayerPosition dest, Route result) {
                  
            if (src.isInBuilding() == dest.isInBuilding()) {
                 return result; //same positisions
             }          
             else {//enter/exit building
                 result._movements.add(new Movement(src, dest));
                 return result;
             }                         

   }
}

