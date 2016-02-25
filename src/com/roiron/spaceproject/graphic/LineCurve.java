package com.roiron.spaceproject.graphic;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LineCurve extends GraphicObject {

	double[][] pointsList;
	public LineCurve()
	{
		pointsList=new double[4][2];
	}
	public void draw(Graphics g) {

		double[] current,previous;
		for(int i=1;i<pointsList.length;i++)
		{
			current=pointsList[i];
			previous=pointsList[i-1];
			g.drawLine((int)previous[0], (int)previous[1], (int)current[0], (int)current[1]);
			
		}
		

	}


	public void update() {
	

	}
	/**
	 * @return the pointsList
	 */
	public double[][] getPointsList() {
		return pointsList;
	}
	/**
	 * @param pointsList the pointsList to set
	 */
	public void setPointsList(double[][] pointsList) {
		this.pointsList = pointsList;
	}
	
}
