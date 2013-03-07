/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package possessions;

import possessions.Possession;

/**
 * Contract is a right to purchase a possession at a fixed price,
 * regardless of it's current price
 * @author dimid
 */
public class Contract {
    protected Possession _possession;
    
    public Contract (Possession poss) {
        _possession = poss;
    }

    public Possession getPossession() {
        return _possession;
    }

    public void setPossession(Possession possession) {
        this._possession = possession;
    }
    
}
