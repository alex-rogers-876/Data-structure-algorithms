package com.mazegen;

public class Wall 
{
	private int c1;
	private int c2;
	private boolean up; 
	public Wall()
	{
		c1 = 0;
		c2 = 0;
		up = true;
	}
	public Wall(int c1, int c2, boolean up)
	{
		this.c1 = c1;
		this.c2 = c2;
		this.up = up;
	}
	public int getC1(){ return c1; }
	public void setC1(int c1){ this.c1 = c1; }
	
	public int getC2(){ return c2; }
	public void setC2(int c2){ this.c2 = c2; }
	
	public boolean isUp(){ return up; }
	public void setUp(boolean up){ this.up = up; }

	@Override
	public String toString()
	{
		String updown;
		if (up)
			updown = "UP";
		else
			updown = "DOWN";
		return "(" + c1 + ", " + c2 + ") : " + updown;
	}
	
	
}
