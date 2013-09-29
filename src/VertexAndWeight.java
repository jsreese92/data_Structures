public class VertexAndWeight implements Comparable<VertexAndWeight> {
	public MyVertex vertex;
	public int weight;

	public VertexAndWeight(MyVertex theVertex, int theWeight) {
		weight = theWeight;
		vertex = theVertex;
	}

	public MyVertex getVertex() {
		return vertex;
	}

	public int getWeight() {
		return weight;
	}

	public int compareTo(VertexAndWeight v) {
		if (this.weight > v.weight)
			return 1;
		else if (this.weight < v.weight)
			return -1;
		return 0;
	}
	
}
