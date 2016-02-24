package com.roiron.spaceproject.graphic;

import java.awt.Color;
import java.awt.Graphics;


/**
 * @author Yohann Roiron
 *
 */
public abstract class GraphicObject {
	/*
	 *	Parameters for the object: position, velocity and acceleration
	 */
	private double posX;
	private double posY;
	private double veloX;
	private double veloY;
	private double accX;
	private double accY;
	private double theta;
	private double vtheta;
	private double atheta;
	public Color color;
	
	
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
	 */
	public GraphicObject(double posX, double posY, double veloX, double veloY, double accX, double accY, double theta,
			double vtheta, double atheta, Color color) {
		this.posX = posX;
		this.posY = posY;
		this.veloX = veloX;
		this.veloY = veloY;
		this.accX = accX;
		this.accY = accY;
		this.theta = theta;
		this.vtheta = vtheta;
		this.atheta = atheta;
		this.color = color;
	}

	/**
	 * @param posX
	 * @param posY
	 * @param theta
	 * @param color
	 */
	public GraphicObject(double posX, double posY, double theta, Color color) {
		this.posX = posX;
		this.posY = posY;
		this.theta = theta;
		this.color = color;
	}

	/**
	 * Print the current figure on the graphic object set.
	 * @param g the graphic object
	 */
	public abstract void draw(Graphics g);
	
	/**
	 * @return the posX
	 */
	public double getPosX() {
		return posX;
	}
	/**
	 * @param posX the posX to set
	 */
	public void setPosX(double posX) {
		this.posX = posX;
	}
	/**
	 * @return the posY
	 */
	public double getPosY() {
		return posY;
	}
	/**
	 * @param posY the posY to set
	 */
	public void setPosY(double posY) {
		this.posY = posY;
	}
	/**
	 * @return the veloX
	 */
	public double getVeloX() {
		return veloX;
	}
	/**
	 * @param veloX the veloX to set
	 */
	public void setVeloX(double veloX) {
		this.veloX = veloX;
	}
	/**
	 * @return the veloY
	 */
	public double getVeloY() {
		return veloY;
	}
	/**
	 * @param veloY the veloY to set
	 */
	public void setVeloY(double veloY) {
		this.veloY = veloY;
	}
	/**
	 * @return the accX
	 */
	public double getAccX() {
		return accX;
	}
	/**
	 * @param accX the accX to set
	 */
	public void setAccX(double accX) {
		this.accX = accX;
	}
	/**
	 * @return the accY
	 */
	public double getAccY() {
		return accY;
	}
	/**
	 * @param accY the accY to set
	 */
	public void setAccY(double accY) {
		this.accY = accY;
	}
	/**
	 * @return the theta
	 */
	public double getTheta() {
		return theta;
	}
	/**
	 * @param theta the theta angle to set
	 */
	public void setTheta(double theta) {
		this.theta = theta;
	}
	/**
	 * @return the vtheta
	 */
	public double getVtheta() {
		return vtheta;
	}
	/**
	 * @param vtheta the theta velocity to set
	 */
	public void setVtheta(double vtheta) {
		this.vtheta = vtheta;
	}
	/**
	 * @return the atheta
	 */
	public double getAtheta() {
		return atheta;
	}
	/**
	 * @param atheta the Theta acceleration to set
	 */
	public void setAtheta(double atheta) {
		this.atheta = atheta;
	}
	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * @param color :the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

}
