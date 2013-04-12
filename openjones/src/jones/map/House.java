/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.map;

import jones.actions.Action;
import jones.actions.RelaxAction;
import jones.general.Player;
import jones.general.PlayerState;
import jones.general.Position;
import net.vivin.GenericTree;
import net.vivin.GenericTreeNode;

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
 
     //   _actions.add();
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
    protected void buildActionsTree(PlayerState player, GenericTree<Action> actionsTree) {
//        Action relaxAction = new RelaxAction(this);
//        actionsTree.getRoot().addChild(new GenericTreeNode<>(relaxAction));
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
