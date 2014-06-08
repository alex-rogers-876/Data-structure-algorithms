package com.mazegen;

import java.util.ArrayList;
public class MazeGenerator{
	
	private UnionFind cells;
	private int sides;
	private ArrayList<Wall> walls;
	
	public MazeGenerator(int n)
	{
		sides = n;
		int totalCells = sides * sides;
		cells = new UnionFind(totalCells);
		walls = new ArrayList<Wall>();
		generateWalls(walls);
		shuffle(walls);
		breakDownWalls();
	}
	
	private void generateWalls(ArrayList<Wall> walls)
	{
		for(int c1 = 0; c1 < (sides * sides) - 1; c1++)
		{
			int c2 = c1 + 1;
			if(c2 % sides != 0)
			{
				walls.add(new Wall(c1,c2,true));
			}
			c2 = c1 + sides;
			if(c2 < sides * sides)
			{
				walls.add(new Wall(c1,c2,true));
			}
		}
	}
	
	private void shuffle(ArrayList<Wall> a)
	{
        int n = a.size();
        for (int i = 0; i < n; i++)
        {
            int r = i + (int) (Math.random() * (n - i));
            Wall swap = (Wall)a.get(r);
            a.set(r, a.get(i));
            a.set(i, swap);
        }
    }
	
	private void breakDownWalls()
	{
		for(Wall wall : this.walls)
		{
			if(cells.pcfind(wall.getC1()) != cells.pcfind(wall.getC2()))
			{
				cells.wunion(wall.getC1(), wall.getC2());
				wall.setUp(false);
			}
		}
	}
	
	public void printWalls()
	{
		for (Wall w : walls)
		{
			System.out.println(w);
		}
		cells.printArray();
		System.out.println();
		System.out.println();
	}

	public ArrayList<Wall> getWalls() 
	{
		return walls;
	}

}
