
public class myLinkedList {

	private Node headNode;
	private Node tailNode;
	private int listCount;
	@SuppressWarnings("unused")
	private int removed;
	
	public myLinkedList(Node headNode, Node tailNode, int listCount) {
		this.headNode = headNode;
		this.tailNode = tailNode;
		this.listCount = listCount;
	}

	public Node getHeadNode() {
		return headNode;
	}

	public void setHeadNode(Node headNode) {
		this.headNode = headNode;
	}

	public Node getTailNode() {
		return tailNode;
	}

	public void setTailNode(Node tailNode) {
		this.tailNode = tailNode;
	}
	
	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	
	public void addNode(String newVertice){
		Node newNode = new Node(newVertice, null);
		if(headNode == null){
			headNode = newNode;
			tailNode = newNode;
		}
		else{
			tailNode.setNextNode(newNode);
			tailNode = newNode;
		}
		listCount++;
	}
	public boolean removeNode(String removeVertice, int removed){
		Node current = headNode;
		Node previous = null;
		
		if (current.getVertice() == removeVertice){
			headNode = tailNode = null;
			listCount = 0;
			removed++;
			return true;
		}
		else{
			for (int i = 0; i <= listCount; i++){
				previous = current;
				if (current.getVertice() == removeVertice){
					previous.setNextNode(current.getNextNode());
					current = null;
					removed++;
					return true;
				}
				current = headNode.getNextNode();
			}
			return false; 
		}
		
	}
	public boolean searchNode(String searchValue){
		Node current = headNode;
		for (int i = 0; i <= listCount; i++){
			if (searchValue == current.getVertice()){
				return true; //edge exists
			}
		}
		return false;
	}
}
