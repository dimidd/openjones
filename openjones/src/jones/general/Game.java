/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.general;

import jones.actions.Movement;
import jones.actions.Action;
import jones.map.MapManager;
import java.util.ArrayList;
import java.util.Iterator;
import jones.actions.ActionResponse;
import jones.map.Building;
import jones.map.GridTile;

/**
 *
 * @author dimid
 */
public class Game {
    
    public final int MAX_PLAYERS = 4;
    
    public final static int TIMEUNITS_PER_WEEK = 600;
    public final static int TIMEUNITS_PER_HOUR = 5;
    

    private ArrayList <Player> _players;
    private EventManager _eventGen;
    
    private int _curPlayerIndex;
    private Player _curPlayer;
    //private boolean [] _hasWon; //true if player met his goals
    
    public Player getCurPlayer() {
		return _curPlayer;	
    }

	private ArrayList <Player> _victors; // = new ArrayList<>;
    private MapManager _map;
    private EconomyManager _economy; //holds a list of stocks and updates them
    
    /**
     * Initializes the game. Adds Default buildings
     */
     
    public Game (MapManager map) {// Grid<Location> grid, ArrayList <Building> buildings) {
        if (null == map) { 
            _map = MapManager.getDefaultMap();
        }
        else {
            _map = map;
        }
                           
        _players = new ArrayList<>();
        _victors = new ArrayList<>();
        
        _eventGen = new EventManager();
        
        _economy = new ConstantEconomyModel();
        addDefaultStocks();
        
        _curPlayerIndex = -1;
        _curPlayer = null;
        
    }
    
    private void addDefaultStocks() {
		// TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

	}

	/**
     * Adds a player to the game
     * @param p player
     * @return True iff added successfully
     */
    public boolean addPlayer (Player p) {
        if (_players.size() >= MAX_PLAYERS) {
            return false;
        }
        _players.add(p);
        return true;
    }
    
    /**
     * Move player to a different position.
     * If there's not enough time, end turn.
     * 
     * @param pos new position
     */
    public void movePlayer (PlayerPosition pos) {
        Action event = _eventGen.getRandomRoadEvent(_curPlayer);
        event.perform (_curPlayer);
        //performAction(event, _curPlayer);
        
        Route route = Route.findRoute (_curPlayer.getState().getPos(), pos, _map);
        ArrayList<Movement> path = route.getMovementSequence();
        Iterator<Movement> iter = path.iterator();
        while (hasTime() && iter.hasNext()) {
           Movement move =  iter.next();
           move.perform(_curPlayer);//updates player state and calls 
           if (!hasTime()) {
               endTurn();
           }
           
        }
    }
    
    public void enterBuilding() {
        PlayerPosition pos = _curPlayer.getState().getPos();
        pos.enterBuilding();
        movePlayer(pos);
    }
 
    public void leaveBuilding() {
        PlayerPosition pos = _curPlayer.getState().getPos();
        pos.exitBuilding();
        movePlayer(pos);
    }

    
    /**
     * Advances game to the next player`s turn.
     * @return True iff any player has won
     */
    
    public boolean endTurn() {
        
        // we check all players, even players who haven't this turn,
        // since they may win due to an economy change
        // (e.g. stocks rising, losing a job)
        for (Player p: _players) {
            if (p.hasWon()) {
                _victors.add(p);
            }
        }
        
        if (!_victors.isEmpty()) {
            return true;
        }
        
        _curPlayer = getNextPlayer();
        _curPlayer.startTurn(); //eat food, rent etc
        Action event = _eventGen.getRandomWeekendEvent(_curPlayer);
        event.perform (_curPlayer);
        
        changeEconomy();
        return false;                
    }

    private Player getNextPlayer() {
        if (_players.isEmpty()) {
            return null;
        }
        
        if (_players.size() - 1 > _curPlayerIndex) {
            ++_curPlayerIndex;
            return _players.get(_curPlayerIndex);
        } 
        else {
            _curPlayerIndex = 0;
            return _players.get(_curPlayerIndex);
        }
						       
    }

    private void changeEconomy() {
        _economy.changeStocks();
        _economy.changeBuildings(_map.buildingsIterator()); //prices + salaries 
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


//    private String performAction(Action event, Player _curPlayer) {
//        String result = event.perform(_curPlayer);
//        return result; 
////        if (!hasTime()) {
////            endTurn();
////        }
//
//        
//        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
    
    private ActionResponse performBuildingAction(int actionIndex) {
        Building build = (Building) getPlayerTile();
        ActionResponse result = build.performAction(actionIndex,_curPlayer);
        return result; 

    }
    
    private boolean hasTime() {
		return _curPlayer.getState().getHour() < TIMEUNITS_PER_WEEK;
    }

    /**
     * Get all possible actions the current player can perform.
     * If he is inside a building, returns the building's actions and an exit action
     * Otherwise, returns all movements (a movement for each building)        
     * @return 
     */    
    public ArrayList<? extends Action> gePossibletActions() {
        PlayerPosition curPos = _curPlayer.getState().getPos();
        if(curPos.isInBuilding()) {
            Building curBuild = (Building) _map.getTile(curPos);
            return curBuild.getPlayerActions(_curPlayer);
        }
        else {
            return getPossibleMovements();
        }
    }
            
   
    /**
     * Return all possible movements to adjacent locations of the current player.
     * Checks the adjacent locations: North, East, South, West. If they are passable, adds them to the list.
     * If the current tile is enterable, also adds the Enter movement.
     * @return List of movements
     */
    private ArrayList<Movement> getPossibleMovements() {
        ArrayList<Movement> result = new ArrayList<>();
        PlayerPosition curPos = _curPlayer.getState().getPos();
        assert(!curPos.isInBuilding());
        Position test = new Position(curPos);
        
        //north
        test.setXY(curPos.getX() , curPos.getY() - 1);
        try {
            if (_map.getTile(test).isPassable()) {
                result.add( new Movement (curPos, new PlayerPosition(test, curPos.isInBuilding())));
            }
        }        
        catch  (IllegalArgumentException iae) {
            
        }
        
        //east
        test.setXY(curPos.getX() + 1, curPos.getY());
        try {
            if (_map.getTile(test).isPassable()) {
                result.add( new Movement (curPos, new PlayerPosition(test, curPos.isInBuilding())));
            }
        }        
        catch  (IllegalArgumentException iae) {
            
        }
        
        //south
        test.setXY(curPos.getX(), curPos.getY() + 1);
        try {
            if (_map.getTile(test).isPassable()) {
                result.add( new Movement (curPos, new PlayerPosition(test, curPos.isInBuilding())));
            }
        }        
        catch  (IllegalArgumentException iae) {
            
        }
        
        //west
        test.setXY(curPos.getX() - 1, curPos.getY());
        try {
            if (_map.getTile(test).isPassable()) {
                result.add( new Movement (curPos, new PlayerPosition(test, curPos.isInBuilding())));
            }
        }        
        catch  (IllegalArgumentException iae) {
            
        }
        
        if (_map.getTile(test).isEnterable()) {
            result.add( new Movement (curPos, new PlayerPosition(test, true)));
        }
        
        return result;
        
    }
    
    public int getTime() {
    	return _curPlayer.getState().getHour();
    }
    
    public int getWeek() {
    	return _curPlayer.getState().getWeeks();
    }
 
    public boolean isInside() {
    	return _curPlayer.getState().getPos().isInBuilding();
    }
    
    public GridTile getPlayerTile() {
        return _map.getTile(_curPlayer.getState().getPos());
    }
}