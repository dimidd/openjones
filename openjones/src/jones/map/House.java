/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.map;

import jones.actions.Action;
import jones.actions.RelaxAction;
import jones.general.Position;

/**
 *
 * @author dimid
 */
public abstract class House extends Building {

    
    /** Create a new Building
    *
    * @param pos
    */
    public House (Position pos, String name) {
        super(pos,name);
 
        _actions.add(new RelaxAction(this));
        
       
    }
    
    /**
     *
     * @return
     */
    public Action relax() {
        return new RelaxAction(this);
    }

    public abstract int getRelaxHealthEffect();

    public abstract int getRelaxHappinessEffect();

    @Override
    protected void addJobs() {
        //no jobs
    }

    @Override
    protected void buildActionsTree() {
        //no actions
    }

    
}
