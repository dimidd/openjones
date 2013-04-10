/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import java.util.ArrayList;
import java.util.List;
import jones.actions.Action;
import jones.actions.SubMenuAction;
import jones.general.Game;
import jones.general.Player;
import jones.general.PlayerPosition;
import jones.general.PlayerState;
import jones.general.Position;
import jones.map.Building;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
class GetABetterJobPlan extends AllOrNothingPlan {
    private final PlayerState _playerState;

    public GetABetterJobPlan(Agent agent, PlayerState playerState) {
        super(agent, PlanType.BETTER_JOB);
        _playerState = playerState;
        build();
    }
  
        
    /**
     * Get a job better (i.e. higher paying) than the current job
     * @param agent
     * @param buildingsWithProfitableJobs A list of inidices from EmploymentAgency`s main menu.
     * The initial list contains all specific actions.
     * After an agent checked a building, and haven't found any job, that pays better,
     * this building`s index is removed. **Currently NOT used**
     * @return 
     */
//    public static Plan getABetterJob(Agent agent, List<Integer> buildingsWithProfitableJobs) {
//        Game game = agent.getGame();
//        Player player = agent.getPlayer();
//        Building employmentAgency = game.getMap().getBuildingByName("employment");
//        assert (employmentAgency != null);
//        
//        Position employmentAgencyPos = employmentAgency.getPosition();   
//        PlayerPosition dest = new PlayerPosition(employmentAgencyPos, true);
//        Plan result = moveTo(agent, dest);
//        
//        //submenu actions of buildings
//        //List<Action> buildingSpecificActions = employmentAgency.getPlayerBuildingSpecificActions(player);
//        ArrayList<? extends Action> possibletActions = game.getPossibletActions();
//        SubMenuAction buildingJobs;
//        
//        for (int buildingIndex = Building.LAST_INDEX_OF_SPECIAL_ACTION + 1; buildingIndex < possibletActions.size(); ++buildingIndex) {
//        //for (Integer buildingIndex : buildingsWithProfitableJobs) {
//            //assert (buildingIndex > Building.LAST_INDEX_OF_SPECIAL_ACTION);
//            buildingJobs = (SubMenuAction) possibletActions.get(buildingIndex);
//            result._actions.add(new PushJobsMarker(result, buildingJobs));
//        }
//        
//        return result;
//    }

    @Override
    public void build() {
               
        Game game = _agent.getGame();
        //Player player = _agent.getPlayer();
        Building employmentAgency = game.getMap().getBuildingByName("employment");
        assert (employmentAgency != null);
        
        Position employmentAgencyPos = employmentAgency.getPosition();   
        PlayerPosition employment = new PlayerPosition(employmentAgencyPos, true);
        _actions.add(new MoveMarker(this, null, employment));
        
        //submenu actions of buildings
        //List<Action> buildingSpecificActions = employmentAgency.getPlayerBuildingSpecificActions(player);
        _actions.add(new UpdatePossibleActionsMarker(this, null, _playerState));
        _actions.add(new AddBuildingsWithJobsMarker(this, null));
        
          
    }

    
}
