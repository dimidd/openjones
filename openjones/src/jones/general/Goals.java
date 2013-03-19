/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.general;

import jones.possessions.PossessionManager;

/**
 *
 * @author dimid
 */
public class Goals {
    
    public static final int DEFAULT_WEALTH_GOAL = 1000;
    public static final int DEFAULT_HEALTH_GOAL = 100;
    public static final int DEFAULT_HAPPINESS_GOAL = 100;
    public static final int DEFAULT_CAREER_GOAL = 20;
    
    public static final int N_GOALS = 4;
    
    private int _wealth;
    private int _health;
    private int _happiness;
    private int _career;

    public Goals(int wealth, int happiness, int health, int career) {
        _wealth = wealth;
        _health = health;
        _happiness = happiness;
        _career = career;
    }

    /**
     * Create a new Goals with default values
     */
    public Goals() {
        this(DEFAULT_WEALTH_GOAL, DEFAULT_HAPPINESS_GOAL, DEFAULT_HEALTH_GOAL, DEFAULT_CAREER_GOAL);
    }

    public boolean recompute(PlayerState ps, Health health, Happiness happiness, Career career) {
        boolean wealthGoalMet    = ps.getCash() + ps.getPossessions().totalValue() >= _wealth;
        boolean healthGoalMet    = health.getScore() >= _health;
        boolean happinessGoalMet = happiness.getScore() >= _happiness;
        boolean careerGoalMet    = career.getScore() >= _career;
        
        return wealthGoalMet && healthGoalMet && happinessGoalMet && careerGoalMet;          
    }
    
    public int score (PlayerState ps, Health health, Happiness happiness, Career career) {
        int wealthGoalMetPercent   = 100 *(ps.getCash() + ps.getPossessions().totalValue()) / _wealth;
        int healthGoalMetPercent   = 100 *(health.getScore()) /  _health;
        int happinessGoalMetPercent = 100 *(happiness.getScore() ) / _happiness;
        int careerGoalMetPercent   = 100 *(career.getScore()) / _career;
        
        return (wealthGoalMetPercent + healthGoalMetPercent + happinessGoalMetPercent + careerGoalMetPercent) / N_GOALS;          

    }
    
   
    public String scoresString (PlayerState ps, Health health, Happiness happiness, Career career) {
         
        int wealthGoalMetPercent   = 100 *(ps.getCash() + ps.getPossessions().totalValue()) / _wealth;
        int healthGoalMetPercent   = 100 *(health.getScore()) /  _health;
        int happinessGoalMetPercent = 100 *(happiness.getScore() ) / _happiness;
        int careerGoalMetPercent   = 100 *(career.getScore()) / _career;
        int total = (wealthGoalMetPercent + healthGoalMetPercent + happinessGoalMetPercent + careerGoalMetPercent) / N_GOALS;
        
        return "Total:"+total+"/100  Wealth:"+wealthGoalMetPercent+"/"+_wealth +" Health:"+healthGoalMetPercent+"/"+_health +" Happiness:" +
              happinessGoalMetPercent+"/"+_happiness +" Career:" + careerGoalMetPercent+"/"+_career; 
    }
}