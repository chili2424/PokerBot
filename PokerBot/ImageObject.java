/**
* ImageObject.java by Peter Olson
*
* WorldObjects that have images
*
*@author Peter Olson
*@version 1.0
*/

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.net.URL;

public class ImageObject extends WorldObject{

	protected ImageIcon pic;
	
	public ImageObject(World world, String name, int worldX, int worldY, String imageName){
		super(world, name, worldX, worldY);
      System.out.println(getClass());
    	URL imgURL = getClass().getResource("cards/" + imageName);
      System.out.println(imgURL);
		pic = new ImageIcon(imgURL);
		
		this.width = pic.getIconWidth();
      this.height = pic.getIconHeight();
	}
	
	public void paintComponent(Graphics g){
   	pic.paintIcon(world, g, this.getWorldX(), this.getWorldY());
   }

}