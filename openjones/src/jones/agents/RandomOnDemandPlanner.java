/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import java.util.List;
import java.util.Random;
import jones.general.Game;
import jones.general.Player;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
public class RandomOnDemandPlanner extends RandomPlanner{

    public RandomOnDemandPlanner(Player p1, Game g) {
        super(p1, g);
    }
    
      
    @Override
    protected Plan getRandomPlan() {
        List<Plan> neededPlans = getNeededPlans();
        Random random = new Random();
        int choice = random.nextInt(neededPlans.size());
        return neededPlans.get(choice);

          
      }
}
