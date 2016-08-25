//
public class myLinkedList {

	private Node headNode;
	private Node tailNode;
	private int listCount;
	@SuppressWarnings("unused")
	private int removed;
	//self made linked list from scratch
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
	public boolean removeEdgeNode(String source, String target){
		Node current = headNode;
		Node previous = null;
		Boolean result = false;
		if (current.getVertice().equalsIgnoreCase(source) && current != tailNode){
			previous = current;
			current = current.getNextNode();
			for(int i = 0; i < listCount -1 ; i++){
				if (current.getVertice().equalsIgnoreCase(target) && current != tailNode){
					previous.setNextNode(current.getNextNode());
					current = null;
					result = true;
					listCount--;
					break;
				}
				else 
					if (current.getVertice().equalsIgnoreCase(target) && current == tailNode){
						if (headNode.getNextNode() != tailNode){
							tailNode = previous;
						}
						current = null;
						result = true;
						listCount--;
						
						break;
				}
				else{
					previous = current;
					current = current.getNextNode();
				}
			}
	
			current = headNode;
		}
		else if (current.getVertice().equalsIgnoreCase(source) && current == tailNode){
			if (tailNode.getVertice().equalsIgnoreCase(target)){
				tailNode = headNode;
				current = null;
				listCount--;
			}
			
		}
			
		return result;
		
	}
	
	public boolean searchNode(String searchValue){
		Node current = headNode;
		for(int i = 0; i < listCount; i++){
			if (searchValue.equalsIgnoreCase(current.getVertice())){
				return true; //edge exists
			}
			if (current.getNextNode()!= null){
				current = current.getNextNode();
			}
		}
		return false;
	}
}
