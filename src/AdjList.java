import java.io.*;
import java.util.*;
import java.util.Map.Entry;

/**
 * Adjacency list implementation for the FriendshipGraph interface.
 * 
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 */
public class AdjList <T extends Object> implements FriendshipGraph<T>
{   
       
	
	Map<Integer, myLinkedList> indexArray;
	int indexCount = 0;
	String newVertice;
	String sourceVertice;
	String targetVertice;
    /**
	 * Contructs empty graph.
	 */
    public AdjList() {
    	indexArray = new HashMap<Integer, myLinkedList>();
    } // end of AdjList()
    
    
    public void addVertex(T vertLabel) {
        newVertice = (String)vertLabel;
    	Node newVerticeNode = new Node(newVertice, null);
    	myLinkedList newLinkedList = new myLinkedList(newVerticeNode, newVerticeNode, 1);
    	indexArray.put(indexCount++, newLinkedList);
    } // end of addVertex()
	
    public int searchVertex(Map<Integer, myLinkedList> arrayMap, String vertLabel){
    	int key = -1;
    	
    	for (Entry<Integer, myLinkedList> entry : arrayMap.entrySet()) {
    		myLinkedList list = entry.getValue();
      	    if(vertLabel == list.getHeadNode().getVertice()){
      	    	key = entry.getKey();
      	    }
    	}    
    	return key;
    }
    public void addEdge(T srcLabel, T tarLabel) {
    	int checkKey;
    	String source = (String) srcLabel;
    	String target = (String) tarLabel;
    	
    	if(searchVertex(indexArray, (String)srcLabel) != -1 && searchVertex(indexArray,(String)tarLabel)!= -1){
    		checkKey = searchVertex(indexArray, (String)srcLabel);
    		
    		for (Entry<Integer, myLinkedList> entry : indexArray.entrySet()) {
    			Integer key = entry.getKey();
    			if(key == checkKey){
    				myLinkedList list = entry.getValue();
    				if(list.searchNode(target) != true){
    					list.addNode(target);
    				}
    				else{
    					System.out.println(">>>Error: Edge already exists.");
    				}
    			}
    		}
    	}
    	else
    	{
    		System.out.println(">>> Error: Inputted source or target does not exist.");
    	}
    } // end of addEdge()
	

    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        
        // Implement me!
        
        return neighbours;
    } // end of neighbours()
    
    
    public void removeVertex(T vertLabel) {
        // Implement me!
    } // end of removeVertex()
	
    
    public void removeEdge(T srcLabel, T tarLabel) {
        // Implement me!
    } // end of removeEdges()
	
    
    public void printVertices(PrintWriter os) {
        // Implement me!
    } // end of printVertices()
	
    
    public void printEdges(PrintWriter os) {
        // Implement me!
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	// Implement me!
    	
        // if we reach this point, source and target are disconnected
        return disconnectedDist;    	
    } // end of shortestPathDistance()
    
} // end of class AdjList