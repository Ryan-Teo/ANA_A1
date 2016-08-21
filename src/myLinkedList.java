
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
		if (current.getVertice() == source && current != tailNode){
			previous = current;
			current = current.getNextNode();
			for(int i = 0; i < listCount -1 ; i++){
				if (current.getVertice() == (target) && current != tailNode){
					previous.setNextNode(current.getNextNode());
					current = null;
					result = true;
					listCount--;
					break;
				}
				else 
					if (current.getVertice() == (target) && current == tailNode){
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
		else if (current.getVertice() == source && current == tailNode){
			if (tailNode.getVertice() == target){
				tailNode = headNode;
				current = null;
				listCount--;
			}
			
		}
			
		return result;
		
	}
	
	public boolean removeVerticeNode(String removeVertice){
		Node current = null;
		
		if (headNode.getVertice() == removeVertice && headNode == tailNode){
			headNode = tailNode = null;
			return true;
		}
		
		else if (headNode.getVertice() == removeVertice && headNode != tailNode){
			current = headNode.getNextNode();
			for(int i = 0; i < listCount - 1; i++){
				headNode.setNextNode(current.getNextNode());
				current = null;
				current = headNode.getNextNode();
			}
			headNode = tailNode = null;
			listCount = 0;
			return true;
		}
		return false;
		
	}
	public boolean searchNode(String searchValue){
		Node current = headNode;
		for(int i = 0; i < listCount; i++){
			if (searchValue == current.getVertice()){
				return true; //edge exists
			}
			if (current.getNextNode()!= null){
				current = current.getNextNode();
			}
		}
		return false;
	}
	public boolean printNode(){
		Node current = headNode;
			for(int i = 0; i < listCount; i++){
				System.out.printf(current.getVertice());
				if (current.getNextNode()!= null){
					current = current.getNextNode();
				}
				else
					return true;
			};
		return true;
	}
}
