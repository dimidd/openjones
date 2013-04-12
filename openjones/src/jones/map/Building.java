/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.map;

import jones.actions.WorkAction;
import jones.actions.SubMenuAction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import jones.actions.Action;
import jones.actions.ActionResponse;
import jones.actions.ExitBuildingMovement;
import jones.actions.RelaxAction;
import jones.jobs.Job;
import jones.general.Player;
import jones.general.PlayerState;
import jones.general.Position;
import net.vivin.GenericTree;
import net.vivin.GenericTreeNode;

/**
 * A Building is a passable, visitable and enterable location
 *
 * @author dimid
 */
public abstract  class Building extends Site {
    protected ArrayList<Job> _jobs;
    //protected ArrayList<Action> _actions;
//    protected GenericTreeNode<Action> _playerActionsParent;
    
    
    /**
     * The first 3 children of the root are reserved places:
     * 0: exit building or return to previous menu
     * 1: relax
     * 2: work
     */
    protected final GenericTree<Action> _actionsTree;
    public static final int DONE_ACTION_INDEX = 0; //exit or return to previous menu
    public static final int RELAX_ACTION_INDEX = 1;
    public static final int WORK_ACTION_INDEX = 2;
  
    public static final int LAST_INDEX_OF_SPECIAL_ACTION = 2;
    
   
    private final boolean _areActionsPlayerDependent;
    
    
    /** Create a new Building
    *
    * @param pos
    */
    public Building (Position pos, String name, boolean areActionsPlayerDependent) {
        super(pos);
        _name = name;
        
        //_actions = new ArrayList<>();
        _jobs = new ArrayList<>();
        _areActionsPlayerDependent = areActionsPlayerDependent;
        addJobs();
        
        if (!areActionsPlayerDependent) {
            _actionsTree = buildAllActionsTree(null);
        }
        else {
            _actionsTree = null;
        }
    }
   
    public Building (Position pos, String name) {
        this(pos, name, true);
    }
       
    @Override
    public boolean isEnterable() {
        return true;
    }

    //protected abstract ArrayList<? extends Action> getBuildinSpecificgActions(PlayerState player);
    
    
//    /**
//     * Return all  specific (i.e. not special) actions from main menu  
//     * @param player
//     * @return 
//     */
    //public abstract List<Action> getPlayerBuildingSpecificActions(PlayerState player);
    
//    {
//        //assert(_actionsTree.getRoot() == getPlayerActionsParent());
//        return getPlayerActionsParent().getDataOfChildrenFrom(LAST_INDEX_OF_SPECIAL_ACTION + 1);
//    }
    
    
    
//    public  ArrayList<? extends Action> getPlayerActions() {
////        if(null == _playerActionsParent) 
////            _playerActionsParent = getActionsTree().getRoot();
//        _actions = new ArrayList<>();
//        List<Action> dataOfChildren = _playerActionsParent.getDataOfChildren();
//         _actions.addAll(dataOfChildren);
//         
//        return _actions;
//    }

//    public void prepareForPlayerEntrance(PlayerState playerState) {
//        _actionsTree.getRoot().removeChildren();
//       // setPlayerActionsParent(getActionsTree().getRoot());
//        setActions(null);
//        buildAllActionsTree(playerState);
//              
//    }
//
//    public void prepareForPlayerExit() {
//        setPlayerActionsParent(null);
//       
//    }

    /**
     * Perform an action from getPlayerActions
     * @param actionIndex the index of the action
     * @param player the player who will carry out the action
     * @return response
     */
    public ActionResponse performAction(int actionIndex, PlayerState player, ArrayList<Action> actions) {
        //assert(null != getActions());
        Action action;
//        if (isSpecialAction(actionIndex)) {
            action = actions.get(actionIndex);
//        }
//        else {             
//            GenericTreeNode<Action> node = player.getPlayerActionsParent().getChildAt(actionIndex);        
//            action = node.getData();
//            if (null == action) {           
//                return new ActionResponse(false, "Node with null Action");        
//            }
//        }
        
        
        ActionResponse response = action.perform(player);
        if (response._isPositive && action.isSubmenu()) {
            player.setPlayerActionsParent(((SubMenuAction) action).getNode());
        }
        
        if (action.shouldRebuildActions()) {
            player.setActionsTree(null);
        }
        return response;
    }

    /**
     * Add nodes for special actions, the subclass adds his specific actions
     * @param player 
     */
    public  GenericTree<Action>   buildAllActionsTree(PlayerState playerState) {
        GenericTree<Action> actionsTree = new GenericTree<>();
        
        GenericTreeNode<Action> root = new GenericTreeNode<>(null);
        SubMenuAction mainMenu = new SubMenuAction(0,"main",root,this); 
        root.setData(mainMenu);
        actionsTree.setRoot(root);                     
                
 
        buildActionsTree(playerState, actionsTree);
        
        return actionsTree;
        
    }

    /**
     * @return the _jobs
     */
    public ArrayList<Job> getJobs() {
        return _jobs;
    }

    /**
     * @param jobs the _jobs to set
     */
    public void setJobs(ArrayList<Job> jobs) {
        this._jobs = jobs;
    }

   

    private boolean isSpecialAction(int actionIndex) {
        return (actionIndex == DONE_ACTION_INDEX  )   ||
               (actionIndex == RELAX_ACTION_INDEX )   ||
               (actionIndex == WORK_ACTION_INDEX  ); 
    }

    protected abstract void addJobs();
    
    
    /**
     * Add building specific actions to tree
     * 
     * @param player
     * @param actionsTree the value of actionsTree
     */
    
    protected abstract void buildActionsTree(PlayerState player, GenericTree<Action> actionsTree);
   
    public List<Action> getPlayerBuildingSpecialActions(PlayerState playerState) {
               
        GenericTreeNode<Action> root = playerState.getActionsTree().getRoot();
        
        Action doneAction;        
        if (root == playerState.getPlayerActionsParent()) {           
            doneAction = new ExitBuildingMovement(this.getPosition(), this); //exit            
        }
        else {
            doneAction = new SubMenuAction(0, "back", playerState.getPlayerActionsParent().getParent(), this); //back
        }
        //root.addChildAt(DONE_ACTION_INDEX, new GenericTreeNode<>(doneAction));
                       
        Action relaxAction =  null;
        if (playerState.getHouse() == this) {
            relaxAction = new RelaxAction(playerState.getHouse());
        }
        //root.addChildAt(RELAX_ACTION_INDEX, new GenericTreeNode<>(relaxAction));
               
        Action workAction = null;
        if (playerState.getJob().getBuilding() == this) {
            workAction =  new WorkAction(playerState.getJob());
        }
        //root.addChildAt(WORK_ACTION_INDEX,new GenericTreeNode<>(workAction));

        ArrayList<Action> result = new ArrayList<>();
        result.add(DONE_ACTION_INDEX, doneAction);
        result.add(RELAX_ACTION_INDEX, relaxAction);
        result.add(WORK_ACTION_INDEX, workAction);
        
        return result;
    }

   
  
}
