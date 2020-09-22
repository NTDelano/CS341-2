package delano;


/**
 * The Class BinarySearchTree.
 */
public class BinarySearchTree {

	/** The base of a leaf */
	private Node base;

	/**
	 * Instantiates a new binary search tree.
	 */
	// Constructor
	BinarySearchTree() {
		base = null;
	}

	/**
	 * Checks if BST is empty.
	 *
	 * @return true, if BST is empty
	 */
	public boolean isEmpty() {
		return base == null;
	}

	/**
	 * Search through BST for specified Value.
	 *
	 * @param value The value we are searching for in the BST
	 * @return true, if the BST does contain the value
	 */
	public boolean search(int value) {
		return search(base, value);
	}

	/**
	 * Search for the the specific Node that has the value we are searching for
	 *
	 * @param node 	the node that contains the value we are searching for in the BST
	 * @param value the value that we are searching for in the BST
	 * @return true, if the node with the number we are searching for exists in the BST
	 */
	private boolean search(Node node, int value) {
		boolean found = false;
		while ((node != null) && !found) {
			int nodeval = node.getOrigin();
			if (value < nodeval)
				node = node.getLeft();
			else if (value > nodeval)
				node = node.getRight();
			else {
				found = true;
				break;
			}
			found = search(node, value);
		}
		return found;
	}

	/**
	 * Insert a new value into the BST.
	 *
	 * @param value The value we are inserting into the BST
	 */
	public void insertNode(int value) {
		base = insertNode(base, value);

	}

	/**
	 * Insert a new node with the value we are injecting into the BST
	 *
	 * @param currentNode The Node we are currently examining
	 * @param value       The value we are injecting into a node for the BST
	 * @return BST with the new node
	 */
	public Node insertNode(Node currentNode, int value) {
		if (currentNode == null) {
			currentNode = new Node(value);
		} else {
			if (value <= currentNode.getOrigin())
				currentNode.left = insertNode(currentNode.left, value);
			else
				currentNode.right = insertNode(currentNode.right, value);
		}
		return currentNode;
	}

	/**
	 * Delete a value from the BST
	 *
	 * @param value the value we are wanting to remove from the BST
	 */
	public void deleteNode(int value) {

		if (isEmpty())
			System.out.println("Tree Empty");
		else if (search(value) == false)
			System.out.println("Sorry " + value + " is not present");
		else {
			base = deleteNode(base, value);
		}
	}
	
	

	/**
	 * Delete the node with the value we are removing from the BST
	 *
	 * @param currentNode the node we are currently examining in the BST
	 * @param value       the value we are trying to remove from the BST
	 * @return the new BST without the node we deleted
	 */
	private Node deleteNode(Node currentNode, int value) {
		Node p, p2, removal;
		
		if(currentNode == null) {
			return null;
		}
		

		if (value == currentNode.getOrigin()) {

			Node left,right;
			left = currentNode.getLeft();
            right = currentNode.getRight();
            
			// When node has no children
			if (left == null && right == null) {
				return null;
			}
			// When node has 1 child
			else if (left == null) {
				p = right;
				return p;
			} else if (right == null) {
				p = left;
				return p;
			} 
			//When Node has both children
			else {
				p2 = right;
				p = right;
				while (p.getLeft() != null) {
					p = p.getLeft();
				p.setLeft(left);
				return p2;
				}
			}
			
		}
		if (value < currentNode.getOrigin()) {
			removal = deleteNode(currentNode.getLeft(), value);
			currentNode.setLeft(removal);
		} else {
			removal = deleteNode(currentNode.getRight(), value);
			currentNode.setRight(removal);
		}
		return currentNode;

	}
	
	/**
	 * Puts the values from the binary search tree in chronological order
	 */
	/* Function for inorder traversal */
    public void inorder()
    {
        inorder(base);
    }
    
    /**
	 * Grabs the values of every node and prints them in order.
	 *
	 * @param node Checking if the current node value is greater than left node and less than right node
	 */
    private void inorder(Node node)
    {
        if (node != null)
        {
            inorder(node.getLeft());
            System.out.print(node.origin +" ");
            inorder(node.getRight());
        }
    }

}
