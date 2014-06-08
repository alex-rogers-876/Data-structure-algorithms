package com.graph;

import java.util.ArrayList;

import com.mazegen.Wall;


public class Graph {
	
	boolean directed;
	boolean weighted;
	Vertex[] adjList;
	ArrayList<Edge> listy;
	
	public Graph(int n, boolean directed, boolean weighted, ArrayList<Edge> listy)
	{
		this.listy = listy;
		adjList = new Vertex[n];
		
		for(int i = 0; i < adjList.length; i++)
			adjList[i] = new Vertex(i);
		
		this.directed = directed;
		this.weighted = weighted;
		
		for(Edge e : listy)
			addEdge(e);
	}
	
	public Graph(int sides)
	{
		listy = new ArrayList<Edge>();
		//generate empty n by n graph
		for(int c1 = 0; c1 < (sides * sides) - 1; c1++)
		{
			int c2 = c1 + 1;
			if(c2 % sides != 0){
				listy.add(new Edge(c1, c2, false, -1));
			}
			c2 = c1 + sides;
			if(c2 < sides * sides)
			{
				listy.add(new Edge(c1, c2, false, -1));
			}
		}
		adjList = new Vertex[sides*sides];
		for(int i = 0; i < adjList.length; i++)
			adjList[i] = new Vertex(i);
		
		this.directed = false;
		this.weighted = false;
		
		for(Edge e : listy)
			addEdge(e);
	}
	
	public void addEdge(Edge e)
	{
		Vertex v;
		v = adjList[e.getX()]; 
		v.getEdgeList().add(e);
		if(!directed){
			v = adjList[e.getY()];
			v.getEdgeList().add(e);
		}
	}
	
	public void removeEdge(int v1, int v2)
	{
		ArrayList<Edge> edgeList = adjList[v1].getEdgeList();
		for(int i = 0; i < edgeList.size(); i++ )
		{
			Edge e = edgeList.get(i);
			if((e.getX() == v1 && e.getY() == v2) || (e.getX() == v2 && e.getY() == v1)) 
				edgeList.remove(e);
		}
		edgeList = adjList[v2].getEdgeList();
		for(int i = 0; i < edgeList.size(); i++ )
		{
			Edge e = edgeList.get(i);
			if((e.getX() == v1 && e.getY() == v2) || (e.getX() == v2 && e.getY() == v1)) 
				edgeList.remove(e);
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
		return listy.size();
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
		return listy;
	}


	public void printAdjList()
	{
		for(int i = 0; i < adjList.length; i++)
		{
			System.out.println(adjList[i].id + " " + adjList[i].getEdgeList().toString());
		}
	}

	@Override
	public String toString() 
	{
		return "Graph [NumVertices = " + getNumVertices()
				+ ", NumEdges = " + getNumEdges() + ", Directed? = "
				+ isDirected() + ", Weighted? = " + isWeighted() + "]";
	}
}
