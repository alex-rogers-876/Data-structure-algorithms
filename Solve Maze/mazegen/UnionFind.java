package com.mazegen;

public class UnionFind {
	
	private int[] parent;
  
	public UnionFind(int numElements)
	{
		parent = new int[numElements];
		for (int i = 0; i < parent.length; i++)
		{
			parent[i] = -1; 
		}
	}
	
	public void wunion(int a, int b)
	{
		int root1 = pcfind(a);     
	    int root2 = pcfind(b);     
	    
	    if(root1 != root2)
	    {
		    if (parent[root1] <= parent[root2])
		    { 
		    	parent[root1] += parent[root2]; 
		    	parent[root2] = root1; 
		    }else
		    {
		    	parent[root2] += parent[root1];
		    	parent[root1] = root2;
		    }
	    }
	}
	
	public int pcfind(int i)
	{
		if (parent[i] < 0) return i; 
		parent[i] = pcfind(parent[i]);
		return parent[i];
	}
	
	public void printArray()
	{
		for(int i : parent)
		{
			System.out.print(i + "|");
		}
		System.out.println();
		System.out.println();
	}
}
