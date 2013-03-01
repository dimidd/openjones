/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.general;

/**
 *
 * @author dimid
 */
public abstract class Action {

    
    public abstract boolean checkConditions();

    public abstract void doAction(Player player);

    public final void perform(Player player) {
        if (!checkConditions())
            return;
        doAction(player);
        player.getState().affectHealth(healthEffect());
        player.getState().affectHappiness(happinessEffect());
        player.getState().affectCareer(careerEffect());
        player.getState().affectCash(cashEffect());
        player.getState().affectTime(timeEffect());
            
    }

    public abstract int healthEffect(); 

    public abstract int happinessEffect();

    public abstract int WealthEffect();

    public abstract int careerEffect();
     
    public abstract int cashEffect();
    
    public abstract int timeEffect();
   
    
}
