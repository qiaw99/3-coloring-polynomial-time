package utils;

import java.util.HashSet;
import java.util.Iterator;

import qiaw99.Vertex;

public class Tools {
	public static Vertex identify_vertices(Vertex v1, Vertex v2) {
		HashSet<Vertex> tempHashSet = v1.getNeighborSet();
		tempHashSet.addAll(v2.getNeighborSet());
		v1.setNeighborSet(tempHashSet);
		return v1;
	}
	
	public static void print_hashset(HashSet<Vertex> hashSet) {
		Iterator i = hashSet.iterator();
		while(i.hasNext()){
			System.out.print(i.next());
		}
	}
}
