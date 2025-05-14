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
	public Map<Vertex<D>,Edge<D>> SingleSourceShortestPaths(Graph<D> graph, Vertex<D> source) {


		return null;		
	}

}

