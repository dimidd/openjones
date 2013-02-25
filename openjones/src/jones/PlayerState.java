/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones;

/**
 *
 * @author dimid
 */
public class PlayerState {
    
    /**
     * Lowest possible housing type
     */
    private static final House LOWEST_HOUSING = new Tent(); 

    public static House getLOWEST_HOUSING() {
        return LOWEST_HOUSING;
    }
    
    
    private int _hour;
    private Goals _goals;
    private Possessions _possessions;
    private Skills _skills;
    private Job _job;
    private House _house;
    private PlayerPosition _pos;
    private Health _health;
    private Happiness _happiness;
    
    
    
    PlayerState () {
        _hour = 0;
        _goals = new Goals();
        _possessions = new Possessions();
        _skills = new Skills();
        _job = Unemployed.getJob();
        _house = LOWEST_HOUSING;
        _pos = new PlayerPosition(_house.getPosition(), false);
        _health = new Health();
        _happiness = new Happiness();
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

    public int getHour() {
        return _hour;
    }

    public Goals getGoals() {
        return _goals;
    }

    public Possessions getPossessions() {
        return _possessions;
    }

    public Skills getSkills() {
        return _skills;
    }

    public Job getJob() {
        return _job;
    }

    public House getHouse() {
        return _house;
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
        this._hour = hour;
    }

    public void setGoals(Goals goals) {
        this._goals = goals;
    }

    public void setPossessions(Possessions possessions) {
        this._possessions = possessions;
    }

    public void setSkills(Skills skills) {
        this._skills = skills;
    }

    public void setJob(Job job) {
        this._job = job;
    }

    public void setHouse(House house) {
        this._house = house;
    }

    public void setPos(PlayerPosition pos) {
        this._pos = pos;
    }

    public void setHealth(Health health) {
        this._health = health;
    }

    public void setHappiness(Happiness happiness) {
        this._happiness = happiness;
    }

    

     
    
    
}
