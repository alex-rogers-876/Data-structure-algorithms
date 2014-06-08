package com.graph;
import java.util.*;

public class DFS {
	Graph g;
	String post;
	Integer v;
	Boolean checker1 = false;
	Boolean checker2 = false;
	ArrayList<Vertex> vList;
	ArrayList<Vertex> vList2;
	ArrayList<Vertex> vList3;
	ArrayList<Integer> Ilist;
	ArrayList<Integer> arl;
	int end;
	public DFS(Graph g, int start, int end)
	{
		this.end = end;
		post = "";
		this.g = g;
		vList = new ArrayList<Vertex>();
		for(Vertex v : g.getAdjList())
			v.setVisited(false);
		vList3 = new ArrayList<Vertex>();
		RDFS(start, end);
		vList2 = new ArrayList<Vertex>();
		for(Vertex x : vList)
		{
			if(x.getId() == 100)
				break;
			else
				vList2.add(x);
		}
	}
	
	private void RDFS(int v, int end) 
	{
		preVisit(v);
		g.getAdjList()[v].setVisited(true);
		Vertex[] verts = g.getAdjList();
		if(v == end){
			postVisit(verts[v]);
			vList.add(new Vertex(100));
			return;
		}
		
		for(int i = 0; i < verts[v].getEdgeList().size(); i++)
		{ 
			int v1 = verts[v].getEdgeList().get(i).getX(); 
			int v2 = verts[v].getEdgeList().get(i).getY();
			
			if(verts[v].getId() != v1)
			{
				Vertex k = verts[v1];
				if(k.isVisited())
					continue;
				RDFS(k.getId(), end);
			}
			if(verts[v].getId() != v2)
			{
				Vertex k = verts[v2];
				if(k.isVisited()) continue;
				RDFS(k.getId(), end);
			}		
		}	
		postVisit(verts[v]);
	}
	
	public String getVisits()
	{
		return "DFS: " + this.post;
	}

	private void preVisit(Integer v) 
	{
		post += v + " ";
	}
	
	private void postVisit(Vertex v)
	{
		vList.add(v);
	}

	public ArrayList<Integer> getvList() 
	{
		String arr = this.post;
		arl = new ArrayList<Integer>();
		Ilist = new ArrayList<Integer>();
		String[] items = arr.split(" ");
		int[] results = new int[items.length];

		for (int i = 0; i < items.length; i++) 
		{
		    try 
		    {
		        results[i] = Integer.parseInt(items[i]);
		    } catch (NumberFormatException nfe) {};
		}
		for (int i = 0; i < results.length; i++)
			vList3.add(new Vertex(results[i]));
		
		
		for(int i = 0; i < vList2.size(); i++)
		{
			Ilist.add(vList2.get(i).id);
			
		}
			for(int j = 0; j < results.length; j++)
			{
				int value;
				Vertex k;
				if(Ilist.contains(results[j])) 
				{
					if(results[j] == end)
					{
						arl.add(results[j]);
						break;
					}
					continue;
				}
				else
				{
					arl.add(results[j]);
					
				}
			}
		return arl;
	}
}
