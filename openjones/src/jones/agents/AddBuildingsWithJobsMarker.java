/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import jones.actions.Action;
import jones.actions.SubMenuAction;
import jones.general.PlayerState;
import jones.map.Building;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
class AddBuildingsWithJobsMarker extends PlanMarker {

    public AddBuildingsWithJobsMarker(Plan plan, Action action) {
        super(plan, action);
    }

    @Override
    public void changeState(PlayerState playerState) {
        SubMenuAction buildingJobs;

        for (int buildingIndex = Building.LAST_INDEX_OF_SPECIAL_ACTION + 1; buildingIndex < _plan.getPossibletActions().size(); ++buildingIndex) {
            //for (Integer buildingIndex : buildingsWithProfitableJobs) {
            //assert (buildingIndex > Building.LAST_INDEX_OF_SPECIAL_ACTION);
            buildingJobs = (SubMenuAction) _plan.getPossibletActions().get(buildingIndex);
            _plan.getActions().add(new NoOpMarker(_plan, buildingJobs));
            _plan.getActions().add(new PushJobsMarker(_plan, null));
        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
