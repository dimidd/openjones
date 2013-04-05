/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.actions;

import java.util.Objects;
import jones.general.Player;
import jones.general.PlayerState;
import jones.jobs.Job;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
public class ApplyForJobAction extends Action {

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this._job);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ApplyForJobAction other = (ApplyForJobAction) obj;
        if (!this._job.equals(other._job)) {
            return false;
        }
        return true;
    }
    /**
     * Number of TU it takes to apply
     */
    public static final int APPLY_DURATION = 5;
    
    private final Job _job;
    
    public ApplyForJobAction (Job job) {
        _job = job;
    }

    public static int getAPPLY_DURATION() {
        return APPLY_DURATION;
    }

    public Job getJob() {
        return _job;
    }

    @Override
    protected ActionResponse checkConditions(PlayerState playerState) {
        ActionResponse checkTime = checkTime(playerState);
        if (!checkTime._isPositive) {
            return checkTime;
        }
        else {
            return _job.checkQualifications(playerState);
        }             
    }

    @Override
    protected void doAction(PlayerState playerState) {
        playerState.setJob(_job);
        playerState.affectTime(APPLY_DURATION);
    }

    @Override
    public int healthEffect(PlayerState playerState) {
       return 0;
    }

    @Override
    public int happinessEffect(PlayerState playerState) {
       return 0;
    }

    @Override
    public int careerEffect(PlayerState playerState) {
         return 0;
         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int cashEffect(PlayerState playerState) {
         return 0;
    }

    @Override
    public int timeEffect(PlayerState playerState) {
        return APPLY_DURATION;
    }

    @Override
    public String toString() {
        return _job.getName() + " "+_job.getWagePerHour()+"$ per hour";
    }

    @Override
    protected ActionResponse getPositiveResponse(PlayerState playerState) {
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
