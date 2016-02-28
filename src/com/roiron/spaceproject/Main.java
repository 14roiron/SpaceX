package com.roiron.spaceproject;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import com.roiron.spaceproject.graphic.AngleControlPanel;
import com.roiron.spaceproject.graphic.RectangleShape;
import com.roiron.spaceproject.graphic.SpacePanel;
import com.roiron.spaceproject.physic.PhysicMotor;

public class Main extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SpacePanel spacePanel;  //the main frame, where we draw the space
	AngleControlPanel angleControlPanel;
	Commandes commandes;
	JPanel controlPannel;
	JProgressBar thrustProgressBar;
	JProgressBar gasProgressBar;
	JProgressBar boosterProgressBar;
	JLabel statutLabel;
	JPanel mainPanel;
	PhysicMotor motor;
	
	
	public Main () throws InterruptedException
	{
	
		start();

	}
	public void start()
	{
		spacePanel = new SpacePanel();
		commandes=new Commandes();


		motor = new PhysicMotor(spacePanel.getListObject(),commandes); 
		
		this.addKeyListener(new KeyListener() {


			public void keyTyped(KeyEvent arg0) {

			}


			
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyChar() == 'w') {
					commandes.increaseThrust();
				}
				if (arg0.getKeyChar() == 's') {
					commandes.decreaseThrust();
				}
				if (arg0.getKeyChar() == 'd') {
					commandes.increaseAngleThrust();
				}
				if (arg0.getKeyChar() == 'a') {
					commandes.decreaseAngleThrust();
				}

			}


			public void keyReleased(KeyEvent e) {
				
			}
		});
		controlPannel = new JPanel();
		controlPannel.setLayout(new GridLayout(5,2));
		
		controlPannel.add(new JLabel("Thrust power"));
		thrustProgressBar = new JProgressBar(0, 100);
		thrustProgressBar.setValue(0);
		thrustProgressBar.setStringPainted(false);
		controlPannel.add(thrustProgressBar);
		
		
		controlPannel.add(new JLabel("angle direction"));
		angleControlPanel=new AngleControlPanel(commandes);
		controlPannel.add(angleControlPanel);
		
		
		controlPannel.add(new JLabel("Remaining gas"));
		gasProgressBar = new JProgressBar(0, 100);
		gasProgressBar.setValue(0);
		gasProgressBar.setStringPainted(true);
		controlPannel.add(gasProgressBar);
		
		controlPannel.add(new JLabel("Remaining booster"));
		boosterProgressBar = new JProgressBar(0, 100);
		boosterProgressBar.setValue(0);
		boosterProgressBar.setStringPainted(true);
		controlPannel.add(boosterProgressBar);
		
		controlPannel.add(new JLabel("Statut"));
		statutLabel = new JLabel("Landed");
		statutLabel.setForeground(Color.green);
		controlPannel.add(statutLabel);
		
		
		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		controlPannel.setBounds(1300-200, 0, 200, 300);
	    mainPanel.add(controlPannel);
		spacePanel.setBounds(0, 0,1300, 1000);
	    mainPanel.add(spacePanel);
	    

		
		
		this.setTitle("Space Simulation");
		this.setSize(1300, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(mainPanel);
		this.setVisible(true);
		
		while(true)
		{
			
			for(int i=0;i<10;i++)
			{
				
				motor.update();
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			motor.simulate();
			updateGraphic();
		}
	}
	public void updateGraphic()
	{
		spacePanel.repaint();
		angleControlPanel.repaint();
		thrustProgressBar.setValue((int)(commandes.getGasThrust()*10.));
		gasProgressBar.setValue((int)(commandes.getGasTankPerecentage()));
		boosterProgressBar.setValue((int)(commandes.getBoosterTankPerecentage()));
		if(commandes.isCrached())
		{
			statutLabel.setText("crashed");;
			statutLabel.setForeground(Color.red);
		}
		else if (((RectangleShape)(spacePanel.getElementListObjectById(3))).getInContact()!=null) {
			
			statutLabel.setText("Landed");
			statutLabel.setForeground(Color.green);
		}
		else {
			statutLabel.setText("In flight");
			statutLabel.setForeground(Color.blue);
		}
		this.repaint();
	}
	public static void main(String[] args) throws InterruptedException {
		new Main();

	}
	

}
