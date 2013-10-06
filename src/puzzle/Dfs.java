package puzzle;
import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Pattern;
public class Dfs {
	static Stack<State> open = new Stack<State>();
	static Queue<State> closed = new LinkedList<State>();
	static LinkedList<State> reconstructed = new LinkedList<State>();
	//static ArrayList<String> direction = new ArrayList<String>();
	public static ArrayList<State> reconstruct = new ArrayList<State>();
	//public static int[][] iniState=new int[4][4];
	public static int a=1;
	public static int b=0;
	

	public static void reconstruct(State current){
	do{
		reconstruct.add(current);
		current = current.getParent();
	}while(current != null);
	}

	public static void draw_reconstruct(){
	
	System.out.print("(((");
	for(int i = reconstruct.size()-2;i>=0;i--)
	{	
		System.out.print("\""+reconstruct.get(i).direction+"\"");
		
	}
	int move=reconstruct.size()-1;
	System.out.print(") "+ move+") ");
	//System.out.print(a);
	}
	public static void Dfs(State start, State goal) {
		open.push(start);
		
		while(open.size() >0){
			if(a==500){
				System.out.println("DFS Wait......");
			}
			if(a>3000){
				System.out.println("DFS cannot solve the problem in less than 3000 steps");
			break;
			}
		int flag=0;
		State current = open.pop();
		closed.add(current);
		for(int i = 0;i<4;i++){
			for(int j =0;j<4;j++){
				if(current.elements[i][j]!=goal.elements[i][j]) {
					flag = 1;
				}
			//	System.out.println("goal:"+goal.getElements()[i][j]);
				//System.out.println("current:"+current.getElements()[i][j]);
				//System.out.println();
			}
		}
		if(flag == 0){
			OutPut output = new OutPut();
			System.out.print("DFS:\n");
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
		
		if(moveUp!=null && !closed.contains(moveUp)){

			open.push(moveUp);
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
			
			open.push(moveDown);
		//	System.out.println("test:"+open.element());
		
			for(int i =0;i<4;i++){
				for(int j = 0;j < 4;j++){
				//	System.out.print(moveDown.getElements()[i][j]);
					//System.out.print(" ");
				}
				
			
		}//System.out.print("\n");
			
		}
			
		if(moveRight!=null && !closed.contains(moveRight)){
		
			open.push(moveRight);
			for(int i =0;i<4;i++){
				for(int j = 0;j < 4;j++){
		//			System.out.print(moveRight.getElements()[i][j]);
			//	System.out.print(" ");
				}
				
			
		}//System.out.print("\n");
			
		}
		if(moveLeft != null && !closed.contains(moveLeft)){
			
			open.push(moveLeft);
			
			for(int i =0;i<4;i++){
				for(int j = 0;j < 4;j++){
			//	System.out.print(moveLeft.getElements()[i][j]);
				//	System.out.print(" ");
				}
				
			
		}//System.out.print("\n");
		}
		
	}
	
	}
}
