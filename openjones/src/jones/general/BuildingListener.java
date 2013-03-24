/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.general;

import jones.map.Location;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
public class BuildingListener extends java.awt.event.MouseAdapter {

    private final int _row;
    private final int _col;
    private final Game _game;
    private final GUI _gui;

    public BuildingListener(int col, int row, Game game, GUI gui) {
        super();
        _row = row;
        _col = col;
        _game = game;
        _gui = gui;
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        //System.out.println("before:"+_game.getCurPlayer().getPos());
        
        PlayerPosition ppos = new PlayerPosition(0, 0, false);
        updateMapPanelBeforeMoving(_game, _gui);      
      
        //move to new position
        ppos.setXY(_col, _row);
        ppos.enterBuilding();        
        _game.movePlayer(ppos);
        
        //System.out.println("after:"+_game.getCurPlayer().getPos());
        _gui.repaint();
    }
    
    
    public static void updateMapPanelBeforeMoving(Game game, GUI gui) {
               PlayerPosition ppos = new PlayerPosition(0, 0, false);
        
        //remove player text from old position
        PlayerPosition curPos = game.getCurPlayer().getPos();
        javax.swing.JButton butt = gui.getButtons()[curPos.getY()][curPos.getX()];        
        Location tile = (Location) game.getMap().getGrid().get(curPos);        
        butt.setText(tile.toString());        

    }
}
