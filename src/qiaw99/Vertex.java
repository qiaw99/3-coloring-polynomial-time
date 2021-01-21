package qiaw99;

import java.util.HashSet;

public class Vertex {
	private int num;
	private int color;
	private HashSet<Vertex> neighborSet;
	
	public Vertex() {}
	
	public Vertex(int num, HashSet<Vertex> neighborSet) {
		this.num = num;
		this.neighborSet = neighborSet;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public HashSet<Vertex> getNeighborSet() {
		return neighborSet;
	}

	public void setNeighborSet(HashSet<Vertex> neighborSet) {
		this.neighborSet = neighborSet;
	}

	@Override
	public String toString() {
		return "Vertex [num=" + num + ", color=" + color + ", neighborSet=" + neighborSet + "]";
	}
}

