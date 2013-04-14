package jones.general;


import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JComponent;

class MyGlassPane extends JComponent
                  
{
    Point point;
 
    
 
    @Override
    protected void paintComponent(Graphics g) {
        if (point != null) {
            g.setColor(Color.red);
            g.fillOval(point.x , point.y , 20, 20);
        }
    }
 
    public void setPoint(Point p) {
        point = p;
    }
 
    public MyGlassPane(Container contentPane) {
        
    }
}