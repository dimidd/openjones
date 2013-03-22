/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import java.util.ArrayList;
import jones.actions.Action;
import jones.actions.ActionResponse;
import jones.general.Game;
import jones.general.Player;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
public abstract class Agent {
    private final Player _player;

    public Player getPlayer() {
        return _player;
    }

    public Game getGame() {
        return _game;
    }
    private final Game _game;
    
    public Agent (Player player, Game game) { 
        assert (null != player);
        _player = player;
        assert (null != game && game.hasStarted());
        _game = game;
    }
    
    /**
     * Notify the agent a new turn has started
     */
    public abstract void notifyOfNewTurn();
   
    /**
     * Notify the agent about the result of an action
     */
    public abstract void notifyOfResult(ActionResponse response);
   
    public abstract int selectAction (ArrayList<? extends Action> actions);
    
    public abstract boolean hasNextAction ();
 
}
