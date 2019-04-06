package controlProject;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;

public class DrawArc extends JComponent {
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setStroke(new BasicStroke(2.0f));
        g2.setPaint(Color.RED);
        g2.drawOval(100,500, 10, 10);
        g2.drawString("1", 100, 500+30);
        g2.drawOval(200,500, 10, 10);
        g2.drawString("2", 200, 500+30);
       
        System.out.println(getWidth());
        System.out.println(getHeight());
        g2.setPaint(Color.BLACK);
        int[] xPoints = {100, 100-5, 100+5};
        int[] yPoints = {500+5, 500-5, 500-5};
        g2.fillPolygon(xPoints, yPoints, 3);
        g2.setPaint(Color.BLUE);
        QuadCurve2D.Double cubicCurve = new QuadCurve2D.Double( 100,500,150,900 , 200, 500);
        g2.draw(cubicCurve); 
        Rectangle2D  rec=cubicCurve.getBounds2D();
        System.out.println(rec);
        System.out.println();
        g2.drawString("-1",(int)(rec.getX()+rec.getWidth()/2),(int)(rec.getHeight()/2+rec.getY()));
        
        
        
        
        
        
//        g2.setPaint(Color.GREEN);
//        g2.draw(new Arc2D.Double(50, 50, 300, 300, 90, 180, Arc2D.PIE));
//        g2.setPaint(Color.BLUE);
//        g2.fill(new Arc2D.Double(50, 50, 300, 300, 180, 0, Arc2D.PIE));
        g2.setPaint(Color.BLACK);
        g2.drawOval(150, 350, 10, 10);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Draw Arc Demo");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(new DrawArc());
        frame.pack();
        frame.setSize(new Dimension(2000, 2000));
        frame.setVisible(true);
    }
}