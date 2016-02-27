package com.roiron.spaceproject.physic;

import java.awt.Color;
import java.util.List;

import javax.print.attribute.standard.PrinterLocation;

import com.roiron.spaceproject.Commandes;
import com.roiron.spaceproject.collisions.Collision;
import com.roiron.spaceproject.graphic.CircleShape;
import com.roiron.spaceproject.graphic.GraphicObject;
import com.roiron.spaceproject.graphic.LineCurve;
import com.roiron.spaceproject.graphic.RectangleShape;
import com.roiron.spaceproject.graphic.SpacePanel;

public class PhysicMotor {

	private CircleShape earth;
	private CircleShape moon;
	private LineCurve moonCurve;
	private RectangleShape rocket;
	private LineCurve rocketCurve;
	private List<GraphicObject> listGraphic;
	private Commandes commandes;
	public static final int height = 900;
	public static final int width = 1100;
	/**
	 * Default constructor of the graphic motor
	 * @param listGraphic object containing all drawing figures
	 * @param commandes object, containing the user interactions.
	 */
	public PhysicMotor(List<GraphicObject> listGraphic, Commandes commandes) {
		this.commandes=commandes;
		synchronized(listGraphic)
		{
			this.listGraphic = listGraphic;
			
			// We fisrt define the earth, Velocity equal to 0
			earth = new CircleShape(width / 2, height / 2, 0, Color.blue, height / 10,
					10000);
			listGraphic.add(earth);
	
			moon = new CircleShape(width * 9 / 10, height / 2, 0, Color.white, height / 30,
					400);
			moon.setVeloY(35);
			listGraphic.add(moon);
	
			moonCurve = new LineCurve();
			listGraphic.add(moonCurve);
			
			rocket = new RectangleShape(width/2, height/2-height/10-30, 0, Color.gray, 40, 60, 10);
			listGraphic.add(rocket);
			
			rocketCurve = new LineCurve();
			listGraphic.add(rocketCurve);
		}

	}
	
	public void update() {
		synchronized (listGraphic) {
			moon.setVelocity(updateMoon(earth.getState(), moon.getState(), moon.getVelocity()));
			commandes.update();
			if(!(rocket.getInContact()==null) && (Collision.simpleCollision(rocket, earth) 
					|| Collision.simpleCollision(rocket, moon)))//we just arrive in contact with earth or moon.
			{
				
				if(Collision.simpleCollision(rocket, earth))
					rocket.setInContact(earth);
				else
					rocket.setInContact(moon);
				
			}
			else if(rocket.getInContact()!=null)
			{
				rocket.setTheta(commandes.getAngleThrust());
				double gravityForceNorm=PhysicUtility.norm2(getForceVector(rocket.getState(), 
						rocket.getInContact().getState(), 
						rocket.getMass()*rocket.getInContact().getMass()));
				
				//we have enough thrust to leave the planet.
				if(commandes.getThrustTotal()>gravityForceNorm)
				{
					rocket.setState(updateRocketVelocity(earth.getState(), moon.getState(), rocket.getState(),
							rocket.getVelocity(), rocket.getMass(), commandes.getThrustTotal(),false));
				}
				
				//we just left a planet
				if(!Collision.simpleCollision(rocket, earth) && Collision.simpleCollision(rocket, moon))
				{
					rocket.setInContact(null);
					
				}
			}
			for (GraphicObject object : listGraphic) {
				object.update();
			}
			
			
		}

	}

	
	/**
	 * Simulation of the trajectories of the moon and then the rocket, step by step
	 *  supposing that we keep the same orders
	 */
	public void simulate() {
		ObjectState[] moonList = new ObjectState[5000];
		ObjectState previousState, curentState;
		moonList[0] = moon.getGlobalState();
		for (int i = 1; i < 5000; i++) {
			// For every step we plot the moon state from the previous one,
			// using the update equation
			previousState = moonList[i - 1];
			moonList[i] = new ObjectState();
			curentState = moonList[i];
			curentState.setMass(moon.getMass());
			curentState.setVelocityXY(
					updateMoon(earth.getState(), previousState.getPositionXY(), previousState.getVelocityXY()));
			double[] update = PhysicUtility.scalarProd3(1./50., curentState.getVelocity());
			double[] sum = PhysicUtility.sum3(update, previousState.getPosition());
			curentState.setPosition(sum);
		}
		
		ObjectState[] rocketList = new ObjectState[1000];
		rocketList[0] = rocket.getGlobalState();
		for (int i = 1; i < rocketList.length; i++) {
			// For every step we plot the moon state from the previous one,
			// using the update equation
			previousState = rocketList[i - 1];
			rocketList[i] = new ObjectState();
			curentState = rocketList[i];
			//should be change to take in account the mass changing of the rocket.
			curentState.setMass(rocket.getMass()+commandes.simulateMass(i));
			curentState.setVelocityXY(
					updateRocketVelocity(earth.getState(), moonList[i].getPositionXY(), previousState.getPositionXY(),
							previousState.getVelocityXY(), curentState.getMass(),commandes.simulateThrust(i),false));
			double[] update = PhysicUtility.scalarProd3(1./10., curentState.getVelocity());
			double[] sum = PhysicUtility.sum3(update, previousState.getPosition());
			curentState.setPosition(sum);
		}
		updateRocketVelocity(earth.getState(), moonList[0].getPositionXY(), rocketList[0].getPositionXY(),
				rocketList[0].getVelocityXY(), rocketList[1].getMass(),commandes.simulateThrust(0),true);
		synchronized (listGraphic) {
			moonCurve.setPointsList(moonList);
			rocketCurve.setPointsList(rocketList);
		}
		

	}
	/**
	 * Get the Gravity force vectors between two bodies
	 * @param pos1 pos of the body we want to evolve
	 * @param pos2 post of the reference body
	 * @param factor mass constant (-G Ma Mb)
	 * @return the force vecteur from b on a
	 */
	public double[] getForceVector(double[] pos1, double[] pos2, double factor) {
		double F = -factor / Math.pow(PhysicUtility.distance(pos1, pos2), 2);
		double[] vect = PhysicUtility.unityVector(PhysicUtility.substraction2(pos1, pos2));
		return PhysicUtility.scalarProd(F, vect);
	}
	/**
	 * Get the moon velocity update from the state parameters, the rocket is too light to influence the system
	 * @param earthState
	 * @param moonState
	 * @param moonVelocity
	 * @return the velocity for the next time period.
	 */
	public double[] updateMoon(double[] earthState, double[] moonState, double[] moonVelocity) {
		double[] moonForces = getForceVector(moonState, earthState, earth.getMass() * moon.getMass());
		double[] moonUpdate = PhysicUtility.scalarProd(1 / moon.getMass(), moonForces);
		double[] moonSpeed = PhysicUtility.sum2(moonVelocity, moonUpdate);
		return moonSpeed;
	}
	
	/**
	 * Get the rocket velocity update from different parameters of the considering situation
	 * @param earthState
	 * @param moonState
	 * @param rocketState
	 * @param rocketVelocity
	 * @param rocketMass
	 * @param thrust
	 * @param print
	 * @return the updatee of the velovity
	 */
	public double[] updateRocketVelocity(double[] earthState, double[] moonState, 
			double[] rocketState, double[] rocketVelocity, double rocketMass,double thrust,boolean print)
	{
		double[] earthForce = getForceVector(rocketState, earthState, earth.getMass() * rocketMass);
		double[] moonForce = getForceVector(rocketState, moonState, moon.getMass() * rocketMass);
		double[] thrustForce = PhysicUtility.scalarProd(thrust, 
				PhysicUtility.unityVector(commandes.getAngleThrust()));
		if(print){
			System.out.println("mass:"+rocketMass);
			System.out.println("earthForce:"+PhysicUtility.norm2(earthForce));
			System.out.println("moonForce:"+PhysicUtility.norm2(moonForce));
			System.out.println("thrust:"+PhysicUtility.norm2(thrustForce));
		}
		//If the force is too high, just cancel it, it doesn't make any sense...
		if(PhysicUtility.norm2(moonForce)>100 || PhysicUtility.norm2(earthForce)>100)
		{
			return new double[]{0,0,0};
		}
		double[] sum = PhysicUtility.sum2(earthForce, moonForce);
		sum=PhysicUtility.sum2(sum, thrustForce);
		double[] update = PhysicUtility.scalarProd(1 / rocketMass, sum);
		double[] rocketSpeed = PhysicUtility.sum2(rocketVelocity, update);
		return rocketSpeed;

	}
}
