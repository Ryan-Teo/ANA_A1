
public class Node {

	private String vertice;
	private Node nextNode;
	private int path;
	
	public Node(String vertice, Node nextNode, int path){
		this.vertice = vertice;
		this.nextNode = nextNode;
		this.path = path;
	}

	public int getPath() {
		return path;
	}

	public void setPath(int path) {
		this.path = path;
	}

	public String getVertice() {
		return vertice;
	}

	public void setVertice(String vertice) {
		this.vertice = vertice;
	}

	public Node getNextNode() {
		return nextNode;
	}

	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}
	
	
}
