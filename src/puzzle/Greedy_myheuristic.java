package puzzle;

	import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;



	public class Greedy_myheuristic {
		static LinkedList<State> reconstructed = new LinkedList<State>();
		public static ArrayList<State> reconstruct = new ArrayList<State>();
		public static int a=1;
		public static int b=0;
		static Queue<State> open = new LinkedList<State>();
		static Queue<State> closed = new LinkedList<State>();
		State start;
		State goal;
		
		public Greedy_myheuristic(final State start, final State goal,String string){
		}
		public void greedysearch(State start, State goal,String string){
		//	Boolean flag = false;
			int a = 1;
			open.add(start);
			while(open.size()>0){
				if(a==500){
					System.out.println("Please wait patiently,this is hard for Greedy(Myheuristics) to solve");
				}
				if(a>30000){
					System.out.println("Greedy (Myheuristics) cannot solve the problem in less than 30000 steps");
				break;
				}
				State min_state=open.peek();
				Iterator<State> i = open.iterator();
				while(i.hasNext()){
					State current = i.next();
					if(string == "Mis")
					{	int h = 0;
					int min = 0;
					
						h = MisplacedTiles(current, goal);
					min = MisplacedTiles(min_state, goal);
					if( h < min){
						min_state = current;
					}
					}
					if(string == "Man")
					{double h=0;
					double min=0;
				//	System.out.println("fighting");
					h = Manhanttan(current, goal);
					min = Manhanttan(min_state, goal);
					if( h < min){
						min_state = current;
					}	
					}
					if(string == "My")
					{double h=0;
					double min=0;
				//	System.out.println("fighting");
					h = SLD(current, goal);
					min = SLD(min_state, goal);
					if( h < min){
						min_state = current;
					}	
					}
					
				}
				State extend = min_state;
				open.remove(min_state);
				closed.add(extend);
				if(string == "Mis")
				{
				if(Manhanttan(extend, goal)==0.0){
					System.out.print("Greedy (");
					System.out.print("Misplaces");
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
				if(string == "Man")
				{
				if(Manhanttan(extend, goal)==0.0){
					System.out.print("Greedy (Manhanttan) :\n");
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
				if(string == "My")
				{
				if(SLD(extend, goal)==0.0){
					System.out.print("Greedy (MyHeuristics) :\n");
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
				State moveUp = extend.moveup();
				State moveDown = extend.movedown();
				State moveRight = extend.moveright();
				State moveLeft = extend.moveleft();
				if(closed.contains(moveLeft)||closed.contains(moveUp)||closed.contains(moveDown)||closed.contains(moveRight))
					{ b++;}
				if(moveLeft != null){a++;}
				if(moveDown != null){a++;}
				if(moveUp != null){a++;}
				if(moveRight != null){a++;}
				if(moveLeft != null && !closed.contains(moveLeft)){
				
					open.add(moveLeft);	
				}//System.out.print("\n");
				
				if(moveUp!=null && !closed.contains(moveUp)){

					open.add(moveUp);
				}
				if(moveDown != null && !closed.contains(moveDown)){	
					open.add(moveDown);
				}//System.out.print("\n");
		
				if(moveRight!=null && !closed.contains(moveRight)){
				
					open.add(moveRight);	
					
				}//System.out.print("\n");
					
				}
				
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
		}
		public int SLD(State a, State goal){
			
			int heuristicValue = 0;
			for(int k = 0; k < 4; k++){
				
			for(int m = 0; m < 4; m++){
				
			   int x = a.elements[k][m];
			for(int i = 0; i < 4; i++){
				for(int j = 0; j<4; j++){
					if(x==goal.elements[i][j]){
						heuristicValue +=Math.sqrt(Math.pow((k-i),2)+Math.pow((m-j),2));
						
					}
				}
			}
			}
			}
		//	System.out.println(heuristicValue);
			return(heuristicValue);
			
		}
		public int MisplacedTiles(State a,State goal){
			int heuristicValue = 0;
			for(int i = 0; i < 4; i++){
				for(int j = 0; j<4; j++){
					if(a.elements[i][j]!=goal.elements[i][j]){
						heuristicValue++;
					}
				}
			}return(heuristicValue);
		}
		public int Manhanttan(State a , State goal){
			
			   int sum = 0;
			//	int heuristicValue = 0;
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
		
	}


