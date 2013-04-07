/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import jones.actions.Action;
import jones.actions.ActionResponse;
import jones.actions.Movement;
import jones.actions.StudyAction;
import jones.actions.SubMenuAction;
import jones.actions.WorkAction;
import jones.general.AbstractPlayer;
import jones.general.AbstractPlayerState;
import jones.general.Game;
import jones.general.Player;
import jones.general.PlayerPosition;
import jones.general.PlayerState;
import jones.general.Position;
import jones.general.Route;
import jones.jobs.Job;
import jones.map.Building;
import jones.map.House;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
public abstract class Plan {
    protected LinkedList<PlanMarker> _actions;
    protected Agent _agent;
    protected boolean _isRepetetive;
    protected int _duration;
    protected ActionResponse _lastResponse;
    protected Job _lastJob;
    protected House _lastHome;

    public Player getPlayer() {
        return _agent.getPlayer();
    }
    
    public House getLastHome() {
        return _lastHome;
    }

    public void setLastHome(House _lastHome) {
        this._lastHome = _lastHome;
    }

    public ArrayList<? extends Action> getPossibletActions() {
        return _possibletActions;
    }

    public void setPossibletActions(ArrayList<? extends Action> _possibletActions) {
        this._possibletActions = _possibletActions;
    }
    protected ArrayList<? extends Action>  _possibletActions;
    
    //public abstract int getDuration(AbstractPlayerState state);

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this._agent);
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
        final Plan other = (Plan) obj;
        if (!Objects.equals(this._agent, other._agent)) {
            return false;
        }
        return true;
    }

    
    public void setPlan (Plan other) {
        _actions = other._actions;
        _agent = other._agent;
        _isRepetetive = other._isRepetetive;
        _duration = other._duration;
        _lastResponse = other._lastResponse;
    }
    
    public void setDuration(int _duration) {
        this._duration = _duration;
    }

    public ActionResponse getLastResponse() {
        return _lastResponse;
    }

    public void setLastResponse(ActionResponse _lastresponse) {
        this._lastResponse = _lastresponse;
    }
    
    private static final long serialVersionUID = 1L;
    
    public Agent getAgent() {
        return _agent;
    }
    
    protected Plan (Agent agent) {
        _agent   = agent;
        _actions = new LinkedList<>();
        //build();
        
    }
    
    public abstract void build();
    
    public void rebuild() {
        _actions.clear();        
        _actions = new LinkedList<>();
        build();
    }

    public Job getLastJob() {
        return _lastJob;
    }

    public void setLastJob(Job job) {
        this._lastJob = job;
    }
    
    public Action getNextAction(PlayerState playerState) {
        //Player player = _agent.getPlayer();
        int timeLeft = playerState.timeLeft();
        PlanMarker marker = _actions.peek();
//        if (null == marker)
//            return null;
        Action result = marker.getAction();
        int timeEffect = Integer.MAX_VALUE;
        if (null != result) {
            timeEffect = result.timeEffect(playerState);
        }
        
        if (!_isRepetetive) {  
            _actions.remove();
        }
        else
            if (timeEffect >= timeLeft) {
                _actions.remove();
            }
        marker.changeState(playerState);
        result = marker.getAction();
            
        return result;
    }

    /**
     * concatenate another Plan to this one.
     * i.e. add the other's actions to the end of the FIFO queue
     * @param other 
     */
    public void concatenate (Plan other) {
        _actions.addAll(other._actions);
        _duration += other._duration;
    }
    
      
    /**
     * concatenate another Plan to this one.
     * i.e. add the other's actions to the end of the FIFO queue
     * @param other 
     */
    public void push (Plan other) {
        Iterator<PlanMarker> actionsReversedIterator = other._actions.descendingIterator();
        while (actionsReversedIterator.hasNext()) {
 //       for (PlanMarker m: other._actions) {
            _actions.push(actionsReversedIterator.next());
        }
        _duration += other._duration;
    }
    
    
 /**
  * Return true if this Plan has no more markers
  * @return 
  */
    public boolean isEmpty() {
        return _actions.isEmpty();
    }
     
   
    
    
     
    


    public LinkedList<PlanMarker> getActions() {
        return _actions;
    }

    public void setActions(LinkedList<PlanMarker> _actions) {
        this._actions = _actions;
    }

    public boolean isIsRepetetive() {
        return _isRepetetive;
    }

    public void setIsRepetetive(boolean _isRepetetive) {
        this._isRepetetive = _isRepetetive;
    }

    public int size() {
        return _actions.size();
    }
    
    public String toString() {
        return getClass().getSimpleName()+" actions:"+_actions.toString();
    }
    
    public abstract void notifyOfNewTurn();
        
    
}
