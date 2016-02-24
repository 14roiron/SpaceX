package com.roiron.spaceproject;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.roiron.spaceproject.graphic.CercleShape;
import com.roiron.spaceproject.graphic.SpacePanel;

public class Main extends JFrame{
	
	SpacePanel spacePanel;  //the main frame, where we draw the space
	
	
	public Main () throws InterruptedException
	{
		spacePanel = new SpacePanel();
		this.setTitle("Space Simulation");
		this.setSize(spacePanel.getWidth(), spacePanel.getHeight());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(spacePanel);
		this.setVisible(true);
		
		Thread.sleep(1000);	
		CercleShape c= new CercleShape(spacePanel.getWidth()/2,spacePanel.getHeight()/2,0, Color.blue, spacePanel.getWidth()/4);
		spacePanel.addElementListObject(c);
		spacePanel.repaint();
		Thread.sleep(1000);
		c.setAccX(0.01);
		while(true)
		{
			spacePanel.repaint();
			Thread.sleep(1000);
		}
		
		
	}
	public static void main(String[] args) throws InterruptedException {
		new Main();

	}
	

}
