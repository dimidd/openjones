/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.agents;

import jones.general.Game;
import jones.general.Player;
import jones.general.PlayerPosition;
import jones.general.Position;
import jones.map.Building;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
class GetBetterClothesPlan extends Plan {

    public GetBetterClothesPlan(Agent agent) {
        super (agent);
        build();
    }

    @Override
    public void build() {
                        
        Game game = _agent.getGame();
        Player player = _agent.getPlayer();
        Building clothesStore = game.getMap().getBuildingByName("QT clothing");
        assert (clothesStore != null);
        
        Position clothesStorePos = clothesStore.getPosition();   
        PlayerPosition clothesStorePPos = new PlayerPosition(clothesStorePos, true);
        _actions.add(new MoveMarker(this, null, clothesStorePPos));
        
        //submenu actions of buildings
        //List<Action> buildingSpecificActions = employmentAgency.getPlayerBuildingSpecificActions(player);
        _actions.add(new UpdatePossibleActionsMarker(this, null));       
        _actions.add(new BuyClothesMarker(this, null));
       
    }
    
}
