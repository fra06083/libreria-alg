package datastructure.unionfind;

import java.util.ArrayList;

/**
 * Implementation of a generic Union Find data structure composed of nodes of type 
 * QuickFindNode containing <code>data</code> objects, based on Quick Find.
 * The nodes belonging to the same set are linked in a list and all the nodes
 * in the list point to a common root which contains pointers to the head and the 
 * tail of the list. The head of the list is the representative of the set.
 * The find operation is implemented by returning the head of the list a node
 * belogs to (to extract the head it is sufficient to access the root).
 * The union operation is implemented by concatenating the two lists representing
 * the two sets, updating the root for the elements of the second list (which will
 * point to the root of the first list), and by updating the tail pointer of the 
 * root of the new set.
 * @param <D> type of the data object 
 */	
public class QuickFind<D> implements UnionFind<D> {
	
	/** ArrayList containing the union find nodes */ 
	protected ArrayList<UnionFindNode<D>> nodes;

	/**
	 * Creates an union find structure following the Quick Find implementation
	 */			
	public QuickFind() { 
		nodes = new ArrayList<UnionFindNode<D>>();
	}	
	
	/**
	 * Inserts a node with a given data object by creating a list with only such node
   	 * @param data data to insert
	 * @return the inserted node
	 */
	public UnionFindNode<D> makeSet(D data) {
		UnionFindNode<D> n = new QuickUnionNode<D>(data); //crea il nodo per il nuovo dato
		nodes.add(n); //aggiunge il nodo all'arraylist dei nodi
		return n; //restituisce il nodo appena creato
	}
	
	/**
	 * Performs the union of two sets represented by two nodes 
	 * (if the nodes are not representatives, or they are identical
	 * nothing is done); Cost: O(n) where n
	 * is the number of nodes in the data structure.
	 * If the nodes are not representatives, or they are identical
	 * nothing is done.
	 * <ul>
	 * <li> Worst-case: &Theta;(n)
	 * <li> Best-case: O(1) 
	 * </ul>
   	 * @param node1 the representative of the first set 
     * @param node2 the representative of the second set 
	 */	
	public void union(UnionFindNode<D> node1, UnionFindNode<D> node2) {
		if (!node1.equals(node2) && (node1.isRepresentative() && node2.isRepresentative())) { 
			QuickFindNode<D> n1 = (QuickFindNode<D>) node1; // down casting so we have access to the root
			QuickFindNode<D> n2 = (QuickFindNode<D>) node2; // down casting so we have access to the root
			n1.root.tail.next = n2; //collega le liste di fratelli
			n1.root.tail = n2.root.tail; //aggiorna campo tail
			while (n2 != null){
				n2.root = n1.root;
				n2 = n2.next;
			}
		}
	}
	
	/**
	 * Returns the representative of the set to which the node belongs to; Cost: O(1)
   	 * @param node the node to consider 
     * @return the representative of the set to which the node belongs to
	 */
	public UnionFindNode<D> find(UnionFindNode<D> node) {
		QuickFindNode<D> n = (QuickFindNode<D>) node;
		QuickFindNode<D> rootNode = n.root.head; // access the head of the root directly
		return (UnionFindNode<D>) rootNode; // return the representative
	}
	
	/**
	 * Returns a printable string representation of Union Find structure. 
	 * For each node prints its contents and the corresponding representative. 
	 * Cost &Theta;(n), where n is the number of nodes in the structure
	 * @return string representation of the> Union Find structure
	 */
	@Override
	public String toString() {
		StringBuilder S = new StringBuilder(nodes.size()*10);
		for (int i=0; i<nodes.size(); i++) {
			UnionFindNode<D> n = nodes.get(i);
			S = S.append(n.getData().toString()).append(" - ").append(find(n).getData().toString()).append("\n");
		}
		return S.toString();
	}

}
