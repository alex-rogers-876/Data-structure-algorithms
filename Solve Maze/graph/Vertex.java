package com.graph;
import java.util.ArrayList;

public class Vertex
{
	int id;
	ArrayList<Edge> edgeList;
	boolean visited;

	public Vertex(int id)
	{
		this.id = id;
		visited = false;
		edgeList = new ArrayList<Edge>();
	}

	public ArrayList<Edge> getEdgeList() 
	{
		return edgeList;
	}

	public void setEdgeList(ArrayList<Edge> edgeList) 
	{
		this.edgeList = edgeList;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}


	public boolean isVisited()
	{
		return visited;
	}

	public void setVisited(boolean visited)
	{
		this.visited = visited;
	}

	@Override
	public String toString()
	{
		return "V:" + id;
	}
	
}
