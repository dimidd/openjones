/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.map;

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
class RentAgency extends Building {
    private  List<House> _houses;

    public RentAgency(Position pos, String name, List<House> houses) {
        super(pos,name);
        _houses = houses; 
    }

    @Override
    protected void buildActionsTree(Player player) {
        GenericTreeNode<Action> root = _actionsTree.getRoot();
        root.addChild(new GenericTreeNode<Action> (new PayRentAction()));
        for (House h: _houses)
            root.addChild(new RentHouseAction(h));                 
    }

    @Override
    protected void addJobs() {
        _jobs.add(new Job("Groundskeeper", this, 1, 5));
        _jobs.add(new Job("Apartment Manager", this, 1, 6));      
        
    }
    
}
