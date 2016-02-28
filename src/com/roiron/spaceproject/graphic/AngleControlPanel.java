package com.roiron.spaceproject.graphic;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.roiron.spaceproject.Commandes;
import com.roiron.spaceproject.Main;

public class AngleControlPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private Commandes commandes;
	public AngleControlPanel(Commandes commandes)
	{
		super();
		this.commandes=commandes;
	}
	
	public void paintComponent(Graphics g) {
		int size=100;
		double radius=(Math.min(getHeight(), getWidth())/2.)*0.90;
		g.setColor(Color.white);
		g.fillOval(0, 0, (int)radius*2, (int)radius*2);
		g.setColor(Color.gray);
		
		
		
		double x1,x2,y1,y2;

		x1=radius+radius*Math.cos(commandes.getAngleThrust());
		x2=radius-radius*Math.cos(commandes.getAngleThrust());
		
		y1=radius+radius*Math.sin(commandes.getAngleThrust());
		y2=radius-radius*Math.sin(commandes.getAngleThrust());

		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
		

	}

	
	

}
