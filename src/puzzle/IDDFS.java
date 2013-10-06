package puzzle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class IDDFS {
	
	
	static LinkedList<State> reconstructed = new LinkedList<State>();
	public static ArrayList<State> reconstruct = new ArrayList<State>();
	public static int a=1;
	public static int b=0;
	public static Queue<State> globle_visited = new LinkedList<State>();
	
	public IDDFS(final State start, final State goal){
		

		}
	public void search(State start, State goal){
		int depth=0;
		Boolean flag = false;
		while (flag == false){
			State start1 = start;
			State goal1 = goal;
			
			flag = bfs_dfs(start1,goal1,depth);
		    depth +=1;
		}
	}
	
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
	
		
	public Boolean bfs_dfs(State start, State goal, int depth){
		//int a=1;
		Boolean found = false;
		int max_depth = 0;
	    Stack<State> open = new Stack<State>();
	    Queue<State> closed = new LinkedList<State>();
		open.push(start);
		start.depth = 0;
	//	closed.add(start);
		while(open.size()>0){
			if(a==500){
				System.out.println("Please wait patiently, this is hard for IDDFS");
			}
			if(a>10000){
				System.out.println("IDDFS cannot solve the problem in less than 10000 steps");
				found=true;
				break;
			}
			State current = open.pop();
			closed.add(current);
			globle_visited.add(current);
			if(current.depth > max_depth);
			max_depth = current.depth;
			if(current != goal && current.depth < depth){
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
					
				}
					
				if(moveRight!=null && !closed.contains(moveRight)){
				
					open.push(moveRight);
					
				}
				if(moveLeft != null && !closed.contains(moveLeft)){
					
					open.push(moveLeft);
				}
			}
			else if(current.equals(goal) ){
				found = true;
				System.out.print("IDDFS:\n");
				reconstruct(current);
				draw_reconstruct();
				System.out.print(a+" ");
				System.out.print(b+" ");
				System.out.print(open.size()+" ");
				System.out.print(globle_visited.size()+")");
				System.out.print("\n");
				break;
			}
			}
		
		
		return found;
	}
}
