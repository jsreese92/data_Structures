import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class MyGraph {
	public ArrayList<String> listFromFile;
	public HashMap<MyVertex, ArrayList<VertexAndWeight>> map;
	public MyVertex startVertex;

	public MyGraph() {
		listFromFile = new ArrayList<String>();
		map = new HashMap<MyVertex, ArrayList<VertexAndWeight>>();
	}

	public void dijkstrasAlgorithm(MyVertex s) {

		Set<MyVertex> sourceSet = new TreeSet<MyVertex>();
		// set of all the keys in map
		sourceSet = map.keySet();
		
		// makes each vertex unknown and distance "infinity"
		for (MyVertex v : sourceSet){
			v.makeUnknown();
			v.setDistance(1000000000);
		}

		PriorityQueue<VertexAndWeight> q = new PriorityQueue<VertexAndWeight>();
		s.setDistance(0);
		s.makeKnown();

		// adds vertices adjacent to s to priority queue and updates them
		for (VertexAndWeight vaw : s.getAdjList()) {
			MyVertex v = vaw.getVertex();
			// sets v's path to original vertex
			v.setPath(s);

			// sets v's distance to the distance of its path plus its weight
			v.setDistance(v.getPath().getDistance() + vaw.getWeight());

			// add vertex with weight to priority queue
			q.add(vaw);
		}

		boolean areUnknowns = true;

		while (areUnknowns) {

			MyVertex v = null;
			// go through queue until we find a vertex that isn't known
			boolean foundVertex = false;
			while (!foundVertex) {
				v = q.poll().getVertex();
				// if v is not known, it's what we'll work with
				if (!v.known())
					foundVertex = true;
			}

			v.makeKnown();

			// for every node adjacent to v...
			for (VertexAndWeight vaw : v.getAdjList()) {
				MyVertex w = vaw.getVertex();
				// if adjacent vertex is unknown...
				if (!w.known()) {
					q.add(vaw);
					// if distance from v + distance to w is less than w's
					// previous distance...
					if (v.getDistance() + vaw.getWeight() < w.getDistance()) {
						// then set new distance to get to w
						w.setDistance(v.getDistance() + vaw.getWeight());
						// and update path
						w.setPath(v);
					}

				}
			}

			// tells loop when to terminate
			int knownVertices = 0;
			for (MyVertex theVertex : sourceSet) {
				if (theVertex.known())
					knownVertices++;
			}

			if (knownVertices == sourceSet.size())
				areUnknowns = false;
		}

		for (MyVertex v : sourceSet) {
			v.printAdjacenyList();
		}
		
		for (MyVertex v : sourceSet) {
			System.out.println("shortest path from " + s.getName() + " to "
					+ v.getName() + " is " + v.getDistance());
		}
		
	}
	
	public MyVertex getStartVertex(){
		return startVertex;
	}

	public void makeGraph() {
		int fileIndex = 0;
		while (fileIndex < listFromFile.size()) {
			//if at end of file
			if (listFromFile.get(fileIndex).equals("-1")){
				Set<MyVertex> sourceSet = new TreeSet<MyVertex>();
				//get set of all source vertices
				sourceSet = map.keySet();
				MyVertex theStartVertex = new MyVertex(listFromFile.get(fileIndex + 1));
				//set theStartVertex to the correct vertex already in map
				for (MyVertex v : sourceSet)
					if (theStartVertex.equals(v))
						theStartVertex = v;
				startVertex = theStartVertex;
				break;
			}

			Set<MyVertex> sourceSet = new TreeSet<MyVertex>();
			// set of all the keys in map
			sourceSet = map.keySet();

			MyVertex source = new MyVertex(listFromFile.get(fileIndex));
			for (MyVertex v : sourceSet)
				// if source is already a node, set source to that node
				if (source.equals(v))
					source = v;

			// refresh sourceSet
			sourceSet = map.keySet();

			MyVertex destination = new MyVertex(listFromFile.get(fileIndex + 1));
			for (MyVertex v : sourceSet)
				if (destination.equals(v))
					// if destination is already a node, set destination to that
					// node
					destination = v;

			// new VertexAndWeight object with destination vertex and path
			// weight
			VertexAndWeight in = new VertexAndWeight(destination,
					Integer.parseInt(listFromFile.get(fileIndex + 2)));

			// this doesn't do anything either?
			/* source.getAdjList().add(in); */

			ArrayList<VertexAndWeight> tempList = new ArrayList<VertexAndWeight>();
			// if source node already has adjacency list, then use it
			if (map.get(source) != null)
				tempList = map.get(source);
			tempList.add(in);
			map.put(source, tempList);
			source.setAdjList(tempList);

			// creates a vertex for any that don't have destination vertices
			ArrayList<VertexAndWeight> tempList2 = new ArrayList<VertexAndWeight>();
			// if destination node already has adjacency list, then use it
			if (map.get(destination) != null)
				tempList2 = map.get(destination);
			map.put(destination, tempList2);
			destination.setAdjList(tempList2);

			//start at next line
			fileIndex += 3;
		}
	}

	public void graphToString() {
		System.out.println("# of vertices: " + map.size());

		Set<MyVertex> sourceSet = new TreeSet<MyVertex>();
		// set of all the keys in map
		sourceSet = map.keySet();
		// for every key in map...
		for (MyVertex v : sourceSet) {
			// print the string of the source vertex...
			System.out.println("Source vertex: " + v.getName());
			// and print out all vertices adjacent to source vertex

			for (VertexAndWeight vaw : map.get(v)) {
				System.out.print("Destination vertex: "
						+ vaw.getVertex().getName());
				System.out.println(" Weight: " + vaw.getWeight());
			}
		}
	}

	public void readFile(String fileName) throws FileNotFoundException {

		Scanner inp = new Scanner(new File(fileName));
		while (inp.hasNext()) {
			String line = inp.nextLine();
			String[] word = line.split(" ");
			// adds all words from file into list
			for (int i = 0; i < word.length; i++)
				listFromFile.add(word[i].trim());
		}
	}
}
