package threeColoring;

import java.util.ArrayList;
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
 
    public static final int N = 13;
    
    // Number of iterations and identified pairs
    public static int iterations = 0;
 
    @SuppressWarnings("unchecked")
    public static Vector<Integer>[] adj = new Vector[N];
    
	public static Vector<Integer> coloring = new Vector<Integer>(N);
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
    
    public static int numberOfVertices = 0;
    //public static int pairNumber = 0;
    
//    public static Vector<Integer> tempSafeVertexPair = new Vector<Integer>(2);
//    
//    public static int safeCounter = 0;
    
    public static ArrayList<Vector<Integer>[]> storedGraphs = new ArrayList<Vector<Integer>[]>(N);
    
    @SuppressWarnings("unchecked")
	public static Vector<Integer>[] originalGraphVectors = new Vector[N];
     
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
    	numberOfVertices -= 1;
    	
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
    	//adj[v].removeAllElements();	
    	
    	// Self-connected
    	// adj[v].add(v);
    	
    	// store the removed vertices
    	identifiedPairs[iterations] = new Vector<Integer>();
    	identifiedPairs[iterations].add(u);
    	identifiedPairs[iterations].add(v);
    	//pairNumber++;
    	//iterations++;
    	
    	// recalculate the number of multigrams
    	if(length == 4) {
    		tetragramNumber -= 1;
    		adj[v].removeAllElements();	
    		removeMultigrams(u, v, 4);
    	} else if(length == 5) {
//    		pentagramNumber -= 1;
//    		removeMultigrams(u, v, 5);
    	} else {
    		hexagramNumber -= 1;
    		adj[v].removeAllElements();	
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
    				break;
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
     * Delete v_2, v_3, v_4 from x_2, x_3, x_4
     * @param u
     * @param v
     */
    public static void removeVerticesInPentagram(int u, int v) {
    	if(adj[u].contains(v)) {
    		adj[u].removeElement(v);
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
    	Vector<Integer>[] tempVectors = adj.clone();
    	storedGraphs.add(tempVectors);
    }
    
    @SuppressWarnings("unchecked")
	public static int getNeighborInPentagram(Vector<Integer> pentagramVector, int i) {
    	Vector<Integer> tempIntegers = (Vector<Integer>) adj[pentagramVector.get(i)].clone();
		   if(i != 0) {
			   tempIntegers.removeElement(pentagramVector.get(i-1));
			   tempIntegers.removeElement(pentagramVector.get(i+1));
			   return tempIntegers.get(0);
		   } else {
			   tempIntegers.removeElement(pentagramVector.get(4));
			   tempIntegers.removeElement(pentagramVector.get(i+1));
			   return tempIntegers.get(0);
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
			for(int j = i+1; j < 4; j++) {
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
    	int x_2 = neighbors[0];
     	int v_5 = pentagramVector.lastElement();
    	int iterationCounter = 0;
    	
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
    		} else {
    			iterationCounter += 1;
    			if(iterationCounter == adj[v_5].size()) {
    				return true;
    			}
    		}
    	}
    	
		return true;
    }
    
    /**
     * Check whether every path in G \ {v_1, v_2, v_3, v_4} of length at most three 
     * from x_3 to x_4 has length exactly two 
     * @param pentagramVector
     * @param neighbors
     * @return true/false
     */
    public static boolean thirdConditionPentagram(Vector<Integer> pentagramVector, int[] neighbors) {
    	int x_3 = neighbors[2];
    	int x_4 = neighbors[3];
    	
    	Iterator<Integer> sIterator = adj[x_3].iterator();
    	Iterator<Integer> tIterator = adj[x_4].iterator();
    	int s;
    	int t;
    	int iterationCounter = 0;
    	while(sIterator.hasNext()) {
    		s = sIterator.next();
    		if(s != pentagramVector.get(0) && s != pentagramVector.get(1) && 
    				s != pentagramVector.get(2) && s != pentagramVector.get(3)) {
    			while(tIterator.hasNext()) {
    				t = tIterator.next();
    				if(t != pentagramVector.get(0) && t != pentagramVector.get(1) && 
    	    				t != pentagramVector.get(2) && t != pentagramVector.get(3)) {
    					if(adj[s].contains(t)) {
    						return false;
    					}
    				}
    			}
    		} else {
    			iterationCounter ++;
    			if(iterationCounter == adj[x_3].size()) {
    				return true;
    			}
    		}
    	}
    	
    	return true;
    }
    
    /**
     * Check three condition according to the definition of safety of pentagram
     * @param pentagramVector
     * @return
     */
    public static boolean testSafetyOfPentagram(Vector<Integer> pentagramVector) {
    	int[] neighbors = firstConditionPentagram(pentagramVector);
    	if(neighbors.length == 1) {
    		return false;
    	} else {
    		if(secondConditionPentagram(pentagramVector, neighbors)) {
    			return thirdConditionPentagram(pentagramVector, neighbors);
    		} else {
    			return false;
    		}
    	}
    }
    
    /**
     * Get all vertices that need to be identified
     * @param pentagramVector
     * @return indices of identified vertices
     */
    @SuppressWarnings("unchecked")
	public static int[] getSafePentagram(Vector<Integer> pentagramVector) {
    	Vector<Integer> originalVector = pentagramVector;
    	Vector<Integer> anotherVector = (Vector<Integer>) pentagramVector.clone();
    	
    	int first = anotherVector.get(0);
    	int second = anotherVector.get(1);
    	int third = anotherVector.get(2);
    	int fourth = anotherVector.get(3);
    	
    	int[] neighbors;
    	
    	anotherVector.set(0, fourth);
    	anotherVector.set(3, first);
    	anotherVector.set(2, second);
    	anotherVector.set(1, third);
    	
    	if(testSafetyOfPentagram(originalVector)) {
    		neighbors = firstConditionPentagram(originalVector);
    		return new int[] {neighbors[1], originalVector.get(4), neighbors[2], neighbors[3]};
    	} else if(testSafetyOfPentagram(anotherVector)) {
    		neighbors = firstConditionPentagram(anotherVector);
    		return new int[] {neighbors[1], anotherVector.get(4), neighbors[2], neighbors[3]};
    	} else {
    		return null;
    	}
    }
    
    /**
     * Identify x_2 with v_5, x_3 with x_4 and remove the identified pentagram
     * @param pentagramVector
     */
    @SuppressWarnings("unchecked")
	public static void identifyPentagram(Vector<Integer> pentagramVector) {
    	Vector<Integer> storedPentagramIntegers = (Vector<Integer>) pentagramVector.clone();
    	int[] identifiedVertices = getSafePentagram(pentagramVector);
    	if(identifiedVertices != null) {
	    	identifyVertices(identifiedVertices[0], identifiedVertices[1], 5);
	    	iterations += 1;
	    	identifyVertices(identifiedVertices[2], identifiedVertices[3], 5);
	    	
	    	removeVerticesInPentagram(pentagramVector.get(4), identifiedVertices[0]);
	    	removeVerticesInPentagram(identifiedVertices[2], pentagramVector.get(2));
	    	removeVerticesInPentagram(identifiedVertices[3], pentagramVector.get(3));
	    	
			pentagramNumber -= 1;
			removeMultigrams(pentagramVector.get(0), pentagramVector.get(1), 5);
			
			// Remove v_1, v_2, v_3, v_4
			Integer temp;
			for(int i = 0; i < 4; i++) {
				temp = storedPentagramIntegers.get(i);
				adj[temp].removeAllElements();
				// adj[temp].add(temp);
			}
			
			
			numberOfVertices -= 4;
			
			//TODO Test code:
			// Store all intermediate graphs
			Vector<Integer>[] tempVectors = adj.clone();
			storedGraphs.add(tempVectors);
    	}
    }
    
    /**
     * The main function to get the proper 3-coloring in the triangle-free planar graph
     */
    public static void getThreeColoring() {
    	System.out.println("The info of input graph: ");
    	detectMultigrams();
        printMultigrams();
        printInformation();
        System.out.println("Remaining #vertex: " + numberOfVertices);
        System.out.println();
        
    	while(tetragramNumber != 0 || pentagramNumber != 0 || hexagramNumber != 0) {
    		if(tetragramNumber != 0) {
    			identifyMultigram(tetragram[tetragramNumber - 1]);
    			System.out.println("Identified vertices: " + identifiedPairs[iterations] + " in " + iterations + "-th iteration");
    			iterations += 1;
    		} else if(pentagramNumber != 0) {
    			identifyPentagram(pentagram[pentagramNumber - 1]);
    			System.out.println("Identified vertices: " + identifiedPairs[iterations - 1] + " in " + (iterations - 1) + "-th iteration");
    			System.out.println("Identified vertices: " + identifiedPairs[iterations] + " in " + iterations + "-th iteration");
    		} else {
    			identifyMultigram(hexagram[hexagramNumber - 1]);
    			System.out.println("Identified vertices: " + identifiedPairs[iterations] + " in " + iterations + "-th iteration");
    			iterations += 1;
    		}
    		
    		// iterations += 1;
            detectMultigrams();
            printMultigrams();
            printInformation();
            System.out.println("Remaining #vertex: " + numberOfVertices);
            System.out.println();
    	}
    	
    	
    	System.out.println("Now removing vertices with minimum degree smaller than three:");
    	// TODO remove single vertices
    	for(int i = 0; i < N; i++) {
			if(numberOfVertices >= 0) {
	    		if(getDegree(i) == 0) {
	    			continue;
	    		} else if(getDegree(i) == 1 || getDegree(i) == 2) {
	    			adj[i].removeAllElements();
	    			adj[i].add(i);
	    			
	    			Vector<Integer>[] tempVectors = adj.clone();
	    	    	storedGraphs.add(tempVectors);
	    			identifiedPairs[iterations] = new Vector<Integer>();
	    			identifiedPairs[iterations].add(i);
	    			
	    			numberOfVertices -= 1;
	    			
	    			System.out.println("Removed vertex: " + i + " with degree " + getDegree(i));
	    			System.out.println("Remaining #vertex: " + numberOfVertices);
	    			System.out.println();
	    			
	    			iterations++;
	    		} else {
	    			continue;
	    		}
			}
    	}
    }
    
    /**
     * Initialize all adjacency lists
     */
    public static void initializeMultigrams() {
    	for (int i = 0; i < N; i++) {
            adj[i] = new Vector<>();
        }
    }
    
    /**
     * Initialize adjacency lists, edges, multigrams and colorings
     */
    @SuppressWarnings({ "unused" })
	public static void initialize() {
    	initializeMultigrams();
 
        // add edges
        addEdge(1, 2);
        addEdge(2, 3);
        addEdge(3, 4);
        addEdge(4, 5);
//        addEdge(6, 5);
//        addEdge(1, 6);
//        addEdge(1, 7);
//        addEdge(7, 8);
//        addEdge(8, 9);
//        addEdge(9, 10);
//        addEdge(7, 10);
        
        addEdge(1, 5);
        addEdge(1, 6);
        addEdge(2, 7);
        addEdge(3, 8);
        addEdge(4, 9);
        addEdge(6, 10);
        addEdge(6, 12);
        addEdge(10, 11);
        addEdge(11, 12);
        
        numberOfVertices = 12;
        
        // Store the original input graph
        originalGraphVectors = adj.clone();
 
        // arrays required to color the
        // graph, store the parent of node
        int[] color = new int[N];
        int[] par = new int[N];
 
        // mark with unique numbers
        // Vector<Integer>[] mark = new Vector[N];
        for (int i = 0; i < N; i++) {
            // mark[i] = new Vector<>();
            originalGraphVectors[i] = new Vector<>();
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
		System.out.println(adj[7]);
		
		System.out.println(adj[9]);
		
//		for(int i = 0; i < iterations; i++) {
//			Vector<Integer>[] tempVectors = storedGraphs.get(i);
//			for(int j = 0; j < tempVectors.length; j++) {
//				Iterator<Integer> tempIterator = tempVectors[j].iterator();
//				int v;
//				while(tempIterator.hasNext()) {
//					v = tempIterator.next();
//					System.out.println(v);
//				}
//			}
//		}
		
//        for(int i = 0; i < pairNumber; i++)
//        	System.out.println(identifiedPairs[i]);
        
        
    }
}

// TODO in for loop removed vertices, remove them from its neighbor's adj list nad recursivly detec them 