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
public class buildingListener extends java.awt.event.MouseAdapter {

    private final int _row;
    private final int _col;
    private final Game _game;
    private final GUI _gui;

    public buildingListener(int col, int row, Game game, GUI gui) {
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
        
        //remove player text from old position
        PlayerPosition curPos = _game.getCurPlayer().getPos();
        javax.swing.JButton butt = _gui.getButtons()[curPos.getY()][curPos.getX()];        
        Location tile = (Location) _game.getMap().getGrid().get(curPos);        
        butt.setText(tile.toString());

       
      
        //move to new position
        ppos.setXY(_col, _row);
        ppos.enterBuilding();        
        _game.movePlayer(ppos);
        
        //System.out.println("after:"+_game.getCurPlayer().getPos());
        _gui.repaint();
    }
    
}
