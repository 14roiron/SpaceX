/**
 * 
 */
package com.roiron.spaceproject.graphic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * @author Yohann Roiron
 *
 */
public class RectangleShape extends GraphicObject {

	//Size of the rectangle, here posX, posY corresponds to the center.
	private double height;
	private double width;
	
	/**
	 * @param posX position of the center of the rectangle
	 * @param posY 
	 * @param veloX velocity of the center of the rectangle
	 * @param veloY
	 * @param accX acceleration of the center of the rectangle
	 * @param accY 
	 * @param theta angle of the rectangle, 0 is the Horrizontale line
	 * @param vtheta angle velocity
	 * @param atheta angle acceleration
	 * @param color color of the rectangle
	 * @param width size of the reactangle
	 * @param height
	 */
	public RectangleShape(double posX, double posY, double veloX, double veloY, double accX, double accY, double theta,
			double vtheta, double atheta, Color color, double width, double height) {
		super(posX, posY, veloX, veloY, accX, accY, theta, vtheta, atheta, color);
		this.height=height;
		this.width=width;
	}

	/**
	 * @param posX
	 * @param posY
	 * @param theta
	 * @param color
	 */
	public RectangleShape(double posX, double posY, double theta, Color color, double width, double height) {
		super(posX, posY, theta, color);
		this.height=height;
		this.width=width;
	}

	/**
	 * draw the template
	 */
	public void draw(Graphics g) {
		
		Graphics2D gg = (Graphics2D) g.create();
		//rotate the screen to draw a easier rectangle
		gg.rotate(getTheta(), getPosX(), getPosY());
		gg.setColor(getColor());
		gg.fillRect((int) (getPosX()-getWidth()/2.), (int) (getPosY()-getHeight()/2.),
				(int) getWidth() , (int)getHeight());
	    gg.dispose();//back to the normal Position
	}

	
	
	
	
	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}


}
