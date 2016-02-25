package com.roiron.spaceproject;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.roiron.spaceproject.graphic.CercleShape;
import com.roiron.spaceproject.graphic.RectangleShape;
import com.roiron.spaceproject.graphic.SpacePanel;

public class Main extends JFrame{
	
	SpacePanel spacePanel;  //the main frame, where we draw the space
	
	
	public Main () throws InterruptedException
	{
		spacePanel = new SpacePanel();
		this.setTitle("Space Simulation");
		this.setSize(spacePanel.width, spacePanel.height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(spacePanel);
		this.setVisible(true);
		
		Thread.sleep(1000);	
		double h= SpacePanel.height;
		double w= SpacePanel.width;
		CercleShape c= new CercleShape(w/2,h/2,0, Color.blue, w/8);
		RectangleShape r = new RectangleShape(w/2-w/6, h/2-h/6, 0, Color.black, w/12, h/12);
		spacePanel.addElementListObject(c);
		spacePanel.addElementListObject(r);
		spacePanel.repaint();
		Thread.sleep(1000);
		c.setAccX(0.01);
		r.setAtheta(0.01);
		while(true)
		{
			spacePanel.repaint();
			Thread.sleep(10);
			System.out.println(r.getVtheta());
		}
		
		
	}
	public static void main(String[] args) throws InterruptedException {
		new Main();

	}
	

}
