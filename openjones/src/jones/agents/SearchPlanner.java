/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import external.AStarPlayer;
import java.util.ArrayList;
import jones.general.Game;
import jones.general.Player;
import jones.measures.Goals;

/**
 *
 * @author dimid
 */
public class SearchPlanner extends PlannerAgent {

    public SearchPlanner(Player p1, Game g) {
        super(p1, g);
    }
    
       
    @Override
    public boolean hasNextAction() {
      while (_schedule.size() > 0) {
          if (getCurPlan().size() > 0) {
              return true;
          }
          else {
              _schedule.remove();
          }
      }
        
      ArrayList<Plan> foundPlans = AStarPlayer.findPlan(_player.getState(), Goals.MAX_TOTAL_SCORE, this, _game.getMap());
//      Plan first = foundPlans.get(0);
//      for (int i =1; i < foundPlans.size(); ++i)
//          first.concatenate(foundPlans.get(i));
//      _schedule.add(first);
      
      _schedule.addAll(foundPlans);
      return true;
    }

    
}
