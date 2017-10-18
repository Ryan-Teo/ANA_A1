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
	Map<Integer, myLinkedList> indexArrayCopy1;
	Map<Integer, myLinkedList> indexArrayCopy2;
	
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
    	long startTime = System.nanoTime();
        newVertice = (String)vertLabel;
    	Node newVerticeNode = new Node(newVertice, null, -1);
    	myLinkedList newLinkedList = new myLinkedList(newVerticeNode, newVerticeNode, 1);
    	if(searchVertex(indexArray, newVertice) == -1){
    		indexArray.put(indexCount++, newLinkedList);
    	}
    	long estimatedTime = System.nanoTime() - startTime;
    	System.out.printf("Add Vertex : Estimated Time : %d\n", estimatedTime);
    	
    } // end of addVertex()
	
    public int searchVertex(Map<Integer, myLinkedList> arrayMap, String vertLabel){
    	int key = -1;
    	
    	for (Entry<Integer, myLinkedList> entry : arrayMap.entrySet()) {
    		myLinkedList list = entry.getValue();
      	    if(vertLabel.equalsIgnoreCase(list.getHeadNode().getVertice())){
      	    	key = entry.getKey();
      	    	break;
      	    }
    	}    
    	return key;
    }
    public void addEdge(T srcLabel, T tarLabel) {
    	long startTime = System.nanoTime();
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
    					System.err.println(">>> Error: Edge already exists.");
    				}
    			}
    		}
    	}
    	else{
    		System.err.println(">>> Error: Inputted source or target does not exist.<<<");
    		throw new IllegalArgumentException();
    	}
    	long estimatedTime = System.nanoTime() - startTime;
    	System.out.printf("Add Edge : Estimated Time : %d\n", estimatedTime);
    } // end of addEdge()
	

    @SuppressWarnings("unchecked")
	public ArrayList<T> neighbours(T vertLabel) {
    	long startTime = System.nanoTime();
    	ArrayList<T> neighbours = new ArrayList<T>();
    	String vertex = (String) vertLabel;
    	int checkKey;
    	Node current = null;
  
    	if(searchVertex(indexArray, vertex) != -1){
    		checkKey = searchVertex(indexArray, vertex);
    		for (Entry<Integer, myLinkedList> entry : indexArray.entrySet()) {
    			Integer key = entry.getKey();
    			if(key == checkKey){
    				myLinkedList list = entry.getValue();
    				if (list.getHeadNode().getVertice().equalsIgnoreCase(vertex)){
    					current = list.getHeadNode();
    					for(int i = 0; i < list.getListCount()-1; i++){
    						if (current == list.getTailNode()){
    							break;
    						}
    						current = current.getNextNode();
    						String newVertex = current.getVertice();
    						neighbours.add((T) newVertex);
    					}
    				}
    			}
    		}
    	}
    	else{
    		throw new IllegalArgumentException();
    	}
    	long estimatedTime = System.nanoTime() - startTime;
    	System.out.printf("Neighbours : Estimated Time : %d\n", estimatedTime);
        return neighbours;
        
    } // end of neighbours()
    
    
    public void removeVertex(T vertLabel) {
    	long startTime = System.nanoTime();
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
    	else{
    		throw new IllegalArgumentException();
    	}
    	long estimatedTime = System.nanoTime() - startTime;
    	System.out.printf("Remove Vertex : Estimated Time : %d\n", estimatedTime);
    			
    }
    	
     // end of removeVertex()
	
    
    public void removeEdge(T srcLabel, T tarLabel) {
    	long startTime = System.nanoTime();
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
	    				System.err.println(">>> Error: Edge does not exist.");
	    			}	
    			}
    		}
    	}
    	else{
    		System.err.println(">>> Error: Inputted source or target does not exist.!!!");
    		throw new IllegalArgumentException();
    	}
    	long estimatedTime = System.nanoTime() - startTime;
    	System.out.printf("Remove Edge : Estimated Time : %d\n", estimatedTime);
   } 
	
    
    public void printVertices(PrintWriter os) {
    	long startTime = System.nanoTime();
    	for (Entry<Integer, myLinkedList> entry : indexArray.entrySet()) {
    		  myLinkedList value = entry.getValue();
    		  os.printf("%s \n",value.getHeadNode().getVertice());	
    	}
    	long estimatedTime = System.nanoTime() - startTime;
    	System.out.printf("Print Vertices : Estimated Time : %d\n", estimatedTime);
    } // end of printVertices()
	
    
    public void printEdges(PrintWriter os) {
    	long startTime = System.nanoTime();
		for (Entry<Integer, myLinkedList> entry : indexArray.entrySet()) {
			myLinkedList value = entry.getValue();
			Node current = value.getHeadNode();
			if(current != value.getTailNode()){
				String orig = current.getVertice();
				current = current.getNextNode();
				for(int i = 1; i < value.getListCount(); i++){
					os.printf("%s %s\n",orig, current.getVertice());
					current = current.getNextNode();
				}
			// value.printNode();
			}
		}
    	long estimatedTime = System.nanoTime() - startTime;
    	System.out.printf("Print Edges : Estimated Time : %d\n", estimatedTime);
    } // end of printEdges()
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	long startTime = System.nanoTime();
    	String vert1 = (String) vertLabel1;
    	String vert2 = (String) vertLabel2;
    	
    	int check = -1, path = 0, check1 = -1, pathCheck = 0;
    	myLinkedList visit = new myLinkedList(null, null, 0);
    	myLinkedList currentList = null; 
    	
    	Node currentListNode = null;
    	Node currentListNodeCopy = null;
    	

    	if(searchVertex(indexArray, vert1) == -1 || searchVertex(indexArray, vert2)== -1){
        	long estimatedTime = System.nanoTime() - startTime;
        	System.out.printf("Shortest Path : Estimated Time : %d\n", estimatedTime);
    		return disconnectedDist;
    	}
    	visit.addNode(vert1);
    	Node current = visit.getHeadNode();
    	current.setPath(path);
    	path = pathCheck = 1;
    	
    	do{
	    	
    		currentList = searchArray(indexArray, current.getVertice());
	    	currentListNode = currentListNodeCopy = currentList.getHeadNode();
	    	
	    	for (int i = 0; i < currentList.getListCount(); i++){
	    		
	    		if(searchList(visit, currentListNode.getVertice()) == -1){
	    			visit.addNode(currentListNode.getVertice());
	    			visit.getTailNode().setPath(path + 1);
	    			
	    		}
	    		currentListNode = currentListNode.getNextNode();
	    		
	    	}
	    	if(searchList(visit, vert2) != -1){
	    		check = 1;
	        	long estimatedTime = System.nanoTime() - startTime;
	        	System.out.printf("Shortest Path : Estimated Time : %d\n", estimatedTime);
	    		return path;
			}
	
	    	if(current == visit.getTailNode()){
	        	long estimatedTime = System.nanoTime() - startTime;
	        	System.out.printf("Shortest Path : Estimated Time : %d\n", estimatedTime);
	    		return -1;
	    	}
	    		
	    	current = current.getNextNode();
	    	if(checkPath(visit, current.getVertice()) != path){
	    		path++;
	    	}
    	}while(check == -1);

    	long estimatedTime = System.nanoTime() - startTime;
    	System.out.printf("Shortest Path : Estimated Time : %d\n", estimatedTime);
        return disconnectedDist;    	
    } // end of shortestPathDistance()
    
    public int checkPath(myLinkedList visit, String vertex){
    	Node current;
    	current = visit.getHeadNode();
    	
    	for (int i = 0; i < visit.getListCount(); i++)
    	{
    		if(current.getVertice().equalsIgnoreCase(vertex)){
    			return current.getPath();
    		}
    		current = current.getNextNode();
    	}
    	return -1;
    }
    
    public myLinkedList searchArray(Map<Integer, myLinkedList> arrayMap, String vertLabel){
    	myLinkedList list = null;
    	
    	for (Entry<Integer, myLinkedList> entry : arrayMap.entrySet()) {
    		
    		list = entry.getValue();
      	    if(vertLabel.equalsIgnoreCase(list.getHeadNode().getVertice())){
      	    	return list;
      	    }
    	}    
    	return list;
    }
    
    public int searchList(myLinkedList list, String vertex){
    	Node current;
    	current = list.getHeadNode();
    	
    	for (int i = 0; i < list.getListCount(); i++)
    	{
    		if(current.getVertice().equalsIgnoreCase(vertex)){
    			return i;
    		}
    		current = current.getNextNode();
    	}
    	return -1;
    }
  
} // end of class AdjList