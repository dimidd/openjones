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
import jones.actions.EnterBuildingMovement;
import jones.map.Building;
import jones.map.GridTile;

/**
 *
 * @author dimid
 */
public class Game {
    public static final int NOOP_ACTION_INDEX = -1;
    public final int MAX_PLAYERS = 4;
    public final static int TIMEUNITS_PER_WEEK = 600;
    public final static int TIMEUNITS_PER_HOUR = 5;
    private ArrayList<Player> _players;
    private EventManager _eventGen;
    private int _curPlayerIndex;
    private Player _curPlayer;
    //private boolean [] _hasWon; //true if player met his goals
    private int _clothesLevel;
    public final int MIN_PERIOD_BETWEEN_RENT_ANNOUNCEMENTS = 4;
    private boolean _hasStarted;
    private boolean _hasEnded;

    public boolean hasStarted() {
        return _hasStarted;
    }

    public boolean hasEnded() {
        return _hasEnded;
    }

    public Player getCurPlayer() {
        return _curPlayer;
    }
    private ArrayList<Player> _victors; // = new ArrayList<>;
    private MapManager _map;
    private EconomyManager _economy; //holds a list of stocks and updates them
    private ArrayList<GameAnnouncement> _annoncments;
    private Action _weekendEvent;

    public boolean hasAnnouncements() {
        return !_annoncments.isEmpty();
    }

    public ArrayList<GameAnnouncement> getAnnouncements() {
        return _annoncments;
    }

    /**
     * Initializes the game. Adds Default buildings
     */
    public Game(MapManager map) {
        this._hasStarted = false;
        this._hasEnded = false;      
        
        if (null == map) {
            _map = MapManager.getDefaultMap();
        } else {
            _map = map;
        }

        _players = new ArrayList<>();
        _victors = new ArrayList<>();
        _eventGen = new EventManager();
        _economy = new ConstantEconomyModel();
        _curPlayerIndex = -1;
        _curPlayer = null;
        _annoncments = new ArrayList<>();
    }

    /**
     * Adds a player to the game
     *
     * @param p player
     * @return True iff added successfully
     */
    public boolean addPlayer(Player p) {
        if (_players.size() >= MAX_PLAYERS) {
            return false;
        }
        _players.add(p);
        return true;
    }

    /**
     * Move player to a different position. If there's not enough time, end
     * turn.
     *
     * @return True iff the move was completed
     * @param pos new position
     */
    public ActionResponse movePlayer(PlayerPosition pos) {
        return _curPlayer.getState().movePlayer(pos, _map);
//        Action event = _eventGen.getRandomRoadEvent(_curPlayer);
//        event.perform (_curPlayer);

//        Route route = Route.findRoute(_curPlayer.getState().getPos(), pos, _map);
//        ArrayList<Movement> path = route.getMovementSequence();
//        Iterator<Movement> iter = path.iterator();
//        while (hasTime() && iter.hasNext()) {
//            Movement move = iter.next();
//            move.perform(_curPlayer.getState());//updates player state and calls 
////            if (!hasTime()) {
////                endTurn();
////            }
//
//        }
//        
//        if (!hasTime() && iter.hasNext())
//            return new ActionResponse(false, "Not enough time to complete move");
//        else
//            return new ActionResponse(true, null);
        
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
     *
     * @return True iff any player has won
     */
    public boolean endTurn() {

        checkVictory();
        _curPlayer = getNextPlayer();        
        _curPlayer.startWeek();
        changeEconomy();
        updateAnnouncements();
        _curPlayer.consume();

        return false;
    }

    private Player getNextPlayer() {
        if (_players.isEmpty()) {
            return null;
        }

        if (_players.size() - 1 > _curPlayerIndex) {
            ++_curPlayerIndex;
            return _players.get(_curPlayerIndex);
        } else {
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
    public ActionResponse performBuildingAction(int actionIndex, ArrayList<Action> possibleActions) {
        
        ActionResponse result =  _curPlayer.getState().performBuildingAction(actionIndex, _map, possibleActions);
        if (null != result._message) {
            _annoncments.add(new GameAnnouncement(result._message+"\n"));
        }
        return result;

    }

    public boolean hasTime() {
        return _curPlayer.getState().getHour() < TIMEUNITS_PER_WEEK;
    }

    /**
     * Get all possible actions the current player can perform. If he is inside
     * a building, returns the building's actions and an exit action Otherwise,
     * returns all movements (a movement for each building)
     *
     * @return
     */
    public ArrayList<Action> getPossibletActions() {        
            return _curPlayer.getPossibletActions(_map);      
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

    

    private void updateAnnouncements() {
        _annoncments.clear();
        //weekendEvent();
        //checkFood();
        checkRent();
        CheckClothes();
        //checkRelative();
    }

    private void checkRelative() {
        int relativeHelp = _curPlayer.getSumOfRescueFromRelative();
        if (relativeHelp > 0) {
            _annoncments.add(new GameAnnouncement("A relative sent you " + relativeHelp + "$"));
        }


    }

    private void CheckClothes() {
        if (_curPlayer.areClothesAboutToWare()) {
            _annoncments.add(new GameAnnouncement("You need new clothes"));
        }

    }

    /**
     * Rent model: The player starts with 4 WORs (weeks of rent), and a rent
     * debt of 0. Every turn, one WOR is consumed. Whenever the player reaches 0
     * week, he player receives an announcement (in condition that at least 4
     * weeks have passed since the last announcement). Every week, if the player
     * has 0 WOR (before consuming), the rent debt is increased by the value of
     * 1 WOR. The debt is garnished from the player's wage, and prevent from
     * signing a rent contract.
     */
    private void checkRent() {
        if (_curPlayer.isRentDue() && (_curPlayer.getWeeks() - _curPlayer.getlastRentAnnouncement()) >= MIN_PERIOD_BETWEEN_RENT_ANNOUNCEMENTS) {
            _annoncments.add(new GameAnnouncement("Rent is due"));
        }

    }

    private void checkFood() {
        if (_curPlayer.hasFoodSpoiled()) {
            if (_curPlayer.hasAllFoodSpoiled()) {
                _annoncments.add(new GameAnnouncement("All your food spoiled!"));
            } else {
                _annoncments.add(new GameAnnouncement("Some of your food spoiled!"));
            }
        }

    }

    private void weekendEvent() {
        _annoncments.add(new GameAnnouncement(_weekendEvent.toString()));

    }

    public void startGame() {
        _curPlayerIndex = 0;
        _curPlayer = _players.get(_curPlayerIndex);
        
        _curPlayer.gotoStartPosition();
        _curPlayer.setClock(0);
        _annoncments.add(new GameAnnouncement("Good Luck!"));
        _hasStarted = true;

    }

    public ArrayList<Player> getPlayers() {
        return _players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this._players = players;
    }

    public EventManager getEventGen() {
        return _eventGen;
    }

    public void setEventGen(EventManager eventGen) {
        this._eventGen = eventGen;
    }

    public int getCurPlayerIndex() {
        return _curPlayerIndex;
    }

    public void setCurPlayerIndex(int curPlayerIndex) {
        this._curPlayerIndex = curPlayerIndex;
    }

    public int getClothesLevel() {
        return _clothesLevel;
    }

    public void setClothesLevel(int clothesLevel) {
        this._clothesLevel = clothesLevel;
    }

    public ArrayList<Player> getVictors() {
        return _victors;
    }

    public void setVictors(ArrayList<Player> victors) {
        this._victors = victors;
    }

    public MapManager getMap() {
        return _map;
    }

    public void setMap(MapManager map) {
        this._map = map;
    }

    public EconomyManager getEconomy() {
        return _economy;
    }

    public void setEconomy(EconomyManager economy) {
        this._economy = economy;
    }

    public ArrayList<GameAnnouncement> getAnnoncments() {
        return _annoncments;
    }

    public void setAnnoncments(ArrayList<GameAnnouncement> annoncments) {
        this._annoncments = annoncments;
    }

    public Action getWeekendEvent() {
        return _weekendEvent;
    }

    public void setWeekendEvent(Action weekendEvent) {
        this._weekendEvent = weekendEvent;
    }

    
    /**     
     * Returns a string that contains all annoncements, each in a separate line;
     */
    public String getAllAnnouncements() {
       StringBuilder result = new StringBuilder(); 
       for (GameAnnouncement ga: _annoncments) {
           result.append(ga._msg+"\n");
       }
       
       return result.toString();
    }

    private boolean checkVictory() {
                ArrayList<GameAnnouncement> vicorsAnnouncements = new ArrayList<>();
        // we check all players, even players who haven't this turn,
        // since they may win due to an economy change
        // (e.g. stocks rising, losing a job)
        for (Player p : _players) {
            if (p.hasWon()) {
                _victors.add(p);
                vicorsAnnouncements.add(new GameAnnouncement(p.getName()+" has won!"));
            }
        }
        
        if (_victors.size() == _players.size())
            _hasEnded = true;
        
        if (!_victors.isEmpty()) {
            _hasEnded = true;
            _annoncments.clear();
            _annoncments.addAll(vicorsAnnouncements);
            return true;
        }
        
        return false;

        
    }

 
}