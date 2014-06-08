package com.graph;

public class Edge
{
	int x;
	int y;
	boolean directed;
	double weight;
	boolean visited;
	
	public Edge(int x, int y, boolean directed, double weight) 
	{		
		this.x = x;
		this.y = y;
		this.directed = directed;
		this.weight = weight;
		this.visited = false;
	}

	public int getX() 
	{
		return x;
	}


	public void setX(int x) 
	{
		this.x = x;
	}


	public int getY() 
	{
		return y;
	}


	public void setY(int y) 
	{
		this.y = y;
	}

	
	public boolean isDirected()
	{
		return directed;
	}


	public void setDirected(boolean directed)
	{
		this.directed = directed;
	}


	public double getWeight() 
	{
		return weight;
	}

	public void setWeight(double weight)
	{
		this.weight = weight;
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
		return "Edge [x = " + x + ", y = " + y + ", directed = " + directed
				+ ", weight = " + weight + "]";
	}
}
