/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.map;

import jones.actions.ApplyForJobAction;
import java.util.ArrayList;
import jones.actions.Action;
import jones.actions.SubMenuAction;
import jones.general.PlayerState;
import jones.general.Position;
import jones.jobs.Job;
import net.vivin.GenericTree;
import net.vivin.GenericTreeNode;

/**
 *
 * @author dimid
 */
class EmploymentAgency extends Building {
    private final MapManager _map;

    public EmploymentAgency(Position pos, String name, MapManager map) {
        
        super(pos,name);
        _map = map;
    }

	@Override
	protected void buildActionsTree(PlayerState player, GenericTree<Action> actionsTree) {
            GenericTreeNode<Action> root = actionsTree.getRoot();
            for (Building b : _map.getBuildings()) {
                ArrayList<Job> jobs = b.getJobs();
                if (!jobs.isEmpty()) {
                    GenericTreeNode<Action> buildingNode = new GenericTreeNode<>(null);
                    Action getBuildingJobsList = new SubMenuAction(0, b.getClass().getSimpleName(), buildingNode, this);
                    buildingNode.setData(getBuildingJobsList);
                    root.addChild(buildingNode);
                    
                    //build submenu ( go back action and building`s jobs
                    Action mainmenu =  new SubMenuAction(0, "back", root, this);
                    GenericTreeNode<Action> mainmenuNode = new GenericTreeNode<>(mainmenu);
                    //buildingNode.addChild(mainmenuNode);
                                         
                    for (Job j: jobs) {
                        Action apply = new ApplyForJobAction(j);
                        GenericTreeNode<Action> jobNode = new GenericTreeNode<>(apply);
                        buildingNode.addChild(jobNode);
                    }
                }
                
            }
		
	}

	@Override
	protected void addJobs() {
		//no jobs 
		
	}
    
}
