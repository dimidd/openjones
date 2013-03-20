/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.general;

import jones.actions.Action;
import jones.map.Location;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
class BuildingActionListener extends java.awt.event.MouseAdapter{
    private final int _actionID;
    private final Game _game;
    private final GUI _gui;

    BuildingActionListener(Game game, GUI gui, int a) {
                
        super();
        _actionID =a;
        _game = game;
        _gui = gui;

    }
    
       
    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        //System.out.println("before:"+_game.getCurPlayer().getPos());
        _game.performBuildingAction(_actionID);
        
        //System.out.println("after:"+_game.getCurPlayer().getPos());
        _gui.repaint();
    }

}
