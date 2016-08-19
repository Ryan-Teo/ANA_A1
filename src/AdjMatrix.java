
import java.io.*;
import java.util.*;


/**
 * Adjacency matrix implementation for the FriendshipGraph interface.
 * 
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 */
public class AdjMatrix <T extends Object> implements FriendshipGraph<T>
{
	/**
	 * Constructs empty graph.
	 */
	private int[][] matrix;
	private T[] vertex;
	
    @SuppressWarnings("unchecked")
	public AdjMatrix() {
    	vertex = (T[]) new String[0];
    	matrix= new int[vertex.length][vertex.length];
    } // end of AdjMatrix()
    
    @SuppressWarnings("unchecked")
	public void addVertex(T vertLabel) {
    	int newSize = vertex.length+1;
    	
    	//Copy array of vertices to temp array
    	T[] tempVertex = (T[]) new String[newSize];
    	for(int i=0;i<vertex.length;i++){
    		tempVertex[i]=vertex[i];
    	}

    	//Copy matrix to temp matrix
    	int[][] tempMatrix = new int[newSize][newSize];
    	for(int i=0;i<newSize;i++){
    		for(int j=0; j<newSize;j++){
    			if(i==vertex.length||j==vertex.length){
    				//Set added values to 0
    				tempMatrix[i][j]=0;
    			}
    			else{
    				tempMatrix[i][j]=matrix[i][j];
    			}
    		}
    	}
    	
    	//Add new vertex to array of vertices
    	tempVertex[vertex.length] = vertLabel;
    	
    	//Move elements back to original resized arrays
    	vertex = (T[]) new String[newSize];
    	matrix = new int[newSize][newSize];
    	for(int i=0;i<newSize;i++){
    		vertex[i]=tempVertex[i];
    	}
    	for(int i=0;i<newSize;i++){
    		for(int j=0; j<newSize;j++){
    			matrix[i][j]=tempMatrix[i][j];
    		}
    	}
    	
    } // end of addVertex()
	
    public void addEdge(T srcLabel, T tarLabel) {
    	//both vertices have to exist
    	//print to system.err if one does not exist
    	//Set indices if vertex exists
    	int srcIndex=-1,tarIndex=-1;
    	for(int i=0; i<vertex.length;i++){
    		if(vertex[i]==srcLabel){
    			srcIndex=i;
    		}
    		if(vertex[i]==tarLabel){
    			tarIndex=i;
    		}
    	}
    	if(srcIndex==-1 && tarIndex==-1){
    		System.err.println("Vertex "+(String)srcLabel+" and "+(String)tarLabel+" do not exist.");
    		return;
    	}else if(srcIndex==-1){
    		System.err.println("Vertex "+(String)srcLabel+" does not exist.");
    		return;
    	}else if(tarIndex==-1){
    		System.err.println("Vertex "+(String)tarLabel+" does not exist.");
    		return;
    	}

    	//if edge already exists, do nothing? maybe print out message
    	if(matrix[srcIndex][tarIndex]==0 && matrix[tarIndex][srcIndex]==0){
    		matrix[srcIndex][tarIndex] = matrix[tarIndex][srcIndex] = 1;
    		System.out.println("Edge added!");
    	}else if(matrix[srcIndex][tarIndex]==1 && matrix[tarIndex][srcIndex]==1){
    		System.err.println("Edge already exists!");
    	}else{
    		//Should never reach here
    		System.err.println("Something went wrong! (1)");
    	}
    } // end of addEdge()

    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        
        //both vertex has to exist
    	//print to system.err if does not exist
    	//Set indices if vertex exists
    	int vertIndex=-1;
    	for(int i=0; i<vertex.length;i++){
    		if(vertex[i]==vertLabel){
    			vertIndex=i;
    		}
    	}
    	if(vertIndex==-1){
    		System.err.println("Vertex "+(String)vertLabel+" does not exist.");
    		return null;
    	}
    	
    	for(int i=0; i<vertex.length;i++){
    		if(matrix[vertIndex][i]==1 && matrix[i][vertIndex]==1){
    			neighbours.add(vertex[i]);
    		}else if(matrix[vertIndex][i]==0 && matrix[i][vertIndex]==0){
    			continue;
    		}else{
    			//Should never reach here
        		System.err.println("Something went wrong! (2)");
    		}
    	}
        return neighbours;
    } // end of neighbours()
    
    
    @SuppressWarnings("unchecked")
	public void removeVertex(T vertLabel) {
    	int newSize = vertex.length-1;
    	int vertIndex=-1;
    	for(int i=0; i<vertex.length;i++){
    		if(vertex[i]==vertLabel){
    			vertIndex=i;
    		}
    	}
    	if(vertIndex==-1){
    		System.err.println("Vertex "+(String)vertLabel+" does not exist.");
    		return;
    	}
    	// if i==vert skip
    	// if i>vert temp[i-1][j]=matrix[i][j]
    	// remove from vertex array
    	T[] tempVertex = (T[]) new String[newSize];
    	for(int i=0;i<vertex.length;i++){
    		if(i==vertIndex){
    			continue;
    		}else if(i>vertIndex){
    			tempVertex[i-1]=vertex[i];
    		}else{
    			tempVertex[i]=vertex[i];
    		}
    	}

    	//remove from matrix
    	int[][] tempMatrix = new int[newSize][newSize];
    	for(int i=0;i<vertex.length;i++){
    		for(int j=0;j<vertex.length;j++){
    			if(i==vertIndex || j==vertIndex){
        			continue;
        		}else if(i>vertIndex){
        			if(j>vertIndex){
            			tempMatrix[i-1][j-1]=matrix[i][j];
        			}else{
        				tempMatrix[i-1][j]=matrix[i][j];
        			}
        		}else if(j>vertIndex){
        			if(i>vertIndex){
            			tempMatrix[i-1][j-1]=matrix[i][j];
        			}else{
        				tempMatrix[i][j-1]=matrix[i][j];
        			}
        		}else{
        			tempMatrix[i][j]=matrix[i][j];
        		}
    		}
    	}
    	//Move elements back to original resized arrays
    	vertex = (T[]) new String[newSize];
    	matrix = new int[newSize][newSize];
    	for(int i=0;i<newSize;i++){
    		vertex[i]=tempVertex[i];
    	}
    	for(int i=0;i<newSize;i++){
    		for(int j=0; j<newSize;j++){
    			matrix[i][j]=tempMatrix[i][j];
    		}
    	}
    } // end of removeVertex()
	
    
    public void removeEdge(T srcLabel, T tarLabel) {
    	//both vertices have to exist
    	//print to system.err if one does not exist
    	//Set indices if vertex exists
    	int srcIndex=-1,tarIndex=-1;
    	for(int i=0; i<vertex.length;i++){
    		if(vertex[i]==srcLabel){
    			srcIndex=i;
    		}
    		if(vertex[i]==tarLabel){
    			tarIndex=i;
    		}
    	}
    	if(srcIndex==-1 && tarIndex==-1){
    		System.err.println("Vertex "+(String)srcLabel+" and "+(String)tarLabel+" do not exist.");
    		return;
    	}else if(srcIndex==-1){
    		System.err.println("Vertex "+(String)srcLabel+" does not exist.");
    		return;
    	}else if(tarIndex==-1){
    		System.err.println("Vertex "+(String)tarLabel+" does not exist.");
    		return;
    	}

    	//if edge already exists, do nothing? maybe print out message
    	if(matrix[srcIndex][tarIndex]==1 && matrix[tarIndex][srcIndex]==1){
    		matrix[srcIndex][tarIndex] = matrix[tarIndex][srcIndex] = 0;
    		System.out.println("Edge removed!");
    	}else if(matrix[srcIndex][tarIndex]==0 && matrix[tarIndex][srcIndex]==0){
    		System.err.println("Edge does not exist!");
    	}else{
    		//Should never reach here
    		System.err.println("Something went wrong! (1)");
    	}
    } // end of removeEdges()
	
    
    public void printVertices(PrintWriter os) {
    	os.println("Vertices:");
    	for(int i=0; i<vertex.length;i++){
        	os.println((String)vertex[i]);
    	}
    	os.println();
    } // end of printVertices()
	
    
    public void printEdges(PrintWriter os) {
    	os.println("Edges:");
    	for(int i=0; i<vertex.length;i++){
    		for(int j=0; j<vertex.length;j++){
    			//If connection
    			if(matrix[i][j]==1){
    				os.printf("%s %s\n", vertex[i],vertex[j]);
    			}
        	}
    	}
    	os.println();
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	// Implement me!
    	
        // if we reach this point, source and target are disconnected
        return disconnectedDist;    	
    } // end of shortestPathDistance()
    
} // end of class AdjMatrix