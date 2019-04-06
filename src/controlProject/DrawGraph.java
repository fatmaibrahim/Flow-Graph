package controlProject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class DrawGraph extends JComponent {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int [][] drawing;
	int n=0;
    DrawGraph(int [][] drawing,int n) {
        super();
        setPreferredSize(new Dimension(1000,1000));
        this.drawing=drawing;
       this.n=n;
    }


    public void paint(Graphics g) {
    	Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        int w=getWidth()/drawing.length;
        g2.setPaint(Color.magenta);
        g2.setFont(new Font("Tahoma", Font.BOLD, 14));
        for(int i=0;i<n;i++){
        	g2.fillOval((i*w),(int)(getHeight()/2-10), 10, 10);
        	g2.drawString(Integer.toString(i+1), (i*w), (getHeight()/2-10)+40);
        }
        g2.setStroke(new BasicStroke(2.0f));
        for (int i=0;i<drawing.length;i++) {
           if(drawing[i][3]==0){
        	   g2.setPaint(Color.gray);
        	   g2.drawLine(
                (int)(drawing[i][0]*w),
                (int)(getHeight()/2),
                (int)(drawing[i][1]*w),
                (int)(getHeight()/2)
                );
        	   g2.drawString(Integer.toString(drawing[i][2]), (drawing[i][0]*w+drawing[i][1]*w)/2, getHeight()/2+15);
        	   g2.setPaint(Color.BLACK);
               int[] xPoints = {((drawing[i][0]*w+drawing[i][1]*w)/2), ((drawing[i][0]*w+drawing[i][1]*w)/2)-5, ((drawing[i][0]*w+drawing[i][1]*w)/2)-5};
               int[] yPoints = {(getHeight()/2), (getHeight()/2)-5, (getHeight()/2)+5};
               g2.fillPolygon(xPoints, yPoints, 3);
           }else if(drawing[i][3]==1){
        	   g2.setPaint(Color.orange);
 			 double h =(drawing[i][1]+drawing[i][0])/2;
 			 QuadCurve2D.Double cubicCurve = new QuadCurve2D.Double(
                       (drawing[i][0]*w),
                       (getHeight()/2),
                       (h*w),
                       (getHeight()/2-((h-drawing[i][0])*w)),(drawing[i][1]*w),(getHeight()/2)); 
 			g2.draw(cubicCurve); 
            Rectangle2D  rec=cubicCurve.getBounds2D();
            double x1=rec.getX()+rec.getWidth()/2;
            double y1=rec.getHeight()/2+rec.getY();
 			 g2.drawString(Integer.toString(drawing[i][2]), (int) x1, (int) y1+15);
 			g2.setPaint(Color.BLACK);
            int[] xPoints = {(int) x1, (int) (x1-5),(int) (x1-5)};
            int[] yPoints = {(int)y1, (int) y1-5, (int) y1+5};
            g2.fillPolygon(xPoints, yPoints, 3);
           }else{
        	   g2.setPaint(Color.pink);
        	   double h =(drawing[i][1]+drawing[i][0])/2;
        	   QuadCurve2D.Double cubicCurve = new QuadCurve2D.Double(
                          (drawing[i][0]*w),
                          (getHeight()/2),
                          (h*w),
                          (getHeight()/2+(drawing[i][0]*w-h*w)),(drawing[i][1]*w),(getHeight()/2)); 
        	   g2.draw(cubicCurve); 
               Rectangle2D  rec=cubicCurve.getBounds2D();
               double x1=rec.getX()+rec.getWidth()/2;
               double y1=rec.getHeight()/2+rec.getY();
    			 g2.drawString(Integer.toString(drawing[i][2]), (int) x1, (int) y1+15);
    			 g2.setPaint(Color.BLACK);
                 int[] xPoints = {(int) x1, (int) (x1+5), (int) (x1+5)};
                 int[] yPoints = {(int) y1, (int) y1+5, (int)y1-5};
                 g2.fillPolygon(xPoints, yPoints, 3);
        }
           
    }
  }
}