import java.io.FileNotFoundException;


/*
 * All work in this program is completely my own, 
 * Jordan Reese
 * 
 */

public class Main {

	public static void main(String args[]) throws FileNotFoundException{
		MyGraph g = new MyGraph();
		g.readFile("GraphCoordinates");
		g.makeGraph();
		g.graphToString();
		System.out.println();
		g.dijkstrasAlgorithm(g.getStartVertex());
	}
}
