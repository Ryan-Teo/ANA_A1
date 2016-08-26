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
    	if(searchVertex(indexArray, newVertice) == -1){
    		indexArray.put(indexCount++, newLinkedList);
    	}
    	
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
    } // end of addEdge()
	

    @SuppressWarnings("unchecked")
	public ArrayList<T> neighbours(T vertLabel) {
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
    	else{
    		throw new IllegalArgumentException();
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
	    				System.err.println(">>> Error: Edge does not exist.");
	    			}	
    			}
    		}
    	}
    	else{
    		System.err.println(">>> Error: Inputted source or target does not exist.!!!");
    		throw new IllegalArgumentException();
    	}
   } 
	
    
    public void printVertices(PrintWriter os) {
    	for (Entry<Integer, myLinkedList> entry : indexArray.entrySet()) {
    		  myLinkedList value = entry.getValue();
    		  os.printf("%s \n",value.getHeadNode().getVertice());	
    	}
    } // end of printVertices()
	
    
    public void printEdges(PrintWriter os) {
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
    } // end of printEdges()
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	System.out.println("Shortest Path Start");
		Map<String, Integer> dist; //Distance of vertex from source
		Map<String, Integer> visited; //Vertex visited
		Map<Integer,myLinkedList> isPath;
		dist = new HashMap<String, Integer>();
		visited = new HashMap<String, Integer>();
		isPath = new HashMap<Integer, myLinkedList>();
    	//both vertices have to exist
    	//print to system.err if one does not exist
    	//Set indices if vertex exists
    	String dfs; //dfs = Distance from source
    	int infinite = Integer.MAX_VALUE;
    	ArrayList<T> vert1Neigh = neighbours(vertLabel1);
    	ArrayList<T> vert2Neigh = neighbours(vertLabel2);
    	if(vert1Neigh.isEmpty() == true || vert2Neigh.isEmpty() == true){
    		return -1;
    	}
        	//Copy vertices with edges and setting indi distance
        if(searchVertex(indexArray, (String)vertLabel2) != -1 && searchVertex(indexArray, (String)vertLabel1)!= -1){
			for (Entry<Integer, myLinkedList> entry : indexArray.entrySet()) {
					int key = entry.getKey();
		  		  myLinkedList value = entry.getValue();
		  		  if(!value.getHeadNode().equals(value.getTailNode())){
		  			  isPath.put(key, value);
		  			  dist.put(value.getHeadNode().getVertice(), infinite);
		  			  visited.put(value.getHeadNode().getVertice(), 0);
		  		  }  
			}
			dist.put((String) vertLabel2, 0);
			for (int i=0 ; i < isPath.size() ; i++){
				dfs = minDistance(dist, visited);
				visited.put(dfs, 1);
				for (Entry<String, Integer> visElem : visited.entrySet()) {
					int checkKeySource = searchVertex(isPath, visElem.getKey());
					for (Entry<Integer, myLinkedList> entry : isPath.entrySet()) {
		    			Integer key = entry.getKey();
		    			if(key == checkKeySource){
		    				myLinkedList list = entry.getValue();
							if(visElem.getValue()==0 && dist.get(dfs) != infinite && list.searchNode(dfs) != false && dist.get(dfs)+1 < dist.get(visElem.getKey())){
					        	 int newDist = dist.get(dfs)+1;
					        	 dist.put(visElem.getKey(), newDist);
					        	 break;
							}
		    			}
					}
		        }
			}
			if(dist.get((String)vertLabel1) > 0 && dist.get((String)vertLabel1) != infinite){
				return dist.get((String)vertLabel1);
			}
        }
        else{
    		throw new IllegalArgumentException();
        }

        // if we reach this point, source and target are disconnected
        return disconnectedDist;    	
    } // end of shortestPathDistance()
    
    String minDistance(Map<String, Integer> dist, Map<String, Integer>  visited)
    {
        // Initialize min value
        int min = Integer.MAX_VALUE;
        String minString = null;
        for (Entry<String, Integer> visElem : visited.entrySet()) {
        	int checkVisit = visElem.getValue();
        	 for (Entry<String, Integer> distElem : dist.entrySet()) {
        		 int checkDist = distElem.getValue();
        		 if(checkVisit == 0 && checkDist <= min && visElem.getKey().equalsIgnoreCase(distElem.getKey())){
        			 min = checkDist;
        			 minString = distElem.getKey();
        		 }
        	 }
        }
 
        return minString;
    }
  
} // end of class AdjList