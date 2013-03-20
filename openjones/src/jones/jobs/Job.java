/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.jobs;

import jones.actions.ActionResponse;
import jones.general.Career;
import jones.general.Game;
import jones.general.Player;
import jones.general.Skills;
import jones.map.Building;
import jones.map.ClothesStore;

/**
 * A general job. Does not affect Health and Happiness
 * @author dimid
 */
public class Job {
    
    protected String _name;
    protected Building _building;
    protected double _wagePerTimeUnit;
    protected int _rank;
    protected Skills _skills;
    
    /**
     * Minimum clothes level required for work
     */
    public final int MIN_CLOTHES_LEVEL;
  
    public final int MIN_EXPERIENCE_LEVEL;
    
 
    public final double EXPERIENCE_UNITS_PER_1_TIME_UNIT_OF_WORK;
    public static final int DEFAULT_EXPERIENCE_RATE = 1;
    
    /**
     * When checking experience, how many lower ranks do we accept.
     * e.g. For a job with rank 5, we check the experience level of ranks 5 and 4
     */
    public static final int LOWER_EXPERIENCE_RANKS_ACCEPTABLE = 1;
    
    public Job (String name, Building build, int rank, int expuRate, double wagePerTimeUnit, Skills skillls, int clothesLevel, int expLevel) {
        assert(clothesLevel <= ClothesStore.MAX_CLOTHES_LEVEL);
        _name = name;
        _building = build;
        _rank = rank;
        EXPERIENCE_UNITS_PER_1_TIME_UNIT_OF_WORK = expuRate;
        _wagePerTimeUnit = wagePerTimeUnit;
        _skills = skillls;
        MIN_CLOTHES_LEVEL = clothesLevel;
        MIN_EXPERIENCE_LEVEL = expLevel;
    }

     
    public Job (String name, Building build, int rank, int wagePerHour, int clothesLevel) {
        this(name, build, rank, DEFAULT_EXPERIENCE_RATE, (double)wagePerHour / Game.TIMEUNITS_PER_HOUR, null, clothesLevel, 10*rank);
    }
    
     
    public Job (String name, Building build, int rank, int wagePerHour, int clothesLevel, int exp) {
        this(name, build, rank, DEFAULT_EXPERIENCE_RATE, (double)wagePerHour / Game.TIMEUNITS_PER_HOUR, null, clothesLevel, exp);
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
    public double getWagePerTimeUnit() {
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

    public ActionResponse checkQualifications(Player player) {
        StringBuilder response = new StringBuilder();
        
        if (!hasEnoughExperience(player)) 
            response.append("Not enough experience\n");
        if (!hasEnoughEducation(player)) 
            response.append("Not enough education\n");
       
        return new ActionResponse(response.length() == 0, response.toString());
    }

    private boolean hasEnoughExperience(Player player) {
        Career career = player.getCareer();
        
        int lowestExpLevel = Math.max(_rank - LOWER_EXPERIENCE_RANKS_ACCEPTABLE, 1);
        for (int i = lowestExpLevel; i <=_rank; ++i) {
            if (career.getExperienceLevel(i) >= MIN_EXPERIENCE_LEVEL)
                return true;            
        }
        
        return false;
    }

    private boolean hasEnoughEducation(Player player) {
        return true;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    


   
}
