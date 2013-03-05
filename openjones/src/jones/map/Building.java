/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.map;

import jones.actions.WorkAction;
import jones.actions.SubMenuAction;
import java.util.ArrayList;
import java.util.Collection;
import jones.actions.Action;
import jones.actions.ActionResponse;
import jones.actions.ExitBuildingMovement;
import jones.actions.RelaxAction;
import jones.general.Job;
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
  
 
    
    
    /** Create a new Building
    *
    * @param pos
    */
    public Building (Position pos, String name) {
        super(pos);
        _name = name;
        _actionsTree = new GenericTree<>();
        
        GenericTreeNode<Action> root = new GenericTreeNode<>(null);
        SubMenuAction mainMenu = new SubMenuAction(0,"main",root); 
        root.setData(mainMenu);
        _actionsTree.setRoot(root);        
        _playerActionsParent = null;       
        _actions = null;
        
    }
   
       
    @Override
    public boolean isEnterable() {
        return true;
    }

    //protected abstract ArrayList<? extends Action> getBuildinSpecificgActions(Player player);
    
    
    public  ArrayList<? extends Action> getPlayerActions(Player player) {
        assert(null != _playerActionsParent);
        _actions = new ArrayList<>();
        GenericTreeNode<Action> root = getActionsTree().getRoot();
        
        Action doneAction;
        if (root == _playerActionsParent) {           
            doneAction = new ExitBuildingMovement(this.getPosition(), this); //exit            
        }
        else {
            doneAction = new SubMenuAction(0, "back", _playerActionsParent.getParent()); //back
        }
        _actions.add(DONE_ACTION_INDEX,doneAction);
                   
        Action relaxAction =  null;
        if (player.getState().getHouse() == this) {
            relaxAction = new RelaxAction(player.getState().getHouse());
        }
        _actions.add(RELAX_ACTION_INDEX,relaxAction);
               
        Action workAction = null;
        if (player.getState().getJob().getBuilding() == this) {
            workAction =  new WorkAction(player.getState().getJob());
        }
        _actions.add(WORK_ACTION_INDEX,workAction);

        _actions.addAll(getMenuActions(player));
        
        return _actions;
    }

    public void prepareForPlayerEntrance(Player player) {
        _playerActionsParent = getActionsTree().getRoot();
        _actions = null;
        buildActionsTree();
              
    }

    public void prepareForPlayerExit(Player player) {
       _playerActionsParent = null;
       
    }

    /**
     * Perform an action from getPlayerActions
     * @param actionIndex the index of the action
     * @param player the player who will carry out the action
     * @return response
     */
    public ActionResponse performAction(int actionIndex, Player player) {
        assert(null != _actions);
        Action action;
        if (isSpecialAction(actionIndex)) {
            action = _actions.get(actionIndex);
        }
        else {             
            GenericTreeNode<Action> node = _playerActionsParent.getChildAt(actionIndex);        
            action = node.getData();
        }
        
        
        ActionResponse response = action.perform(player);
        if (response._wasPerformed && action.isSubmenu()) {
            _playerActionsParent = ((SubMenuAction) action).getNode();
        }
        
        return response;
    }

    protected abstract void buildActionsTree();

    private Collection<? extends Action> getMenuActions(Player player) {
        assert (null != _playerActionsParent);
        return _playerActionsParent.getDataOfChildren();
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
    
  
}
