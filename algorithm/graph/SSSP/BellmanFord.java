package algorithm.graph.SSSP;

import datastructure.graph.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Generic graph computation of the shortest paths from a given source vertex using the Bellman-Ford algorithm
 * @param <D> type of the data object in the graph vertexes
 */	
public class BellmanFord<D> implements SSSP<D> {

	/**
	 * Constructs a BellmanFord object exposing the SingleSourceShortestPaths method
	 */	
	public BellmanFord() { }
	
	/**
	 * Calcola i cammini minimi da una sorgente usando Bellman-Ford.
	 * 
	 * 1. Crea strutture dati ausiliarie:
	 *    - distance: stima della distanza minima dalla sorgente a ciascun vertice.
	 *    - parent: mappa ogni vertice all'arco entrante nell'albero dei cammini minimi.
	 * 2. Estrae la lista dei vertici e degli archi dal grafo.
	 * 3. Inizializza:
	 *    - Tutte le distanze a infinito e parent a null.
	 *    - La distanza della sorgente a 0.
	 * 4. Esegue |V|-1 iterazioni di rilassamento su tutti gli archi:
	 *    - Se trova un cammino più breve verso un vertice, aggiorna distanza e parent.
	 * 5. Controlla la presenza di cicli negativi raggiungibili dalla sorgente:
	 *    - Se trova un ulteriore rilassamento possibile, ritorna null.
	 * 6. Restituisce la mappa parent che rappresenta l'albero dei cammini minimi.
	 * 
	 * @param graph il grafo su cui calcolare i cammini minimi
	 * @param source il vertice sorgente
	 * @return la mappa dei cammini minimi o null se esistono cicli negativi raggiungibili
	 */
	public Map<Vertex<D>, Edge<D>> SingleSourceShortestPaths(Graph<D> graph, Vertex<D> source) {
		// Strutture dati ausiliarie
		Map<Vertex<D>, Double> distance = new HashMap<Vertex<D>, Double>(); // stima della distanza
		Map<Vertex<D>, Edge<D>> parent = new HashMap<>();  // parent nell'albero dei cammini minimi

		// Estrae vertici e archi
		ArrayList<Vertex<D>> vert = new ArrayList<>(graph.vertexes());
		ArrayList<Edge<D>> edge = new ArrayList<>(graph.edges());

		// Inizializzazione strutture dati
		for (Vertex<D> v : vert) {
			distance.put(v, Double.POSITIVE_INFINITY);
			parent.put(v, null);
		}
		distance.put(source, 0.0);

		// Effettua le iterazioni per i possibili rilassamenti
		for (int i = 1; i < vert.size(); i++) {
			for (Edge<D> e : edge) { // Effettua i possibili rilassamenti
				Vertex<D> u = e.getSource();
				Vertex<D> v = e.getDest();
				double weight = e.getWeight();
				if (distance.get(u) + weight < distance.get(v)) {
					distance.put(v, distance.get(u) + weight);
					parent.put(v, e);
				}
			}
		}

		// Controllo presenza cicli negativi raggiungibili da source
		for (Edge<D> e : edge) {
			Vertex<D> u = e.getSource();
			Vertex<D> v = e.getDest();
			double weight = e.getWeight();
			if (distance.get(u) + weight < distance.get(v)) {
				return null;
			}
		}

		return parent;
	}
}
