package algorithm.graph.APSP;

import datastructure.graph.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Generic graph computation of the shortest path for each pair of source-target vertexes
 * using the Floyd-Warshall algorithm
 * @param <D> type of the data object in the graph vertexes
 */
public class FloydWarshall<D> implements APSP<D> {

	/**
	 * Constructs a FloydWarshall object exposing the AllPairsShortestPaths method
	 */
	public FloydWarshall() { }

	/**
	 * Computates the shortest paths for each pair of source-target vertexes using the Floyd-Warshall algorithm
	 * and returns the next relation that associates to each pair of source-target vertexes the
	 * first edge in the corresponding shortest path (returns null if there are negative cycles); Cost: O(n^3),
	 * where n is the number of vertexes in the graph
	 * @param graph the graph on which the shortest paths must be computed
	 * @return a map that associates to each vertex another map associating 
	 * to each target destination the next edge in the corresponding shortest path	
	 * (null in case of negative cycles)	
	 */
	public Map<Vertex<D>, Map<Vertex<D>, Edge<D>>> AllPairsShortestPaths(Graph<D> graph) {
		// strutture dati ausiliarie
		Map<Vertex<D>, Map<Vertex<D>, Edge<D>>> next = new HashMap<>(); // next matrix da restituire
		Map<Vertex<D>, Integer> map = new HashMap<>(); // associa ad ogni vertice un suo indice

		// estrae vertici e archi
		ArrayList<Vertex<D>> vert = graph.vertexes();
		ArrayList<Edge<D>> edge = graph.edges();

		for (int i = 0; i < vert.size(); i++) {
			map.put(vert.get(i), i); // associa ad ogni vertice il suo indice
			next.put(vert.get(i), new HashMap<Vertex<D>, Edge<D>>()); // predispone next matrix
		}

		double[][] dist = new double[vert.size()][vert.size()]; // matrice delle distanze

		for (int i = 0; i < vert.size(); i++) {
			for (int j = 0; j < vert.size(); j++) {
				if (i == j) // distanza di un vertice da se stesso uguale a 0
					dist[i][j] = 0.0;
				else { // per indici diversi distanza infinita e nessun cammino per ora trovato
					dist[i][j] = Double.POSITIVE_INFINITY;
				}
				next.get(vert.get(i)).put(vert.get(j), null);
			}
		}

		for (Edge<D> e : edge) { // considera cammini costituiti da un solo arco
			dist[map.get(e.getSource())][map.get(e.getDest())] = e.getWeight();
			next.get(e.getSource()).put(e.getDest(), e);
		}

		for (int k = 0; k < vert.size(); k++) {
			for (int x = 0; x < vert.size(); x++) {
				for (int y = 0; y < vert.size(); y++) {
					if (dist[x][k] + dist[k][y] < dist[x][y]) { // cammino migliore grazie a k
						dist[x][y] = dist[x][k] + dist[k][y]; // aggiorna distanza
						next.get(vert.get(x)).put(vert.get(y),
							next.get(vert.get(x)).get(vert.get(k))); // aggiorna next
					}
				}
			}
		}

		// controllo presenza cicli negativi
		for (int x = 0; x < vert.size(); x++) {
			if (dist[x][x] < 0)
				return null; // ciclo negativo che include vertice con indice x
		}

		return next;
	}
}
