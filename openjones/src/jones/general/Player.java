/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.general;

import java.util.ArrayList;
import jones.actions.Action;
import jones.measures.Goals;
import jones.measures.ExperienceManager;
import jones.measures.Career;
import jones.measures.Education;
import jones.jobs.Job;
import jones.map.House;
import jones.map.MapManager;
import jones.measures.Happiness;
import jones.measures.Health;
import jones.possessions.Possession;
import jones.possessions.PossessionManager;
import jones.possessions.RentContract;

/**
 *
 * @author dimid
 */
public class Player extends AbstractPlayer {
    
    private int _id;
    private String _name;
    private PlayerState _state;
    private PlayerGraphics _graphics;

    public Player (String name, PlayerGraphics graphics, MapManager map) {
    	 
        _name = name;
        _state = new PlayerState(map);
        _graphics = graphics;
        
    }
    
   
    public Health getHealth() {
        return _state.getHealth();
    }
    
    public Education getEducation() {
        return _state.getEducation();
    }

    public void setEducation(Education education) {
        _state.setEducation(education);
    }
  
    public void setClock(int clock) {
        _state.setClock(clock);
    }

    boolean hasWon() {
        return _state.hasWon();
    }

    public void setGoals(Goals goals) {
        _state.setGoals(goals);

    }
    
    void gotoStartPosition() {
        //_state.advanceWeeks();
        House home = _state.getHouse();
        _state.setPos(home.getPosition(), false);
    }

    public int getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public PlayerState getState() {
        return _state;
    }

    public PlayerGraphics getGraphics() {
        return _graphics;
    }
    
    public Career getCareer() {
        return _state.getCareer();
    }

    public void affectCash(int cashEffect) {
        _state.affectCash(cashEffect);
    }

    public int getWeeks() {
        return _state.getWeeks();
    }

    public void affectTime(int timeEffect) {
        _state.affectTime(timeEffect);
    }

    public int getHour() {
        return _state.getHour();
    }

    public boolean hasTime() {
        return _state.hasTime();
    }

    public int timeLeft() {
         return _state.timeLeft();
    }

    public RentContract getRentContract() {
        return _state.getRentContract();
    }
 
    
    public void setRentContract(RentContract r) {
        _state.setRentContract(r);
    }

    public boolean isRentDue() {		
            return  _state.isRentDue();
    }

    public boolean hasFoodSpoiled() {
            // TODO Auto-generated method stub

            return false;
    }

    public boolean hasAllFoodSpoiled() {
            // TODO Auto-generated method stub
            return false;
    }

    public boolean areClothesAboutToWare() {
            
        return _state.areClothesAboutToWare();
    }

    public int getSumOfRescueFromRelative() {
            // TODO Auto-generated method stub
            return 0;
    }

    public void removeNonContractRentPossesions() {
            // TODO Auto-generated method stub

    }

    public void setRentPossession(Possession possession) {
            _state.getPossessions().setRentPossession(possession);

    }

    int getlastRentAnnouncement() {
        return _state.getLastRentAnnouncement();
    }

    /**
     * Consume all possessions, Handle rent, food and clothes 
     */
    public void consume() {
        _state.consume();
    }

    public int getRentDebt() {
        return _state.getRentDebt();
    }

    public void setRentDebt(int i) {
        _state.setRentDebt(i);    
    }

    public int getClothesLevel() {
        return _state.getClothesLevel();
    }

    public PlayerPosition getPos() {
        return _state.getPos();
    }

    
    @Override
    public String toString() {
        return "name:"+_name +" clock:"+getHour()+" weeks:"+getWeeks()+" cash:"+getCash()+" rentDebt:"+getRentDebt();
    }

    public int getCash() {
        return _state.getCash();
    }

    public int getTotalScore() {
        return _state.getTotalScore();
    }

     public String scoresString() {
         return _state.scoresString();
     }

    void advanceWeeks() {
        _state.advanceWeeks();
    }

    public PossessionManager getPossessions() {
        return _state.getPossessions();
    }

    public void setJob(Job job) {
        _state.setJob(job);
    }

    public void affectHealth(int healthEffect) {
        _state.affectHealth( healthEffect);
    }

    public void affectHappiness(int happinessEffect) {
        _state.affectHappiness(happinessEffect);
    }

   public Job getJob() {
       return _state.getJob();
    }
    
    public ExperienceManager getExperiences() {
        return _state.getExpriences();
    }

    public void affectEducation(int EDUCATION_POINTS_GAIN) {
        _state.affectEducation(EDUCATION_POINTS_GAIN);
    }

    public double getEducationScore() {
        return _state.getGoals().educationScore(getEducation());        
    }
  
    public double getHealthScore() {
        return _state.getGoals().healthScore(getHealth());        
    }
    
    public double getCareerScore() {
        return _state.getGoals().careerScore(getCareer(), _state);        
    }
    
     public double getHappinessScore() {
        return _state.getGoals().happinessScore(getHappiness());        
    }

    public Happiness getHappiness() {
        return _state.getHappiness();
    }
    
    public double getWealthscore() {
        return _state.getGoals().wealthScore(_state);
    }

    
    void startWeek() {
        _state.startWeek();
    }
 
    

    public ArrayList<Action> getPossibletActions(MapManager map) {
        return _state.getPossibleActions(map);
    }

   
    
    
            
}
