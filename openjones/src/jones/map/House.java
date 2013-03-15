/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.map;

import jones.actions.Action;
import jones.actions.RelaxAction;
import jones.general.Player;
import jones.general.Position;

/**
 *
 * @author dimid
 */
public abstract class House extends Building {

    protected int _pricePerMonth;
    
    /** Create a new Building
    *
    * @param pos
    */
    public House (Position pos, String name, int pricePerMonth) {
        super(pos,name);
 
        _actions.add(new RelaxAction(this));
        _pricePerMonth = pricePerMonth;
       
    }
    
    /**
     *
     * @return
     */
    public Action relax() {
        return new RelaxAction(this);
    }

    public abstract int getRelaxHealthEffect();

    public abstract int getRelaxHappinessEffect();

    @Override
    protected void addJobs() {
        //no jobs
    }

    @Override
    protected void buildActionsTree(Player player) {
        //no actions
    }

    /**
     * Rent cost per week
     * @return 
     */
    public int pricePerWeek() {
        return _pricePerMonth / RentAgency.WEEKS_OF_RENT_IN_A_MONTH;
    }

    /**
     * @return the _pricePerMonth
     */
    public int getPricePerMonth() {
        return _pricePerMonth;
    }

    /**
     * @param pricePerMonth the _pricePerMonth to set
     */
    public void setPricePerMonth(int pricePerMonth) {
        this._pricePerMonth = pricePerMonth;
    }

    
}
