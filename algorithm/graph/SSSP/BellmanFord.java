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
	 * Computes the shortest paths from a given source vertex using the Bellman-Ford algorithm
	 * and returns the tree of the shortes paths represented as a map from each vertex to the 
	 * incoming edge in the tree (returns null if there are negative cycles); Cost: O(n m),
	 * where n and m are respectively the number of vertexes and edges in the graph
	 * @param graph the graph on which the shortest paths must be computed	
	 * @param source the initial source vertex
	 * @return the tree of the shortest paths represented as a map from each vertex to the incoming 
	 *  edge in the tree (null in case of negative cycles)	
	 */	
	public Map<Vertex<D>,Edge<D>> SingleSourceShortestPaths(Graph<D> graph, Vertex<D> source) {
		
		
		return null;
	}


}



