/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import jones.actions.RelaxAction;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
public class RestAllWeekPlan extends Plan {

    public RestAllWeekPlan(Agent agent) {
        super(agent);
        build();
    }

    @Override
    public void build() {
                
        _actions.add( new GoHomeMarker(this, null));
        
        //after the last movement (i.e. enter building), we work repetetively
        _actions.add(new SetRepetetiveMarker(this, null, true));        
        _actions.add(new NoOpMarker(this, new RelaxAction(_lastHome)));

    }
    
    
}
