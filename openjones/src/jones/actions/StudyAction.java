/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.actions;

import jones.general.Player;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
public class StudyAction extends Action {
    
    public static final int STUDY_DURATION = 20;
    public static final int STUDY_COST = 15;
    public static final int EDUCATION_POINTS_GAIN = 1;
    
    @Override
    protected ActionResponse checkConditions(Player player) {
        ActionResponse checkTime = checkTime(player);
        if (checkTime._isPositive) { 
            return PurchaseAction.checkCash(player, cashEffect(player));
        }
        else {
            return checkTime;
        }
    }

    @Override
    protected void doAction(Player player) {
        player.affectCash( -STUDY_COST);
        player.affectTime(STUDY_DURATION);
        player.affectEducation(EDUCATION_POINTS_GAIN);
       
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
    }

    @Override
    public int cashEffect(Player player) {
        return STUDY_COST;
    }

    @Override
    public int timeEffect(Player player) {
        return STUDY_DURATION;
    }

    @Override
    public String toString() {
        return "Study "+STUDY_COST;
    }

    @Override
    protected ActionResponse getPositiveResponse(Player player) {
        return new ActionResponse(true, "Another brick in the wall");
    }

    @Override
    public boolean isSubmenu() {
       return false;
    }

    @Override
    public void clearCachedValues() {
        //nothing
    }
    
}
