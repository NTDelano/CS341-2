package delano;

/**
 * The Class Node.
 */
public class Node {
	//NOTE: All data members are public
	/** The origin. */
	//		Setters and Getters are not needed
	int origin;
	
	/** The right. */
	Node left,right;
	
	/**
	 * Instantiates a new node.
	 */
	public Node() {
		left = null;
		right = null;
		origin = 0;
	}
	
	/**
	 * Instantiates a new node.
	 *
	 * @param item the root the BST
	 */
	//Constructor
	public Node(int item) {
		left = null;
		right = null;
		origin = item;
	}
	 
 	/**
	 * Sets the left node.
	 *
	 * @param node the new left node
	 */
 	/* Function to set left node */
    public void setLeft(Node node)
    {
        left = node;
    }
    
    /**
	 * Sets the right node.
	 *
	 * @param node the new right node
	 */
    /* Function to set right node */ 
    public void setRight(Node node)
    {
        right = node;
    }
	 
 	/**
	 * Gets the left node value.
	 *
	 * @return the left node value
	 */
 	/* Function to get left node */
    public Node getLeft()
    {
        return left;
    }
    
    /**
	 * Gets the right node value.
	 *
	 * @return the right node value
	 */
    /* Function to get right node */
    public Node getRight()
    {
        return right;
    }
    
    /**
	 * Sets the origin node value.
	 *
	 * @param o the new origin node value
	 */
    /* Function to set data to node */
    public void setOrigin(int o)
    {
        origin = o;
    }
    
    /**
	 * Gets the origin node value.
	 *
	 * @return the origin node value
	 */
    /* Function to get data from node */
    public int getOrigin()
    {
        return origin;
    }     
}
