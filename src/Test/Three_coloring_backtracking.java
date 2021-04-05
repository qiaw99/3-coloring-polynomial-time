package Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
 
public class Three_coloring_backtracking {
	
	// Number of vertices
	private int V; 
	
	// Adjacency list
	private LinkedList<Integer> adj[]; 
 
	@SuppressWarnings("unchecked")
	public Three_coloring_backtracking(int v) {
		V = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; ++i) {
			adj[i] = new LinkedList<Integer>();
		}
	}
 
	/**
	 * Add edge (v, w) into undirected graph
	 * @param v
	 * @param w
	 */
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v); 
	}
 
	/**
	 * Assign colors to all vertices, 
	 * print out the corresponding color of each vertex
	 */
	public void greedyColoring() {
		
		// The target result list
		int result[] = new int[V];
 
		// Initialize all unassigned vertices with -1 
		Arrays.fill(result, -1);
 
		// Assign the first color to the first vertex 
		result[0] = 0;
 
		/**
		 * Using available list to store all available colors 
		 * that can be assigned.
		 * 
		 * If available[cr] == false, the color cr has been assigned to
		 * its neighbor(s)
		 */
		boolean available[] = new boolean[V];
 
		// Initially, all colors are available to be assigned 
		Arrays.fill(available, true);
 
		// Assign colors to all other (V-1) vertices
		for (int u = 1; u < V; u++) {

			// Iterate all neighbors and mark their colors as unavailable
			Iterator<Integer> it = adj[u].iterator();
			while (it.hasNext()) {
				int i = it.next();
				if (result[i] != -1) {
					available[result[i]] = false;
				}
			}
 
			// Find out the first available color 
			int cr = 0;
			for (; cr < V; cr++) {
				if (available[cr]) {
					break;
				}
			}
			
			// Assign the color to vertex u
			result[u] = cr; 
 
			// Set all colors as available and begin the next iteration
			Arrays.fill(available, true);
		}
 
		// Print out the result
		for (int u = 0; u < V; u++) {
			System.out.println("Vertex " + u + " --->  Color " + result[u]);
		}
	}
 
	public static void main(String args[]) {
		Three_coloring_backtracking g1 = new Three_coloring_backtracking(8);
		g1.addEdge(0, 1);
		g1.addEdge(1, 2);
		g1.addEdge(2, 3);
		g1.addEdge(4, 3);
		g1.addEdge(4, 5);
		g1.addEdge(0, 5);
		g1.addEdge(0, 3);
		g1.addEdge(6, 4);
		g1.addEdge(6, 7);
		g1.addEdge(0, 7);
		System.out.println("Coloring of graph 1");
		g1.greedyColoring();
 
		System.out.println();
		Three_coloring_backtracking g2 = new Three_coloring_backtracking(10);
		g2.addEdge(0, 1);
		g2.addEdge(0, 5);
		g2.addEdge(1, 2);
		g2.addEdge(2, 3);
		g2.addEdge(4, 3);
		g2.addEdge(5, 4);
		g2.addEdge(0, 6);
		g2.addEdge(7, 8);
		g2.addEdge(7, 6);
		g2.addEdge(8, 9);
		g2.addEdge(6, 9);
		g2.addEdge(3, 8);
		System.out.println("Coloring of graph 2 ");
		g2.greedyColoring();
	}
}
