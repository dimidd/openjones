/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.Map;

import jones.general.Action;
import jones.general.Position;

/**
 *
 * @author dimid
 */
public class Villa extends House{

    public Villa(Position pos, String name) {
        super(pos,name);
        
    }

    @Override
    public Action relax() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
