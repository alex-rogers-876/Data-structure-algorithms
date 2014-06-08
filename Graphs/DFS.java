public class DFS {
	Graph graphy;
	String post;
	Integer v;

	public DFS(Graph graphy, int start)
	{
		post = "";
		this.graphy = graphy;
		for(Vertex v : graphy.getAdjList())
			v.setVisited(false);

		RDFS(start);
	}

	private void RDFS(int v) 
	{
		preVisit(v);
		graphy.getAdjList()[v].setVisited(true);

		Vertex[] verts = graphy.getAdjList();
		for(int i = 0; i < verts[v].getEdgeList().size(); i++)
		{
			int v1 = verts[v].getEdgeList().get(i).getX();
			int v2 = verts[v].getEdgeList().get(i).getY();
			if(verts[v].getId() != v1)
			{
				Vertex k = verts[v1];
				if(k.isVisited()) continue;
				RDFS(k.getId());
			}
			if(verts[v].getId() != v2)
			{
				Vertex k = verts[v2];
				if(k.isVisited()) 
					continue;
				RDFS(k.getId());
			}		
		}	

	}

	public String getVisits()
	{
		return "DFS: " + this.post;
	}

	private void preVisit(Integer v)
	{
		post += v + " ";
	}
}