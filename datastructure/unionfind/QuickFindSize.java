package datastructure.unionfind;

/**
 * Implementation of a generic Union Find data structure composed of nodes of type 
 * QuickFindSizeNode containing <code>data</code> objects, based on Quick Find
 * with heuristic on the size: the implementation of union checks what is 
 * the shortest list to concatenate and updates the pointers to the root only 
 * for its elements.
 * @param <D> type of the data object 
 */	

public class QuickFindSize<D> extends QuickFind<D> {
			
	/**
	 * Creates an union find structure following the Quick Find implementation
	 * with heuristic on the size
     */		
	public QuickFindSize() { }	
	
	/**
	 * Inserts a node with a given data object by creating a list with only such node
	 * and initializing the size to 1
   	 * @param data data to insert
	 * @return the inserted node
	 */
	@Override
	public UnionFindNode<D> makeSet(D data) {
		QuickFindSizeNode<D> n = new QuickFindSizeNode<D>(data);
		nodes.add(n);
		return n;
	}
	
	/**
	 * Performs the union of two sets represented by two nodes by using the heuristic
	 * on the size: it checks what is the shortest list to concatenate and updates the 
	 * pointers to the root only for its elements	 
	 * (if the nodes are not representatives, or they are identical
	 * nothing is done); Amortized Cost: O(log n) where n
	 * is the number of nodes in the data structure
	 * <ul>
	 * <li> Worst-case: &Theta;(n)
	 * <li> Best-case: O(1) 
	 * </ul>
   	 * @param node1 the representative of the first set 
     * @param node2 the representative of the second set 
	 */	
	@Override	
	public void union(UnionFindNode<D> node1, UnionFindNode<D> node2) {
		if (!node1.equals(node2) && (node1.isRepresentative() && node2.isRepresentative())) { 
			QuickFindSizeNode<D> n1 = (QuickFindSizeNode<D>) node1; // down casting so we have access to the size
			QuickFindSizeNode<D> n2 = (QuickFindSizeNode<D>) node2; // down casting so we have access to the size
			if (n1.size >= n2.size) {
				super.union(node1, node2); //rende node2 figlio di node1
				n1.size = n1.size + n2.size;
				//controlla se il rango aumenta
			} else {
				super.union(node2, node1); //rende node1 figlio di node2
				n2.size = n1.size + n2.size;
			}
		}

	}
}
