package com.roiron.spaceproject.physic;

import java.awt.Color;
import java.util.List;

import com.roiron.spaceproject.Commandes;
import com.roiron.spaceproject.collisions.Collision;
import com.roiron.spaceproject.graphic.CircleShape;
import com.roiron.spaceproject.graphic.GraphicObject;
import com.roiron.spaceproject.graphic.LineCurve;
import com.roiron.spaceproject.graphic.RectangleShape;

public class PhysicMotor {

	private CircleShape earth;
	private CircleShape moon;
	private LineCurve moonCurve;
	private RectangleShape rocket;
	private LineCurve rocketCurve;
	private List<GraphicObject> listGraphic;
	private Commandes commandes;
	public static final int height = 1000;
	public static final int width = 1300;

	/**
	 * Default constructor of the graphic motor
	 * 
	 * @param listGraphic
	 *            object containing all drawing figures
	 * @param commandes
	 *            object, containing the user interactions.
	 */
	public PhysicMotor(List<GraphicObject> listGraphic, Commandes commandes) {
		this.commandes = commandes;
		synchronized (listGraphic) {
			this.listGraphic = listGraphic;

			// We fisrt define the earth, Velocity equal to 0
			earth = new CircleShape(width / 2, height / 2, 0, Color.BLUE, 40, 3000);
			listGraphic.add(earth);

			moon = new CircleShape(width * 8 / 10, height / 2, 0, Color.WHITE, 15, 800);
			moon.setVeloY(20);
			listGraphic.add(moon);

			moonCurve = new LineCurve();
			listGraphic.add(moonCurve);

			rocket = new RectangleShape(width / 2, height / 2 - 40 - 10, 0, Color.DARK_GRAY, 20, 30, 30);
			rocket.setInContact(earth);
			listGraphic.add(rocket);

			rocketCurve = new LineCurve();
			listGraphic.add(rocketCurve);

		}

	}

	public void update() {
		// if we already crashed on the planet it's done
		if (commandes.isCrached())
			return;

		synchronized (listGraphic) {

			moon.setVelocity(updateMoon(earth.getState(), moon.getState(), moon.getVelocity()));

			commandes.update();// decrease the tank stock
			rocket.setTheta(commandes.getAngleThrust() + Math.PI / 2);

			boolean inContact = Collision.simpleCollision(rocket, earth);

			// we just arrive in contact with earth or moon.
			if ((rocket.getInContact() == null)
					&& (Collision.simpleCollision(rocket, earth) || Collision.simpleCollision(rocket, moon))) {

				if (Collision.simpleCollision(rocket, earth))
					rocket.setInContact(earth);
				else
					rocket.setInContact(moon);
				if (PhysicUtility.norm2(
						PhysicUtility.substraction2(rocket.getVelocity(), rocket.getInContact().getVelocity())) > 10) {
					commandes.setCrached(true);
				}

			} else if (rocket.getInContact() != null) {
				double gravityForceNorm = PhysicUtility.norm2(getForceVector(rocket.getState(),
						rocket.getInContact().getState(), rocket.getMass() * rocket.getInContact().getMass(), false));

				// we have enough thrust to leave the planet.
				if (commandes.getThrustTotal() > gravityForceNorm) {
					rocket.setVelocity(updateRocketVelocity(earth.getState(), moon.getState(), rocket.getState(),
							rocket.getVelocity(), rocket.getMass(), commandes.getThrustTotal(), false));
				} else
					rocket.setVelocity(rocket.getInContact().getVelocity());

				// we just left a planet
				if (!Collision.simpleCollision(rocket, rocket.getInContact())) {
					rocket.setInContact(null);

				}
			} else {
				rocket.setVelocity(updateRocketVelocity(earth.getState(), moon.getState(), rocket.getState(),
						rocket.getVelocity(), rocket.getMass(), commandes.getThrustTotal(), false));
			}
			// */
			for (GraphicObject object : listGraphic) {
				object.update();
			}

		}

	}

	/**
	 * Simulation of the trajectories of the moon and then the rocket, step by
	 * step supposing that we keep the same orders
	 */
	public void simulate() {
		ObjectState[] moonList = new ObjectState[7000];
		ObjectState previousState, curentState;
		moonList[0] = moon.getGlobalState();
		for (int i = 1; i < 7000; i++) {
			// For every step we plot the moon state from the previous one,
			// using the update equation
			previousState = moonList[i - 1];
			moonList[i] = new ObjectState();
			curentState = moonList[i];
			curentState.setMass(moon.getMass());
			curentState.setVelocityXY(
					updateMoon(earth.getState(), previousState.getPositionXY(), previousState.getVelocityXY()));
			double[] update = PhysicUtility.scalarProd3(1. / 50., curentState.getVelocity());
			double[] sum = PhysicUtility.sum3(update, previousState.getPosition());
			curentState.setPosition(sum);
		}
		// We initialise the positon array
		ObjectState[] rocketList = new ObjectState[7000];
		rocketList[0] = rocket.getGlobalState();
		rocketList[0].setMass(rocket.getMass() + commandes.simulateMass(0));
		for (int i = 1; i < rocketList.length; i++) {
			// For every step we plot the rocket state from the previous one,
			// using the update equation and the new moon position
			previousState = rocketList[i - 1];
			rocketList[i] = new ObjectState();
			curentState = rocketList[i];
			// should be change to take in account the mass changing of the
			// rocket.
			curentState.setMass(rocket.getMass() + commandes.simulateMass(i));
			curentState.setVelocityXY(
					updateRocketVelocity(earth.getState(), moonList[i].getPositionXY(), previousState.getPositionXY(),
							previousState.getVelocityXY(), curentState.getMass(), commandes.simulateThrust(i), false));
			//Scalair product to make it working
			double[] update = PhysicUtility.scalarProd3(1. / 50., curentState.getVelocity());
			double[] sum = PhysicUtility.sum3(update, previousState.getPosition());
			curentState.setPosition(sum);
		}
		updateRocketVelocity(earth.getState(), moonList[0].getPositionXY(), rocketList[0].getPositionXY(),
				rocketList[0].getVelocityXY(), rocketList[0].getMass(), commandes.simulateThrust(0), true);
		synchronized (listGraphic) {
			moonCurve.setPointsList(moonList);
			rocketCurve.setPointsList(rocketList);
		}

	}

	/**
	 * Get the Gravity force vectors between two bodies
	 * 
	 * @param pos1
	 *            pos of the body we want to evolve
	 * @param pos2
	 *            post of the reference body
	 * @param factor
	 *            mass constant (-G Ma Mb)
	 * @return the force vector from b on a
	 */
	public double[] getForceVector(double[] pos1, double[] pos2, double factor, boolean print) {
		double F = -factor / Math.pow(PhysicUtility.distance(pos1, pos2), 2);
		if (print) {
			System.out.println("factor: " + factor);
			System.out.println("distance: " + Math.pow(PhysicUtility.distance(pos1, pos2), 2));
			System.out.println("F: " + F);
		}
		double[] vect = PhysicUtility.unityVector(PhysicUtility.substraction2(pos1, pos2));
		return PhysicUtility.scalarProd(F, vect);
	}

	/**
	 * Get the moon velocity update from the state parameters, the rocket is too
	 * light to influence the system
	 * 
	 * @param earthState
	 * @param moonState
	 * @param moonVelocity
	 * @return the velocity for the next time period.
	 */
	public double[] updateMoon(double[] earthState, double[] moonState, double[] moonVelocity) {
		double[] moonForces = getForceVector(moonState, earthState, earth.getMass() * moon.getMass(), false);
		double[] moonUpdate = PhysicUtility.scalarProd(1 / moon.getMass(), moonForces);
		double[] moonSpeed = PhysicUtility.sum2(moonVelocity, moonUpdate);
		return moonSpeed;
	}

	/**
	 * Get the rocket velocity update from different parameters of the
	 * considering situation
	 * 
	 * @param earthState
	 * @param moonState
	 * @param rocketState
	 * @param rocketVelocity
	 * @param rocketMass
	 * @param thrust
	 * @param print
	 * @return the updatee of the velovity
	 */
	public double[] updateRocketVelocity(double[] earthState, double[] moonState, double[] rocketState,
			double[] rocketVelocity, double rocketMass, double thrust, boolean print) {
		double[] earthForce = getForceVector(rocketState, earthState, earth.getMass() * rocketMass, false);
		double[] moonForce = getForceVector(rocketState, moonState, moon.getMass() * rocketMass, false);
		double[] thrustForce = PhysicUtility.scalarProd(thrust, PhysicUtility.unityVector(commandes.getAngleThrust()));
		if (print) {
			commandes.resetInfo("rocket parameters");
			commandes.addInfos("mass: " + rocketMass);
			commandes.addInfos("earthForce: " + PhysicUtility.norm2(earthForce));
			commandes.addInfos("moonForce: " + PhysicUtility.norm2(moonForce));
			commandes.addInfos("thrust: " + PhysicUtility.norm2(thrustForce));
			commandes.addInfos("velocity: " + PhysicUtility.norm2(rocketVelocity));
			//System.out.println(commandes.getInfos());
		}
		// If the force is too high, just cancel it, it doesn't make any
		// sense...
		if (PhysicUtility.norm2(moonForce) > 100 || PhysicUtility.norm2(earthForce) > 100) {
			return new double[] { 0, 0, 0 };
		}
		double[] sum = PhysicUtility.sum2(earthForce, moonForce);
		sum = PhysicUtility.sum2(sum, thrustForce);
		double[] update = PhysicUtility.scalarProd(1 / (rocketMass), sum);
		double[] rocketSpeed = PhysicUtility.sum2(rocketVelocity, update);
		return rocketSpeed;

	}
}
