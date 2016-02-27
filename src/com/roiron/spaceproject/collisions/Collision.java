package com.roiron.spaceproject.collisions;

import java.awt.Color;
import java.util.List;

import javax.swing.JFrame;

import com.roiron.spaceproject.Main;
import com.roiron.spaceproject.graphic.CircleShape;
import com.roiron.spaceproject.graphic.GraphicObject;
import com.roiron.spaceproject.graphic.RectangleShape;
import com.roiron.spaceproject.graphic.SpacePanel;
import com.roiron.spaceproject.physic.PhysicUtility;

public class Collision{

	/**
	 * Return a double[][] containing the 4 vertices of a rectangle
	 * A-----B
	 * |  o	 |
	 * D-----C
	 * Be the axes are
	 * 0---->x
	 * |
	 * |
	 * \/
	 * y
	 * so the projection use -theta.
	 * @param rect: a rectangleShape object center o
	 * @return double[A,B,C,D][x,y] a array containing the four edges, center and then from top 
	 * left corner (theta=0) and turning clockwise;
	 */
	public static double[][] getVerticesFrongTriangle(RectangleShape rect)
	{
		double[][] edges = new double[5][2];
		double posX= rect.getPosX();			
		double posY = rect.getPosY();
		double theta=rect.getTheta();
		
		double w=rect.getWidth();
		double h=rect.getHeight();
		
		double alpha=Math.atan2(h, w);
		double r = Math.sqrt(1./4.*(w*w+h*h));
		
		edges[0]=new double[]{posX,posY}; // center of the rectangle
		
		edges[1]=new double[]{posX-r*Math.cos(theta+alpha),posY-r*Math.sin(theta+alpha)};
		edges[2]=new double[]{posX+r*Math.cos(-theta+alpha),posY-r*Math.sin(-theta+alpha)};
		edges[3]=new double[]{posX+r*Math.cos(theta+alpha),posY+r*Math.sin(theta+alpha)};
		edges[4]=new double[]{posX-r*Math.cos(-theta+alpha),posY+r*Math.sin(-theta+alpha)};
		
		
		return edges;
	}
	/**
	 * A simple collision algorithm, test if every vertices of the rectangle belongs 
	 * to the circle, It can works only if the rectangle are always smaller than the 
	 * circle and if we don't need to be really precise.
	 * @param rect: a rectangle shape
	 * @param circle a cirlceShape
	 * @return boolean, are they in contact?
	 */
	
	public static boolean simpleCollision(RectangleShape rect, CircleShape circle,List<GraphicObject> l)
	{
		double[][] vertices = getVerticesFrongTriangle(rect);
		boolean inContact=false;
		for(int i=0;i<vertices.length;i++)
		{
			l.add(new CircleShape(vertices[i][0], vertices[i][1], 0, Color.gray, 20, 0));
			if(  PhysicUtility.distance(vertices[i], circle.getState())   <=  circle.getRadius())
			{
				inContact=true;
			}
		}
		return inContact;
	}
	
	
//	 public static void main(String[] args) throws InterruptedException {
//	 
//		CircleShape circleShape = new CircleShape(500, 500, 0, Color.green, 300, 0);
//		RectangleShape rectangleShape = new RectangleShape(300, 200, 0*Math.PI/2, Color.red, 200, 100, 0);
//	
//		
//		SpacePanel spacePanel = new SpacePanel();
//		spacePanel.addElementListObject(circleShape);
//		spacePanel.addElementListObject(rectangleShape);
//		System.out.println(simpleCollision(rectangleShape, circleShape,spacePanel.getListObject()));
//		JFrame jFrame= new JFrame();
//		jFrame.setTitle("Space Simulation");
//		jFrame.setSize(1100, 800);
//		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		jFrame.setLocationRelativeTo(null);
//		jFrame.setContentPane(spacePanel);
//		jFrame.setVisible(true);
//		for(int i=0; i<100;i++)
//		{
//			List<GraphicObject> list=spacePanel.getListObject();
//			list.clear();
//			list.add(circleShape);
//			list.add(rectangleShape);
//			rectangleShape.setTheta(rectangleShape.getTheta()+1./10.);
//			System.out.println(simpleCollision(rectangleShape, circleShape, list));
//			spacePanel.repaint();
//			Thread.sleep(1000);
//		}
//	}

}
