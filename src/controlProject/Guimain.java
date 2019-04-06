package controlProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

public class Guimain {

	private JFrame frame;
	private JTextField end;
	private JTextField start;
	private JTextField numbernodes;
	private JTextField startt;
	private JTextField endt;
	private JTextField gain;
	private JTextField gainn;
	public int [][]graph;
	public int numNodes;
	public int outputNode;
	public int inputNode;
	public FlowGraph flow;
	public Draw draw;
	public JPanel panel ;
	public int Numbranches=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Guimain window = new Guimain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Guimain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 919, 432);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		end = new JTextField();
		end.setBounds(361, 78, 129, 43);
		end.setColumns(10);
		frame.getContentPane().add(end);
		
		start = new JTextField();
		start.setBounds(138, 78, 129, 43);
		start.setColumns(10);
		frame.getContentPane().add(start);
		
		numbernodes = new JTextField();
		numbernodes.setBounds(138, 24, 129, 43);
		numbernodes.setColumns(10);
		frame.getContentPane().add(numbernodes);
		
		startt = new JTextField();
		startt.setBounds(92, 188, 103, 43);
		startt.setColumns(10);
		frame.getContentPane().add(startt);
		
		endt = new JTextField();
		endt.setBounds(243, 188, 103, 43);
		endt.setColumns(10);
		frame.getContentPane().add(endt);
		
		gain = new JTextField();
		gain.setBounds(376, 188, 103, 43);
		gain.setColumns(10);
		frame.getContentPane().add(gain);
		
		JButton button = new JButton("OK");
		button.setBounds(542, 188, 66, 43);
		button.setFont(new Font("Tahoma", Font.BOLD, 13));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    	int fromnode=Integer.parseInt(startt.getText());
			    	int tonode=Integer.parseInt(endt.getText());
			    	int gain1=Integer.parseInt(gain.getText());
			    	try{
			    		graph[fromnode-1][tonode-1]=gain1;
			    		Numbranches++;
			    	}catch(Exception e){
			    		JOptionPane.showMessageDialog(null, "the numbers  is wrong! try again");
			    	}
			    	startt.setText(null);
			    	endt.setText(null);
			    	gain.setText(null);
			}
		});
		button.setBackground(Color.PINK);
		frame.getContentPane().add(button);
		
		JButton btnNewButton = new JButton("Run");
		btnNewButton.setBounds(150, 247, 97, 51);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				    for(int i=0;i<graph.length;i++){
						if(graph[i][inputNode]!=0){
							JOptionPane.showMessageDialog(null, "the input node was wrong there node is go to here");
						}
						if(graph[outputNode][i]!=0){
							JOptionPane.showMessageDialog(null, "the output node was wrong there node is go to here");
						}
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "the number of input and output node is wrong");
				}	
			   flow=new FlowGraph();
				
				gainn.setText(Double.toString(flow.transferfun(graph, inputNode, outputNode)));
				System.out.println(flow.transferfun(graph, inputNode, outputNode));
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBackground(Color.MAGENTA);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnDraw = new JButton("Draw");
		btnDraw.setBounds(304, 247, 97, 51);
		btnDraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				draw=new Draw();
				draw.run(graph, Numbranches);
			}
		});
		btnDraw.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDraw.setBackground(Color.MAGENTA);
		btnDraw.setForeground(Color.BLACK);
		frame.getContentPane().add(btnDraw);
		
		JLabel lblNumberOfNodes = new JLabel("Number of Nodes");
		lblNumberOfNodes.setBounds(10, 36, 121, 29);
		lblNumberOfNodes.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(lblNumberOfNodes);
		
		JLabel lblStartPoint = new JLabel("Start Point");
		lblStartPoint.setBounds(10, 92, 112, 29);
		lblStartPoint.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(lblStartPoint);
		
		JLabel lblStartnode = new JLabel("StartNode ");
		lblStartnode.setBounds(99, 148, 97, 29);
		lblStartnode.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(lblStartnode);
		
		JLabel lblEndnode = new JLabel("EndNode");
		lblEndnode.setBounds(243, 148, 97, 29);
		lblEndnode.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(lblEndnode);
		
		JLabel lblGain = new JLabel("Gain");
		lblGain.setBounds(382, 148, 97, 29);
		lblGain.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(lblGain);
		
		JLabel en = new JLabel("End Point");
		en.setBounds(277, 92, 81, 29);
		en.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(en);
		
		gainn = new JTextField();
		gainn.setBounds(204, 337, 187, 45);
		gainn.setHorizontalAlignment(SwingConstants.LEFT);
		gainn.setEditable(false);
		frame.getContentPane().add(gainn);
		gainn.setColumns(10);
		
		JLabel label_1 = new JLabel("Gain");
		label_1.setBounds(110, 352, 66, 29);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		frame.getContentPane().add(label_1);
		
		JButton btnNewButton_1 = new JButton("Insert");
		btnNewButton_1.setBounds(500, 79, 89, 38);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 numNodes=Integer.parseInt(numbernodes.getText());
				 graph=new int[numNodes][numNodes];
				inputNode=Integer.parseInt(start.getText());
				inputNode--;
			   outputNode=Integer.parseInt(end.getText());
			  outputNode--;
			  numbernodes.setText(null);
			  start.setText(null);
			  end.setText(null);
			  
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(btnNewButton_1);
		
		JButton show = new JButton("Details");
		show.setBounds(453, 247, 121, 51);
		show.setFont(new Font("Tahoma", Font.BOLD, 13));
		show.setBackground(Color.MAGENTA);
		show.setForeground(Color.BLACK);
		show.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				LinkedList<LinkedList<Integer>> forward=flow.forwardpath;
				System.out.println(forward);
				LinkedList<LinkedList<Integer>> loops=flow.loops;
				System.out.println(forward);
				System.out.println(loops);
				String str="the forward path:";
				
				for(int i=0;i<forward.size()/2;i++){
					str+="(";
					for(int j=0;j<forward.get(i).size();j++){
						if(j!=0){
							str+=",";
						}
						str+=Integer.toString(forward.get(i).get(j)+1);
						
					}
					str+=")";
				}
				String strl="the looops:";
				for(int i=0;i<loops.size();i++){
					strl+="(";
					for(int j=0;j<loops.get(i).size();j++){
						if(j!=0){
							strl+=",";
						}
						strl+=Integer.toString(loops.get(i).get(j)+1);
						
					}
					strl+=")";
				}
				str+=" \n\r ";
				str+=strl;
				//JOptionPane.showMessageDialog(null,str);
				panel.setToolTipText(str);
				
			}
		});
		frame.getContentPane().add(show);
		
	     panel = new JPanel();
	     panel.setBorder(new LineBorder(Color.MAGENTA, 3, true));
		panel.setBackground(Color.WHITE);
		panel.setForeground(Color.PINK);
		panel.setBounds(631, 11, 262, 371);
		frame.getContentPane().add(panel);
	}
}
