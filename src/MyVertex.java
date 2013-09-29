import java.util.ArrayList;


public class MyVertex implements Comparable<MyVertex>{
	public ArrayList<VertexAndWeight> adj; //list of adjacent nodes
	public String name;
	public boolean known;
	public MyVertex path;
	public int distance; //distance from the given node for Dijkstra's Algorithm
	private int MAX_VALUE = 1000000000;
	
	public MyVertex(String str){
		adj = new ArrayList<VertexAndWeight>();
		name = str;
		known = false;
		path = null;
		distance = MAX_VALUE;
	}
	
	public void printAdjacenyList(){
		System.out.println("Source vertex: " + this.getName());
		for (VertexAndWeight vaw : adj){
			System.out.print("    ");
			System.out.println("Vertex: " + vaw.getVertex().getName() + " Weight: " + vaw.getWeight());
		}
	}
	
	public MyVertex getPath(){
		return path;
	}
	
	public void setPath(MyVertex v){
		path = v;
	}

	public int compareTo(MyVertex v) {
		return this.name.compareTo(v.name);
	}
	
	public int getDistance(){
		return distance;
	}
	
	public void setDistance(int theDistance){
		distance = theDistance;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean known(){
		return known;
	}
	
	public void makeKnown(){
		known = true;
	}
	
	public void makeUnknown(){
		known = false;
	}
	
	public ArrayList<VertexAndWeight> getAdjList(){
		return adj;
	}
	
	public void setAdjList(ArrayList<VertexAndWeight> theList){
		adj = theList;
	}
	
	public boolean equals(MyVertex v){
		return this.name.equals(v.name);
	}
}
