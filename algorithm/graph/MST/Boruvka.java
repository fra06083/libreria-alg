package algorithm.graph.MST;

import java.util.HashMap;
import java.util.Map;
// We need to import the ArrayList class, it is required for the aknowledgment of all the edges
import java.util.ArrayList;

import datastructure.graph.*;
import datastructure.unionfind.*;

/**
 * Generic graph computation of the Minimum Spanning Tree using the Boruvka algorithm
 * @param <D> type of the data object in the graph vertexes
 */
public class Boruvka<D> implements MST<D> {


	/**
	 * Computes a Minimum Spanning Tree of a passed graph using the Boruvka algorithm 
	 * and returns the computed spanning tree represented as a graph
	 * @param graph the graph for which the Minimum Spanning Tree must be computed
	 * @return the graph representing the computed Minimum Spanning Tree
	 */	
	public Graph<D> MinimumSpanningTree(Graph<D> graph) {
        GraphAL<D> T = new GraphAL<D>();
        Map<Vertex<D>, Vertex<D>> vertexMap = new HashMap<Vertex<D>, Vertex<D>>();
		UnionFind<D> uf = new QuickUnionRank<D>();
		Map<Vertex<D>, UnionFindNode<D>> ufnodes = new HashMap<Vertex<D>, UnionFindNode<D>>(); //mappa da vert. a set in uf
        // We need to initialize the union-find structure with all the vertices
        for (Vertex<D> v : graph.vertexes()) {
            vertexMap.put(v, T.addVertex(v.getData()));
            ufnodes.put(v, uf.makeSet(v.getData()));
        }
		int count = graph.vertexNum();
        // we need to get the vertex number and all the edges of the graph
		ArrayList<Edge<D>> allEdges = graph.edges();
        /*  while T has more than 1 component connected, we need to find the minimum edge for each component
             and add it to the MST */
		while (count > 1) {
            // S is initialized as an empty set (first page proj.)
            Map<UnionFindNode<D>, Edge<D>> S = new HashMap<>();
            // for each edge, we need to find the two vertices and their respective sets
            for (Edge<D> edge : allEdges) {
                Vertex<D> u = edge.getSource();
                Vertex<D> v = edge.getDest();
                UnionFindNode<D> setU = uf.find(ufnodes.get(u));
                UnionFindNode<D> setV = uf.find(ufnodes.get(v));
                /* We need to get edge V and edge U  */
                Edge<D> edgeU = S.get(setU);
                Edge<D> edgeV = S.get(setV);

				if (setU != setV) {
                    /*  This is the main difference with the Kruskal algorithm:
                     *  Boruvka, in each iteration picks one minimum weight for every component 
                     *  and adds all such edges simultaneously to S */
					if (!S.containsKey(setU) || edgeU.getWeight() > edge.getWeight()) {
						S.put(setU, edge);
					}
					if (!S.containsKey(setV) || edgeV.getWeight() > edge.getWeight()) {
						S.put(setV, edge);
					}
				}
            }
            /*  for each edge in S, we need to find the two vertices and their respective sets
             *  - values() returns a collection view of the values contained in the map
             *  so we can iterate over the edges in S 
             */
            for (Edge<D> edge : S.values()) {
                Vertex<D> u = edge.getSource();
                Vertex<D> v = edge.getDest();
                double w = edge.getWeight();
                /* We need to find the two vertices and their respective sets
                 * and check if they are in the same set */
                UnionFindNode<D> ru = uf.find(ufnodes.get(u));
                UnionFindNode<D> rv = uf.find(ufnodes.get(v));
                /* If they are not in the same set, we need to add the edge to T */
                if (ru != rv) {
                    uf.union(ru, rv);
                    T.addEdge(vertexMap.get(u), vertexMap.get(v), w);
                    T.addEdge(vertexMap.get(v), vertexMap.get(u), w);
                    count--;
                }
            }
		}
        return T;
	
}

}
