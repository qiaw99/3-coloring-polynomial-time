package threeColoring;

import java.util.*;

/**
 * detect all cycles
 * @author 87290
 *
 */
public class GFG2 {
 
    public static final int N = 100000;
 
    // variables to be used
    // in both functions
    @SuppressWarnings("unchecked")
    static Vector<Integer>[] adj = new Vector[N];
    
    @SuppressWarnings("unchecked")
    static Vector<Integer>[] cycles = new Vector[N];
    static int cyclenumber;
    
    @SuppressWarnings("unchecked")
	public static Vector<Integer>[] tetragram = new Vector[N];
    public static int tetragramNumber = 0;
    
    @SuppressWarnings("unchecked")
	public static Vector<Integer>[] pentagram = new Vector[N];
    public static int pentagramNumber = 0;
    
    @SuppressWarnings("unchecked")
	public static Vector<Integer>[] hexagram = new Vector[N];
    public static int hexagramNumber = 0;
 
    // Function to mark the vertex with
    // different colors for different cycles
    @SuppressWarnings("unchecked")
	static void dfs_cycle(int u, int p, int[] color, @SuppressWarnings("rawtypes") Vector[] mark, int[] par) {
 
        // already (completely) visited vertex.
        if (color[u] == 2)
        {
            return;
        }
 
        // seen vertex, but was not completely visited -> cycle detected.
        // backtrack based on parents to find the complete cycle.
        if (color[u] == 1){
 
            cyclenumber++;
            int cur = p;
            mark[cur].add(cyclenumber);
            // backtrack the vertex which are
            // in the current cycle thats found
            while (cur != u)
            {
                cur = par[cur];
                
                mark[cur].add(cyclenumber);
            }
            return;
        }
        par[u] = p;
 
        // partially visited.
        color[u] = 1;
 
        // simple dfs on graph
        for (int v : adj[u]) {
 
            // if it has not been visited previously
            if (v == par[u]) {
                continue;
            }
            dfs_cycle(v, u, color, mark, par);
        }
 
        // completely visited.
        color[u] = 2;
    }
 
    // add the edges to the graph
    public static void addEdge(int u, int v) {
        adj[u].add(v);
        adj[v].add(u);
    }
 
    // Function to print the cycles
    public static void printCycles(int edges, Vector<Integer> mark[]) {
 
        // push the edges that into the
        // cycle adjacency list
        for (int i = 1; i <= edges; i++) {
            if (!mark[i].isEmpty()) {
            	Iterator<Integer> iterator = mark[i].iterator();
            	int jInteger;
            	
            	while(iterator.hasNext()) {
            		jInteger = iterator.next();
            		cycles[jInteger].add(i);
            	}
        	}
        }
 
        // print all the vertex with same cycle
        for (int i = 1; i <= cyclenumber; i++){
            // Print the i-th cycle
            System.out.printf("Cycle Number %d: ", i);
            for (int x : cycles[i])
                System.out.printf("%d ", x);
            System.out.println();
        }
    }
 
    // Driver Code
    @SuppressWarnings("unchecked")
	public static void main(String[] args) {
 
        for (int i = 0; i < N; i++) {
            adj[i] = new Vector<>();
            cycles[i] = new Vector<>();
        }
 
        // add edges
        addEdge(1, 2);
        addEdge(2, 3);
        addEdge(3, 4);
        addEdge(1, 4);
        addEdge(4, 6);
        addEdge(4, 7);
        addEdge(5, 6);
        addEdge(3, 5);
        addEdge(7, 8);
        addEdge(6, 10);
        addEdge(5, 9);
        addEdge(10, 11);
        addEdge(11, 12);
        addEdge(11, 13);
        addEdge(12, 13);
 
        // arrays required to color the
        // graph, store the parent of node
        int[] color = new int[N];
        int[] par = new int[N];
 
        // mark with unique numbers
        Vector<Integer>[] mark = new Vector[N];
        for (int i = 0; i < N; i++) {
            mark[i] = new Vector<>();
        }
        
        for (int i = 0; i < cyclenumber; i++) {
            tetragram[i] = new Vector<>();
            pentagram[i] = new Vector<>();
            hexagram[i] = new Vector<>();
        }
 
        // store the numbers of cycle
        cyclenumber = 0;
        int edges = 13;
 
        // call DFS to mark the cycles
        dfs_cycle(1, 0, color, mark, par);
 
        // function to print the cycles
        printCycles(edges, mark);
        
        for(int i = 0; i < cyclenumber; i++) {
        	if(cycles[i].size() == 4) {
        		tetragram[tetragramNumber] = cycles[i];
        	} else if(cycles[i].size() == 5) {
        		pentagram[pentagramNumber] = cycles[i];
        	} else if(cycles[i].size() == 6) {
        		hexagram[hexagramNumber] = cycles[i];
        	}
        }
    }
}
