import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Set;
import java.util.HashSet;

public class Search{
	// action description
	String[] act = {"Full jug4", "Full jug3", "Empty jug4"
					, "Empty jug3", "Pull jug4 to jug3"
					, "Pull jug3 to jug4"}; 
	public int levelLimit;
	public boolean reach = false; // used to represent whether it reaches the goal or not
	public int cost = 0; // used to represent the node which has been expened
	public ArrayList list = new ArrayList(); // uesd to store the node
	// set the initial state	
	public State initialState = new State(0, 0, null, "initialize", 0); 
	// declare action group which contains six actions and it will return a new state
	public State action(State s, int a){
		State tempState = null;
		switch(a){
			case 1:
				if(!(isFull_4(s.jug4))){
					tempState = new State(4, s.jug3, s, act[0], s.level + 1);
					cost++;
					break;
				}
				else break;
			case 2:
				if(!(isFull_3(s.jug3))){
					tempState = new State(s.jug4, 3, s, act[1], s.level + 1);
					cost++;
					break;
				}
				else break;
			case 3:
				if(!(isEmpty(s.jug4))){
					tempState = new State(0, s.jug3, s, act[2], s.level + 1);
					cost++;
					break;
				}
				else break;
			case 4:
				if(!(isEmpty(s.jug3))){
					tempState = new State(s.jug4, 0, s, act[3], s.level + 1);
					cost++;
					break;
				}
				else break;
			case 5:
				if( !(isEmpty(s.jug4)) && !(isFull_3(s.jug3))){
					int temp = 3 - s.jug3;
					if(s.jug4 > temp){
						int pull = s.jug4 - temp;
						tempState = new State(pull, 3, s, act[4], s.level + 1);
						cost++;
						break;
					}
					else{
						int pull = s.jug3 + s.jug4;
						tempState = new State(0, pull, s, act[4], s.level + 1);
						cost++;
						break;
					}
				}
				else break;
			case 6:
				if(!(isEmpty(s.jug3)) && !(isFull_4(s.jug4))){
					int temp = 4 - s.jug4;
					if(s.jug3 > temp){
						int pull = s.jug3 - temp;
						tempState = new State(4, pull, s, act[5], s.level + 1);
						cost++;
						break;
					}
					else{
						int pull = s.jug4 + s.jug3;
						tempState = new State(pull, 0, s, act[5], s.level + 1);
						cost++;
						break;
					}
				}
				else break;
		}
		return tempState;
	}

   	// define breath first search which is used to find the solution 	
	public void bfs(){ 
		Queue q = new LinkedList();
	   	q.add(this.initialState);
		list.add(this.initialState);
		while(!q.isEmpty()){
			State curState = (State)q.remove();
			for(int i = 0; i < 6 ; i++){
				State newState = action(curState, i + 1);
				if(newState != null){
					//if new state is not null and equals goal state then we can say that we find the solution.
					if(isGoal(newState)){ 
						getPath(newState); 
						//reach = true;
					}
					// else we insert the new state to the queue.
					else if(!isExist(newState)){
						q.add(newState);
					}
				}
			}
		}
		// if(!reach) System.out.print("No solution found");
		// reset the cost to zero
		cost = 0;	
		// reset the reach to false
		reach = false;
		list.clear();
	}

	public void dfs(){
		Stack s = new Stack();
		s.push(this.initialState);
		list.add(this.initialState);
		while(!s.empty()){
			State curState = (State)s.pop();
			if(isGoal(curState)){
				getPath(curState);
				reach = true;
			}
		   	else if(curState.level < levelLimit){	
				for(int i = 0; i < 6; i++){
					State newState = action(curState, i + 1);
					if(newState != null && !isExist(newState)){
						s.push(newState);
					}
				}	
			}
		}
		if(!reach) System.out.println("When levelLimit is: " 
						+ this.levelLimit + ", No solution found");
		// reset cost to zero
		cost = 0;
		list.clear();
	}

	public boolean isGoal(State s){
		if(s.jug4 == 2) return true;
		else return false;
	}
	public boolean isEmpty(int jug){
		if(jug == 0) return true;
		else return false;
	}
	public boolean isFull_4(int jug){
		if(jug == 4) return true;
		else return false;
	}	
	public boolean isFull_3(int jug){
		if(jug == 3) return true;
		else return false;
	}
	public void getPath(State s){
		String str = "";
		while(s != null){
			str = s.whatAct + "," + "(" + Integer.toString(s.jug4) + "," 
					+ Integer.toString(s.jug3) + ")" + "\n" + str;
			s = s.parent; 
		}
		System.out.println(str + "cost is: " + cost);
	}
	public boolean isExist(State s){
		for(int i = 0; i < list.size(); i++){
			State tempS =(State)list.get(i);
			if(tempS.jug3 == s.jug3 && tempS.jug4 == s.jug4) return true;
		}
		list.add(s);	
		return false;		
	}
}
