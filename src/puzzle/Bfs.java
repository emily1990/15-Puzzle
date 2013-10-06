package puzzle;

import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;
public class Bfs {
	static Queue<State> open = new LinkedList<State>();
	static Queue<State> closed = new LinkedList<State>();
	static LinkedList<State> reconstructed = new LinkedList<State>();
	static ArrayList<String> direction = new ArrayList<String>();
	public static ArrayList<State> reconstruct = new ArrayList<State>();
	public static int[][] iniState=new int[4][4];
	public static int[][] goalState=new int[4][4];
	public static int a=1;
	public static int b=0;
	
	public static void main(String[] args){
		if(args.length > 0){
		Read read = new  Read(args[0]);
		iniState=read.convert(args[0]);
		Read read2 = new Read(args[1]);
		goalState=read2.convert(args[1]);
		}
		final int cols = 4;
		final int rows = 4;
		int[][] goalState={{0,1,2,3},{4,5,6,7},{8,9,10,11},{12,13,14,15}};
		State start = new State(rows, cols, iniState);
		State goal = new State(rows, cols, goalState);
	//	Bfs( start, goal);
		Bfs(start,goal);
		Dfs Dfs = new Dfs();
		Dfs.Dfs(start,goal);
		IDDFS iddfs = new IDDFS(start,goal);
		iddfs.search(start,goal);
		
		
		Room room_start = new Room(rows, cols, iniState);
		Room room_goal = new Room(rows, cols, goalState);
		Unicost unicost = new Unicost(room_start, room_goal);
		unicost.Search(room_start, room_goal);
		
		Greedy greedy = new Greedy(start,goal,"Man");
		greedy.greedysearch(start,goal,"Man");
		
		Astar astar = new Astar();
		astar.AstarSearch(room_start, room_goal, "Man");
		Greedy_myheuristic greedy_myheuristic = new Greedy_myheuristic(start,goal,"My");
		greedy_myheuristic.greedysearch(start,goal,"My");
		Astar astar1 = new Astar();
		astar1.AstarSearch(room_start, room_goal, "My");
		
	}
	
	public static void reconstruct(State current){
	do{
		reconstruct.add(current);
		current = current.getParent();
	}while(current != null);
	}

	public static void draw_reconstruct(){
	int state_count = 0;
	System.out.print("(((");
	for(int i = reconstruct.size()-2;i>=0;i--)
	{	
		System.out.print("\""+reconstruct.get(i).direction+"\"");
		
	}
	int move=reconstruct.size()-1;
	System.out.print(") "+ move+") ");
	//System.out.print(a);
	}
	public static void Bfs(State start, State goal) {
		start.depth=0;
		open.add(start);
		while(open.size() >0){
			if(a==500){
				System.out.println("Please wait patiently, this is hard for BFS");
			}
			if(a>10000){
				System.out.println("BFS cannot solve the problem in less than 10000 steps");
			break;
			}
		int flag=0;
		State current = open.poll();
		closed.add(current);
		for(int i = 0;i<4;i++){
			for(int j =0;j<4;j++){
				if(current.elements[i][j]!=goal.elements[i][j]) {
					flag = 1;
				}
			}
		}
		if(flag == 0){
			System.out.print("BFS:\n");
			reconstruct(current);
			draw_reconstruct();
			System.out.print(a+" ");
			System.out.print(b+" ");
			System.out.print(open.size()+" ");
			System.out.print(closed.size()+")");
			System.out.print("\n");
			break;
		}
		State moveUp = current.moveup();
		State moveDown = current.movedown();
		State moveRight = current.moveright();
		State moveLeft = current.moveleft();
		if(closed.contains(moveLeft)||closed.contains(moveUp)||closed.contains(moveDown)||closed.contains(moveRight))
			{ b++;}
		if(moveLeft != null){a++;}
		if(moveDown != null){a++;}
		if(moveUp != null){a++;}
		if(moveRight != null){a++;}
		if(moveLeft != null && !closed.contains(moveLeft)){
		
			open.add(moveLeft);
		}
		if(moveUp!=null && !closed.contains(moveUp)){

			open.add(moveUp);
		//	System.out.println("test:"+open.toString());
			for(int i =0;i<4;i++){
				for(int j = 0;j < 4;j++){
			//		System.out.print(moveUp.getElements()[i][j]);
				//	System.out.print(" ");
				}
			}
		//	System.out.print("\n");
		
			//break;
		}
		if(moveDown != null && !closed.contains(moveDown)){
			
			open.add(moveDown);
			
		}
			
		if(moveRight!=null && !closed.contains(moveRight)){
		
			open.add(moveRight);
			
		}
		
	}
	
	}
}
