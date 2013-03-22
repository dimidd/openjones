/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import jones.actions.Action;
import jones.actions.ActionResponse;
import jones.actions.Movement;
import jones.actions.StudyAction;
import jones.actions.SubMenuAction;
import jones.actions.WorkAction;
import jones.general.Game;
import jones.general.Player;
import jones.general.PlayerPosition;
import jones.general.Position;
import jones.general.Route;
import jones.jobs.Job;
import jones.map.Building;

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

    public int getDuration() {
        return _duration;
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
    
    public Action getNextAction() {
        PlanMarker marker;
        Action result;
        if (!_isRepetetive) {
            marker = _actions.remove();
        }
        else {
            marker = _actions.peek();
        }
        
        marker.changeState();
        result = marker.getAction();
        if (null != result) {
            return result;
        }
        else {
            return getNextAction();
        }
        
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
    
    
    
}
