package com.roiron.spaceproject.physic;

import java.awt.Color;
import java.util.List;

import com.roiron.spaceproject.graphic.CercleShape;
import com.roiron.spaceproject.graphic.GraphicObject;
import com.roiron.spaceproject.graphic.RectangleShape;
import com.roiron.spaceproject.graphic.SpacePanel;

public class physiqueMotor {
	
	private CercleShape earth;
	private CercleShape moon;
	private RectangleShape rocket;
	

	public physiqueMotor(List<GraphicObject> listGraphic) {
		//We fisrt define the earth, Velocity equal to 0
		earth=new CercleShape(SpacePanel.width/2, SpacePanel.height/2, 0, Color.blue, SpacePanel.height/10);
		listGraphic.add(earth);
		
		moon=new CercleShape(SpacePanel.width*3/4, SpacePanel.height/2, 0, Color.white, SpacePanel.height/30);
		listGraphic.add(moon);
	}
	
	
	public void simulate(){
		
	}
	

}
