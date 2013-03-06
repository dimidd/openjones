/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.actions;

import jones.general.Career;
import jones.jobs.Job;
import jones.general.Player;

/**
 * A general work action.
 * @author dimid
 */
public class WorkAction extends Action {

    protected Job _job;
    
    /**
     * The period of the work action, if player has enough time
     */
    public static final int WORK_PERIOD_IN_TIME_UNITS = 60;    
    
    public WorkAction(Job job) {
        _job = job;
    }

    @Override
    protected void doAction(Player player) {
        player.affectCash(cashEffect(player));
        int timeEffect = timeEffect(player);
        player.affectTime(timeEffect);
        
        int addditionalEXPUs = (int) Math.round(timeEffect(player) * _job.EXPERIENCE_UNITS_PER_1_TIME_UNIT_OF_WORK);
        player.getCareer().gain(_job.getRank(), addditionalEXPUs, player);
        
    }

    @Override
    public int healthEffect(Player player) {
        return _job.healthEffect();
    }

    @Override
    public int happinessEffect(Player player) {
        return _job.happinessEffect();       
    }

    @Override
    public int careerEffect(Player player) {
        Career preChange = player.getCareer();
        Career postChange = new Career(preChange);
        int addditionalEXPUs = (int) Math.round(timeEffect(player) * _job.EXPERIENCE_UNITS_PER_1_TIME_UNIT_OF_WORK);
        postChange.gain(_job.getRank(), addditionalEXPUs, player);
        return postChange.getScore() - preChange.getScore();
    }

    @Override
    public int cashEffect(Player player) {
        return timeEffect(player) + _job.getWagePerTimeUnit();
    }

    @Override
    public int timeEffect(Player player) {
        return getAvailiableTimeUpto(WORK_PERIOD_IN_TIME_UNITS, player);
    }

    @Override
    public String toString() {
        return "Work";
    }

    @Override
    protected ActionResponse checkConditions(Player player) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ActionResponse getPositiveResponse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isSubmenu() {
        return false;
    }
    
}
