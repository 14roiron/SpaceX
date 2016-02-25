package com.roiron.spaceproject.graphic;

import java.awt.Graphics;

public abstract class GraphicObject {
	
	
	/**
	 * Print the current figure on the graphic object set.
	 * @param g the graphic object
	 */
	public abstract void draw(Graphics g);
	public abstract void update();

}
