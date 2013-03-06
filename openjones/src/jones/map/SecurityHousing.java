/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.map;

import java.util.ArrayList;
import jones.actions.Action;
import jones.general.Position;

/**
 *
 * @author dimid
 */
public class SecurityHousing extends House{

    public SecurityHousing(Position pos, String name) {
        super(pos,name);
        
    }

 
    @Override
    public int getRelaxHealthEffect() {
        return 7;
    }

    @Override
    public int getRelaxHappinessEffect() {
        return 7;
    }

      
}
