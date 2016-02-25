package com.roiron.spaceproject;



import javax.swing.JFrame;

import com.roiron.spaceproject.graphic.SpacePanel;
import com.roiron.spaceproject.physic.PhysicMotor;

public class Main extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SpacePanel spacePanel;  //the main frame, where we draw the space
	
	
	public Main () throws InterruptedException
	{
		spacePanel = new SpacePanel();
		this.setTitle("Space Simulation");
		this.setSize(SpacePanel.width, SpacePanel.height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(spacePanel);
		this.setVisible(true);
		PhysicMotor motor = new PhysicMotor(spacePanel.getListObject()); 
		
		
		
		while(true)
		{
			for(int i=0;i<10;i++)
			{
				
				motor.update();
				Thread.sleep(1);
			}
			motor.simulate();
			spacePanel.repaint();
			
			
			
		}
		
		
	}
	public static void main(String[] args) throws InterruptedException {
		new Main();

	}
	

}
