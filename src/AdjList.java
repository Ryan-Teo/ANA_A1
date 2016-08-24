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
	Map<String, Integer> indexShort;
	Map<String, Integer> indexCheck;
	int shortCount = 0;
	int indexCount = 0;
	String newVertice;
	String sourceVertice;
	String targetVertice;
	PrintWriter os = new PrintWriter(System.out,true);
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
    		System.out.println(srcLabel.toString()+" :"+tarLabel.toString()+">>> Error: Inputted source or target does not exist.<<<");
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
         for (int i = 0; i < neighbours.size(); i++) {
        	 System.out.printf(neighbours.get(i) + " ");
        	
  		}
         System.out.println();
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
	    				System.err.println(">>>Error: Edge does notexists.");
	    			}	
    			}
    		}
    	}
    	
    	else{
    		System.out.println(">>> Error: Inputted source or target does not exist.!!!");
    	}
   } 
	
    
    public void printVertices(PrintWriter os) {
    	
    	for (Entry<Integer, myLinkedList> entry : indexArray.entrySet()) {
    		  myLinkedList value = entry.getValue();
    		  
    		  os.print(value.getHeadNode().getVertice() + " ");
    		
    	}
    	os.println();
    } // end of printVertices()
	
    
    public void printEdges(PrintWriter os) {
    	for (Entry<Integer, myLinkedList> entry : indexArray.entrySet()) {
  		  myLinkedList value = entry.getValue();
  		  
  		Node current = value.getHeadNode();
  		if(current != value.getTailNode()){
  			for(int i = 0; i < value.getListCount(); i++){
				os.printf("%s ",current.getVertice());
				current = current.getNextNode();
	  		}
  		  
//  		  value.printNode();
  		  
  		}
		os.println();
    	}
    	os.println();
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	String vert1 = (String) vertLabel1;
    	String vert2 = (String) vertLabel2;
    	Node source = null;
    	Node target = null;
    	Map<Node, Boolean> vis = new HashMap<Node, Boolean>();
    	Map<Node, Node> prev = new HashMap<Node, Node>();
    	
    	
    	for (Entry<Integer, myLinkedList> entry : indexArray.entrySet()) {
    		myLinkedList list = entry.getValue();
      	    if(vert1.equalsIgnoreCase(list.getHeadNode().getVertice())){
      	    	source = list.getHeadNode();
      	    }
      	    else if (vert2.equalsIgnoreCase(list.getHeadNode().getVertice())){
      	    	target = list.getHeadNode();
      	    }
    	}    
    	
    	    myLinkedList directions = new myLinkedList(null, null, 0);
    	    Queue q = (Queue) new myLinkedList(null, null, 0);
    	    Node current = source;
    	    q.add(current);
    	    vis.put(current, true);
    	    while(!q.isEmpty()){
    	        current = q.remove();
    	        if (current.equals(target)){
    	            break;
    	        }else{
    	            for(Node node : current.getOutNodes()){
    	                if(!vis.contains(node)){
    	                    q.add(node);
    	                    vis.put(node, true);
    	                    prev.put(node, current);
    	                }
    	            }
    	        }
    	    }
    	    if (!current.equals(finish)){
    	        System.out.println("can't reach destination");
    	    }
    	    for(Node node = finish; node != null; node = prev.get(node)) {
    	        directions.add(node);
    	    }
    	    directions.reverse();
    	
    	
    	
//    	 int n = indexArray.size();
//
//         // dist[i] is the distance from v to i
//         int[] dist = new int[n];
//         for (int i = 0; i < n; i++) {
//             dist[i] = Integer.MAX_VALUE;
//         }
//
//         // seen[i] is true if there is a path from v to i
//         boolean[] seen = new boolean[n];
//
//         dist[v] = 0;
//
//         // determine n-1 paths from v
//         for (int j = 0; j < n; j++) {
//             // choose closest unseen vertex
//             int u = -1;
//
//             for (int k = 0; k < n; k++) {
//                 if (!seen[k]) {
//                     // check if u needs updating
//                     if (u < 0 || dist[k] < dist[u]) {
//                         u = k;
//                     }
//                 }
//             }
//
//             if (u < 0 || dist[u] == Integer.MAX_VALUE) {
//                 break;
//             }
//
//             // at this point dist[u] is the cost of the
//             // shortest path from v to u
//
//             // set seen[u] to true and update the distances
//             seen[u] = true;
//
//             for (Edge e : edges[u]) {
//                 int nbr = e.getTarget();
//                 int altDist = dist[u] + e.getCost();
//                 dist[nbr] = Math.min(dist[nbr], altDist);
//             }
//         }


    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
//    	String vert1 = (String) vertLabel1;
//    	String vert2 = (String) vertLabel2;
//    	String source = null;
//    	myLinkedList sourceList = null;
//    	
//    	if(searchVertex(indexArray,vert1) != -1 && searchVertex(indexArray,vert2) != -1){
//    		for (Entry<Integer, myLinkedList> entry : indexArray.entrySet()) {
//        		myLinkedList list = entry.getValue();
//          	    if(vert1.equalsIgnoreCase(list.getHeadNode().getVertice())){
//          	    	source = vert1;
//          	    	sourceList = list;
//          	    }
//        	}    
//    	
//    		indexShort.put(sourceList.getHeadNode().getVertice(), 0);
//    		Node current = null;
//    		{}while()
//    			
//    	}
//    		
//    		
//    		
//    	}
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
       	//both vertices have to exist
    	//print to system.err if one does not exist
    	//Set indices if vertex exists
//    	
//    	String vert1 = (String) vertLabel1;
//    	String vert2 = (String) vertLabel2;
//    	int vert1Key = -1;
//    	int vert2Key = -1;
//    	
//    	int indexCount = indexArray.size();
//    	if(searchVertex(indexArray,vert1) != -1 && searchVertex(indexArray,vert2) != -1){
//    		vert1Key = searchVertex(indexArray, vert1);
//    		vert2Key = searchVertex(indexArray, vert2);
//    		int dfs = -1;
//    		int infinite = Integer.MAX_VALUE;
//    		myLinkedList list = null; 
//    		
//    		int dist[] = new int[indexCount];
//    		Boolean visited[] = new Boolean[indexCount]; //Vertex visited
//    		
//    		// Initialize all distances as INFINITE and visited[] as false
//    		for (int i = 0; i < indexCount; i++){
//    			dist[i] = Integer.MAX_VALUE; //infinite
//    			visited[i] = false;
//    		}
//    		dist[vert1Key]= 0;
//    		
//    		
//    		for (int i=0 ; i < indexCount-1 ; i++){
//    			dfs = minDistance(dist, visited);
//    			
//    			visited[dfs] = true;
//    			
//    			
//    			for (int x=0 ; x < indexCount ; x++){
//    				for (Entry<Integer, myLinkedList> entry : indexArray.entrySet()) {
//    	    			Integer key = entry.getKey();
//    	    			if(key == x){
//    	    				list = entry.getValue();
//    	    			}
//    				}
//    				
//    				for (Entry<Integer, myLinkedList> entry : indexArray.entrySet()) {
//    	    			Integer key = entry.getKey();
//    	    			if(key == dfs){
//    	    				list = entry.getValue();
//    	    			}
//        			}	
//    				
//    				if (visited[x] == false && dist[dfs] != infinite && dist[dfs] + matrix[dfs][x] < dist[x]){
//    					dist[x] = dist[dfs] + matrix[dfs][x];
//    				}
//    			}
//    		}
//    		if(dist[vert1Key] > 0 && dist[vert2Key] != infinite){
//    			return dist[vert2Key];
//    		}
//        }
//    		
//    	
//    	
//    	
    	 //Distance between vertex and itself is 0
		
        // if we reach this point, source and target are disconnected
        return disconnectedDist;    	
    } // end of shortestPathDistance()
    
int minDistance(int dist[], Boolean visited[])
{
    // Initialize min value
    int min = Integer.MAX_VALUE, min_index=-1;
    indexCount = indexArray.size();
    for (int v = 0; v < indexCount; v++)
        if (visited[v] == false && dist[v] <= min){
            min = dist[v];
            min_index = v;
        }

    return min_index;
}
} // end of class AdjList