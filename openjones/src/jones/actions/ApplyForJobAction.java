/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.actions;

import jones.general.Player;
import jones.jobs.Job;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
public class ApplyForJobAction extends Action {
    /**
     * Number of TU it takes to apply
     */
    public static final int APPLY_DURATION = 5;
    
    private final Job _job;
    
    public ApplyForJobAction (Job job) {
        _job = job;
    }

    @Override
    protected ActionResponse checkConditions(Player player) {
        ActionResponse checkTime = checkTime(player);
        if (!checkTime._isPositive) {
            return checkTime;
        }
        else {
            return _job.checkQualifications(player);
        }             
    }

    @Override
    protected void doAction(Player player) {
        player.setJob(_job);
        player.affectTime(APPLY_DURATION);
    }

    @Override
    public int healthEffect(Player player) {
       return 0;
    }

    @Override
    public int happinessEffect(Player player) {
       return 0;
    }

    @Override
    public int careerEffect(Player player) {
         return 0;
         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int cashEffect(Player player) {
         return 0;
    }

    @Override
    public int timeEffect(Player player) {
        return APPLY_DURATION;
    }

    @Override
    public String toString() {
        return _job.getName() + " "+_job.getWagePerHour()+"$ per hour";
    }

    @Override
    protected ActionResponse getPositiveResponse(Player player) {
        return new ActionResponse(true, "You got the job");
    }

    @Override
    public boolean isSubmenu() {
        return false;
    }
    
   
    @Override
    public void clearCachedValues() {
      
    }

    
}
