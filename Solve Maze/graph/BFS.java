package com.graph;
import java.util.LinkedList;
import java.util.Queue;

public class BFS 
{
	Graph g;
	Queue<Integer> q;
	String post;
	Integer v;
	
	public BFS(Graph g, int start) 
	{
		post = "";
		this.g = g;
		for(Vertex v : g.getAdjList())
			v.setVisited(false);
		g.getAdjList()[start].setVisited(true);
		
		q = new LinkedList<Integer>();
		q.add(start);
		
		while (!(q.isEmpty()))
		{
			v = q.poll();
			preVisit(v);
			Vertex[] verts = g.getAdjList();
			for(int i = 0; i < verts[v].getEdgeList().size(); i++)
			{
				int v1 = verts[v].getEdgeList().get(i).getX();
				int v2 = verts[v].getEdgeList().get(i).getY();
				if(verts[v].getId() != v1)
				{
					Vertex k = verts[v1];
					if(k.isVisited())
						continue;
					k.setVisited(true);
					q.add(k.getId());
				}
				if(verts[v].getId() != v2)
				{
					Vertex k = verts[v2];
					if(k.isVisited()) 
						continue;
					k.setVisited(true);
					q.add(k.getId());
				}		
			}	
		}
	}
	
	public String getVisits()
	{
		return "BFS: " + this.post;
	}

	private void preVisit(Integer v) 
	{
		post += v + " ";
	}
}
