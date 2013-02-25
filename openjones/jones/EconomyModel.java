/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones;

import java.util.ArrayList;

/**
 *
 * @author dimid
 */
public abstract class EconomyModel {
    
	public final int MAX_STOCKS = 8; 
	
	public boolean addStock(Stock s) {
		if (_stocks.size() >= MAX_STOCKS)
			return false;
		
		_stocks.add(s);
		return true;
			
	}
	
    protected ArrayList<Stock> _stocks;

    /**
     * Updates stock prices according to two factors:
     * 1. Global economic change
     * 2. Specific stock logic (explained in the Stock class)
     * 
     
     */
    abstract public void changeStocks();

    /**
     * Goes over the buildings, and updates salaries and action costs
     * 
     * @param _buildings
     */
    abstract public void changeBuildings(ArrayList<Building> _buildings);

      
}
