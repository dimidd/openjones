/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.actions;

/**
 *
 * @author dimid
 */
public class ActionResponse {
    
    public final boolean _wasPerformed;
    public final String _message;
    
    
    public ActionResponse (boolean wasPerformed, String message) {
        _wasPerformed = wasPerformed;
        _message = message;
    }
}
