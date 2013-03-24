/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import jones.actions.Action;
import jones.actions.WorkAction;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
class PushMarkerOnResponseMarker extends PlanMarker {
    private final boolean _responsePushValue;
    private final PlanMarker _pushMarker;
    private final String _responseMessageSubstring;

    public PushMarkerOnResponseMarker(Plan plan, Action action, PlanMarker marker, boolean b ) {
      this(plan, action, marker, b, null);
    }

    public PushMarkerOnResponseMarker(Plan plan, Action action, PlanMarker marker, boolean b, String responeSubstring) {
           
        super(plan, action);
        _responsePushValue = b;
        _pushMarker = marker;
        _responseMessageSubstring = responeSubstring; 
        
    }

    @Override
    public void changeState() {
        _plan.setIsRepetetive(false);
        
        if (null != _plan.getLastResponse() && _plan.getLastResponse()._isPositive == _responsePushValue && _plan.getLastResponse()._message.contains(_responseMessageSubstring)) {
            _plan.getActions().push(_pushMarker);
            _action = null;
        }
    }
    
}
