package puzzle;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;

public class Unicost {
	static LinkedList<Room> reconstructed = new LinkedList<Room>();
	public static ArrayList<Room> reconstruct = new ArrayList<Room>();
	public static int a=1;
	public static int b=0;
	static Queue<Room> open = new LinkedList<Room>();
	static Queue<Room> closed = new LinkedList<Room>();
	    public Unicost(Room start, Room goal) {
	    }
//lets begin
	    
	/*    static <K,V extends Comparable<? super V>>
	    SortedSet<Entry<K,V>> entriesSortedByValues(SortedSet<Entry<Room,Integer>> open) {
	        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
	            new Comparator<Map.Entry<K,V>>() {
	                @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
	                    return e1.getValue().compareTo(e2.getValue());
	                }
	            }
	        );
	        sortedEntries.add((Entry<K, V>) open);
	        return sortedEntries;
	    }
	   */
	    public static void reconstruct(Room current){
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
	    
	    public static void Search(Room start, Room goal) {
	    	int a =1;
	    	open.add(start);
	        while (open.size()>0) {
	        	if(a>10000){
					System.out.println("UCS cannot solve the problem in less than 10000 steps");
				break;
				}
	        	int flag = 0;
	        	Room min_Room=open.peek();
				Iterator<Room> i = open.iterator();
				while(i.hasNext()){
					Room current = i.next();
					int h = 0;
					int min = 0;
					
					h = current.getCost();
					min = min_Room.getCost();
					if( h < min){
						min_Room = current;
					}
					}
	        	Room extend = min_Room;
	        	open.remove(min_Room);
	            closed.add(extend);
	            for(int k = 0;k<4;k++){
	    			for(int j =0;j<4;j++){
	    				if(extend.elements[k][j]!=goal.elements[k][j]) {
	    					flag = 1;
	    				}
	    			//	System.out.println("goal:"+goal.getElements()[i][j]);
	    				//System.out.println("current:"+current.getElements()[i][j]);
	    				//System.out.println();
	    			}
	    		}
	    		if(flag == 0){
	    		//	OutPut output = new OutPut();
	    			System.out.print("UCS:\n");
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
	            
	    		Room moveUp = extend.moveup();
	    		Room moveDown = extend.movedown();
	    		Room moveRight = extend.moveright();
	    		Room moveLeft = extend.moveleft();
	    		if(closed.contains(moveLeft)||closed.contains(moveUp)||closed.contains(moveDown)||closed.contains(moveRight))
	    			{ b++;}
	    		if(moveLeft != null){a++;}
	    		if(moveDown != null){a++;}
	    		if(moveUp != null){a++;}
	    		if(moveRight != null){a++;}
	    		
	    		if(moveUp!=null && !closed.contains(moveUp)){
	    			if(!open.contains(moveUp)){
	    				open.add(moveUp);
	    				moveUp.setParent(extend);
	    				moveUp.setCost(extend.getCost()+1);
	    			}
	    			else if(extend.getCost()+1<moveUp.getCost()){
	    				moveUp.setCost(extend.getCost()+1);
	    				moveUp.setParent(extend);
	    			}
	    				
	    		}
	    		
	    			
	    			
	    			if(moveDown!=null && !closed.contains(moveDown)){
		    			if(!open.contains(moveDown)){
		    				open.add(moveDown);
		    				moveDown.setParent(extend);
		    				moveDown.setCost(extend.getCost()+1);
		    			}
		    			else if(extend.getCost()+1<moveDown.getCost()){
		    				moveDown.setCost(extend.getCost()+1);
		    				moveDown.setParent(extend);
		    			}
	    				
	    			
	    		}
	    			
	    		
	    			
	    		if(moveRight!=null && !closed.contains(moveRight)){
	    		
	    			if(!open.contains(moveRight)){
	    				open.add(moveRight);
	    				moveRight.setParent(extend);
	    				moveRight.setCost(extend.getCost()+1);
	    			}
	    			else if(extend.getCost()+1<moveRight.getCost()){
	    				moveRight.setCost(extend.getCost()+1);
	    				moveRight.setParent(extend);
	    			}
	    				
	    			
	    		}//System.out.print("\n");
	    			
	    		
	    		if(moveLeft != null && !closed.contains(moveLeft)){
	    			
	    			if(!open.contains(moveLeft)){
	    				open.add(moveLeft);
	    				moveLeft.setParent(extend);
	    				moveLeft.setCost(extend.getCost()+1);
	    			}
	    			else if(extend.getCost()+1<moveLeft.getCost()){
	    				moveLeft.setCost(extend.getCost()+1);
	    				moveLeft.setParent(extend);
	    			}
	    				
	    			
	    		}//System.out.print("\n");
	    		}
	    		
	    		
	    	}
	    	
	   }
	    
