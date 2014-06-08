import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main 
{
	//Command line arguments, 2 args, input file - output file
	public static void main(String[] args) throws IOException
	{
		File inFile;
	    inFile = new File(args[1]);
	    if(!inFile.exists())
	    	inFile.createNewFile();

	    FileReading parserator = new FileReading(args[0]);
	    parserator.processLineByLine();

		PrintWriter outFile = new PrintWriter(new FileWriter(args[1])); 

		outFile.println("Number of vertices: " + parserator.getNumV());


		Graph graphy = new Graph(parserator.getNumV(), parserator.isDirected(), parserator.isWeighted());
		graphy.initEdges(parserator.getListy());
		outFile.println(graphy.toString());

		if(graphy.getEdge(0, 3) != null)
			outFile.println(graphy.getEdge(0, 3).toString());
		if(graphy.getEdge(1, 0) != null)
			outFile.println(graphy.getEdge(1, 0).toString());

		outFile.println();

		BFS bfs = new BFS(graphy, 0);
		outFile.println(bfs.getVisits());

		DFS dfs = new DFS(graphy, 0);
		outFile.println(dfs.getVisits());

		System.out.println("Ajacency List: ");
		graphy.printAdjList();

		outFile.close();
	}
}