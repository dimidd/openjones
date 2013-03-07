/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.general;

/**
 * An announcement for the player.
 * e.g. "Rent is due", "Your food has spoiled" etc.
 *
 * @author dimid
 */
public class GameAnnouncement {
    public String _msg;
    
    public GameAnnouncement (String msg) {
        _msg = msg;
    }
}
