package com.roiron.spaceproject.graphic;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class SpacePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * Screen Size and Object List
	 */
	public static final int height=600;
	public static final int width=800;
	
	private List<GraphicObject> listObject;
	
	
	
	
	public SpacePanel() {
		listObject=new ArrayList<GraphicObject>();
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		draw(g);

	}
	private void draw(Graphics g) {
		for(GraphicObject object : listObject)
		{
			object.update();
			object.draw(g);
		}
		
	}

	/**
	 * Add an element to the graphic list
	 * @param o object to add
	 */
	public void addElementListObject(GraphicObject o)
	{
		this.listObject.add(o);
	}
	/**
	 * Get an element from the graphic list
	 * @param id of the element you want
	 * @return GraphicObject of the id given
	 */
	public GraphicObject getElementListObjectById(int id)
	{
		return this.listObject.get(id);
	}
	/**
	 * Getter for the entire list
	 * @return the listObject
	 */
	public List<GraphicObject> getListObject() {
		return listObject;
	}

}
