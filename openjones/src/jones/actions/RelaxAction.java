/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.actions;

import jones.general.Player;
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
    private Player _cachedPlayer;
    
    private static RelaxAction _instance;
    
    
     public RelaxAction(House house) {
        _house = house;
        _healthEffect = Integer.MIN_VALUE;
        _happinessEffect = Integer.MIN_VALUE;
        _cachedPlayer = null;
    }
    
//    public static RelaxAction getInstance() {
//       if (null == _instance) {
//           _instance = new RelaxAction();
//       }
//       return _instance;
//    }

    
    @Override
    protected ActionResponse checkConditions(Player player) {
        return checkTime(player);
    }

    
    
    @Override
    protected void doAction(Player player) {
       // if (_cachedPlayer != player) {
            _happinessEffect = happinessEffect(player);
            _healthEffect    = healthEffect(player);
      //  }       
        
        player.getState().affectHappiness(_happinessEffect);
        player.getState().affectHealth(_healthEffect);
        player.affectTime(timeEffect(player));
    }

    @Override
    public int healthEffect(Player player) {
        _cachedPlayer = player;
        _healthEffect = _house.getRelaxHealthEffect() + player.getState().getPossessions().sumRestHealthEffectsPerTimeUnit();
        return _healthEffect * timeEffect(player);
    }

    @Override
    public int happinessEffect(Player player) {
        _cachedPlayer = player;
        _happinessEffect = _house.getRelaxHappinessEffect() + player.getState().getPossessions().sumRestHappinessEffectsPerTimeUnit();
        return _happinessEffect * timeEffect(player);
    }

//    @Override
//    public int WealthEffect(Player player) {
//        return 0;
//    }

    @Override
    public int careerEffect(Player player) {
        return 0;
    }

    @Override
    public int cashEffect(Player player) {
         return 0;
    }

    @Override
    public int timeEffect(Player player) {
        if (null == _timeEffect)            
            _timeEffect = getAvailiableTimeUpto(REST_DURATION, player);
        
        return _timeEffect;
    }

    @Override
    public String toString() {
        return "Rest";
    }

 
    @Override
    protected ActionResponse getPositiveResponse(Player player) {
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
