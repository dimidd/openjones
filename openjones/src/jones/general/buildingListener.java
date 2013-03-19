/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.general;

/**
 *
 * @author dimid <dimidd@gmail.com>
 */
public class buildingListener extends java.awt.event.MouseAdapter {

    private final int _row;
    private final int _col;
    private final Game _game;

    public buildingListener(int col, int row, Game game) {
        super();
        _row = row;
        _col = col;
        _game = game;
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        System.out.println("before:"+_game.getCurPlayer().getPos());
        
        PlayerPosition ppos = new PlayerPosition(0, 0, false);
        ppos.setXY(_col, _row);
        ppos.enterBuilding();
        _game.movePlayer(ppos);
        
        System.out.println("after:"+_game.getCurPlayer().getPos());

    }
}
