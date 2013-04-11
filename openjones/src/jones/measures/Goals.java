/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.measures;

import jones.general.PlayerState;
import jones.measures.Health;
import jones.measures.Happiness;
import jones.measures.Career;
import jones.measures.Education;
import jones.possessions.PossessionManager;

/**
 *
 * @author dimid
 */
public class Goals {
    
    public static final int DEFAULT_WEALTH_GOAL = 10000;
    public static final int DEFAULT_HEALTH_GOAL = 100;
    public static final int DEFAULT_HAPPINESS_GOAL = 100;
    public static final int DEFAULT_CAREER_GOAL = 850;
    public static final int DEFAULT_EDUCATION_GOAL = 100;
  
    public static final int N_GOALS = 5;
    public static final int  MAX_MEASURE_SCORE = 100;
    public static final int  MAX_TOTAL_SCORE = 100;
   
    
    private int _wealth;
    private int _health;
    private int _happiness;
    private int _career;
    private int _education;

    public Goals(int wealth, int happiness, int health, int career, int education) {
        _wealth = wealth;
        _health = health;
        _happiness = happiness;
        _career = career;
        _education = education;
    }

    /**
     * Create a new Goals with default values
     */
    public Goals() {
        this(DEFAULT_WEALTH_GOAL, DEFAULT_HAPPINESS_GOAL, DEFAULT_HEALTH_GOAL, DEFAULT_CAREER_GOAL, DEFAULT_EDUCATION_GOAL);
    }

    public boolean recompute(PlayerState ps, Health health, Happiness happiness, Career career, Education education) {
        int wealthGoalMetPercent    = wealthScore(ps);
        int healthGoalMetPercent    = healthScore(health);
        int happinessGoalMetPercent = happinessScore(happiness);
        int careerGoalMetPercent    = careerScore(career, ps);
        int educationGoalMetPercent = educationScore(education);
        
        boolean wealthGoalMet    = wealthGoalMetPercent >= _wealth;
        boolean healthGoalMet    = healthGoalMetPercent >= _health;
        boolean happinessGoalMet = happinessGoalMetPercent >= _happiness;
        boolean careerGoalMet    = careerGoalMetPercent >= _career;
        boolean educationGoalMet = educationGoalMetPercent >= _education;
        
        return wealthGoalMet && healthGoalMet && happinessGoalMet && careerGoalMet && educationGoalMet;          
    }
    
    public int getAverageScore (PlayerState ps, Health health, Happiness happiness, Career career, Education education) {
         
        int wealthGoalMetPercent    = wealthScore(ps);
        int healthGoalMetPercent    = healthScore(health);
        int happinessGoalMetPercent = happinessScore(happiness);
        int careerGoalMetPercent    = careerScore(career, ps);
        int educationGoalMetPercent = educationScore(education);
        
        int total = (wealthGoalMetPercent + healthGoalMetPercent + happinessGoalMetPercent + careerGoalMetPercent + educationGoalMetPercent) / N_GOALS;

        return total;     

    }
    
    public int getSumScore (PlayerState ps, Health health, Happiness happiness, Career career, Education education) {
         
        int wealthGoalMetPercent    = wealthScore(ps);
        int healthGoalMetPercent    = healthScore(health);
        int happinessGoalMetPercent = happinessScore(happiness);
        int careerGoalMetPercent    = careerScore(career, ps);
        int educationGoalMetPercent = educationScore(education);
        
        int sum = (wealthGoalMetPercent + healthGoalMetPercent + happinessGoalMetPercent + careerGoalMetPercent + educationGoalMetPercent);
        
        return sum;
    }
    
   
    public String getScoresString (PlayerState ps, Health health, Happiness happiness, Career career, Education education) {
         
        int wealthGoalMetPercent    = wealthScore(ps);
        int healthGoalMetPercent    = healthScore(health);
        int happinessGoalMetPercent = happinessScore(happiness);
        int careerGoalMetPercent    = careerScore(career, ps);
        int educationGoalMetPercent = educationScore(education);


        int total = (wealthGoalMetPercent + healthGoalMetPercent + happinessGoalMetPercent + careerGoalMetPercent + educationGoalMetPercent) / N_GOALS;
        
        return "Total:"+total+"/100  Wealth:"+(ps.getCash() + ps.getPossessions().totalValue())+"/"+_wealth +" Health:"+health.getScore()+"/"+_health +" Happiness:" +
              happiness.getScore()+"/"+_happiness +" Career:" + career.getScore()+"/"+_career+" Education:" +education.getScore()+"/"+_education; 
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
   
    public int careerScore (Career career, PlayerState playerState) {
        double score = 100 * ((double)career.getScore() + playerState.getJob().getWagePerHour());
        return (int) Math.min(MAX_MEASURE_SCORE, score /  _career);
    }

    public int educationScore(Education education) {
        return (int) Math.min(MAX_MEASURE_SCORE, 100 * ((double)education.getScore() /  _education));
    }
 
 
    
}