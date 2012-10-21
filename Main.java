import java.lang.System; 
public class Main{
	public static void main(String[] args){
		Search s = new Search(); // Create a Search object
		System.out.println("Use BFS to solve the problem: ");
		long time = System.currentTimeMillis(); // get the System time
		s.bfs();
		time = System.currentTimeMillis() - time;
		System.out.println("time: " + time + "ms");
		System.out.println("==================================================");
		System.out.println("Use IDS(Iterative Deeping Search) to solve the problem: ");
		time = System.currentTimeMillis(); // get the System time
		// while it's not reaching the goal then keep incresing the depth
		while(!s.reach){
			s.dfs();
			s.levelLimit++;
		}		
		time = System.currentTimeMillis() - time;
		System.out.println("time: " + time + "ms");
		
	}
}
