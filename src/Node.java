
public class Node {

	private String vertice;
	private Node nextNode;
	
	public Node(String vertice, Node nextNode){
		this.vertice = vertice;
		this.nextNode = nextNode;
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
