package com.roiron.spaceproject;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.roiron.spaceproject.graphic.SpacePanel;

public class Main extends JFrame{
	
	JPanel spacePanel;  //the main frame, where we draw the space
	
	
	public Main ()
	{
		spacePanel = new SpacePanel();
		this.setTitle("Space Simulation");
		this.setSize(spacePanel.getWidth(), spacePanel.getHeight());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(spacePanel);
		this.setVisible(true);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		new Main();

	}
	

}
