import java.util.*;
public class State{
	int jug4, jug3; //each state has two jug.
	State parent = null; //this state's parent
	String whatAct = ""; //do what action from previous state to this state.
	int level;	
	public State(int j4, int j3, State parent, String act, int l){
		this.level = l;
		this.jug4 = j4;
		this.jug3 = j3;
		this.parent = parent;
		this.whatAct = act;
	}	
}
