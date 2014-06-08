import java.util.ArrayList;
import com.graph.DFS;
import com.graph.Graph;
import com.mazegen.MazeGenerator;
import com.mazegen.Wall;

public class Main {
	public static void main(String[] args) {
		MazeGenerator maze = new MazeGenerator(4);
		ArrayList<Wall> walls = maze.getWalls();
		
		Graph g = new Graph(4);
		for(Wall wall : walls)
			System.out.println(wall.toString());
		for(Wall wall : walls)
			if(wall.isUp())
				g.removeEdge(wall.getC1(), wall.getC2());
		DFS dfs = new DFS(g, 0, 15);
		String visits = dfs.getVisits();
		System.out.println(visits);
		System.out.println("Maze Solution"+dfs.getvList().toString());	
	}
}
