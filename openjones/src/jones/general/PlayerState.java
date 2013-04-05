/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import jones.actions.Action;
import jones.actions.ActionResponse;
import jones.actions.EnterBuildingMovement;
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

    //private int _rentDebt;
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

    public Goals getGoals() {
        return _goals;
    }

    public PossessionManager getPossessions() {
        return _possessions;
    }

    public Skills getSkills() {
        return _skills;
    }

    public Job getJob() {
        return _job;
    }

    public final House getHouse() {
        return _possessions.getRentContract().getHouse();
    }

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

    public int getCash() {
        return _cash;
    }

    public void advanceWeeks() {
        _weeks += 1;
    }

    public int getWeeks() {
        return _weeks;
    }

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

    public int getScore() {
        return _goals.getTotalScore(this, _health, _happiness, _career, _education);
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
    public int getEducationScore() {
        return getGoals().educationScore(getEducation());
    }

    @Override
    public int getHealthScore() {
        return getGoals().healthScore(getHealth());
    }

    @Override
    public int getCareerScore() {
        return getGoals().careerScore(getCareer());
    }

    @Override
    public int getHappinessScore() {
        return getGoals().happinessScore(getHappiness());
    }

    @Override
    public int getWealthscore() {
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
        Action nextAction = plan.getNextAction();
        while (nextAction != null) {
            ArrayList<? extends Action> possibleActions = getPossibleActions(map);
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
                response = performBuildingAction(indexInPossibleActions, map);
            } else {
                Movement move = (Movement) possibleActions.get(indexInPossibleActions);
                if (move.timeEffect(this) > timeLeft()) {
                    newTurn();
                    continue;
                }
                response = movePlayer(move.getNewPos(), map);
            }

            if (!hasTime()) {
                newTurn();              
            }
        }
    }

    public ActionResponse performBuildingAction(int actionIndex, MapManager map) {
        Building build = (Building) getPlayerTile(map);
        ActionResponse result = build.performAction(actionIndex, this);

        return result;
    }

    public GridTile getPlayerTile(MapManager map) {
        return map.getTile(getPos());
    }

    public ArrayList<? extends Action> getPossibleActions(MapManager map) {

        PlayerPosition curPos = getPos();
        if (curPos.isInBuilding()) {
            Building curBuild = (Building) map.getTile(curPos);
            return curBuild.getPlayerActions();
        } else {
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
    private ArrayList<? extends Action> getPossibleMovements(MapManager map) {

        ArrayList<Movement> result = new ArrayList<>();
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
}
