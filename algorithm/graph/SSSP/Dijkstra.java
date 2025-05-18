package algorithm.graph.SSSP;

import datastructure.graph.*;
import datastructure.priorityqueue.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Generic graph computation of the shortest paths from a given source vertex using the Dijkstra algorithm
 * (it assumes non negative edge weights)
 * @param <D> type of the data object in the graph vertexes
 */	
public class Dijkstra<D> implements SSSP<D> {
	
	/**
   	 * Constructs a Dijkstra object exposing the SingleSourceShortestPaths method
	 */	
	public Dijkstra() { }

	/**
	 * Computes the shortest paths from a given source vertex using the Dijkstra algorithm
	 * and returns the tree of the shortes paths represented as a map from each vertex to the 
	 * incoming edge in the tree (it assumes non negative edge weights); Cost: O(m log n),
	 * where n and m are respectively the number of vertexes and edges in the graph
	 * @param graph the graph on which the shortest paths must be computed	
	 * @param source the initial source vertex
	 * @return the tree of the shortest paths represented as a map from each vertex to the incoming edge in the tree	
	 */	
	public Map<Vertex<D>, Edge<D>> SingleSourceShortestPaths(Graph<D> graph, Vertex<D> source) {
		// Strutture dati ausiliarie
		PriorityQueue<Double, Vertex<D>> pq = new DHeap<>();
		Map<Vertex<D>, PriorityQueueNode<Double, Vertex<D>>> pqnodes = new HashMap<>();
		Map<Vertex<D>, Double> distance = new HashMap<>();
		Map<Vertex<D>, Edge<D>> parent = new HashMap<>();

		ArrayList<Vertex<D>> vert = graph.vertexes();
		for (Vertex<D> v : vert) {
			distance.put(v, Double.POSITIVE_INFINITY); // inizializza distanza
			parent.put(v, null); // nessun parent identificato
		}
		distance.put(source, 0.0); // source a distanza 0 da se stessa
		pqnodes.put(source, pq.insert(0.0, source)); // inserimento source in coda

		while (!pq.isEmpty()) { // finché ci sono vertici nella coda
			Vertex<D> u = pq.findMin();
			pq.deleteMin();

			for (Edge<D> e : graph.outEdges(u)) { // controlla le adiacenze
				Vertex<D> d = e.getDest(); // sia d l'adiacenza da considerare
				double newDist = distance.get(u) + e.getWeight();

				if (distance.get(d) == Double.POSITIVE_INFINITY) { // d mai incontrato
					distance.put(d, newDist); // setta distanza
					parent.put(d, e); // indicare il parent momentaneo
					pqnodes.put(d, pq.insert(newDist, d)); // inserisce d in pq
				} else if (distance.get(d) > newDist) {
					// si è trovato un arco migliore per aggiungere d
					distance.put(d, newDist); // aggiorna distanza
					parent.put(d, e); // aggiorna parent
					pq.decreaseKey(newDist, pqnodes.get(d)); // aggiorna chiave in pq
				}
			}
		}
		return parent;
	}
}
