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
	private T[] vert;
	private int[][] matrix;
	private int vertCount;
	private int arraySize = 10000;
	
	/**
	 * Contructs empty graph.
	 */
    @SuppressWarnings("unchecked")
	public AdjMatrix() {
    	vert =  (T[]) new String[arraySize];
    	matrix = new int[arraySize][arraySize];
    	vertCount = 0;
    } // end of AdjMatrix()
    
    
    @SuppressWarnings("unchecked")
	public void addVertex(T vertLabel) {
	 	int vertIndex = -1;
        for(int i=0 ; i < vertCount ; i++){
         	if(vertLabel.equals(vert[i])){
         		vertIndex = i;
         	}
        }
        if(vertIndex == -1){
			if(vertCount==arraySize){
				//Temporary array to store vert values
				T[] tempVert =(T[]) new String[arraySize];
				for(int i=0 ; i < vertCount ; i++){
					tempVert[i] = vert[i];
				}
				//Temporary array to store matrix values
				int[][] tempMat = new int[arraySize][arraySize];
				for(int i=0 ; i < vertCount ; i++){
					for(int j=0 ; j < vertCount ; j++){
						tempMat[i][j] = matrix[i][j];
					}
				}
			
				//Expand array size
				arraySize += 10000;
				//Resize vert array
				vert = (T[]) new String[arraySize];
				//Copy values back to resized array
				for(int i=0 ; i < tempVert.length ; i++){
					vert[i] = tempVert[i];
				}
			
				//Resize matrix array
				matrix = new int[arraySize][arraySize];
				//Copy values back to resized matrix
				for(int i=0 ; i < tempVert.length ; i++){
					for(int j=0 ; j< tempVert.length ; j++){
						matrix[i][j] = tempMat[i][j] ;
					}
				}
			}
			vert[vertCount] = vertLabel;
			vertCount++;
		}
    } // end of addVertex()
	
    
    public void addEdge(T srcLabel, T tarLabel) {
        int srcIndex = -1, tarIndex= -1;
        for(int i=0 ; i < vertCount; i++){
        	if(vert[i].equals(srcLabel)){
        		srcIndex = i;
        	}
        	if(vert[i].equals(tarLabel)){
        		tarIndex = i;
        	}
        }
        
        if(srcIndex != -1 && tarIndex != -1){ //If both vertices have been found
        	matrix[srcIndex][tarIndex] = 1;
        	matrix[tarIndex][srcIndex] = 1;
        }
        else{ //If one of the vertices is not found
        	System.err.println("One of the vertices doesn't exist");
            throw new IllegalArgumentException();
        }
    } // end of addEdge()
	

    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        int vertIndex = -1;
        for(int i=0 ; i < vertCount ; i++){
        	if(vertLabel.equals(vert[i])){
        		vertIndex = i;
        	}
        }
        
        if(vertIndex != -1){ //If vertex exists
        	for(int i=0 ; i < vertCount ; i++){
        		if(matrix[vertIndex][i]==1){
        			neighbours.add(vert[i]);
        		}
        	}
        }
        else{ //If the vertex is not found
        	System.err.println("Vertex doesn't exist");
            throw new IllegalArgumentException();
        }
        return neighbours;
    } // end of neighbours()
    
    
    @SuppressWarnings("unchecked")
	public void removeVertex(T vertLabel) {
    	 int vertIndex = -1;
         for(int i=0 ; i < vertCount ; i++){
         	if(vertLabel.equals(vert[i])){
         		vertIndex = i;
         	}
         }
         
         if(vertIndex != -1){ //If vertex exists
        	 //Copy vert array to temp array
        	 T[] tempVert = (T[]) new String[vertCount];
        	 for(int i=0 ; i < tempVert.length ; i++){
        		 tempVert[i] = vert[i];
        	 }
        	 
        	 //Copy matrix array to temp matrix
        	 int[][] tempMat = new int[vertCount][vertCount];
        	 for(int i=0 ; i < tempVert.length ; i++){
     			for(int j=0 ; j< tempVert.length ; j++){
     				tempMat[i][j] = matrix[i][j];
     			}
        	 }
        	 
        	 //Copying values back without deleted vertex
        	 for(int i=0 ; i < tempVert.length ; i++){
        		 if(i<vertIndex){
        			 vert[i] = tempVert[i];
        		 }
        		 else if(i == vertIndex){
        			 continue;
        		 }
        		 else{ //Any vertex coming after the deleted vertex
        			 vert[i-1] = tempVert[i];
        		 }
        	 }
        	 
        	 for(int i=0 ; i < tempVert.length ; i++){
      			for(int j=0 ; j< tempVert.length ; j++){
      				if(i<vertIndex && j<vertIndex){
      					 matrix[i][j] = tempMat[i][j];
      				}
      				else if(i == vertIndex || j == vertIndex){
      					continue;
      				}
      				else if(i > vertIndex && j > vertIndex){
      					matrix[i-1][j-1] = tempMat[i][j];
      				}
      				else if(i > vertIndex){
      					matrix[i-1][j] = tempMat[i][j];
      				}
      				else if(j > vertIndex){
      					matrix[i][j-1] = tempMat[i][j];
      				}
      			}
         	 }
             vertCount--;
         }
         else{ //If the vertex is not found
         	System.err.println("Vertex doesn't exist");
         	throw new IllegalArgumentException();
         }
    } // end of removeVertex()
	
    
    public void removeEdge(T srcLabel, T tarLabel) {
    	int srcIndex = -1, tarIndex= -1;
        for(int i=0 ; i < vertCount; i++){
        	if(vert[i].equals(srcLabel)){
        		srcIndex = i;
        	}
        	if(vert[i].equals(tarLabel)){
        		tarIndex = i;
        	}
        }
        
        if(srcIndex != -1 && tarIndex != -1){ //If both vertices have been found
        	if(matrix[srcIndex][tarIndex] == 1 && matrix[tarIndex][srcIndex] == 1){
        		matrix[srcIndex][tarIndex] = 0;
        		matrix[tarIndex][srcIndex] = 0;
        	}
        	else if(matrix[srcIndex][tarIndex] == 0 && matrix[tarIndex][srcIndex] == 0){
        		System.err.println("Edge doesn't exist in the graph");
        	}
        	else{
        		System.err.println("Unexpected Error (1)"); //EXTRA PRINTING
        	}
        }
        else{ //If one of the vertices is not found
        	System.err.println("One of the vertices doesn't exist");
         	throw new IllegalArgumentException();
        }
    } // end of removeEdges()
	
    
    public void printVertices(PrintWriter os) {
        for(int i=0 ; i < vertCount ; i++){
        	os.println(vert[i]);
        }
    } // end of printVertices()
	
    
    public void printEdges(PrintWriter os) {
        for(int i=0 ; i < vertCount ; i++){
        	for(int j=0 ; j < vertCount ; j++){
        		if(matrix[i][j]==1){
        			os.printf("%s %s\n", vert[i], vert[j]);
        		}
        	}
        }
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	//both vertices have to exist
    	//print to system.err if one does not exist
    	//Set indices if vertex exists
    	int vert1Index = -1, vert2Index= -1;
        for(int i=0 ; i < vertCount; i++){
        	if(vert[i].equals(vertLabel1)){
        		vert1Index = i;
        	}
        	if(vert[i].equals(vertLabel2)){
        		vert2Index = i;
        	}
        }
    
    	int dist[] = new int[vertCount];
		Boolean visited[] = new Boolean[vertCount]; //Vertex visited
		
		// Initialize all distances as INFINITE and visited[] as false
		for (int i = 0; i < vertCount; i++){
			dist[i] = Integer.MAX_VALUE; //infinite
			visited[i] = false;
		}
		dist[vert1Index]= 0;
		for (int count = 0; count < vertCount-1; count++){
			// Pick the minimum distance vertex from the set of vertices
			// not yet processed. u is always equal to src in first
			// iteration.
			int u = minDistance(dist, visited);
			// Mark the picked vertex as processed
			visited[u] = true;
			// Update dist value of the adjacent vertices of the
			// picked vertex.
			for (int v = 0; v < vertCount; v++){
				// Update dist[v] only if is not in sptSet, there is an
				// edge from u to v, and total weight of path from src to
				// v through u is smaller than current value of dist[v]
				if (!visited[v] && matrix[u][v]!=0 &&
					dist[u] != Integer.MAX_VALUE &&
					dist[u]+matrix[u][v] < dist[v])
					dist[v] = dist[u] + matrix[u][v];
				}
		}
		
		if(dist[vert2Index]>0 && dist[vert2Index]!=Integer.MAX_VALUE){
			return dist[vert2Index];
		}

        // if we reach this point, source and target are disconnected
        return disconnectedDist;    	
    } // end of shortestPathDistance()
    
    int minDistance(int dist[], Boolean visited[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index=-1;
 
        for (int v = 0; v < vertCount; v++)
            if (visited[v] == false && dist[v] <= min){
                min = dist[v];
                min_index = v;
            }
 
        return min_index;
    }
    
} // end of class AdjMatrix