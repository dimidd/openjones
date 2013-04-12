/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.general;

import java.util.HashMap;
import javax.swing.Icon;
import jones.map.GridTile;

/**
 *
 * @author dimid
 */
public class GUIGraphics {

    HashMap<String, Icon> _mapGraphics;

    public GUIGraphics() {
        _mapGraphics = new HashMap<>();
        _mapGraphics.put("Low-Cost Apartment", new javax.swing.ImageIcon("/home/dimid/NetBeansProjects/openjones/openjones/images/lowcost.png"));
        _mapGraphics.put("rent", new javax.swing.ImageIcon("/home/dimid/NetBeansProjects/openjones/openjones/images/rent.png"));
        _mapGraphics.put("pawn", new javax.swing.ImageIcon("/home/dimid/NetBeansProjects/openjones/openjones/images/pawn.png"));
        _mapGraphics.put("zmarket", new javax.swing.ImageIcon("/home/dimid/NetBeansProjects/openjones/openjones/images/zmart.png"));
        _mapGraphics.put("Monolith Burgers", new javax.swing.ImageIcon("/home/dimid/NetBeansProjects/openjones/openjones/images/monolith.png"));
        _mapGraphics.put("QT clothing", new javax.swing.ImageIcon("/home/dimid/NetBeansProjects/openjones/openjones/images/gt.png"));
        _mapGraphics.put("socket_top", new javax.swing.ImageIcon("/home/dimid/NetBeansProjects/openjones/openjones/images/socket_top.png"));
        _mapGraphics.put("SocketCity", new javax.swing.ImageIcon("/home/dimid/NetBeansProjects/openjones/openjones/images/socket_bot.png"));
        _mapGraphics.put("HI-TECH U", new javax.swing.ImageIcon("/home/dimid/NetBeansProjects/openjones/openjones/images/hitech.png"));
        _mapGraphics.put("employment", new javax.swing.ImageIcon("/home/dimid/NetBeansProjects/openjones/openjones/images/employment.png"));
        _mapGraphics.put("factory", new javax.swing.ImageIcon("/home/dimid/NetBeansProjects/openjones/openjones/images/factory.png"));
        _mapGraphics.put("bank", new javax.swing.ImageIcon("/home/dimid/NetBeansProjects/openjones/openjones/images/bank_bot.png"));
        _mapGraphics.put("black_top", new javax.swing.ImageIcon("/home/dimid/NetBeansProjects/openjones/openjones/images/black_top.png"));
        _mapGraphics.put("black market", new javax.swing.ImageIcon("/home/dimid/NetBeansProjects/openjones/openjones/images/black_bot.png"));
        _mapGraphics.put("Security Apartment", new javax.swing.ImageIcon("/home/dimid/NetBeansProjects/openjones/openjones/images/security.png"));
        _mapGraphics.put("clock", new javax.swing.ImageIcon("/home/dimid/NetBeansProjects/openjones/openjones/images/clock.png"));
    }

    public Icon getTileIcon(String name) {
        return _mapGraphics.get(name);
    }
}
