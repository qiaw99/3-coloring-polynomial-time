package Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

/**
 * 
 * @author Qianli Wang
 * @email qiaw99@zedat.fu-berlin.de
 * @since 01.04.2021
 * @category Graph Theory -- 3-COLORING Problem
 * @description Bachelor Thesis at Freie Universität from work group theoretical computer science
 * @supervised Prof. Dr. László Kozma
 *
 */
public class GFG3 {
 
    public static final int N = 100;
 
    @SuppressWarnings("unchecked")
    public static Vector<Integer>[] adj = new Vector[N];
    
	public static Vector<Integer> color = new Vector<Integer>(N);
	public static int[] avaiableColor = new int[]{1, 2, 3};
    
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
    
    public static Vector<Integer> tempSafeVertexPair = new Vector<Integer>(2);
    
    public static int safeCounter = 0;
    
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
     * Test the degree of each vertex in pentagram: v_1, v_2, v_3, v_4 have degree exactly 3
     * @param pentagramVector
     * @return true/false
     */
    public static int[] testPentagram(int[] pentagramVector) {
    	int numberOfDegreeThreeVertex = 0;
		int index = 0;
		
    	for(int i = 0; i < pentagramVector.length; i++) {
    		if(getDegree(pentagramVector[i]) == 3) {
    			numberOfDegreeThreeVertex += 1;
    		} else {
    			index = i;
    		}
    	}
    	
    	if(numberOfDegreeThreeVertex < 4) {
    		return new int[] {-1, -1};
    	}
    	return new int[] {1, index};
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
			    							int[] tempPentagram = new int[] {v, v1, v2, v3, v4};
			    							if(adj[v].contains(v4) && contain(tempPentagram)) {
			    								int[] temp = testPentagram(tempPentagram);
			    								if(temp[0] == 1) {
			    									int index = temp[1];
				    								pentagram[pentagramNumber].add(tempPentagram[(index + 1) % 5]);
				    								pentagram[pentagramNumber].add(tempPentagram[(index + 2) % 5]);
				    								pentagram[pentagramNumber].add(tempPentagram[(index + 3) % 5]);
				    								pentagram[pentagramNumber].add(tempPentagram[(index + 4) % 5]);
				    								pentagram[pentagramNumber].add(tempPentagram[index]);
		    										pentagramNumber++;
			    								}
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
    	
    	// Self-connected
    	adj[v].add(v);
    	
    	// store the removed vertices
    	identifiedPairs[pairNumber] = new Vector<Integer>();
    	identifiedPairs[pairNumber].add(u);
    	identifiedPairs[pairNumber].add(v);
    	pairNumber++;
    	
    	// recalculate the number of multigrams
    	if(length == 4) {
    		tetragramNumber -= 1;
    		removeMultigrams(u, v, 4);
    	} else if(length == 5) {
    		// TODO remove the following lines 
    		pentagramNumber -= 1;
    		removeMultigrams(u, v, 5);
    	} else {
    		hexagramNumber -= 1;
    		removeMultigrams(u, v, 6);
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
     * Remove duplicate neighbors from the adjacency list
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
     * @return If the tetragram/hexagram is safe then return the index of pivot. Otherwise, -1.
     */
    public static int testSafeMultigram(Vector<Integer> multigramVector) {
    	int size = multigramVector.size();
    	int tempElement;
    	
		for(int i = 0; i < size / 2; i++) {
			int v = multigramVector.get(i);
			int u = multigramVector.get((i + 2) % size);

			Iterator<Integer> v_1 = adj[v].iterator();
	    	int v1;
	    	int v_size = adj[v].size();
	    	int tempCounter = 0;
	    	while(v_1.hasNext()) {
	    		v1 = v_1.next();
	    		
	    		// The case that there are vertices outside the multigram 
	    		if(!multigramVector.contains(v1)) {
	    			if(adj[v1].contains(u)) {
	    				continue;
	    			} else {
	    				Iterator<Integer> v_2 = adj[v1].iterator();
	    	    		int v2;
	    	    		while(v_2.hasNext()) {
	    	    			v2 = v_2.next();
	    	    			if(v2 != v) {
	    	    				if(!multigramVector.contains(v2)) {
	    	    					if(adj[v2].contains(u)) {
	    	    						continue;
	    	    					} else {
	    	    						return i;
	    	    					}
	    	    				} else {
	    	    					continue;
	    	    				}
	    	    			} else {
	    	    				continue;
	    	    			}
	    	    		}
	    			}
	    		} else {
	    			// The case that every vertex is on the multigram
	    			tempCounter += 1;
	    			if(tempCounter == v_size) {
	    				return i;
	    			}
	    			if(adj[u].contains(v1)) {
	    				tempElement = v1;
	    				continue;
	    				// return i;
	    			}
	    		}
	    	}
		}
		return -1;
    }
    
    /**
     * Identify the given tetragram(detect the right pivot and identify vertices)
     * @param tetragramIntegers
     */
    public static void identifyMultigram(Vector<Integer> multigramVector) {
    	int size = multigramVector.size();
    	int temp = testSafeMultigram(multigramVector);
    	
    	// If the multigram is safe
    	if(temp != -1) {
    		int u = multigramVector.get(temp);
	    	int v = multigramVector.get((temp + 2) % size);
	    	identifyVertices(u, v, size);
    	}    	
    }
    
    @SuppressWarnings("unchecked")
	public static int getNeighborInPentagram(Vector<Integer> pentagramVector, int i) {
    	Vector<Integer> tempIntegers = (Vector<Integer>) adj[pentagramVector.get(i)].clone();
		   if(i != 0) {
			   tempIntegers.remove(pentagramVector.get(i-1));
			   tempIntegers.remove(pentagramVector.get(i+1));
			   return pentagramVector.get(0);
		   } else {
			   tempIntegers.remove(pentagramVector.get(4));
			   tempIntegers.remove(pentagramVector.get(i+1));
			   return pentagramVector.get(0);
		   }
    }
    
    /**
     * Check whether every neighbors are distinct and pairwise non-adjacent
     * @param pentagramVector
     * @return true/false
     */
    public static int[] firstConditionPentagram(Vector<Integer> pentagramVector) {
		int x_1, x_2, x_3, x_4;

		x_1 = getNeighborInPentagram(pentagramVector, 0);
		x_2 = getNeighborInPentagram(pentagramVector, 1);
		x_3 = getNeighborInPentagram(pentagramVector, 2);
		x_4 = getNeighborInPentagram(pentagramVector, 3);
		
		int[] neighbors = new int[] {x_1, x_2, x_3, x_4};
		
		for(int i = 0; i < 3; i++) {
			for(int j = 1; j < 4; j++) {
				if(neighbors[i] == neighbors[j] || adj[neighbors[i]].contains(neighbors[j])) {
					return new int[] {-1};
				}
			}
		}
		
    	return neighbors;
    }
    
    /**
     * Check whether there is no path in G \ {v_1, v_2, v_3, v_4} of length at most three from x_2 to v_5
     * @param pentagramVector
     * @param neighbors
     * @return true/false
     */
    public static boolean secondConditionPentagram(Vector<Integer> pentagramVector, int[] neighbors) {
    	int x_2 = neighbors[1];
    	int v_5 = pentagramVector.lastElement();
    	
    	Iterator<Integer> sIterator = adj[v_5].iterator();
    	int s; 
    	while(sIterator.hasNext()) {
    		s = sIterator.next();
    		if(s != pentagramVector.get(0) && s != pentagramVector.get(1) && 
    				s != pentagramVector.get(2) && s != pentagramVector.get(3)) {
    			if(adj[x_2].contains(s)) {
    				return false;
    			}
    			
    			Iterator<Integer> tIterator = adj[x_2].iterator();
    			int t;
    			while(tIterator.hasNext()) {
    				t = tIterator.next();
    				if(t != pentagramVector.get(0) && t != pentagramVector.get(1) && 
    	    				t != pentagramVector.get(2) && t != pentagramVector.get(3)) {
    					if(adj[x_2].contains(t)) {
    						return false;
    					}
    				}
    			}
    		}
    	}
    	
		return true;
    }
    
    // TODO
    public static boolean thirdConditionPentagram(Vector<Integer> pentagramVector, int[] neighbors) {
    	return false;
    }
    
    // TODO pentagram 12345 43215
    public static boolean testSafePentagram(Vector<Integer> pentagramVector) {
    	int[] neighbors = firstConditionPentagram(pentagramVector);
    	if(neighbors.length == 1) {
    		
    	} else {
    		// TODO
    		if(secondConditionPentagram(pentagramVector, neighbors)) {
    			return thirdConditionPentagram(pentagramVector, neighbors);
    		} else {
    			return false;
    		}
    	}
		return false;
    }
    
    /**
     * The main function to get the proper 3-coloring in the triangle-free planar graph
     */
    public static void getThreeColoring() {
    	System.out.println("The info of input graph: ");
    	detectMultigrams();
        printMultigrams();
        printInformation();
        System.out.println();
        
        int iterations = 0;
        
    	while(tetragramNumber != 0 || pentagramNumber != 0 || hexagramNumber != 0) {
    		if(tetragramNumber != 0) {
    			identifyMultigram(tetragram[tetragramNumber - 1]);
    		} else if(pentagramNumber != 0) {
    			// identifyMultigram(pentagram[tetragramNumber - 1]);
    		} else {
    			identifyMultigram(hexagram[hexagramNumber - 1]);
    		}
    		System.out.println("Identified vertices: " + identifiedPairs[iterations]);
    		iterations += 1;
            detectMultigrams();
            printMultigrams();
            printInformation();
            System.out.println();
    	}
    	
//    	for(int i = 0; i < N; i++) {
//    		if(adj[i].size() == 1) {
//    			
//    		} else if(adj[i].size() == 2) {
//    			
//    		} else {
//    			
//    		}
//    	}
    }
    
    public static void initializeMultigrams() {
    	for (int i = 0; i < N; i++) {
            adj[i] = new Vector<>();
        }
    }
    
    /**
     * Initialize adjacency lists, edges, multigrams and colorings
     */
    @SuppressWarnings({ "unchecked", "unused" })
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
 
    public static void printInformation() {
    	System.out.println("Number of tetragram: " + tetragramNumber);
        System.out.println("Number of pentagram: " + pentagramNumber);
        System.out.println("Number of hexagram: " + hexagramNumber);
    }
    
    // Driver Code
	public static void main(String[] args) {
		
		initialize();
 
//        System.out.println("Before identifying: ");
//        detectMultigrams();
//        printMultigrams();
//        printInformation();
//        for(int i = 0; i < N; i++) {
//			System.out.println(hexagram[i]);
//		}
        
//        System.out.println();
//        System.out.println("After identifying 1, 3: ");
//        identifyVertices(1, 3, 6);
//        detectMultigrams();
//        printMultigrams();
//        System.out.println("Number of tetragram: " + tetragramNumber);
//        System.out.println("Number of pentagram: " + pentagramNumber);
//        System.out.println("Number of hexagram: " + hexagramNumber);
        
//        System.out.println();
//        System.out.println("Identify a tetragram: ");
//        //identifyVertices(8, 10, 4);
//        identifyMultigram(tetragram[0]);
//        detectMultigrams();
//        printMultigrams();
//        printInformation();
//        
//        System.out.println();
//        System.out.println("Identify a hexagram: ");
//        identifyMultigram(hexagram[0]);
//        detectMultigrams();
//        printMultigrams();
//        printInformation();
        
//        for(int i = 0; i < N; i++) {
//			System.out.println(tetragram[i]);
//		}
        
//        System.out.println();
//        System.out.println("Identify a hexagram: ");
//        identifyMultigram(tetragram[0]);
//        detectMultigrams();
//        printMultigrams();
//        printInformation();
                
//        System.out.println(adj[1]);
//        System.out.println(adj[3]);
//        System.out.println(adj[8]);
//        System.out.println(adj[10]);
		
		getThreeColoring();
		
//        for(int i = 0; i < pairNumber; i++)
//        	System.out.println(identifiedPairs[i]);
        
        
    }
}
// TODO removed multigrams

