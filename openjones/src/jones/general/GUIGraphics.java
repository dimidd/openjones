/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jones.general;

import java.util.HashMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import jones.map.GridTile;

/**
 *
 * @author dimid
 */
public class GUIGraphics {

    HashMap<String, Icon> _mapGraphics;

    public GUIGraphics() {
        _mapGraphics = new HashMap<>();
        _mapGraphics.put("Low-Cost Apartment", new javax.swing.ImageIcon(System.getProperty("user.dir") + "/images/lowcost.png"));
        _mapGraphics.put("rent", new javax.swing.ImageIcon(System.getProperty("user.dir") + "/images/rent.png"));
        _mapGraphics.put("pawn", new javax.swing.ImageIcon(System.getProperty("user.dir") + "/images/pawn.png"));
        _mapGraphics.put("lowcost_bot", new javax.swing.ImageIcon(System.getProperty("user.dir") + "/images/lowcost_bot2.png"));
        _mapGraphics.put("rent_bot", new javax.swing.ImageIcon(System.getProperty("user.dir") + "/images/rent_bot2.png"));
        _mapGraphics.put("pawn_bot", new javax.swing.ImageIcon(System.getProperty("user.dir") + "/images/pawn_bot.png"));
        _mapGraphics.put("zmarket", new javax.swing.ImageIcon(System.getProperty("user.dir") + "/images/zmart.png"));
        _mapGraphics.put("Monolith Burgers", new javax.swing.ImageIcon(System.getProperty("user.dir") + "/images/monolith.png"));
        _mapGraphics.put("QT clothing", new javax.swing.ImageIcon(System.getProperty("user.dir") + "/images/clothing.png"));
        _mapGraphics.put("clothing_left", new javax.swing.ImageIcon(System.getProperty("user.dir") + "/images/clothing_left.png"));
        _mapGraphics.put("socket_top", new javax.swing.ImageIcon(System.getProperty("user.dir") + "/images/socket_top.png"));
        _mapGraphics.put("SocketCity", new javax.swing.ImageIcon(System.getProperty("user.dir") + "/images/socket_bot.png"));
        _mapGraphics.put("HI-TECH U", new javax.swing.ImageIcon(System.getProperty("user.dir") + "/images/hitech_bot.png"));
        _mapGraphics.put("employment", new javax.swing.ImageIcon(System.getProperty("user.dir") + "/images/employment_bot.png"));
        _mapGraphics.put("factory", new javax.swing.ImageIcon(System.getProperty("user.dir") + "/images/factory_bot.png"));
        _mapGraphics.put("bank", new javax.swing.ImageIcon(System.getProperty("user.dir") + "/images/bank_bot.png"));
        _mapGraphics.put("black_top", new javax.swing.ImageIcon(System.getProperty("user.dir") + "/images/black_top.png"));
        _mapGraphics.put("black_right", new javax.swing.ImageIcon(System.getProperty("user.dir") + "/images/black_right.png"));
        _mapGraphics.put("black market", new javax.swing.ImageIcon(System.getProperty("user.dir") + "/images/black_bot.png"));
        _mapGraphics.put("Security Apartment", new javax.swing.ImageIcon(System.getProperty("user.dir") + "/images/security.png"));
        _mapGraphics.put("clock_bot", new javax.swing.ImageIcon(System.getProperty("user.dir") + "/images/clock_bot.png"));
        _mapGraphics.put("clock_top", new javax.swing.ImageIcon(System.getProperty("user.dir") + "/images/clock_top3.png"));
        _mapGraphics.put("employment_top", new javax.swing.ImageIcon(System.getProperty("user.dir") + "/images/employment_top.png"));
        _mapGraphics.put("hitech_top", new javax.swing.ImageIcon(System.getProperty("user.dir") + "/images/hitech_top3.png"));
        _mapGraphics.put("Wall", new javax.swing.ImageIcon(System.getProperty("user.dir") + "/images/Grass_small_blur.png"));


        for (int row = 1; row <= 3; ++row) {
            for (int col = 1; col <= 3; ++col) {
                //pos.setXY(col,row);
                int index = (5 * (row) + col);
                String str;
                if (index < 10) {
                    str = new String("test0" + index);
                } else {
                    str = new String("test" + index);
                }
                String filename = System.getProperty("user.dir") + "/images/" + str + ".png";
                ImageIcon imageIcon = new javax.swing.ImageIcon(filename);
                _mapGraphics.put(str, imageIcon);
            }
        }

    }

    public Icon getTileIcon(String name) {
        return _mapGraphics.get(name);
    }
}
