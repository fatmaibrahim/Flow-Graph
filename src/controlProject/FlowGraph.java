package controlProject;

import java.util.LinkedList;

public class FlowGraph {

	public LinkedList<LinkedList<Integer>> forwardpath=new LinkedList<LinkedList<Integer>>();
	public LinkedList<LinkedList<Integer>> loops=new LinkedList<LinkedList<Integer>>();
	public LinkedList<LinkedList<Integer>> nontouchloop=new LinkedList<LinkedList<Integer>>();
	private int [][]graph;
	private int inputNode;
	private int outputNode;
	
	public double transferfun(int graph[][], int inputNode, int outputNode) {
		this.graph = graph;
		this.inputNode = inputNode;
		this.outputNode = outputNode;
		LinkedList<Integer> list = new LinkedList<Integer>();
		intializeforwardpath(this.inputNode, list);
		removedublicatenode();
		list = new LinkedList<Integer>();
		for (int i = 0; i < loops.size(); i++) {
			createnontouchloop(i, loops.size(), list, loops, nontouchloop);
			list = new LinkedList<Integer>();
		}
		int d = calculatethegain(this.nontouchloop,this.loops);
		double gain = 0;
		for (int i = 0; i < forwardpath.size(); i++) {
			LinkedList<LinkedList<Integer>> loops = new LinkedList<LinkedList<Integer>>();
			LinkedList<LinkedList<Integer>> nontouchloop = new LinkedList<LinkedList<Integer>>();
			for (int j = 0; j < this.loops.size(); j++) {
				if (!isconnect(forwardpath.get(i), this.loops.get(j))) {
					loops.add(this.loops.get(j));
				}
			}
			list = new LinkedList<Integer>();
			for (int k = 0; k < loops.size(); k++) {
				createnontouchloop(k, loops.size(), list, loops, nontouchloop);
				list = new LinkedList<Integer>();
			}
			int di = calculatethegain(nontouchloop,loops);
			int mul = 1;
			for (int u = 0; u < forwardpath.get(i).size() - 1; u++) {
				mul *= this.graph[forwardpath.get(i).get(u)][forwardpath.get(i).get(u+1)];
			}

			gain += di * mul;
		}
		gain /= d;

		return gain;

	}
	
    private void removedublicatenode() {
		int [] removenum=new int[loops.size()];
		for(int i=0;i<loops.size();i++){
			for(int j=i+1;j<loops.size();j++){
				if(comp(loops.get(i),loops.get(j))){
					removenum[j]=1;
				}
			}
		}
		for(int i=removenum.length-1;i>=0;i--){
			if(removenum[i]==1){
				loops.remove(i);
			}
		}
		
	}

	

	private boolean comp(LinkedList<Integer> list1, LinkedList<Integer> list2) {
		if (list1.size() != list2.size()) {
			return false;
		}
		for (int i = 0; i < list1.size(); i++) {
			boolean flag = true;
			for (int j = 0; j < list2.size(); j++) {
				if (list1.get(i) == list2.get(j)) {
					flag = false;
				}
			}
			if (flag) {
				return false;
			}
			flag = true;
		}
		return true;
	}

	private int calculatethegain(LinkedList<LinkedList<Integer>> nontouchloop,LinkedList<LinkedList<Integer>> loops){
    	LinkedList<LinkedList<Integer>> newnontouches=new LinkedList<LinkedList<Integer>>();
    	boolean flag=true;
    	int counter=1;
    	while(flag){
    	  boolean flagcounter=false;
    		for(int i=0;i<nontouchloop.size();i++){
    		   if(nontouchloop.get(i).size()==counter){
    			   LinkedList<Integer> temp=new LinkedList<Integer>();
    			   temp.addAll(nontouchloop.get(i));
    			   newnontouches.add(temp);
    			   flagcounter=true;
    		   }
    	   }
    		if(flagcounter){
    			counter++;
    		}else{
    			flag=false;
    		}
    	}
    	
		int d=1;
		int flag1=1;
		int tempd=0;
		for(int i=0;i<newnontouches.size();i++){
			LinkedList<Integer> temp=new LinkedList<Integer>();
			temp.addAll(newnontouches.get(i));
			int flag2=temp.size();
			if(flag1==flag2){
				tempd+=multiple(temp,loops);
			}else{
				if(flag1%2==1){
					d-=tempd;
				}else{
				  d+=tempd;	
				}
				flag1++;
				i--;
				tempd=0;
			}
			
		}
		if(flag1%2==1){
			d-=tempd;
		}else{
		  d+=tempd;	
		}
		return d;
    }
	private int multiple(LinkedList<Integer> temp,LinkedList<LinkedList<Integer>> loops) {
		int mul=1;
		for(int i=0;i<temp.size();i++){
			int x=temp.get(i);
			for(int j=0;j<loops.get(x).size()-1;j++){
				mul*=graph[loops.get(x).get(j)][loops.get(x).get(j+1)];
			}
			
		}
		return mul;
	}


	private void createnontouchloop(int loopnum,int n,LinkedList<Integer> list,LinkedList<LinkedList<Integer>> loops,LinkedList<LinkedList<Integer>> nontouchloop) {
		list.add(loopnum);
		if(!checkdisconect(list,loops)){
			return;
		}
		int index=list.size()-1;
		LinkedList<Integer> temp=new LinkedList<Integer>();
		for(int i=0;i<list.size();i++){
			temp.add(list.get(i));
		}
		nontouchloop.add(index,temp);
		for(int i=loopnum+1;i<n;i++){
			createnontouchloop(i,n,list,loops,nontouchloop);
			list.removeLast();
		}
		
	}

    boolean checkdisconect(LinkedList<Integer> list,LinkedList<LinkedList<Integer>> loops){
    	int x=list.get(list.size()-1);
    	for(int i=0;i<list.size()-1;i++){
    			if(isconnect(loops.get(list.get(i)),loops.get(x))){
    				return false;
    			}
    	}
    	return true;
    }
    
    boolean isconnect(LinkedList<Integer> list1,LinkedList<Integer> list2){
    	for(int i=0;i<list1.size();i++){
    		for(int j=0;j<list2.size();j++){
    			if(list1.get(i)==list2.get(j)){
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
	private void intializeforwardpath(int initialpoint,LinkedList<Integer> list){
		list.add(initialpoint);
		LinkedList<Integer> checklist=checkifloop(list);
		if(!checklist.isEmpty()){
			loops.add(checklist);
			return;
		}
		if(initialpoint==outputNode){
			LinkedList<Integer> n=new LinkedList<Integer>();
			for(int i=0;i<list.size();i++){
				n.add(list.get(i));
			}
			forwardpath.add(n);
			return;
		}	
         for(int i=0;i<graph.length;i++){
        	 if(graph[initialpoint][i]!=0){
        		 intializeforwardpath(i,list);
        		 list.removeLast();
        	 }
        	 
         }
		
	}
	private LinkedList<Integer> checkifloop(LinkedList<Integer> list){
		int element=list.getLast();
		LinkedList<Integer> listnew=new LinkedList<Integer>();
		for(int i=0;i<list.size()-1;i++){
		    if(list.get(i)==element){
		    	listnew.addAll(list.subList(i,list.size()));
		    	return listnew;
		    }
		}
		return listnew;
	}

	
}
