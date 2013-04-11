/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.general;

import jones.jobs.Job;
import jones.map.House;
import jones.measures.Career;
import jones.measures.Education;
import jones.measures.ExperienceManager;
import jones.measures.Goals;
import jones.measures.Happiness;
import jones.measures.Health;
import jones.measures.Skills;
import jones.possessions.PossessionManager;
import jones.possessions.RentContract;

/**
 *
 * @author dimid
 */
public abstract class AbstractPlayerState {

    //public abstract boolean areClothesAboutToWare();

    public abstract Career getCareer();

    public abstract int getCash();

    //private int _rentDebt;
    public abstract int getClock();

    //public abstract int getClothesLevel();

//    public abstract Education getEducation();

//    public abstract ExperienceManager getExpriences();

    public abstract Goals getGoals();

//    public abstract Happiness getHappiness();
//
//    public abstract Health getHealth();

    public abstract int getHour();

    public abstract House getHouse();

    public abstract Job getJob();

    //public abstract int getLastRentAnnouncement();

    //public abstract int getNumOfWeeksOfRent();

    public abstract PlayerPosition getPos();

    //public abstract PossessionManager getPossessions();

    //public abstract RentContract getRentContract();

    //public abstract int getRentDebt();

    public abstract int getTotalScore();

    //public abstract Skills getSkills();

    public abstract int getWeeks();

    public abstract boolean hasTime();

    public abstract boolean hasWon();

    public abstract double getCareerScore();

    public abstract double getEducationScore();

    public abstract double getHappinessScore();

    public abstract double getHealthScore();

    public abstract double getWealthscore();

    public int getTotalTime() {
        return getWeeks() * Game.TIMEUNITS_PER_WEEK + getHour();
    }

    public abstract int  getPossessionsWorth();
    
    
    
}
