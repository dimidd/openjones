/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.actions;

import jones.general.Player;
import jones.map.House;

/**
 *
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
    protected String checkConditions(Player player) {
        return null;
    }

    @Override
    protected void doAction(Player player) {
        if (_cachedPlayer != player) {
            _happinessEffect = happinessEffect(player);
            _healthEffect    = healthEffect(player);
        }       
        
        player.getState().affectHappiness(_happinessEffect);
        player.getState().affectHealth(_healthEffect);
       
    }

    @Override
    public int healthEffect(Player player) {
        _cachedPlayer = player;
        _healthEffect = _house.getRelaxHealthEffect() + player.getState().getPossessions().sumRestHealthEffects();
        return _healthEffect;
    }

    @Override
    public int happinessEffect(Player player) {
        _cachedPlayer = player;
        _happinessEffect = _house.getRelaxHappinessEffect() + player.getState().getPossessions().sumRestHappinessEffects();
        return _happinessEffect;
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
        return Math.min(REST_DURATION, player.getState().timeLeft());
    }

    @Override
    public String toString() {
        return "Rest";
    }
    
}
