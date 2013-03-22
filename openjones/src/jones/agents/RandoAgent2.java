/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import java.util.Random;
import jones.actions.ActionResponse;
import jones.general.Game;
import jones.general.Player;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
public class RandoAgent2 extends Agent {

    public  RandoAgent2 (Player player, Game game) { 
        super(player, game);
    }
    
     
    @Override
    public int selectAction(java.util.ArrayList<? extends jones.actions.Action> actions) {
        Random random = new Random();
        return random.nextInt(actions.size());
    }

    @Override
    public void notifyOfNewTurn() {
        //nothing
    }

    @Override
    public void notifyOfResult(ActionResponse response) {
       //noop
    }

    @Override
    public boolean hasNextAction() {
        return true;
    }
}
