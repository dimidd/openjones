/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package external;

import jones.agents.Plan;
import jones.general.AbstractPlayerState;
import jones.general.PlayerState;

/**
 * Container for an AbstractPlayer and a Plan
 * @author dimid
 */
public class PlayerStatePlan {
    private  PlayerState _playerState;
    private  Plan _plan;

    public void setPlayerState(PlayerState playerState) {
        this._playerState = playerState;
    }

    public void setPlan(Plan _plan) {
        this._plan = _plan;
    }

    public PlayerState getPlayerState() {
        return _playerState;
    }

    public Plan getPlan() {
        return _plan;
    }
    
    public  PlayerStatePlan(PlayerState playerState, Plan plan) {
        _playerState = playerState;
        _plan = plan;
    }

    @Override
    public String toString() {
        return "PlayerStatePlan{" + "_playerState=" + _playerState + ", _plan=" + _plan + '}';
    }
    
    
}
