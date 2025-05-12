package algorithm.graph.traversal;

import datastructure.graph.*;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.HashMap;

/**
 * Implementation of the BFS traversal for graphs containing generic data. Returns an ArrayList of VisitedVertex<D>
 * @param <D> type of the data object in the graph vertexes
 */
public class BFS<D> implements Traversal<D> {
	
	/**
   	 * Constructs a BFS object exposing the visit method
	 */	
	public BFS() {
		// empty on purpose because there is nothing to initialize
	}
	
	/**
	 * Performs a visit of a passed graph and returns tha ArrayList with the visited vertexes
	 * created as objects of type VisitedVertexBFS<D>
	 * @param graph the graph to visit
	 * @param source the initial source vertex (irrelevant in case of DFS)
	 * @return the ArrayList with the visited vertexes
	 */	
	public ArrayList<VisitedVertex<D>> visit(Graph<D> graph, Vertex<D> source) {
		Map<Vertex<D>, Boolean> visited = new HashMap<Vertex<D>, Boolean>();
		ArrayList<VisitedVertex<D>> l = new ArrayList<VisitedVertex<D>>(); //ArrayList da ritornare
		Queue<Vertex<D>> q = new ArrayDeque<>(); //Coda FIFO per visita
		ArrayList<Vertex<D>> vert = graph.vertexes(); //Estrae vertici dal grafo
		for (Vertex<D> v : vert) {
			visited.put(v, false);
		} //Marcature a false
		visited.put(source, true); //Marca source
		l.add(new VisitedVertexBFS<D>(source,null)); //Source non ha padre
		q.add(source); //Inserisce sorce in coda
		while (!q.isEmpty()) {
			Vertex<D> u = (Vertex<D>) q.remove(); //Estrae nuovo vertice per la visita
			for (Edge<D> e : graph.outEdges(u)) { //Controlla adiacenze
				Vertex<D> v = e.getDest();
				if (!visited.get(v)) { //Se non ancora marcato, inserisce in coda
					visited.put(v, true);
					l.add(new VisitedVertexBFS<D>(v, u)); //u è padre di v
					q.add(v);
				}
			}
		}
		return l;
	}
		
}
