package binarytree;

/*
 * Reference Video:
 * https://www.youtube.com/watch?v=M6lYob8STMI
 * https://www.youtube.com/watch?v=UcOxGmj45AA
 */
public class BinaryTree {
	Node root;
	public void addNode(int newKey, String newName) {
		/*
		 * Add Node algorithm 
		 */
		Node newNode = new Node(newKey, newName);
		if ( root == null ) {
			root = newNode;
		} else {
			Node focusNode = root;
			Node parent;
			
			while ( true ) {
				parent = focusNode;
				
				if ( newKey < focusNode.key ) {
					focusNode = focusNode.leftChild;
					if ( focusNode == null ) {
						parent.leftChild = newNode;
						return;
					}
				} else {
					focusNode = focusNode.rightChild;
					
					if ( focusNode == null ) {
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
	}
	public void inOrderTraverseTree(Node focusNode) {
		if ( focusNode != null ) {
			inOrderTraverseTree(focusNode.leftChild);
			System.out.println(focusNode);
			inOrderTraverseTree(focusNode.rightChild);
		}
	}
	
	public void preOrderTraverseTree(Node focusNode) {
		if ( focusNode != null ) {
			System.out.println(focusNode);
			preOrderTraverseTree(focusNode.leftChild);
			preOrderTraverseTree(focusNode.rightChild);
		}
	}
	
	public void postOrderTraverseTree(Node focusNode) {
		if ( focusNode != null ) {
			postOrderTraverseTree(focusNode.leftChild);
			postOrderTraverseTree(focusNode.rightChild);
			System.out.println(focusNode);
		}
	}
	public Node findNode(int key) {
		Node focusNode = root;
		while (focusNode.key != key) {
			if (key < focusNode.key) {
				focusNode = focusNode.leftChild;				
			} else {
				focusNode = focusNode.rightChild;
			}
			if (focusNode == null) {
				return null;
			}
		}
		return focusNode;
	}
	public boolean remove(int key) {
		Node focusNode = root;
		Node parent = root;
		
		boolean isItALeftChild = true;
		
		while ( focusNode.key != key ) {
			parent = focusNode;
			
			if ( key < focusNode.key ) {
				isItALeftChild = true;
				focusNode = focusNode.leftChild;
			} else {
				isItALeftChild = false;
				focusNode = focusNode.rightChild;
			}
			if ( focusNode == null ) {
				return false;
			} 
		}
		
		if ( focusNode.leftChild == null && focusNode.rightChild == null ) {
			if ( focusNode == root ) {
				root = null;
			} else if ( isItALeftChild ) {
				parent.leftChild = null;
			} else {
				parent.rightChild = null;
			}
		} else if ( focusNode.rightChild == null ) {
			if ( focusNode == root ) {
				root = focusNode.leftChild;
			} else if ( isItALeftChild ) {
				parent.leftChild = focusNode.leftChild;
			} else {
				parent.rightChild = focusNode.leftChild;
			}
		} else if ( focusNode.leftChild == null ) {
			if ( focusNode == root ) {
				root = focusNode.rightChild;
			} else if ( isItALeftChild ){
				parent.leftChild = focusNode.rightChild;
			} else {
				parent.rightChild = focusNode.leftChild;
			} 
		} else {
			Node replacement = getReplacementNode( focusNode );
			if ( focusNode == root ) {
				root = replacement;
			} else if ( isItALeftChild ) {
				parent.leftChild = replacement;
			} else {
				parent.rightChild = replacement;
			}
			replacement.leftChild = focusNode.leftChild;
			
		}
		
		return true;
	}
	
	public Node getReplacementNode( Node replacedNode ) {
		Node replacementParent = replacedNode;
		Node replacement = replacedNode;
		
		Node focusNode = replacedNode.rightChild;
		
		while ( focusNode != null ) {
			replacementParent = replacement;
			replacement = focusNode;
			focusNode = focusNode.leftChild;
		}
		if ( replacement != replacedNode.rightChild ) {
			replacementParent.leftChild = replacement.rightChild;
			replacement.rightChild = replacedNode.rightChild;	
		}
		return replacement;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTree theTree = new BinaryTree();
		theTree.addNode(50, "Boss");
		theTree.addNode(25, "Vice President");
		theTree.addNode(15, "Office Manager");
		theTree.addNode(2, "Intern");
		theTree.addNode(18, "Data Entry");
		theTree.addNode(30, "Secretary");
		theTree.addNode(75, "Sales Manager");
		theTree.addNode(85, "Salesman 1");
		System.out.println("==================================================");
		theTree.inOrderTraverseTree(theTree.root);
		System.out.println("==================================================");
		//theTree.preOrderTraverseTree(theTree.root);
		//theTree.postOrderTraverseTree(theTree.root);
		System.out.println("Search for 30: " + theTree.findNode(30));
		System.out.println("\nRemove Key 25\n");
		theTree.remove(25);
		System.out.println("==================================================");
		theTree.inOrderTraverseTree(theTree.root);
		System.out.println("==================================================");
		System.out.println("testing commit");
	}

}
class Node {
	int key;
	String name;
	Node leftChild;
	Node rightChild;
	
	Node (int key, String name){
		this.key = key;
		this.name = name;		
	}
	public String toString() {
		return name + " has a key " + key;
	}
}
