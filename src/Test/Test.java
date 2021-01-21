package Test;

import java.util.HashSet;

import qiaw99.Vertex;

public class Test {
	public static void main(String args[]) {
		Vertex v2 = new Vertex();
		v2.setNum(2);
		HashSet<Vertex> v1_nHashSet = new HashSet<Vertex>();
		v1_nHashSet.add(v2);
		Vertex v1 = new Vertex(1, v1_nHashSet);
//		System.out.println(v1);
		
		Vertex v3 = new Vertex();
		v3.setNum(3);
		HashSet<Vertex> v4_nHashSet = new HashSet<Vertex>();
		v4_nHashSet.add(v3);
		Vertex v4 = new Vertex(4, v4_nHashSet);
//		System.out.println(v4);
		
		Vertex tempVertex = utils.Tools.identify_vertices(v1, v4);
		utils.Tools.print_hashset(tempVertex.getNeighborSet());
	}
}
