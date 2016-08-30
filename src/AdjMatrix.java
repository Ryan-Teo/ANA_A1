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
		long startVertTime = System.nanoTime();
	 	int vertIndex = -1;
        for(int i=0 ; i < vertCount ; i++){
         	if(vertLabel.equals(vert[i])){
         		vertIndex = i;
         	}
        }
        if(vertIndex == -1){
			if(vertCount==arraySize){
				long startTime = System.nanoTime();
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
				long estimatedTime = System.nanoTime() - startTime;
				System.out.printf("Array Resize %d to %d : Estimated Time : %d\n", vertCount, arraySize, estimatedTime);
			}
			vert[vertCount] = vertLabel;
			vertCount++;
			long estimatedVertTime = System.nanoTime() - startVertTime;
			System.out.printf("Add Vertex : Estimated Time : %d\n", estimatedVertTime);
		}
    } // end of addVertex()
	
    
    public void addEdge(T srcLabel, T tarLabel) {
    	long startTime = System.nanoTime();
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
    	long estimatedTime = System.nanoTime() - startTime;
    	System.out.printf("Add Edge : Estimated Time : %d\n", estimatedTime);
    } // end of addEdge()
	

    public ArrayList<T> neighbours(T vertLabel) {
    	long startTime = System.nanoTime();
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
        long estimatedTime = System.nanoTime() - startTime;
    	System.out.printf("Neighbours : Estimated Time : %d\n", estimatedTime);
        return neighbours;
    } // end of neighbours()
    
    
    @SuppressWarnings("unchecked")
	public void removeVertex(T vertLabel) {
    	long startTime = System.nanoTime();
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
        long estimatedTime = System.nanoTime() - startTime;
     	System.out.printf("Remove vertex : Estimated Time : %d\n", estimatedTime);
    } // end of removeVertex()
	
    
    public void removeEdge(T srcLabel, T tarLabel) {
    	long startTime = System.nanoTime();
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
        long estimatedTime = System.nanoTime() - startTime;
    	System.out.printf("Remove edge : Estimated Time : %d\n", estimatedTime);
    } // end of removeEdges()
	
    
    public void printVertices(PrintWriter os) {
    	long startTime = System.nanoTime();
        for(int i=0 ; i < vertCount ; i++){
        	os.println(vert[i]);
        }
        long estimatedTime = System.nanoTime() - startTime;
    	System.out.printf("Print Vertices : Estimated Time : %d\n", estimatedTime);
    } // end of printVertices()
	
    
    public void printEdges(PrintWriter os) {
    	long startTime = System.nanoTime();
        for(int i=0 ; i < vertCount ; i++){
        	for(int j=0 ; j < vertCount ; j++){
        		if(matrix[i][j]==1){
        			os.printf("%s %s\n", vert[i], vert[j]);
        		}
        	}
        }
        long estimatedTime = System.nanoTime() - startTime;
    	System.out.printf("Print Edges : Estimated Time : %d\n", estimatedTime);
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	long startTime = System.nanoTime();
    	//both vertices have to exist
    	//print to system.err if one does not exist
    	//Set indices if vertex exists
    	int vert1Index = -1, vert2Index= -1, dfs; //dfs = Distance from source
    	int infinite = Integer.MAX_VALUE;
        for(int i=0 ; i < vertCount; i++){
        	if(vert[i].equals(vertLabel1)){
        		vert1Index = i;
        	}
        	if(vert[i].equals(vertLabel2)){
        		vert2Index = i;
        	}
        }
    
        if(vert1Index != -1 && vert2Index != -1){
	    	int dist[] = new int[vertCount];
			Boolean visited[] = new Boolean[vertCount]; //Vertex visited
			for (int i = 0 ; i < vertCount ; i++){
				dist[i] = infinite; //init as infinite to show not reached yet
				visited[i] = false;
			}
			dist[vert1Index]= 0; //Distance between vertex and itself is 0
			for (int i=0 ; i < vertCount-1 ; i++){
				dfs = minDistance(dist, visited);
				visited[dfs] = true;
				for (int x=0 ; x < vertCount ; x++){
					// Update dist[v] only if is not in sptSet, there is an
					// edge from u to v, and total weight of path from src to
					// v through u is smaller than current value of dist[v]
					if (visited[x] == false && matrix[dfs][x] != 0 && dist[dfs] != infinite && dist[dfs] + matrix[dfs][x] < dist[x]){
						dist[x] = dist[dfs] + matrix[dfs][x];
					}
				}
			}
			if(dist[vert2Index] > 0 && dist[vert2Index] != infinite){
		        long estimatedTime = System.nanoTime() - startTime;
		    	System.out.printf("Print Edges : Estimated Time : %d\n", estimatedTime);
				return dist[vert2Index];
			}
        }
        long estimatedTime = System.nanoTime() - startTime;
    	System.out.printf("Print Edges : Estimated Time : %d\n", estimatedTime);
        // if we reach this point, source and target are disconnected
        return disconnectedDist;    	
    } // end of shortestPathDistance()
    
    int minDistance(int dist[], Boolean visited[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index=-1;
 
        for (int i=0 ; i < vertCount ; i++)
            if (visited[i] == false && dist[i] <= min){
                min = dist[i];
                min_index = i;
            }
 
        return min_index;
    }
    
}  // end of class AdjMatrix