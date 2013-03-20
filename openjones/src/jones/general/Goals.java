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
    
    public static final int DEFAULT_WEALTH_GOAL = 10000;
    public static final int DEFAULT_HEALTH_GOAL = 100;
    public static final int DEFAULT_HAPPINESS_GOAL = 100;
    public static final int DEFAULT_CAREER_GOAL = 20;
    
    public static final int N_GOALS = 4;
    public static final int  MAX_MEASURE_SCORE = 100;
    public static final int  MAX_TOTAL_SCORE = 100;
   
    
    private int _wealth;
    private int _health;
    private int _happiness;
    private int _career;
    private Object ps;

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
    
    public int getTotalScore (PlayerState ps, Health health, Happiness happiness, Career career) {
         
        int wealthGoalMetPercent    = wealthScore(ps);
        int healthGoalMetPercent    = healthScore(health);
        int happinessGoalMetPercent = happinessScore(happiness);
        int careerGoalMetPercent    = careerScore(career);

        int total = (wealthGoalMetPercent + healthGoalMetPercent + happinessGoalMetPercent + careerGoalMetPercent) / N_GOALS;

        return total;     

    }
    
   
    public String getScoresString (PlayerState ps, Health health, Happiness happiness, Career career) {
         
        int wealthGoalMetPercent    = wealthScore(ps);
        int healthGoalMetPercent    = healthScore(health);
        int happinessGoalMetPercent = happinessScore(happiness);
        int careerGoalMetPercent    = careerScore(career);

        int total = (wealthGoalMetPercent + healthGoalMetPercent + happinessGoalMetPercent + careerGoalMetPercent) / N_GOALS;
        
        return "Total:"+total+"/100  Wealth:"+(ps.getCash() + ps.getPossessions().totalValue())+"/"+_wealth +" Health:"+health.getScore()+"/"+_health +" Happiness:" +
              happiness.getScore()+"/"+_happiness +" Career:" + career.getScore()+"/"+_career; 
    }
    
    public int wealthScore (PlayerState ps) {
        return (int) Math.min(MAX_MEASURE_SCORE, 100 * (  ((double)ps.getCash() + ps.getPossessions().totalValue()) / _wealth));
    }
    
     
    public int healthScore (Health health) {
        return (int) Math.min(MAX_MEASURE_SCORE, 100 * ((double)health.getScore() /  _health));
    }
 
      
    public int happinessScore (Happiness happiness) {
        return (int) Math.min(MAX_MEASURE_SCORE, 100 * ((double)happiness.getScore() /  _happiness));
    }
   
    public int careerScore (Career career) {
        return (int) Math.min(MAX_MEASURE_SCORE, 100 * ((double)career.getScore() /  _career));
    }
 
 
    
}