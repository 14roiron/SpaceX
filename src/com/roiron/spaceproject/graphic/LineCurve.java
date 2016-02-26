package com.roiron.spaceproject.graphic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.roiron.spaceproject.physic.ObjectState;

public class LineCurve extends GraphicObject {

	ObjectState[] pointsList;
	public LineCurve()
	{
		pointsList=new ObjectState[0];
	}
	public void draw(Graphics g) {

		ObjectState current,previous;
		g.setColor(Color.white);
		for(int i=1;i<pointsList.length;i++)
		{
			current=pointsList[i];
			previous=pointsList[i-1];
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.drawLine((int)previous.getPositionXY()[0], 
					(int)previous.getPositionXY()[1], 
					(int)current.getPositionXY()[0], 
					(int)current.getPositionXY()[1]);
			
		}
		

	}


	public void update() {
	

	}
	/**
	 * @return the pointsList
	 */
	public ObjectState[] getPointsList() {
		return pointsList;
	}
	/**
	 * @param pointsList the pointsList to set
	 */
	public void setPointsList(ObjectState[] pointsList) {
		this.pointsList = pointsList;
	}
	
	
}
