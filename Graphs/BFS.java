import java.util.LinkedList;
import java.util.Queue;

public class BFS 
{
	Graph graphy;
	Queue<Integer> que;
	String post;
	Integer v;

	public BFS(Graph graphy, int start) 
	{
		post = "";
		this.graphy = graphy;
		for(Vertex v : graphy.getAdjList())
			v.setVisited(false);
		graphy.getAdjList()[start].setVisited(true);

		que = new LinkedList<Integer>();
		que.add(start);

		while (!(que.isEmpty()))
		{
			v = que.poll();
			preVisit(v);
			Vertex[] verts = graphy.getAdjList();
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
					que.add(k.getId());
				}
				if(verts[v].getId() != v2)
				{
					Vertex k = verts[v2];
					if(k.isVisited())
						continue;
					k.setVisited(true);
					que.add(k.getId());
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
