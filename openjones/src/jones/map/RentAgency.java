/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.map;

import jones.actions.RentHouseAction;
import possessions.RentContract;
import jones.actions.PayRentAction;
import java.util.List;
import jones.jobs.Job;
import jones.actions.Action;
import jones.general.Player;
import jones.general.Position;
import net.vivin.GenericTreeNode;

/**
 *
 * @author dimid
 */
public class RentAgency extends Building {
    private  List<House> _houses;
        /**
     * Number of weeks in a month of rent
     */
    public static final int WEEKS_OF_RENT = 4;
    public static final int GROUNSKEEPER_BASE_WAGE = 5;
    public static final int APARTMENT_MANAGER_BASE_WAGE = 7;

    public RentAgency(Position pos, String name, List<House> houses) {
        super(pos,name);
        _houses = houses; 
    }

    @Override
    protected void buildActionsTree(Player player) {
        GenericTreeNode<Action> root = _actionsTree.getRoot();
        RentContract rentContract = player.getRentContract();
        if (null != rentContract) {
            root.addChild(new GenericTreeNode<Action> (new PayRentAction(rentContract.getPossession())));
        }
        
        for (House h: _houses) {
            root.addChild(new GenericTreeNode<Action> (new RentHouseAction(h, WEEKS_OF_RENT)));
        }                 
    }

    @Override
    protected void addJobs() {
        _jobs.add(new Job("Groundskeeper", this, 1, GROUNSKEEPER_BASE_WAGE));
        _jobs.add(new Job("Apartment Manager", this, 1, APARTMENT_MANAGER_BASE_WAGE));      
        
    }
    
}
