package com.roiron.spaceproject.graphic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * @author Yohann Roiron
 *
 */
public class CercleShape extends GraphicObject {
	
	
	//here we have the center of the circle, and its radius
	private double radius;
	/**
	 * @param posX
	 * @param posY
	 * @param veloX
	 * @param veloY
	 * @param accX
	 * @param accY
	 * @param theta
	 * @param vtheta
	 * @param atheta
	 * @param color
	 * @param radius 	
	 */
	public CercleShape(double posX, double posY, double veloX, double veloY, double accX, double accY, double theta,
			double vtheta, double atheta, Color color,double radius) {
		super(posX, posY, veloX, veloY, accX, accY, theta, vtheta, atheta, color);
		this.radius=radius;
	}

	/**
	 * @param posX
	 * @param posY
	 * @param theta
	 * @param color
	 * @param radius
	 */
	public CercleShape(double posX, double posY, double theta, Color color,double radius) {
		super(posX, posY, theta, color);
		this.radius=radius;
	}


	public void draw(Graphics g) {
		
		Graphics2D gg = (Graphics2D) g.create();
	    gg.setColor(getColor());
	    int upperLeftX=(int) (getPosX()-getRadius());
	    int upperLeftY=(int)(getPosY()-getRadius());
	    gg.fillOval(upperLeftX, upperLeftY, 2*(int)getRadius(), 2*(int)getRadius());
	    gg.dispose();

	}

	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}
}
