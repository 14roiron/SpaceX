package com.roiron.spaceproject;

public class Commandes {
	public final static double MaxPropeller = 3;
	private double gasThrust;
	private double angleThrust;
	private double gasTank;
	public final static double GasTankCapacity=5;
	private boolean isBoosterOn;
	private double boosterTank;
	public final static double BoosterTankCapacity=20;
	public Commandes()
	{
		gasTank=GasTankCapacity;
		boosterTank=BoosterTankCapacity;
		angleThrust=-Math.PI/2.;
		isBoosterOn=false;
	}
	/**Return the Thrust, depending if we have enough gas and
	 *  if the booster is started and not yet empty
	 * @return the thrust
	 */
	public double getThrustTotal() {
		double thrustTotal=0;
		if(gasTank>0)
			thrustTotal+=gasThrust;
		if(isBoosterOn && boosterTank>0)
			thrustTotal+=boosterTank*1.265;
		return thrustTotal;
	}
	/**
	 * get the trustTotal in i periods
	 * @param i: number of period
	 * @return estimation of the thrust in i periods
	 */
	public double simulateThrust(int i){
		double thrustTotal=0;
		if(gasTank>i*getThrust()/10.)
			thrustTotal+=gasThrust;
		if(isBoosterOn && boosterTank>i*0.1)
			thrustTotal+=boosterTank*1.265;
		return thrustTotal;
	}
	/**
	 * Return the mass of the propeller part
	 * @return masse of the propeller
	 */
	public double getMass(){
		return boosterTank+gasTank;
	}
	/**
	 * We simulate the mass of the system in i periods, keeping the parameters
	 * @param i
	 * @return the mass
	 */
	public double simulateMass(int i)
	{
		double boosterTankCopy=boosterTank;
		double gasTankCopy=gasTank;
		for(int j=0;j<i;j++)
		{
			if(isBoosterOn & boosterTankCopy>0)
				boosterTankCopy-=0.0010;
			if(getGasTank()>0)
				gasTankCopy-=gasThrust/500.;
		}
		return gasTankCopy+boosterTankCopy;
	}
	/**
	 * Update the state of the commands, decrease the fuel 
	 * capacity and turn off the booster if it's empty
	 */
	public void update()
	{
		if(isBoosterOn)
			boosterTank-=0.0010;
		if(boosterTank<=0)
			setBoosterOn(false);
		if(getGasTank()>0)
		{
			gasTank-=gasThrust/500.;
			
		}
		else{
			setThrust(0);
		}
	}
	public void increaseThrust() {
		isBoosterOn=true;
		this.gasThrust+=0.1;
	}
	public void decreaseThrust() {
		this.gasThrust-=0.1;
		if(gasThrust<0)
			gasThrust=0;
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
	/**
	 * @return the isBoosterOn
	 */
	public boolean isBoosterOn() {
		return isBoosterOn;
	}
	/**
	 * @param isBoosterOn the isBoosterOn to set
	 */
	public void setBoosterOn(boolean isBoosterOn) {
		this.isBoosterOn = isBoosterOn;
	}
	/**
	 * @return the boosterTank
	 */
	public double getBoosterTank() {
		return boosterTank;
	}
	/**
	 * @param boosterTank the boosterTank to set
	 */
	public void setBoosterTank(double boosterTank) {
		this.boosterTank = boosterTank;
	}
	/**
	 * @return the thrust
	 */
	public double getThrust() {
		return gasThrust;
	}
	/**
	 * @param thrust the thrust to set
	 */
	public void setThrust(double thrust) {
		this.gasThrust = thrust;
	}
	
	

}
