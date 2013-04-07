/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.possessions;

/**
 * A simple stock. Doesn't consider units owned to update price.
 *  Override for a more realistic model
 * @author dimid
 */
public class Stock extends ModifiableCommodity {
    
    private static int _totalUnitsOwned;
    
    public Stock (int unitValue, String name, int totalUnitsOwned) {
        super(unitValue, name);
        _totalUnitsOwned = totalUnitsOwned;
    }
    
    
    
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
    
   
    
    /**
     * Updates price at the end of the turn, usually dependent on getTotalUnitsOwned
     */
    protected void updatePrice() {
    	//no update, this method should be overridden
    }
 
   
    
}
