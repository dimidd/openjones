/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.map;

import java.util.ArrayList;
import jones.actions.Action;
import jones.actions.RelaxAction;
import jones.general.Position;

/**
 *
 * @author dimid
 */
public class Tent extends House {

    /** Create a new Building
    *
    * @param pos
    */
    public Tent (Position pos, String name) {
        super(pos,name);
        
    }


    @Override
    public int getRelaxHealthEffect() {
        return 3;
    }

    @Override
    public int getRelaxHappinessEffect() {
        return 3;
    }

  
    
}
