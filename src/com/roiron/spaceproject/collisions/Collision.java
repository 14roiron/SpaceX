package com.roiron.spaceproject.collisions;

import com.roiron.spaceproject.graphic.CercleShape;
import com.roiron.spaceproject.graphic.RectangleShape;

public class Collision {

	/**
	 * Return a double[][] containing the 4 vertices of a rectangle
	 * A-----B
	 * |	 |
	 * D-----C
	 * Be the axes are
	 * 0---->x
	 * |
	 * |
	 * \/
	 * y
	 * so the projection use -theta.
	 * @param rect: a rectangleShape object
	 * @return double[A,B,C,D][x,y] a array containing the four edges, from top left corner (theta=0) and turning clockwise;
	 */
	public static double[][] getVerticesFrongTriangle(RectangleShape rect)
	{
		double[][] edges = new double[4][2];
		double posX= rect.getPosX();			
		double posY = rect.getPosY();
		double theta=rect.getTheta();
		
		double w=rect.getWidth();
		double h=rect.getHeight();
		
		edges[0]=new double[]{posX,posY};
		edges[1]=new double[]{posX+w*Math.cos(theta),posY+w*Math.sin(-theta)};
		edges[2]=new double[]{posX+w*Math.cos(theta)+h*Math.sin(-theta),posY+w*Math.sin(-theta)+h*Math.cos(theta)};
		edges[3]=new double[]{posX+h*Math.sin(-theta),posY+h*Math.cos(theta)};
		
		
		return edges;
	}
	public static boolean simpleCollision(RectangleShape rect, CercleShape cercle)
	{
		double[][] vertices = getVerticesFrongTriangle(rect);
		boolean inContact=false;
		for(int i=0;i<4;i++)
		{
			if(PhysicUt)
		}
	}

}
