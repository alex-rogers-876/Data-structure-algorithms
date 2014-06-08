
import java.util.ArrayList;

public class Graph 
{
	boolean directed;
	boolean weighted;
	Vertex[] adjList;
	ArrayList<Edge> listArray;

	public Graph(int n, boolean directed, boolean weighted)
	{
		adjList = new Vertex[n];
		for(int i = 0; i < adjList.length; i++)
			adjList[i] = new Vertex(i);
		this.directed = directed;
		this.weighted = weighted;

	}
	public void initEdges(ArrayList<Edge> listArray)
	{
		this.listArray = listArray;
		for(Edge e : listArray)
			addEdge(e);
	}
	public void addEdge(Edge e)
	{
		Vertex v;
		v = adjList[e.getX()]; 
		v.getEdgeList().add(e);
		if(!directed)
		{
			v = adjList[e.getY()];
			v.getEdgeList().add(e);
		}
	}

	public int getAdjacentList(int v)
	{

		return adjList[v].getEdgeList().size();
	}

	public Edge getEdge(int v1, int v2)
	{
		Vertex list = adjList[v1];
		for(Edge e : list.getEdgeList())
		{
			if(e.getY() == v2)
				return e;
		}
		return null;
	}

	public int getNumVertices()
	{
		return adjList.length;
	}

	public int getNumEdges()
	{
		return listArray.size();
	}

	public boolean isDirected()
	{
		return this.directed;
	}

	public boolean isWeighted()
	{
		return this.weighted;
	}
	
	public Vertex[] getAdjList()
	{
		return adjList;
	}
	
	public ArrayList<Edge> getListy() 
	{
		return listArray;
	}


	public void printAdjList()
	{
		for(int i = 0; i < adjList.length; i++)
		{
			System.out.println(adjList[i].id + " " + adjList[i].getEdgeList().toString());
		}
	}


	public String toString()
	{
		return "Graph [NumVertices = " + getNumVertices()
				+ ", NumEdges = " + getNumEdges() + ", Directed = "
				+ isDirected() + ", Weighted = " + isWeighted() + "]";
	}
}
