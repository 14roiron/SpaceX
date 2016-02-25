package com.roiron.spaceproject.physic;

public class ObjectState {
	double[] position;
	double[] velocity;
	double mass;
	/**
	 * Default Constructor
	 */
	public ObjectState()
	{
		
	}
	/**
	 * @param position
	 * @param velocity
	 * @param mass
	 */
	public ObjectState(double[] position, double[] velocity, double mass) {
		this.position = position;
		this.velocity = velocity;
		this.mass = mass;
	}
	/**
	 * @return the position
	 */
	public double[] getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(double[] position) {
		this.position = position;
	}
	/**
	 * @return the position, remove the angle information
	 */
	public double[] getPositionXY() {
		return new double[]{position[0],position[1]};
	}
	/**
	 * @param position the position to set without angle information
	 */
	public void setPositionXY(double[] position) {
		this.position[0] = position[0];
		this.position[1] = position[1];
	}
	/**
	 * @return the velocity
	 */
	public double[] getVelocity() {
		return velocity;
	}
	/**
	 * @param velocity the velocity to set
	 */
	public void setVelocity(double[] velocity) {
		this.velocity = velocity;
	}
	public double[] getVelocityXY() {
		return new double[]{velocity[0],velocity[1]};
	}
	/**
	 * @param velocity the velocity to set
	 */
	public void setVelocityXY(double[] velocity) {
		this.velocity[0] = velocity[0];
		this.velocity[1] = velocity[1];
	}
	/**
	 * @return the mass
	 */
	public double getMass() {
		return mass;
	}
	/**
	 * @param mass the mass to set
	 */
	public void setMass(double mass) {
		this.mass = mass;
	}
	
}
