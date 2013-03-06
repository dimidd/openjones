/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.jobs;

import jones.general.Game;
import jones.general.Skills;
import jones.map.Building;

/**
 * A general job. Does not affect Health and Happiness
 * @author dimid
 */
public class Job {
    
    protected String _name;
    protected Building _building;
    protected int _wagePerTimeUnit;
    protected int _rank;
    protected Skills _skills;
    public final double EXPERIENCE_UNITS_PER_1_TIME_UNIT_OF_WORK;
    public static final int DEFAULT_EXPERIENCE_RATE = 1;
    
    
    public Job (String name, Building build, int rank, int expuRate, int wagePerTimeUnit, Skills skillls) {
        _name = name;
        _building = build;
        _rank = rank;
        EXPERIENCE_UNITS_PER_1_TIME_UNIT_OF_WORK = expuRate;
        _wagePerTimeUnit = wagePerTimeUnit;
        _skills = skillls;
    }

     
    public Job (String name, Building build, int rank, int wagePerHour) {
        this(name, build, rank, DEFAULT_EXPERIENCE_RATE, wagePerHour / Game.TIMEUNITS_PER_HOUR, null);
    }
  
    
    /**
     * @return the _name
     */
    public String getName() {
        return _name;
    }

    /**
     * @param name the _name to set
     */
    public void setName(String name) {
        this._name = name;
    }

    /**
     * @return the _building
     */
    public Building getBuilding() {
        return _building;
    }

    /**
     * @param building the _building to set
     */
    public void setBuilding(Building building) {
        this._building = building;
    }

    /**
     * @return the _wagePerTimeUnit
     */
    public int getWagePerTimeUnit() {
        return _wagePerTimeUnit;
    }

    /**
     * @param wagePerTimeUnit the _wagePerTimeUnit to set
     */
    public void setWagePerTimeUnit(int wagePerTimeUnit) {
        this._wagePerTimeUnit = wagePerTimeUnit;
    }

    /**
     * @return the _rank
     */
    public int getRank() {
        return _rank;
    }

    /**
     * @param rank the _rank to set
     */
    public void setRank(int rank) {
        this._rank = rank;
    }

    public int healthEffect() {
        return 0;
    }

    public int happinessEffect() {
        return 0;
    }
    
    


   
}
