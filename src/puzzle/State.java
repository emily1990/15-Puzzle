package puzzle;

import java.util.Arrays;

public class State implements Comparable<State>{

	public static final int NUM_TILES = 16;
	public static final int ROWS = (int )Math.sqrt(NUM_TILES);
	public static final int COLS = (int )Math.sqrt(NUM_TILES);	
	int[][] elements;
	private State parent;
	String direction;
	int depth;
	private int gcost;
//	int cost;
	/*state constructor for children states, with link to its parent
	 * 
	 */
	public State( int[][] tiles, State parent,String direction,int depth){
		int cost;
		this.parent = parent;
		this.elements = new int[ROWS][COLS];
		elements = tiles;
		this.direction = direction;
		this.depth = depth;
		//this.cost = cost;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + depth;
		result = prime * result
				+ ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + Arrays.hashCode(elements);
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
	      State other = (State)obj;
		if(obj instanceof State){
			int i;
			int j;
            for(i = 0; i< 4;i++){
            	for(j=0;j<4;j++){
            		if(!(this.elements[i][j]== other.elements[i][j]) ){
            			   return false;
            	           }
            	}
            }
            return true;
            	     		
            	
            }
			
		else
			return false;
	
	}
	
	
	//state constructor for initial and goal state
	public State(int row, int column, int[][] StartNums){
		this.elements = StartNums;
	}
	//move to the left
	public State moveleft(){
		int[][] temp = new int[ROWS][COLS];
		copyElements(temp);
		//find the empty tile, and determine if a left-move is possible based on whether or not it's on the left
		for(int i =0;i < ROWS;i++ ){
			for(int j=0;j < COLS;j++){
				if(temp[i][j]==0){
					if(j !=0){
						temp[i][j]=elements[i][j-1];
						temp[i][j-1]=0;
						return new State(temp,this,"W",this.depth+1);
					}
				}
			}
		}
			
		return null;
		
	}
	//move the 0 to the right
	public State moveright(){
		int[][] temp = new int[ROWS][COLS];
		copyElements(temp);
		for(int i = 0;i < ROWS; i++){
			for(int j = 0; j<COLS; j++){
				if(temp[i][j]==0){
					if(j != COLS-1){
						temp[i][j]=elements[i][j+1];
						temp[i][j+1]=0;
						return new State(temp,this,"E",this.depth+1);
					}
				}
			}
		}
		return null;
		
	}
	//move the 0 to the up
	public State moveup(){
		int[][] temp = new int[ROWS][COLS];
		copyElements(temp);
		//search 0 and move up
		for (int i = 0;i<ROWS;i++){
			for(int j = 0; j < COLS; j++){
				if(temp[i][j]==0){
					if(i!= 0){
						temp[i][j]=temp[i-1][j];
						temp[i-1][j]=0;
						return new State(temp, this,"N",this.depth+1);
					}
				}
			}
		}
		return null;
	}
	//move the 0 down
	public State movedown(){
		int[][] temp = new int[ROWS][COLS];
		copyElements(temp);
		//search 0 and move up
		for (int i = 0;i<ROWS;i++){
			for(int j = 0; j < COLS; j++){
				if(temp[i][j]==0){
					if(i!= ROWS-1){
						temp[i][j]=temp[i+1][j];
						temp[i+1][j]=0;
						return new State(temp, this,"S",this.depth+1);
					}
				}
			}
		}
		return null;
	}
	//copy elements of original puzzle to a new list
	private void copyElements(int[][] temp) {
		for (int i = 0;i < ROWS; i++){
			for (int j = 0;j < COLS;j++){
				temp[i][j] = elements[i][j];
			//	System.out.print(elements[i][j]+"");
			}
		}
		
		
	}
	
	public int compareTo(State arg0) {
	
		return 0;
	}
	
	public State getParent() {
		return parent;
	}

	int[][] getElements(){
		return this.elements;
	}

	public int getCost() {
		return gcost;
	}
	public void setCost(int cost){
		this.gcost = cost;
	}
}
