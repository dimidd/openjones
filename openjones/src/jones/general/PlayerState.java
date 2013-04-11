/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import jones.actions.Action;
import jones.actions.ActionResponse;
import jones.actions.EnterBuildingMovement;
import jones.actions.ExitBuildingMovement;
import jones.actions.Movement;
import jones.agents.IllegalActionException;
import jones.agents.Plan;
import jones.agents.PlannerAgent;
import jones.measures.Skill;
import jones.measures.Skills;
import jones.measures.Goals;
import jones.measures.ExperienceManager;
import jones.measures.Health;
import jones.measures.Happiness;
import jones.measures.Career;
import jones.measures.Education;
import jones.possessions.Possession;
import jones.possessions.PossessionManager;
import jones.possessions.RentPossession;
import jones.possessions.WeekOfRent;
import jones.jobs.Unemployed;
import jones.jobs.Job;
import jones.map.Building;
import jones.map.GridTile;
import jones.possessions.RentContract;
import jones.map.House;
import jones.map.MapManager;
import jones.map.RentAgency;
import net.vivin.GenericTree;
import net.vivin.GenericTreeNode;

/**
 *
 * @author dimid
 */
public class PlayerState extends AbstractPlayerState {

    public final static int MAX_JOB_RANK = 9;
    public final static int INITIAL_CASH = 200;
    //public final static int CASUAL_CLOTHES_BASE_VALUE = 70;
    /**
     * Lowest possible housing type
     */
    public final House LOWEST_HOUSING;// = new Tent(); 
    private int _clock;
    private int _weeks;
    private Goals _goals;
    private PossessionManager _possessions;
    private Skills _skills;
    private Job _job;
    private PlayerPosition _pos;
    private Health _health;
    private Happiness _happiness;
    private Career _career;
    private Education _education;
    private int _cash;
    
    //last time the player was annonced, that rent was due
    private int _lastRentAnnouncement;
    
    private GenericTreeNode<Action> _playerActionsParent;   
    private GenericTree<Action> _actionsTree;

             
            
    /**
     * Creates a new PlayerState with default values
     *
     * @param map
     */
    public PlayerState(MapManager map) {
        LOWEST_HOUSING = map.getLowestHousing();
        _clock = 0;
        _weeks = 1;
        _goals = new Goals();
        _possessions = new PossessionManager(map);
        WeekOfRent lowestHousingRentWeek = new WeekOfRent(map.getLowestHousing().pricePerWeek(), LOWEST_HOUSING);
        RentPossession baseRent = new RentPossession(RentAgency.WEEKS_OF_RENT_IN_A_MONTH, lowestHousingRentWeek);
        _possessions.setRentContract(new RentContract(baseRent));
        //       _possessions.add(baseRent);
        //       _possessions.add(new Possession(1, new CasualClothes()));
        _skills = new Skills();
        _job = new Unemployed();

        //_house = LOWEST_HOUSING;
        _pos = new PlayerPosition(getHouse().getPosition(), false);
        _health = new Health();
        _happiness = new Happiness();
        _career = new Career(MAX_JOB_RANK, _weeks);
        _cash = INITIAL_CASH;
        _education = new Education();
        _playerActionsParent = null;
        _actionsTree = null;
    }

    public GenericTreeNode<Action> getPlayerActionsParent() {
        return _playerActionsParent;
    }

    public PlayerState(PlayerState o) {
        LOWEST_HOUSING = o.LOWEST_HOUSING;
        _clock = o._clock;
        _weeks = o._weeks;

        //Goals gets a shallow copy since it's not likely to change
        _goals = o._goals;
        _possessions = new PossessionManager(o._possessions);
        _skills = new Skills();

        //Goals gets a shallow copy since it could only be repointed, and doestn't modify the internal state.   
        _job = o._job;
        _pos = new PlayerPosition(o._pos);
        _health = new Health(o.getHealth().getScore());
        _happiness = new Happiness(o.getHappiness().getScore());
        _career = new Career(o._career);
        _cash = o._cash;
        _education = new Education(o.getEducation().getScore());
        _playerActionsParent = null;
        _actionsTree = null;
    }

    
  
    public GenericTree<Action> getActionsTree() {
        return _actionsTree;
    }

    public void setActionsTree(GenericTree<Action> _actionsTree) {
        this._actionsTree = _actionsTree;
    }
    

    /**
     * @param playerActionsParent the _playerActionsParent to set
     */
    public void setPlayerActionsParent(GenericTreeNode<Action> playerActionsParent) {
        this._playerActionsParent = playerActionsParent;
    }
    
    public int getClock() {
        return _clock;
    }

    public void setClock(int _clock) {
        this._clock = _clock;
    }

    public Career getCareer() {
        return _career;
    }

    public void setCareer(Career _career) {
        this._career = _career;
    }

    public int getLastRentAnnouncement() {
        return _lastRentAnnouncement;
    }

    public void setLastRentAnnouncement(int _lastRentAnnouncement) {
        this._lastRentAnnouncement = _lastRentAnnouncement;
    }

    public void recomputeGoals() {
        _goals.recompute(this, _health, _happiness, _career, _education);
    }

    public void addPossession(Possession ps) {
        _possessions.add(ps);
    }

    public void removePossession(Possession ps) {
        _possessions.remove(ps);
    }

    public void addSkill(Skill sk) {
        _skills.add(sk);
    }

    public void removeSkill(Skill sk) {
        _skills.remove(sk);
    }

    public void affectHealth(int effect) {
        _health.add(effect);
    }

    public void affectHappiness(int effect) {
        _happiness.add(effect);
    }

    public void affectCareer(int effect) {
        _career.add(effect);
    }

    public int getHour() {
        return _clock;
    }

    /**
     *
     * @return
     */
    @Override
    public Goals getGoals() {
        return _goals;
    }

    public PossessionManager getPossessions() {
        return _possessions;
    }

    public Skills getSkills() {
        return _skills;
    }

    @Override
    public Job getJob() {
        return _job;
    }

    @Override
    public final House getHouse() {
        RentContract rentContract = _possessions.getRentContract();
        return rentContract.getHouse();
    }

    @Override
    public PlayerPosition getPos() {
        return _pos;
    }

    public Health getHealth() {
        return _health;
    }

    public Happiness getHappiness() {
        return _happiness;
    }

    public void setHour(int hour) {
        this._clock = hour;
    }

    public void setGoals(Goals goals) {
        this._goals = goals;
    }

    public void setPossessions(PossessionManager possessions) {
        this._possessions = possessions;
    }

    public void setSkills(Skills skills) {
        this._skills = skills;
    }

    public void setJob(Job job) {
        this._job = job;
    }

//    public void setHouse(House house) {
//        this._house = house;
//    }
    public void setPos(PlayerPosition pos) {
        this._pos = pos;
    }

    public void setPos(Position pos, boolean isInside) {

        this._pos.setPosition(new PlayerPosition(pos, isInside));
    }

    public void setHealth(Health health) {
        this._health = health;
    }

    public void setHappiness(Happiness happiness) {
        this._happiness = happiness;
    }

    public void affectCash(int cashEffect) {
        _cash += cashEffect;
    }

    public void affectTime(int timeEffect) {
        _clock += timeEffect;
    }

    public int timeLeft() {
        return Game.TIMEUNITS_PER_WEEK - getHour();
    }

    @Override
    public int getCash() {
        return _cash;
    }

    public void advanceWeeks() {
        _weeks += 1;
    }

    @Override
    public int getWeeks() {
        return _weeks;
    }

    @Override
    public boolean hasTime() {
        return timeLeft() > 0;
    }

    public RentContract getRentContract() {
        return _possessions.getRentContract();
    }

    public void setRentContract(RentContract r) {
        _possessions.setRentContract(r);
    }

    public int getNumOfWeeksOfRent() {
        return _possessions.getRentPossession().getUnits();
    }

    public void consume() {
        _possessions.consume();
    }

    public int getRentDebt() {
        return _possessions.getRentDebt();
    }

    public void setRentDebt(int newRentDebt) {
        _possessions.setRentDebt(newRentDebt);
    }

    @Override
    public boolean hasWon() {
        return _goals.recompute(this, _health, _happiness, _career, _education);
    }

    public Education getEducation() {
        return _education;
    }

    public void setEducation(Education education) {
        this._education = education;
    }

    public int getClothesLevel() {
        return _possessions.getClothesLevel();
    }

    public boolean isRentDue() {
        return _possessions.isRentDue();

    }

    public boolean areClothesAboutToWare() {
        return _possessions.areClothesAboutToWare();
    }

    @Override
    public int getTotalScore() {
        return (int) _goals.getAverageScore(this, _health, _happiness, _career, _education);
    }

    public String scoresString() {
        return _goals.getScoresString(this, _health, _happiness, _career, _education);
    }

    public ExperienceManager getExpriences() {
        return _career.getExp();
    }

    public void affectEducation(int EDUCATION_POINTS_GAIN) {
        _education.add(EDUCATION_POINTS_GAIN);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this._clock;
        hash = 61 * hash + this._weeks;
//        hash = 61 * hash + Objects.hashCode(this._possessions);
        hash = 61 * hash + Objects.hashCode(this._job);
        hash = 61 * hash + Objects.hashCode(this._pos);
        hash = 61 * hash + Objects.hashCode(this._health);
        hash = 61 * hash + Objects.hashCode(this._happiness);
        hash = 61 * hash + Objects.hashCode(this._career);
        hash = 61 * hash + Objects.hashCode(this._education);
        hash = 61 * hash + this._cash;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PlayerState other = (PlayerState) obj;
        if (this._clock != other._clock) {
            return false;
        }
        if (this._weeks != other._weeks) {
            return false;
        }
//        if (!Objects.equals(this._possessions, other._possessions)) {
//            return false;
//        }
        if (!Objects.equals(this._job, other._job)) {
            return false;
        }
        if (!Objects.equals(this._pos, other._pos)) {
            return false;
        }
        if (!Objects.equals(this._health, other._health)) {
            return false;
        }
        if (!Objects.equals(this._happiness, other._happiness)) {
            return false;
        }
        if (!Objects.equals(this._career, other._career)) {
            return false;
        }
        if (!Objects.equals(this._education, other._education)) {
            return false;
        }
        if (this._cash != other._cash) {
            return false;
        }
        return true;
    }

    @Override
    public double getEducationScore() {
        return getGoals().educationScore(getEducation());
    }

    @Override
    public double getHealthScore() {
        return getGoals().healthScore(getHealth());
    }

    @Override
    public double getCareerScore() {
        return getGoals().careerScore(getCareer(), this);
    }

    @Override
    public double getHappinessScore() {
        return getGoals().happinessScore(getHappiness());
    }

    @Override
    public double getWealthscore() {
        return getGoals().wealthScore(this);
    }

    @Override
    public int getPossessionsWorth() {
        return getPossessions().totalValue();
    }

    void startWeek() {
        setPos(getHouse().getPosition(), false);
        setClock(0);
        ++_weeks;
    }

    /**
     * start a new turn without updating Game
     */
    void newTurn() {
        startWeek();
        consume();
    }

    public void simulatePlan(Plan plan, MapManager map) {

        while (!plan.isEmpty()) {
            Action nextAction = plan.getNextAction(this);
            if (null == nextAction) {
                continue;
            }
            ArrayList<Action> possibleActions = getPossibleActions(map);
            int indexInPossibleActions;
            try {
                indexInPossibleActions = PlannerAgent.getActionIndex(possibleActions, nextAction);
            } catch (IllegalActionException ex) {
                Logger.getLogger(PlayerState.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }

            if (Game.NOOP_ACTION_INDEX == indexInPossibleActions) {
                continue;
            }
            ActionResponse response;
            if (_pos.isInBuilding()) {
                response = performBuildingAction(indexInPossibleActions, map, possibleActions);
            } else {
                Movement move = (Movement) possibleActions.get(indexInPossibleActions);
                if (move.timeEffect(this) > timeLeft()) {
                    newTurn();
                    plan.notifyOfNewTurn();
                    continue;
                }
                response = movePlayer(move.getNewPos(), map);
            }

            plan.setLastResponse(response);
            if (!hasTime()) {
                newTurn();
                plan.notifyOfNewTurn();
            }
        }
    }

    public void notifyOfNewTurn(Plan plan) {
        if (plan.size() > 0) {
            plan.rebuild();
        }
    }

    public ActionResponse performBuildingAction(int actionIndex, MapManager map, ArrayList<Action> possibleActions) {
        Building build = (Building) getPlayerTile(map);
        ActionResponse result = build.performAction(actionIndex, this, possibleActions);

        return result;
    }

    public GridTile getPlayerTile(MapManager map) {
        return map.getTile(getPos());
    }

    public ArrayList<Action> getPossibleActions(MapManager map) {

        PlayerPosition curPos = getPos();
        if (curPos.isInBuilding()) {
            Building curBuild = (Building) map.getTile(curPos);
            if (null == _actionsTree || null == _playerActionsParent) {
                _playerActionsParent = null;
                _actionsTree = curBuild.buildAllActionsTree(this);
                _playerActionsParent = _actionsTree.getRoot();
            }
            
            List<Action> playerBuildingSpecialActions = curBuild.getPlayerBuildingSpecialActions(this);
            List<Action> playerBuildingSpecificActions = _playerActionsParent.getDataOfChildren();
            ArrayList<Action> possibleActions = new ArrayList<>();
            possibleActions.addAll(playerBuildingSpecialActions);
            possibleActions.addAll(playerBuildingSpecificActions);
            
            //ArrayList<? extends Action> possibleActions = curBuild.getPlayerActions();
               
//            Action first = possibleActions.get(0);
//            if (first instanceof ExitBuildingMovement) {
//                ExitBuildingMovement exit = (ExitBuildingMovement) first;
//                if (exit.getOldPos().getX() != exit.getNewPos().getX()) {
//                    int fuck = 0;
//
//                }
//            }
//            
            return possibleActions;

        } 
        
        else {
            return getPossibleMovements(map);
        }

    }

    /**
     * Return all possible movements to adjacent locations of the current
     * position. Checks the adjacent locations: North, East, South, West. If
     * they are passable, adds them to the list. If the current tile is
     * enterable, also adds the Enter movement.
     *
     * @return List of movements
     */
    private ArrayList<Action> getPossibleMovements(MapManager map) {

        ArrayList<Action> result = new ArrayList<>();
        PlayerPosition curPos = getPos();
        assert (!curPos.isInBuilding());
        Position test = new Position(curPos);

        ArrayList<Position> neigbours = Route.getNeigbours(test, map);
        for (Position neighbour : neigbours) {
            result.add(new Movement(curPos, new PlayerPosition(neighbour, false)));
        }

        if (map.getTile(curPos).isEnterable()) {
            Building build = (Building) map.getTile(curPos);
            result.add(new EnterBuildingMovement(curPos, build));
        }

        return result;
    }

    ActionResponse movePlayer(PlayerPosition pos, MapManager _map) {

        Route route = Route.findRoute(getPos(), pos, _map);
        ArrayList<Movement> path = route.getMovementSequence();
        Iterator<Movement> iter = path.iterator();
        while (hasTime() && iter.hasNext()) {
            Movement move = iter.next();
            move.perform(this);//updates player state and calls 
        }

        if (!hasTime() && iter.hasNext()) {
            return new ActionResponse(false, "Not enough time to complete move");
        } else {
            return new ActionResponse(true, null);
        }
    }

    public double getSumScore() {
        return _goals.getSumScore(this, _health, _happiness, _career, _education);
    }

    
}
