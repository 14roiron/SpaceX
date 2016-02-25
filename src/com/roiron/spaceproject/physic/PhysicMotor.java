package com.roiron.spaceproject.physic;

import java.awt.Color;
import java.util.List;

import com.roiron.spaceproject.graphic.CercleShape;
import com.roiron.spaceproject.graphic.GraphicObject;
import com.roiron.spaceproject.graphic.SpacePanel;

public class PhysicMotor {
	
	private CercleShape earth;
	private CercleShape moon;
	//private RectangleShape rocket;
	private List<GraphicObject> listGraphic;
	

	public PhysicMotor(List<GraphicObject> listGraphic) {
		this.listGraphic=listGraphic;
		//We fisrt define the earth, Velocity equal to 0
		earth=new CercleShape(SpacePanel.width/2, SpacePanel.height/2, 0, Color.blue, SpacePanel.height/10,1000);
		listGraphic.add(earth);
		
		moon=new CercleShape(SpacePanel.width*3/4, SpacePanel.height/2, 0, Color.white, SpacePanel.height/30,10);
		moon.setVeloY(-8);
		listGraphic.add(moon);
	}
	
	
	public void update(){
		
		moon.setVelocity(updateMoon(earth.getState(), moon.getState(), moon.getVelocity()));
		
		for(GraphicObject object : listGraphic)
		{
			object.update();
		}
		
		
	}
	public void simulate()
	{
		ObjectState[] moonList=new ObjectState[100];
		ObjectState previousState,curentState;
		moonList[0]=moon.getGlobalState();
		for(int i=1;i<100;i++)
		{
			//For every step we plot the moon state from the previous one, using the update equation
			previousState=moonList[i-1];
			curentState=moonList[i];
			curentState=new ObjectState();
			curentState.setMass(moon.getMass());
			curentState.setVelocityXY(updateMoon(earth.getState(),
					previousState.getPositionXY(), 
					previousState.getVelocityXY()));
			double[] update=PhysicUtility.scalarProd(1/10, curentState.getVelocity());
			double[] sum=PhysicUtility.sum3(update, previousState.getPosition());
			curentState.setPosition(sum);
			
		}
		
	}
	public double[] getForceVector(double[] pos1, double[] pos2, double factor)
	{
		double F = - factor/Math.pow(PhysicUtility.distance(pos1,pos2),2);
		double[] vect= PhysicUtility.unityVector(PhysicUtility.substraction2(pos1, pos2));
		return PhysicUtility.scalarProd(F, vect);
	}
	public double[] updateMoon(double[] earthState, double[] moonState, double[] moonVelocity)
	{
		double[] moonForces=getForceVector(moonState, earthState, earth.getMass()*moon.getMass());
		double[] moonUpdate=PhysicUtility.scalarProd(1/moon.getMass(), moonForces);
		double[] moonSpeed=PhysicUtility.sum2(moonVelocity, moonUpdate);
		return moonSpeed;
	}

	

}
