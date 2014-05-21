/**
* MovableObject.java by Peter Olson
*
* All draggable/movable or moving objects
*
*@author Peter Olson
*@version 1.0
*/

import java.awt.Graphics;

public class MovableObject extends ImageObject{
	
	public MovableObject(World world, String name, int worldX, int worldY, String imageName){
		super(world, name, worldX, worldY, imageName);
	}
	
	public void paintComponent(Graphics g){
  		pic.paintIcon(world, g, this.getWorldX(), this.getWorldY());
   }
}