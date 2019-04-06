package controlProject;

import java.awt.Dimension;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {

	private static Scanner scan;

	public static void main(String[] args) {
		scan = new Scanner(System.in);
		System.out.println("Enter the number of nodes");
		int numNodes=scan.nextInt();
		int [][]graph=new int[numNodes][numNodes];
		System.out.println("Enter tne node of input");
		int inputNode=scan.nextInt();
		inputNode--;
		System.out.println("Enter the node of output");
		int outputNode=scan.nextInt();
		outputNode--;
		System.out.println("Enter the number branches");
		int Numbranches=scan.nextInt();
	    for(int i=0;i<Numbranches;i++){
	    	System.out.println("Enter branches from (node1) to (node2) (gain) ");
	    	int fromnode=scan.nextInt();
	    	int tonode=scan.nextInt();
	    	int gain=scan.nextInt();
	    	try{
	    		graph[fromnode-1][tonode-1]=gain;
	    	}catch(Exception e){
	    		System.out.println("the number of node is wrong Enter the number again");
	    		i--;
	    	}
	    }
		try{
	    for(int i=0;i<graph.length;i++){
			if(graph[i][inputNode]!=0){
				System.out.println("the input node was wrong there node is go to here");
			}
			if(graph[outputNode][i]!=0){
				System.out.println("the output node was wrong there node is go to here");
			}
		}
	}catch(Exception e){
		System.out.println("the number of input and output node is wrong");
	}		
		 
		FlowGraph flow=new FlowGraph();
		
		System.out.println(flow.transferfun(graph, inputNode, outputNode));
		
		
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
