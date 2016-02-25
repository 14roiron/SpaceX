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
	private double theta;
	private double vtheta;
	public Color color;
	
	
	/**
	 * @param posX
	 * @param posY
	 * @param veloX
	 * @param veloY
	 * @param theta
	 * @param vtheta
	 * @param color
	 */
	public GraphicObject(double posX, double posY, double veloX, double veloY, double theta,
			double vtheta, Color color) {
		this.posX = posX;
		this.posY = posY;
		this.veloX = veloX;
		this.veloY = veloY;
		this.theta = theta;
		this.vtheta = vtheta;
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
	 * Update the position from velocity
	 * Update velocity from acceleration
	 */
	public void update()
	{

		setPosX(getPosX()+getVeloX());
		setPosY(getPosY()+getVeloY());
		
		setTheta(getTheta()+getVtheta());
	}
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
	
	/**
	 * Get a vector
	 * @return the state vector [x,y,theta] 
	 */
	public double[] getState()
	{
		double[] state= {this.getPosX(),this.getPosY(),this.getTheta()};
		return state;
	}
	/**
	 * Set the state by vector
	 * @param state
	 */
	
	public void setState(double[] state)
	{
		setPosX(state[0]);
		setPosY(state[1]);
		setTheta(state[2]);
	}
	/**
	 * Get Velocity by vector
	 * @return the velocity vector
	 */
	public double[] getVelocity()
	{
		double[] state= {this.getVeloX(),this.getVeloY(),this.getVtheta()};
		return state;
	}
	/**
	 * get the velocity state
	 * @param state
	 */
			
	public void setVelocity(double[] state)
	{
		setVeloX(state[0]);
		setVeloY(state[1]);
		setVtheta(state[2]);
	}

}
