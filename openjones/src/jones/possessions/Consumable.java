/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.possessions;

/**
 * Consumable is a commodity that is used each week. e.g. food
 * @author dimid
 */
public class Consumable extends Possession {
    
    private double _consuptionRate;
    private double _consumed;
    public final double EPSILON = 0.001;
    
    public Consumable (int units, Commodity comm, double rate) {
        super(units, comm);
        _consuptionRate = rate;
        _consumed = 0;
    }
    
    public void consume() {
        _consumed += _consuptionRate;
        if (_consuptionRate > 1.00 + EPSILON){
            int depleted = (int) _consuptionRate;
            _units -= depleted;
            _consumed = 0.0;            
        }
    }
    
}
