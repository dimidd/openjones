/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones;

/**
 *
 * @author dimid
 */
public abstract class Stock extends ModifiableCommodity {
    
    private static int _totalUnitsOwned;

    /**
     * Number of units of this stock owned by all players
     * @return
     */
    public static int getTotalUnitsOwned() {
        return _totalUnitsOwned;
    }

    protected static void setTotalUnitsOwned(int aTotalUnitsOwned) {
        _totalUnitsOwned = aTotalUnitsOwned;
    }
    
    public Stock (int unitValue, String name, int totalUnitsOwned) {
        super(unitValue, name);
        _totalUnitsOwned = totalUnitsOwned;
    }
    
    /**
     * Updates price at the end of the turn, usually dependent on getTotalUnitsOwned
     */
    protected void updatePrice() {
    	//no update, this method should be overridden
    }
 
   
    
}
