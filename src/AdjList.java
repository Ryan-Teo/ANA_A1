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
	Map<Integer, myLinkedList> indexArrayCopy = indexArray;
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
    	int checkKeySource, checkKeyTarget;
    	String source = (String) srcLabel;
    	String target = (String) tarLabel;
    	if(searchVertex(indexArray, source) != -1 && searchVertex(indexArray,target)!= -1){
    		checkKeySource = searchVertex(indexArray, source);
    		checkKeyTarget = searchVertex(indexArray, target);
    		for (Entry<Integer, myLinkedList> entry : indexArray.entrySet()) {
    			Integer key = entry.getKey();
    			if(key == checkKeySource){
    				myLinkedList list = entry.getValue();
    				if(list.searchNode(target) == false){
    					list.addNode(target);
    					for (Entry<Integer, myLinkedList> entry1 : indexArray.entrySet()) {
    						key = entry1.getKey();
    		    			if(key == checkKeyTarget){
    		    				list = entry1.getValue();
    		    				list.addNode(source);
    		    				break;
    		    			}
    					}	
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
	

    @SuppressWarnings("unchecked")
	public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        String vertex = (String) vertLabel;
        int checkKey;
        
        if(searchVertex(indexArray, vertex) != -1){
    		checkKey = searchVertex(indexArray, vertex);
    		for (Entry<Integer, myLinkedList> entry : indexArray.entrySet()) {
    			Integer key = entry.getKey();
    			if(key == checkKey){
    				myLinkedList list = entry.getValue();
    				while(list.getHeadNode().getNextNode()!= null){
    					String newVertex = list.getHeadNode().getVertice();
    					neighbours.add((T) newVertex);
    				}
    			}
    		}
    	}
        
        return neighbours;
    } // end of neighbours()
    
    
    public void removeVertex(T vertLabel) {
    	String removeVert = (String) vertLabel;
    	int checkKey = -1;
    	
    	if(searchVertex(indexArray, removeVert) != -1){
    	checkKey = searchVertex(indexArray,removeVert);
    				indexArray.remove(checkKey);
    		for (Entry<Integer, myLinkedList> entry : indexArray.entrySet()) {
    			myLinkedList list = entry.getValue();
    			String source = list.getHeadNode().getVertice();
    			if(list.searchNode(removeVert) == true){
    				list.removeEdgeNode(source, removeVert);
    			}
    		}
    	}
    			
    }
    	
     // end of removeVertex()
	
    
    public void removeEdge(T srcLabel, T tarLabel) {
    	int checkKeySource, checkKeyTarget;
    	String source = (String) srcLabel;
    	String target = (String) tarLabel;
    	if(searchVertex(indexArray, source) != -1 && searchVertex(indexArray,target)!= -1){
    		checkKeySource = searchVertex(indexArray, source);
    		checkKeyTarget = searchVertex(indexArray, target);
    		for (Entry<Integer, myLinkedList> entry : indexArray.entrySet()) {
    			Integer key = entry.getKey();
    			if(key == checkKeySource){
    				myLinkedList list = entry.getValue();
	    			if(list.searchNode(target) == true){
	    				list.removeEdgeNode(source, target);
	    				for (Entry<Integer, myLinkedList> entry1 : indexArray.entrySet()) {
    						key = entry1.getKey();
    						if(key == checkKeyTarget){
    		    				list = entry1.getValue();
    		    				for (int i = 0; i < list.getListCount(); i++){
    		    					if(list.searchNode(source) == true){
    		    					
    		    					list.removeEdgeNode(target, source);
    		    					}
    		    				}	
    		    			}
	    				}
	    			}
	    			else{
	    				System.out.println(">>>Error: Edge does notexists.");
	    			}	
    			}
    		}
    	}
    	
    	else{
    		System.out.println(">>> Error: Inputted source or target does not exist.");
    	}
   } 
	
    
    public void printVertices(PrintWriter os) {
    	System.out.println("Test1");
    	for (Entry<Integer, myLinkedList> entry : indexArray.entrySet()) {
    		  myLinkedList value = entry.getValue();
    		  
    		  os.printf(value.getHeadNode().getVertice() + " ");
    	}
    	System.out.println("\n");
    } // end of printVertices()
	
    
    public void printEdges(PrintWriter os) {
    	for (Entry<Integer, myLinkedList> entry : indexArray.entrySet()) {
  		  myLinkedList value = entry.getValue();
  		  value.printNode();
  		  System.out.println();
  		}
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    
    	
    	
    	
    	
    	
        return disconnectedDist;    	
    } // end of shortestPathDistance()
    
} // end of class AdjList