/**
 * 
 */
package com.roiron.spaceproject.graphic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Yohann Roiron
 * Enable to draw a rectangle
 */
public class RectangleShape extends GraphicShape {

	//Size of the rectangle, here posX, posY corresponds to the center of the rectangle
	private double height;
	private double width;
	private CircleShape inContact; 
	BufferedImage image;
	
	/** Complete Template with parameters
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
	 * @param width size of the rectangle
	 * @param height
	 * @param mass mass of the system
	 */
	public RectangleShape(double posX, double posY, double veloX, double veloY, double theta,
			double vtheta, Color color, double width, double height,double mass) {
		super(posX, posY, veloX, veloY,  theta, vtheta, color,mass);
		this.height=height;
		this.width=width;try {
		image = ImageIO.read(new File("rocket"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * first constructor with useful parameters
	 * @param posX center  
	 * @param posY center 
	 * @param theta
	 * @param color
	 * @param mass mass of the system
	 */
	public RectangleShape(double posX, double posY, double theta, Color color, double width, double height,double mass) {
		super(posX, posY, theta, color,mass);
		this.height=height;
		this.width=width;
		try {
			image = ImageIO.read(new File("rocket.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * draw the rectangle in the template
	 */
	public void draw(Graphics g) {
		
		Graphics2D gg = (Graphics2D) g.create();
		//rotate the screen to draw a easier rectangle
		gg.rotate(getTheta(), getPosX(), getPosY());
		gg.setColor(getColor());
		gg.fillRect((int) (getPosX()-getWidth()/2.), (int) (getPosY()-getHeight()/2.),
				(int) getWidth() , (int)getHeight());
		gg.drawImage(image, (int) (getPosX()-getWidth()/2.), (int) 
				(getPosY()-getHeight()/2.), (int) getWidth(), (int)getHeight(), null);
	    gg.dispose();//back to the normal Position
	}

	
	
	
	
	/**
	 * Set the height of the rectangle
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * set the width of the rectangle
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Is the rectangle in contact with something?
	 * @return the inContact
	 */
	public CircleShape getInContact() {
		return inContact;
	}

	/**
	 * Set the rectangle in contact with something
	 * @param inContact the inContact to set
	 */
	public void setInContact(CircleShape inContact) {
		this.inContact = inContact;
	}


}
