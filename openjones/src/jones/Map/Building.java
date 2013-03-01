/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.Map;

import java.util.ArrayList;
import jones.general.Action;
import jones.general.Job;
import jones.general.Position;

/**
 * A Building is a passable 
 *
 * @author dimid
 */
public abstract  class Building extends Site {
    
    protected String _name;
    protected ArrayList<Job> _jobs;
    protected ArrayList<Action> _actions;

    /** Create a new Building
    *
    * @param pos
    */
    public Building (Position pos, String name) {
        super(pos);
        _name = name;
        
    }
   
    
    
   
    @Override
    public boolean isEnterable() {
        return true;
    }

 
    public ArrayList<? extends Action> getBuildingActions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  
}
