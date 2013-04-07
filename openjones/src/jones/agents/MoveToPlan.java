/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import java.util.ArrayList;
import jones.actions.Movement;
import jones.general.Game;
import jones.general.Player;
import jones.general.PlayerPosition;
import jones.general.Route;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
public class MoveToPlan extends AllOrNothingPlan {
    private final PlayerPosition _dest;
    private final PlayerPosition _src;

    public MoveToPlan(Agent agent, PlayerPosition src, PlayerPosition dest) {
        super(agent);
        _dest = dest;
        _src = src;
        build();
    }

    @Override
    public void build() {
          
        Player player = _agent.getPlayer();

        Game game = _agent.getGame();
        //Plan result = new Plan(agent);

        //PlayerPosition curPos = player.getPos();
        Route route = Route.findRoute(_src, _dest, game.getMap());
        ArrayList<Movement> path = route.getMovementSequence();

        //make sure we aren't repeating movements
        _actions.add(new SetRepetetiveMarker(this, null, false));

        //add all movements with noop markers
        for (int i = 0; i < path.size(); ++i) {
            _actions.add(new NoOpMarker(this, path.get(i)));
        }

    }

//    public static Plan moveTo(Agent agent, PlayerPosition dest) {
//
//        Player player = agent.getPlayer();
//
//        Game game = agent.getGame();
//        Plan result = new Plan(agent);
//
//        PlayerPosition curPos = player.getPos();
//        Route route = Route.findRoute(curPos, dest, game.getMap());
//        ArrayList<Movement> path = route.getMovementSequence();
//
//        //make sure we aren't repeating movements
//        result._actions.add(new SetRepetetiveMarker(result, null, false));
//
//        //add all movements with noop markers
//        for (int i = 0; i < path.size(); ++i) {
//            result._actions.add(new NoOpMarker(result, path.get(i)));
//        }
//
//        return result;
//
//    }

  
    
    
}
