/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.map;

import jones.actions.Action;
import jones.general.Player;
import jones.general.PlayerState;
import jones.general.Position;
import jones.jobs.Job;
import net.vivin.GenericTree;

/**
 *
 * @author dimid
 */
class Restaurant extends Building {

       
    public static final int COOK_BASE_WAGE = 3;
    public static final int CLERK_BASE_WAGE = 5;
    public static final int ASSISTANT_MANAGER_BASE_WAGE = 7;
    public static final int MANAGER_BASE_WAGE = 9;

    public Restaurant(Position pos ,String name) {
                super(pos,name);

    }

	@Override
	protected void buildActionsTree(PlayerState player, GenericTree<Action> actionsTree) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addJobs() {               
            
            getJobs().add(new Job("Cook", this, 1, COOK_BASE_WAGE ,1, 0));            
            getJobs().add(new Job("Clerk", this, 2, CLERK_BASE_WAGE,1));           
            getJobs().add(new Job("Assistant Manager", this, 3, ASSISTANT_MANAGER_BASE_WAGE, 2));      		          
            getJobs().add(new Job("Manager", this, 4, MANAGER_BASE_WAGE,3));      		
		
	}
    
}
