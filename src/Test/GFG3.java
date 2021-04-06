package Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

public class GFG3 {
 
    public static final int N = 11;
 
    // variables to be used
    // in both functions
    @SuppressWarnings("unchecked")
    public static Vector<Integer>[] adj = new Vector[N];
    
	public static Vector<Integer> color = new Vector<Integer>(N);
    
    @SuppressWarnings("unchecked")
	public static Vector<Integer>[] tetragram = new Vector[N];
    public static int tetragramNumber = 0;
    
    @SuppressWarnings("unchecked")
	public static Vector<Integer>[] pentagram = new Vector[N];
    public static int pentagramNumber = 0;
    
    @SuppressWarnings("unchecked")
	public static Vector<Integer>[] hexagram = new Vector[N];
    public static int hexagramNumber = 0;
    
    @SuppressWarnings("unchecked")
	public static Vector<Integer>[] identifiedPairs = new Vector[N];
    public static int pairNumber = 0;
    
    /**
     * Get the degree of certain vertex
     * @param v
     * @return number of neighbors
     */
    public static int getDegree(int v) {
    	return adj[v].size();
    }
    
    /**
     * Check whether a multigram is already contained 
     * @param v
     * @return true / false
     */
    public static boolean contain(int[] v) {
    	if(v.length == 4) {
	    	for(int i = 0; i < tetragram.length; i++) {
	    		int counter = 0;
	    		for(int j = 0; j < 4; j++) {
	    			if(tetragram[i].contains(v[j])) {
	    				counter ++;
	    			}
	    		}
	    		if(counter == 4) {
	    			return false;
	    		}
	    	}	
	    	return true;
    	} else if(v.length == 5) {
    		for(int i = 0; i < pentagram.length; i++) {
	    		int counter = 0;
	    		for(int j = 0; j < 5; j++) {
	    			if(pentagram[i].contains(v[j])) {
	    				counter ++;
	    			}
	    		}
	    		if(counter == 5) {
	    			return false;
	    		}
	    	}	
	    	return true;
    	} else {
    		for(int i = 0; i < hexagram.length; i++) {
	    		int counter = 0;
	    		for(int j = 0; j < 6; j++) {
	    			if(hexagram[i].contains(v[j])) {
	    				counter ++;
	    			}
	    		}
	    		if(counter == 6) {
	    			return false;
	    		}
	    	}	
	    	return true;
    	}
    }
    
    /**
     * Detect all multigrams by 6 inner-loops
     */
    public static void detectMultigrams() {
    	
    	int v;
    	for(v = 1; v < N; v++) {

    		Iterator<Integer> v_1 = adj[v].iterator();
    		while(v_1.hasNext()) {
    			int v1 = v_1.next();
    			if(v1 != v) {
	    			Iterator<Integer> v_2 = adj[v1].iterator();
	    			
	    			while(v_2.hasNext()) {
	    				int v2 = v_2.next();
	    				
	    				if(v2 != v && v2 != v1) {
		    				Iterator<Integer> v_3 = adj[v2].iterator();
		    				
		    				while(v_3.hasNext()) {
		    					int v3 = v_3.next();
		    					if(v3 != v2 && v3 != v1 && v3 != v) {
		    						// Detected a tetragram
			    					if(adj[v].contains(v3) && contain(new int[] {v, v1, v2, v3})) {
			    						tetragram[tetragramNumber].add(v);
			    						tetragram[tetragramNumber].add(v1);
			    						tetragram[tetragramNumber].add(v2);
			    						tetragram[tetragramNumber].add(v3);
			    						tetragramNumber++;
			    					}
			    					
			    					Iterator<Integer> v_4 = adj[v3].iterator();
			    					while(v_4.hasNext()) {
			    						int v4 = v_4.next();
			    						if(v4 != v3 && v4 != v2 && v4 != v1 && v4 != v) {
			    							// Detected a pentagram
			    							if(adj[v].contains(v4) && contain(new int[] {v, v1, v2, v3, v4})) {
			    								pentagram[pentagramNumber].add(v);
			    								pentagram[pentagramNumber].add(v1);
			    								pentagram[pentagramNumber].add(v2);
			    								pentagram[pentagramNumber].add(v3);
			    								pentagram[pentagramNumber].add(v4);
	    										pentagramNumber++;
			    							}
			    							
			    							Iterator<Integer> v_5 = adj[v4].iterator();
				    						while(v_5.hasNext()) {
				    							int v5 = v_5.next();
				    							if(v4 != v5 && v5 != v3 && v5 != v2 && v5!=v && v5 != v1) {
				    								// Detected a hexagram
				    								if(adj[v].contains(v5) && contain(new int[] {v, v1, v2, v3, v4, v5})) {
				    									hexagram[hexagramNumber].add(v);
				    									hexagram[hexagramNumber].add(v1);
				    									hexagram[hexagramNumber].add(v2);
				    									hexagram[hexagramNumber].add(v3);
				    									hexagram[hexagramNumber].add(v4);
				    									hexagram[hexagramNumber].add(v5);
				    									hexagramNumber++;
				    								}
				    							} else {
				    		    					continue;
				    		    				}
				    						}
			    						} else {
			    	    					continue;
			    	    				}
	 		    					}
		    					} else {
		        					continue;
		        				}
		    				}
	    				} else {
	    					continue;
	    				}
	    			}
    			} else {
    				continue;
    			}
    		}
    	}
    }
 
    /**
     * Add edges to the graph
     * @param u
     * @param v
     */
    public static void addEdge(int u, int v) {
        adj[u].add(v);
        adj[v].add(u);
    }

    /**
     * Print out all multigrams
     */
    public static void printMultigrams() {
    	System.out.println("Tetragram: ");
    	for(int i = 0; i < N; i++) {
        	if(tetragram[i].size() != 0) {
        		System.out.println(tetragram[i]);
        	}
        }
    	
    	System.out.println("Pentagram: ");
    	for(int i = 0; i < N; i++) {
    		if(pentagram[i].size() != 0) {
    			System.out.println(pentagram[i]);
    		}
		}
    	
    	System.out.println("Hexagram: ");
    	for(int i = 0; i < N; i++) {
    		if(hexagram[i].size() != 0) {
    			System.out.println(hexagram[i]);
    		}
		}
    }
    
    
    /**
     * Identify vertices to reduce size of the graph
     * @param u
     * @param v
     */
    @SuppressWarnings("unchecked")
	public static void identifyVertices(int u, int v, int length) {
    	Vector<Integer> tempIntegers = (Vector<Integer>) adj[u].clone();
    	tempIntegers.addAll((Collection<? extends Integer>) adj[v].clone());
    	
    	// Get all distinct neighbors
    	Set<Integer> adjacentNeighborSet = new HashSet<Integer>();
    	for(int i : tempIntegers) {
    		if(!adjacentNeighborSet.contains(i))
    			adjacentNeighborSet.add(i);
    	}
    	
    	// Add distinct neighbors to u
    	Iterator<Integer> iterator = adjacentNeighborSet.iterator();
    	int temp;
    	while(iterator.hasNext()) {
    		temp = iterator.next();
    		if(!adj[u].contains(temp)) {
    			adj[u].add(temp);
    		}
    	}
    	handleNeighbors(u, v);
    	adj[v].removeAllElements();	
    	
    	// store the removed vertices
    	identifiedPairs[pairNumber] = new Vector<Integer>();
    	identifiedPairs[pairNumber].add(u);
    	identifiedPairs[pairNumber].add(v);
    	pairNumber++;
    	
    	// recalculate the number of multigrams
    	if(length == 4) {
    		tetragramNumber -= 1;
    		removeMultigrams(u ,v, 4);
    	} else if(length == 5) {
    		pentagramNumber -= 1;
    		removeMultigrams(u ,v, 5);
    	} else {
    		hexagramNumber -= 1;
    		removeMultigrams(u ,v, 6);
    	}
    }
    
    /**
     * Remove the multigrams in which vertices are identified
     * @param u
     * @param v
     * @param length
     */
    public static void removeMultigrams(int u, int v, int length) {
    	if(length == 4) {
    		for(int i = 0; i < N; i++) {
    			if(tetragram[i].contains(u) && tetragram[i].contains(v)) {
    				tetragram[i].removeAllElements();
    			}
    		}
    	} else if(length == 5) {
    		for(int i = 0; i < N; i++) {
    			if(pentagram[i].contains(u) && pentagram[i].contains(v)) {
    				pentagram[i].removeAllElements();
    			}
    		}
    	} else {
    		for(int i = 0; i < N; i++) {
    			if(hexagram[i].contains(u) && hexagram[i].contains(v)) {
    				hexagram[i].removeAllElements();
    			}
    		}
    	}
    }
    
    /**
     * Remove duplicate neighbors in adjacency list
     * @param u
     * @param v
     */
    public static void handleNeighbors(int u, int v) {
    	for(int i = 0; i < N; i++) {
    		if(adj[i].contains(v)) {
    			adj[i].removeElement(v);
    			if(!adj[i].contains(u)) {
    				adj[i].add(u);
    			}
    		}
    	}
    }
    
    /**
     * Safety of tetragrams: there is no path from v_1 to v_3 at most three that is not part of tetragram
     * @param tetragramIntegers
     * @return whether the tetragram is safe
     */
    public static boolean testSafeTetragram(Vector<Integer> tetragramIntegers) {
		for(int i = 0; i < 2; i++) {
			int v = tetragramIntegers.get(i);
			// int s = tetragramIntegers.get((i + 1) % 4);
			int u = tetragramIntegers.get((i + 2) % 4);
			// int t = tetragramIntegers.get((i + 3) % 4);
    	
			Iterator<Integer> v_1 = adj[v].iterator();
	    	int v1;
	    	while(v_1.hasNext()) {
	    		v1 = v_1.next();
	    		if(!tetragramIntegers.contains(v1) && adj[v1].contains(u)) {
	    			return false;
	    		}
	    		
	    		Iterator<Integer> v_2 = adj[v1].iterator();
	    		int v2;
	    		while(v_2.hasNext()) {
	    			v2 = v_2.next();
	    			if(v2 != v) {
	    				if(!tetragramIntegers.contains(v2) && adj[v2].contains(u)) {
	    	    			return false;
	    	    		}
	    			}
	    		}
	    	}
		}
    	return true;
    	
    }
    
    public static void getThreeColoring() {
    	
    }
    
    public static void initializeMultigrams() {
    	for (int i = 0; i < N; i++) {
            adj[i] = new Vector<>();
        }
    }
    
    /**
     * Initialize adjacency lists, edges, multigrams and colorings
     */
    @SuppressWarnings("unchecked")
	public static void initialize() {
    	initializeMultigrams();
 
        // add edges
        addEdge(1, 2);
        addEdge(2, 3);
        addEdge(3, 4);
        addEdge(4, 5);
        addEdge(6, 5);
        addEdge(1, 6);
        addEdge(1, 7);
        addEdge(7, 8);
        addEdge(8, 9);
        addEdge(9, 10);
        addEdge(7, 10);
 
        // arrays required to color the
        // graph, store the parent of node
        int[] color = new int[N];
        int[] par = new int[N];
 
        // mark with unique numbers
        Vector<Integer>[] mark = new Vector[N];
        for (int i = 0; i < N; i++) {
            mark[i] = new Vector<>();
        }
        
        for (int i = 0; i < N; i++) {
            tetragram[i] = new Vector<>();
            pentagram[i] = new Vector<>();
            hexagram[i] = new Vector<>();
            identifiedPairs[i] = new Vector<>();
        }
    }
 
    // Driver Code
	public static void main(String[] args) {
		initialize();
 
        System.out.println("Before identifying: ");
        detectMultigrams();
        printMultigrams();
        System.out.println("Number of tetragram: " + tetragramNumber);
        System.out.println("Number of pentagram: " + pentagramNumber);
        System.out.println("Number of hexagram: " + hexagramNumber);
        
        System.out.println();
        System.out.println("After identifying 1, 3: ");
        identifyVertices(1, 3, 6);
        detectMultigrams();
        printMultigrams();
        System.out.println("Number of tetragram: " + tetragramNumber);
        System.out.println("Number of pentagram: " + pentagramNumber);
        System.out.println("Number of hexagram: " + hexagramNumber);
        
        System.out.println();
        System.out.println("After identifying 8, 10: ");
        identifyVertices(8, 10, 4);
        detectMultigrams();
        printMultigrams();
        
        System.out.println("Number of tetragram: " + tetragramNumber);
        System.out.println("Number of pentagram: " + pentagramNumber);
        System.out.println("Number of hexagram: " + hexagramNumber);
                
//        System.out.println(adj[1]);
//        System.out.println(adj[3]);
//        System.out.println(adj[8]);
//        System.out.println(adj[10]);
        for(int i = 0; i < pairNumber; i++)
        	System.out.println(identifiedPairs[i]);
        
        
    }
}
// TODO removed multigrams

