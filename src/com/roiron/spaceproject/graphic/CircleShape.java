package com.roiron.spaceproject.graphic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * @author Yohann Roiron
 *
 */
public class CircleShape extends GraphicShape {
	
	
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
	 * @param mass mass of the system
	 */
	public CircleShape(double posX, double posY, double veloX, double veloY, double accX, double accY, double theta,
			double vtheta, Color color,double radius,double mass) {
		super(posX, posY, veloX, veloY, theta, vtheta, color,mass);
		this.radius=radius;
	}

	/**
	 * @param posX
	 * @param posY
	 * @param theta
	 * @param color
	 * @param radius
	 * @param mass mass of the system
	 */
	public CircleShape(double posX, double posY, double theta, Color color,double radius,double mass) {
		super(posX, posY, theta, color,mass);
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
