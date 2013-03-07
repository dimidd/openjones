/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.general;

import possessions.CasualClothes;
import jones.possessions.Possession;
import jones.possessions.PossessionManager;
import jones.possessions.RentPossesion;
import jones.possessions.WeekOfRent;
import jones.jobs.Unemployed;
import jones.jobs.Job;
import jones.possessions.RentContract;
import jones.map.House;
import jones.map.MapManager;
import jones.map.RentAgency;

/**
 *
 * @author dimid
 */
public class PlayerState {
    
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
    //private House _house;
    //private Position 
    private PlayerPosition _pos;
    private Health _health;
    private Happiness _happiness;
    private Career _career;
    //private Clothes _clothes;
    private int _cash;
    
    public final static int MAX_JOB_RANK = 9;
    public final static int INITIAL_CASH = 200;
    public final static int CASUAL_CLOTHES_BASE_VALUE = 70;
    
    
    /**
     * Creates a new PlayerState with default values
     * @param map
     */
    public PlayerState (MapManager map) {
        LOWEST_HOUSING = map.getLowestHousing();
        _clock = 0;
        _weeks = 1;
        _goals = new Goals();
        _possessions = new PossessionManager();
        WeekOfRent lowestHousingRentWeek = new WeekOfRent(map.getLowestHousing().pricePerWeek(), LOWEST_HOUSING);
        RentPossesion baseRent = new RentPossesion(RentAgency.WEEKS_OF_RENT, lowestHousingRentWeek);
        _possessions.setRentContract(new RentContract(baseRent));
        _possessions.add(baseRent);
        _possessions.add(new Possession(1, new CasualClothes(CASUAL_CLOTHES_BASE_VALUE)));
        _skills = new Skills();
        _job = new Unemployed();
        
        //_house = LOWEST_HOUSING;
        _pos = new PlayerPosition(getHouse().getPosition(), false);
        _health = new Health();
        _happiness = new Happiness();
        _career = new Career(MAX_JOB_RANK, _weeks);
        _cash = INITIAL_CASH;
    }
    
    public void recomputeGoals() {
        _goals.recompute(_possessions, _skills, _health, _happiness);
    }
    
    public void addPossession (Possession ps) {
         _possessions.add(ps);
    }

    public void removePossession (Possession ps) {
         _possessions.remove(ps);
    }
    
      
    public void addSkill (Skill sk) {
         _skills.add(sk);
    }

    public void removeSkill (Skill sk) {
         _skills.remove(sk);
    }
   
    public void affectHealth (int effect ) {
         _health.add(effect);
    }

    public void affectHappiness (int effect ) {
         _happiness.add(effect);
    }

 
    public void affectCareer (int effect ) {
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

    public House getHouse() {
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

    void advanceWeeks() {
        ++_weeks;
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

    

     
    
    
}
