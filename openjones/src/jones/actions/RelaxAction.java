/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.actions;

import jones.general.Player;
import jones.general.PlayerState;
import jones.map.House;

/**
 * Relax
 * @author dimid
 */
public class RelaxAction extends Action {
    public final int REST_DURATION = 24;
    
    private House _house;
    private int _healthEffect;
    private int _happinessEffect;
    private PlayerState _cachedPlayerState;
    
    private static RelaxAction _instance;
    
    
     public RelaxAction(House house) {
        _house = house;
        _healthEffect = Integer.MIN_VALUE;
        _happinessEffect = Integer.MIN_VALUE;
        _cachedPlayerState = null;
    }
    
//    public static RelaxAction getInstance() {
//       if (null == _instance) {
//           _instance = new RelaxAction();
//       }
//       return _instance;
//    }

    
    @Override
    protected ActionResponse checkConditions(PlayerState player) {
        return checkTime(player);
    }

    
    
    @Override
    protected void doAction(PlayerState player) {
       // if (_cachedPlayerState != player) {
            _happinessEffect = happinessEffect(player);
            _healthEffect    = healthEffect(player);
      //  }       
        
        player.affectHappiness(_happinessEffect);
        player.affectHealth(_healthEffect);
        player.affectTime(timeEffect(player));
    }

    @Override
    public int healthEffect(PlayerState player) {
        _cachedPlayerState = player;
        _healthEffect = _house.getRelaxHealthEffect() + player.getPossessions().sumRestHealthEffectsPerTimeUnit();
        return _healthEffect * timeEffect(player);
    }

    @Override
    public int happinessEffect(PlayerState player) {
        _cachedPlayerState = player;
        _happinessEffect = _house.getRelaxHappinessEffect() + player.getPossessions().sumRestHappinessEffectsPerTimeUnit();
        return _happinessEffect * timeEffect(player);
    }

//    @Override
//    public int WealthEffect(PlayerState player) {
//        return 0;
//    }

    @Override
    public int careerEffect(PlayerState player) {
        return 0;
    }

    @Override
    public int cashEffect(PlayerState player) {
         return 0;
    }

    @Override
    public int timeEffect(PlayerState player) {
        if (null == _timeEffect)            
            _timeEffect = getAvailiableTimeUpto(REST_DURATION, player);
        
        return _timeEffect;
    }

    @Override
    public String toString() {
        return "Rest";
    }

 
    @Override
    protected ActionResponse getPositiveResponse(PlayerState player) {
        return new ActionResponse(true, "zzzzz");
    }

    @Override
    public boolean isSubmenu() {
        return false;
    }

    @Override
    public void clearCachedValues() {
        _timeEffect = null;
    }

     
}
