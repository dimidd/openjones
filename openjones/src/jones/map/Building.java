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
import jones.general.Position;
import net.vivin.GenericTree;
import net.vivin.GenericTreeNode;

/**
 * A Building is a passable, visitable and enterable location
 *
 * @author dimid
 */
public abstract  class Building extends Site {
    
    
    protected String _name;
    protected ArrayList<Job> _jobs;
    protected ArrayList<Action> _actions;
    protected GenericTreeNode<Action> _playerActionsParent;
    
    
    /**
     * The first 3 children of the root are reserved places:
     * 0: exit building or return to previous menu
     * 1: relax
     * 2: work
     */
    protected GenericTree<Action> _actionsTree;
    public static final int DONE_ACTION_INDEX = 0; //exit or return to previous menu
    public static final int RELAX_ACTION_INDEX = 1;
    public static final int WORK_ACTION_INDEX = 2;
  
    public static final int LAST_INDEX_OF_SPECIAL_ACTION = 2;
    
    
    /** Create a new Building
    *
    * @param pos
    */
    public Building (Position pos, String name) {
        super(pos);
        _name = name;
        _actionsTree = new GenericTree<>();
        
        GenericTreeNode<Action> root = new GenericTreeNode<>(null);
        SubMenuAction mainMenu = new SubMenuAction(0,"main",root,this); 
        root.setData(mainMenu);
        _actionsTree.setRoot(root);        
        _playerActionsParent = null;       
        _actions = new ArrayList<>();
        _jobs = new ArrayList<>();
        
        addJobs();       
    }
   
       
    @Override
    public boolean isEnterable() {
        return true;
    }

    //protected abstract ArrayList<? extends Action> getBuildinSpecificgActions(Player player);
    
    
    /**
     * Return all  specific (i.e. not special) actions from main menu  
     * @param player
     * @return 
     */
    public  List<Action> getPlayerBuildingSpecificActions(Player player) {
        assert(_actionsTree.getRoot() == getPlayerActionsParent());
        return getPlayerActionsParent().getDataOfChildrenFrom(LAST_INDEX_OF_SPECIAL_ACTION + 1);
    }
    
    public  ArrayList<? extends Action> getPlayerActions(Player player) {
        assert(null != getPlayerActionsParent());
        setActions(new ArrayList<Action>());
//        GenericTreeNode<Action> root = getActionsTree().getRoot();
//        
//        Action doneAction;
//        if (root == _playerActionsParent) {           
//            doneAction = new ExitBuildingMovement(this.getPosition(), this); //exit            
//        }
//        else {
//            doneAction = new SubMenuAction(0, "back", _playerActionsParent.getParent()); //back
//        }
//        _actions.add(DONE_ACTION_INDEX,doneAction);
//                   
//        Action relaxAction =  null;
//        if (player.getState().getHouse() == this) {
//            relaxAction = new RelaxAction(player.getState().getHouse());
//        }
//        _actions.add(RELAX_ACTION_INDEX,relaxAction);
//               
//        Action workAction = null;
//        if (player.getState().getJob().getBuilding() == this) {
//            workAction =  new WorkAction(player.getState().getJob());
//        }
//        _actions.add(WORK_ACTION_INDEX,workAction);

  //      _actions.addAll(getMenuActions(player));
         getActions().addAll(getPlayerActionsParent().getDataOfChildren());
        return getActions();
    }

    public void prepareForPlayerEntrance(Player player) {
        _actionsTree.getRoot().removeChildren();
        setPlayerActionsParent(getActionsTree().getRoot());
        setActions(null);
        buildAllActionsTree(player);
              
    }

    public void prepareForPlayerExit(Player player) {
        setPlayerActionsParent(null);
       
    }

    /**
     * Perform an action from getPlayerActions
     * @param actionIndex the index of the action
     * @param player the player who will carry out the action
     * @return response
     */
    public ActionResponse performAction(int actionIndex, Player player) {
        assert(null != getActions());
        Action action;
//        if (isSpecialAction(actionIndex)) {
//            action = _actions.get(actionIndex);
//        }
//        else {             
            GenericTreeNode<Action> node = getPlayerActionsParent().getChildAt(actionIndex);        
            action = node.getData();
            if (null == action) {
            return new ActionResponse(false, "Null Action");
        }
  //      }
        
        
        ActionResponse response = action.perform(player);
        if (response._isPositive && action.isSubmenu()) {
            setPlayerActionsParent(((SubMenuAction) action).getNode());
        }
        
        if (action.shouldRebuildActions()) {
            prepareForPlayerEntrance(player);
        }
        return response;
    }

    /**
     * Add nodes for special actions, the subclass adds his specific actions
     * @param player 
     */
    public  void buildAllActionsTree(Player player) {
        GenericTreeNode<Action> root = getActionsTree().getRoot();
                
        Action doneAction;
        if (root == getPlayerActionsParent()) {           
            doneAction = new ExitBuildingMovement(this.getPosition(), this); //exit            
        }
        else {
            doneAction = new SubMenuAction(0, "back", getPlayerActionsParent().getParent(), this); //back
        }
        root.addChildAt(DONE_ACTION_INDEX, new GenericTreeNode<>(doneAction));
                       
        Action relaxAction =  null;
        if (player.getState().getHouse() == this) {
            relaxAction = new RelaxAction(player.getState().getHouse());
        }
        root.addChildAt(RELAX_ACTION_INDEX, new GenericTreeNode<>(relaxAction));
               
        Action workAction = null;
        if (player.getState().getJob().getBuilding() == this) {
            workAction =  new WorkAction(player.getState().getJob());
        }
        root.addChildAt(WORK_ACTION_INDEX,new GenericTreeNode<>(workAction));

        buildActionsTree(player);
        
    }

    private Collection<? extends Action> getMenuActions(Player player) {
        assert (null != getPlayerActionsParent());
        return getPlayerActionsParent().getDataOfChildren();
    }

    /**
     * @return the _name
     */
    public String getName() {
        return _name;
    }

    /**
     * @param name the _name to set
     */
    public void setName(String name) {
        this._name = name;
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

    /**
     * @return the _actionsTree
     */
    public GenericTree<Action> getActionsTree() {
        return _actionsTree;
    }

    /**
     * @param actionsTree the _actionsTree to set
     */
    public void setActionsTree(GenericTree<Action> actionsTree) {
        this._actionsTree = actionsTree;
    }

    private boolean isSpecialAction(int actionIndex) {
        return (actionIndex == DONE_ACTION_INDEX  )   ||
               (actionIndex == RELAX_ACTION_INDEX )   ||
               (actionIndex == WORK_ACTION_INDEX  ); 
    }

    protected abstract void addJobs();
    
    /**
     * Add building specific actions to tree
     * @param player 
     */
    protected abstract void buildActionsTree(Player player);

    /**
     * @return the _actions
     */
    public ArrayList<Action> getActions() {
        return _actions;
    }

    /**
     * @param actions the _actions to set
     */
    public void setActions(ArrayList<Action> actions) {
        this._actions = actions;
    }

    /**
     * @return the _playerActionsParent
     */
    public GenericTreeNode<Action> getPlayerActionsParent() {
        return _playerActionsParent;
    }

    /**
     * @param playerActionsParent the _playerActionsParent to set
     */
    public void setPlayerActionsParent(GenericTreeNode<Action> playerActionsParent) {
        this._playerActionsParent = playerActionsParent;
    }
  
}
