package controlProject;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Draw {
	
	
	public void run(int [][]graph,int Numbranches){
		 int [][] drawing=new int [Numbranches][4];
         int counter=0;
          for(int i=0;i<graph.length;i++){
        	  for(int j=0;j<graph.length;j++){
        		  if(graph[i][j]!=0){
        			  drawing[counter][0]=i;
        			  drawing[counter][1]=j;
        			  drawing[counter][2]=graph[i][j];
        			  if((j-i)==1){
        				  drawing[counter][3]=0;
        			  }else if((j-i)>1){
        				  drawing[counter][3]=1;
        			  }else{
        				  drawing[counter][3]=-1; 
        			  }
        			  counter++; 
        		  }
        	  }
          }
          DrawGraph paint=new DrawGraph(drawing,graph.length);
          JFrame frame = new JFrame("Draw Arc Demo");
          frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
          frame.getContentPane().add(paint);
          frame.pack();
          frame.setSize(new Dimension(1000, 1000));
          frame.setVisible(true);
          paint.repaint();
	}
	
	
	

}
