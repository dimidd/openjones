/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.general;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import jones.actions.Action;
import jones.actions.SubMenuAction;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
class BuildingActionListener extends java.awt.event.MouseAdapter{
    private final int _actionID;
    private final Game _game;
    private final GUI _gui;
    private final JLabel _label;
    private final Action _action;

    BuildingActionListener(Game game, GUI gui, int a, JLabel label, Action action) {
                
        super();
        _actionID =a;
        _game = game;
        _gui = gui;
        _label = label;
        _action = action;

    }
    final Object lock = new Object();
       
    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
    //    try {
            
            //System.out.println("before:"+_game.getCurPlayer().getPos());
            _game.performBuildingAction(_actionID, _gui.getPossibletActions());
            if (_action instanceof SubMenuAction) {
                _gui.setLastSelectedBuildingActionIndex(-1);
            }
            else {
                _gui.setLastSelectedBuildingActionIndex(_actionID);
            }
//            Color background = _label.getBackground();
//            Color foreground = _label.getForeground();
//            _label.setForeground(background);
//            _gui.repaint();
//            synchronized(lock) {
//                
//                lock.wait(5000);
//            }
//            _label.setForeground(foreground);
             _gui.repaint();
//        } catch (InterruptedException ex) {
//            Logger.getLogger(BuildingActionListener.class.getName()).log(Level.SEVERE, null, ex);
//        }
//       
        
    }

}
