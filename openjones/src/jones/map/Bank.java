/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.map;

import java.util.ArrayList;
import jones.actions.Action;

import jones.possessions.Stock;

import jones.general.Player;
import jones.general.PlayerState;
import jones.general.Position;
import net.vivin.GenericTree;

/**
 *
 * @author dimid
 */
class Bank extends Building {

	private ArrayList<Stock> _stocks;
	public final static int T_BILLS_BASE_VALUE = 100;
	public final static int GOLD_BASE_VALUE = 450;
	public final static int SILVER_BASE_VALUE = 150;	
	public final static int PIG_BELLIES_BASE_VALUE = 15;
	public final static int BLUE_CHIP_BASE_VALUE = 50;
	public final static int PENNY_BASE_VALUE = 5;
	
    public Bank(Position pos, String name) {        
        super(pos,name);
        addDefaultStocks();

    }
    
    private void addDefaultStocks() {
    	_stocks = new ArrayList<>();
    	_stocks.add(new Stock(T_BILLS_BASE_VALUE, "T-BILLS", 0));
    	_stocks.add(new Stock(GOLD_BASE_VALUE, "Gold", 0));
    	_stocks.add(new Stock(SILVER_BASE_VALUE, "Silver", 0));
    	_stocks.add(new Stock(PIG_BELLIES_BASE_VALUE, "Pig Bellies", 0));   	
    	_stocks.add(new Stock(BLUE_CHIP_BASE_VALUE, "Blue Chip", 0));
    	_stocks.add(new Stock(PENNY_BASE_VALUE, "Penny", 0));

 	}

	@Override
	protected void buildActionsTree(PlayerState player, GenericTree<Action> actionsTree) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addJobs() {
		// TODO Auto-generated method stub
		
	}

    
}
