/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.map;

import java.util.ArrayList;
import java.util.HashMap;
import jones.actions.Action;
import jones.general.Player;
import jones.general.Position;
import jones.general.Possession;

/**
 * Allows the user to pawn items for quick cash.
 * In the first two weeks, the player may redeem them, and after that all players may buy them
 * @author dimid
 */
class PawnShop extends Building {

    /*
     *  Note: If multiplayer games are played simultaneously
     *  (each player doesn't wait for the others to finish their turn, and has his own week count),
     *  the pawnshop must synchronyzed (e/g/ protected by a lock) to prevent race conditions
     */
    
    private HashMap<Player, ArrayList<Possession>> _redeemables;
    private ArrayList<Possession> _buyables;
    
    public PawnShop(Position pos, String name) {
        super(pos,name);
        _redeemables = new HashMap<>();
        _buyables = new ArrayList<>();
        _actions.add(new PawnAction);
    }

    @Override
    public ArrayList<? extends Action> getBuildingActions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
