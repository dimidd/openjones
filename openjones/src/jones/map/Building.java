/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.map;

import jones.actions.SubMenuAction;
import java.util.ArrayList;
import jones.actions.Action;
import jones.actions.Movement;
import jones.general.Job;
import jones.general.Position;
import net.vivin.GenericTree;
import net.vivin.GenericTreeNode;

/**
 * A Building is a passable 
 *
 * @author dimid
 */
public abstract  class Building extends Site {
    
    
    protected String _name;
    protected ArrayList<Job> _jobs;
    //protected ArrayList<Action> _actions;
    
    
    /**
     * The first 3 children of the root are reserved places:
     * 0: exit building
     * 1: relax
     * 2: work
     */
    protected GenericTree<Action> _actionsTree;
    public final int EXIT_BUILDING_ACTION_INDEX = 0;
    public final int RELAX_ACTION_INDEX = 1;
    public final int WORK_ACTION_INDEX = 2;
  
 
    
    
    /** Create a new Building
    *
    * @param pos
    */
    public Building (Position pos, String name) {
        super(pos);
        _name = name;
        _actionsTree = new GenericTree<>();
        SubMenuAction mainMenu = new SubMenuAction(0,"main"); 
        GenericTreeNode<Action> root = new GenericTreeNode<Action>(mainMenu);
        _actionsTree.setRoot(root);        
        GenericTreeNode<Action> exit = new GenericTreeNode<Action>(Movement.getExitMovement(this.getPosition()));
        root.addChildAt(EXIT_BUILDING_ACTION_INDEX, exit);
        
        
        //_actions = new ArrayList<>();
        //_actions.add(Movement.getExitMovement(this.getPosition()));
        
    }
   
    
    
   
    @Override
    public boolean isEnterable() {
        return true;
    }

 
    public abstract ArrayList<? extends Action> getBuildingActions();
    
  
}
