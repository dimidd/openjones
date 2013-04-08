/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package external;

import java.util.Objects;
import jones.agents.Plan;
import jones.general.PlayerState;

/**
 *
 * @author dimid
 */
public class PlayerStateNode {
    private final PlayerState _state;
    private final PlayerStateNode _parent;
    private final Plan _edge;
    
    public  PlayerStateNode (PlayerState cur, PlayerStateNode parent, Plan edge) {
        _state = cur;
        _parent = parent;
        _edge= edge;
    }

    public PlayerState getState() {
        return _state;
    }

    public PlayerStateNode getParent() {
        return _parent;
    }

    public Plan getEdge() {
        return _edge;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this._state.hashCode();
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
        final PlayerStateNode other = (PlayerStateNode) obj;
        
        if (!this._state.equals(other._state)) {
            return false;
        }
        return true;
    }
    
    
}
