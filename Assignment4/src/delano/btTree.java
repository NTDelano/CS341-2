package delano;


/**
 * The Class btTree, for testing the binary search tree.
 */
public class btTree {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		//New Tree
		BinarySearchTree bt = new BinarySearchTree();

		//Check Tree starts out empty
		assert bt.isEmpty(): "Should be an empty BST";
		
		//Insert 6
		int value = 6;
		bt.insertNode(value);
		assert bt.search(value): " Doesn't contain "+value;
		bt.inorder(); System.out.println("");
		
		//Insert 4
		value = 4;
		bt.insertNode(4);
		assert bt.search(value): " Doesn't contain "+value;
		bt.inorder(); System.out.println("");
		
		//Insert 8
		value = 8;
		bt.insertNode(8);
		assert bt.search(value): " Doesn't contain "+value;
		bt.inorder(); System.out.println("");
		
		//Insert 3
		value = 3;
		bt.insertNode(3);
		assert bt.search(value): " Doesn't contain "+value;
		bt.inorder(); System.out.println("");
		
		//Insert 5
		value = 5;
		bt.insertNode(5);
		assert bt.search(value): " Doesn't contain "+value;
		bt.inorder(); System.out.println("");
		
		//Insert 7
		value = 7;
		bt.insertNode(7);
		assert bt.search(value): " Doesn't contain "+value;
		bt.inorder(); System.out.println("");
		
		//Remove 3
		value = 3;
	    bt.deleteNode(value);
	    assert bt.search(value)==false: " Still contains "+value;
	    bt.inorder(); System.out.println("");
	    
	    //Remove 8
	    value = 8;
	    bt.deleteNode(value);
	    assert bt.search(value)==false: " Still contains "+value;
	    bt.inorder(); System.out.println("");
	    
	    //Remove 5
	    value = 5;
	    bt.deleteNode(value);
	    assert bt.search(value)==false: " Still contains "+value;
	    bt.inorder(); System.out.println("");
	    	

	}

}
