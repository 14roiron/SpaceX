package com.roiron.spaceproject;

public class Commandes {
	private double thrust;
	private double angleThrust;
	private double gasTank;
	public final static double GasTankCapacity=100;
	public Commandes()
	{
		gasTank=GasTankCapacity;
		angleThrust=0;
	}
	/**
	 * @return the thrust
	 */
	public double getThrust() {
		return thrust;
	}
	/**
	 * @param thrust the thrust to set
	 */
	public void setThrust(double thrust) {
		this.thrust = thrust;
	}

	public void increaseThrust() {
		this.thrust+=0.5;
	}
	public void decreaseThrust() {
		this.thrust-=0.5;
	}
	/**
	 * @return the angleThrust
	 */
	public double getAngleThrust() {
		return angleThrust;
	}
	/**
	 * @param angleThrust the angleThrust to set
	 */
	public void setAngleThrust(double angleThrust) {
		this.angleThrust = angleThrust;
	}
	public void increaseAngleThrust() {
		this.angleThrust+=1/5.;
	}
	public void decreaseAngleThrust() {
		this.angleThrust-=1/5.;
	}
	/**
	 * @return the gasTank
	 */
	public double getGasTank() {
		return gasTank;
	}
	/**
	 * @param gasTank the gasTank to set
	 */
	public void setGasTank(double gasTank) {
		this.gasTank = gasTank;
	}
	public void decreaseGasTank(double gasUsed)
	{
		this.gasTank-=gasUsed;
	}
	public double getGasTankPerecentage(){
		return getGasTank()/GasTankCapacity*100.;
	}
	
	

}
