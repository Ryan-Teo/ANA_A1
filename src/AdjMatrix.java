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
    	T[] tempVertex = (T[]) new String[vertex.length+1];
    	for(int i=0;i<vertex.length;i++){
    		tempVertex[i]=vertex[i];
    	}
 
    	int[][] tempMatrix = new int[vertex.length+1][vertex.length+1];
    	//Copy matrix to temporary matrix
    	for(int i=0;i<vertex.length;i++){
    		for(int j=0; j<vertex.length;j++){
    			tempMatrix[i][j]=matrix[i][j];
    		}
    	}
    	tempVertex[vertex.length] = vertLabel;
    	
    	// Implement me!
    } // end of addVertex()
	
    public void addEdge(T srcLabel, T tarLabel) {
        // Implement me!
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
    
} // end of class AdjMatrix