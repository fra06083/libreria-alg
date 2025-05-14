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
	 * first edge in the corresponding shortest path (returns null if there are negative cycles); Cost: O(n<sup>3</sup>),
	 * where n is the number of vertexes in the graph
	 * @param graph the graph on which the shortest paths must be computed
	 * @return a map that associates to each vertex another map associating 
	 * to each target destination the next edge in the corresponding shortest path	
   	 * (null in case of negative cycles)	
	 */	
	public  Map<Vertex<D>, Map<Vertex<D>,Edge<D>>> AllPairsShortestPaths(Graph<D> graph) {

		
		return null;	
	}

}

