package puzzle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Astar {
	LinkedList<Room> reconstructed = new LinkedList<Room>();
	ArrayList<Room> reconstruct = new ArrayList<Room>();

	 Queue<Room> open = new LinkedList<Room>();
	 Queue<Room> closed = new LinkedList<Room>();
	
	
	public Astar(){
	}
	
	public void AstarSearch(final Room start, final Room goal,String string){
		reconstructed = new LinkedList<Room>();
		ArrayList<Room> reconstruct = new ArrayList<Room>();
		int a=1;
		 int b=0;
		open = new LinkedList<Room>();
		closed = new LinkedList<Room>();
		open.add(start);
		start.setCost(0);
		while(open.size()>0){
			if(a==500){
				System.out.println("Please wait patiently, this is hard for A*");
			}
			if(a>30000){
				if(string=="Man"){
				System.out.println("A*(Mahanttan)  cannot solve the problem in less than 30000 steps");
				}
				else{
					System.out.println("A*(Myheuristic)  cannot solve the problem in less than 30000 steps");
					}
					break;
			}
			Room min_Room=open.peek();
			Iterator<Room> i = open.iterator();
			//find the minimum fhat Room
			while(i.hasNext()){
				Room current = i.next();
				if(string == "Mis")
				{	
					int fhat = 0;
					int min_fhat = 0;
				
					int hhat = MisplacedTiles(current, goal);
					int ghat = current.getCost();
					fhat = hhat + ghat;
					min_fhat = MisplacedTiles(min_Room, goal)+current.getCost();
					if( fhat < min_fhat){
					min_Room = current;
				}
				}
				if(string == "Man")
				{	//if(a==30000){
					//System.out.println("A* (Manhattan) cannot solve the problem in less than 30000 steps");
					//break;
					//}
					int fhat = 0;
					int min_fhat = 0;
				
					int hhat = Manhanttan(current, goal);
					int ghat = current.getCost();
					fhat = hhat + ghat;
					min_fhat = Manhanttan(min_Room, goal)+current.getCost();
					if( fhat < min_fhat){
					min_Room = current;
				}
				}
				if(string == "My")
				{//if(a==30000){
					//System.out.println("A* (Myheuristics) cannot solve the problem in less than 30000 steps");
				//	break;
					//}
					double h=0;
				double min=0;
			//	System.out.println("fighting");
				h = SLD(current, goal);
				min = SLD(min_Room, goal);
				if( h < min){
					min_Room = current;
				}	
				}
				
				
			}
				Room extend = min_Room;
				open.remove(min_Room);
				closed.add(extend);
				if(string == "Mis")
				{
				if(MisplacedTiles(extend, goal)==0){
					System.out.print("Astar (");
					System.out.print("Misplaces");
					System.out.print("):\n");
					reconstruct(extend);
					draw_reconstruct();
					System.out.print(a+" ");
					System.out.print(b+" ");
					System.out.print(open.size()+" ");
					System.out.print(closed.size()+")");
					System.out.print("\n");
					break;
				}
				}
				if(string == "Man")
				{
				if(Manhanttan(extend, goal)==0){
					System.out.print("Astar (");
					System.out.print("Manhanttan");
					System.out.print("):\n");
					reconstruct(extend);
					draw_reconstruct();
					
					//output.reconstruct(current);
					//output.draw_reconstruct();
					System.out.print(a+" ");
					System.out.print(b+" ");
					System.out.print(open.size()+" ");
					System.out.print(closed.size()+")");
					System.out.print("\n");
					break;
				}
				}
				if(string == "My")
				{
				if(Manhanttan(extend, goal)==0.0){
					System.out.print("A* (Myheuristics) :\n");
					reconstruct(extend);
					draw_reconstruct();
					System.out.print(a+" ");
					System.out.print(b+" ");
					System.out.print(open.size()+" ");
					System.out.print(closed.size()+")");
					System.out.print("\n");
				
					break;
				}
				}
				Room moveUp ;
				Room moveDown;
				Room moveRight ;
				Room moveLeft;
				 moveUp = extend.moveup();
				 moveDown = extend.movedown();
				 moveRight = extend.moveright();
				 moveLeft = extend.moveleft();
				if(closed.contains(moveLeft)||closed.contains(moveUp)||closed.contains(moveDown)||closed.contains(moveRight))
					{ b++;}
				if(moveLeft != null){a++;}
				if(moveDown != null){a++;}
				if(moveUp != null){a++;}
				if(moveRight != null){a++;}
				if(moveLeft != null && !closed.contains(moveLeft)){
					if(!open.contains(moveLeft) ){
					open.add(moveLeft);	
					moveLeft.setParent(extend);
					
					moveLeft.setCost(extend.getCost()+1);
					}
					else if(moveLeft.getCost()>extend.getCost()+1){
						moveLeft.setCost(extend.getCost()+1);
						moveLeft.setParent(extend);
					}
				}//System.out.print("\n");
				
				if(moveUp!=null && !closed.contains(moveUp)){
					if(!open.contains(moveUp) ){
						open.add(moveUp);	
						moveUp.setParent(extend);
						moveUp.setCost(extend.getCost()+1);
						}
						else if(moveUp.getCost()>extend.getCost()+1){
							moveUp.setCost(extend.getCost()+1);
							moveUp.setParent(extend);
						}
				}
				if(moveDown != null && !closed.contains(moveDown)){	
					if(!open.contains(moveDown) ){
						open.add(moveDown);	
						moveDown.setParent(extend);
						moveDown.setCost(extend.getCost()+1);
						}
						else if(moveDown.getCost()>extend.getCost()+1){
							moveDown.setCost(extend.getCost()+1);
							moveDown.setParent(extend);
						}
				}//System.out.print("\n");
		
				if(moveRight!=null && !closed.contains(moveRight)){
				
					if(!open.contains(moveRight) ){
						open.add(moveRight);	
						moveRight.setParent(extend);
						moveRight.setCost(extend.getCost()+1);
						}
						else if(moveRight.getCost()>extend.getCost()+1){
							moveRight.setCost(extend.getCost()+1);
							moveRight.setParent(extend);
						}
					
				}
			}
	}
	
	public int SLD(Room current, Room goal){
		
		int heuristicValue = 0;
		for(int k = 0; k < 4; k++){
			
		for(int m = 0; m < 4; m++){
			
		   int x = current.elements[k][m];
		for(int i = 0; i < 4; i++){
			for(int j = 0; j<4; j++){
				if(x==goal.elements[i][j]){
					heuristicValue +=Math.abs(k-i)+Math.abs(m-j);
					
				}
			}
		}
		}
		}
	//	System.out.println(heuristicValue);
		return(heuristicValue);
		
	}

	public int MisplacedTiles(Room a,Room goal){
		int heuristicValue = 0;
		for(int i = 0; i < 4; i++){
			for(int j = 0; j<4; j++){
				if(a.elements[i][j]!=goal.elements[i][j]){
					heuristicValue++;
				}
			}
		}return(heuristicValue);
	}
	public int Manhanttan(Room a , Room goal){
		
	   int sum = 0;
		int heuristicValue = 0;
		for(int i = 0; i <4;i++){
			for(int j = 0;j<4;j++){
				
			int x = a.elements[i][j];
				for(int k = 0; k<4;k++){
					
				for(int m = 0; m <4;m++){
					if(x==goal.elements[k][m]){
					sum += Math.abs( k- i) + Math.abs( m- j);
			}
				}
				}
			}
		}return(sum);
	}

	public  void reconstruct(Room current){
		do{
			reconstruct.add(current);
			current = current.getParent();
		}while(current != null);
	}

	public  void draw_reconstruct(){
		int Room_count = 0;
		System.out.print("(((");
		for(int i = reconstruct.size()-2;i>=0;i--)
		{	
			System.out.print("\""+reconstruct.get(i).direction+"\"");
			
		}
		int move=reconstruct.size()-1;
		System.out.print(") "+ move+") ");
	}
}
