/**
* WorldObject.java by Peter Olson
*
* General object in game
*
*@author Peter Olson
*@version 1.0
*/

import javax.swing.JPanel;
import java.awt.Graphics;

public class WorldObject extends JPanel{
	
	protected World world;
	protected String name;
	protected int worldX;
	protected int worldY;
	protected int width;
	protected int height;
	
	public WorldObject(World world, String name, int worldX, int worldY){
		this.world = world;
		this.name = name;
		this.worldX = worldX;
		this.worldY = worldY;
		width = 0;
		height = 0;
	}
	
	public WorldObject(World world, String name, int worldX, int worldY, int width, int height){
		this.world = world;
		this.name = name;
		this.worldX = worldX;
		this.worldY = worldY;
		this.width = width;
		this.height = height;
	}
	
	public String getName(){
		return name;
	}
	
	public int getWorldX(){
		return worldX;
	}
	
	public int getWorldY(){
		return worldY;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void delete(){
      this.world.removeObject(this);
   }
	
	public void paintComponent(Graphics g){
      super.paintComponent(g);
   }
}