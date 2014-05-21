/**
* World.java by Peter Olson
*
* Runs the game
*
*@author Peter Olson
*@version 1.0
*/

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class World extends JPanel{
	
	public final int SCREEN_WIDTH = 1200;
	public final int SCREEN_HEIGHT = 800;
	
	protected ArrayList<WorldObject> allStillObjects = new ArrayList<WorldObject>();
   protected ArrayList<MovableObject> allMovableObjects = new ArrayList<MovableObject>();
   protected ArrayList<WorldObject> screenStillObjects = new ArrayList<WorldObject>();
   protected ArrayList<MovableObject> screenMovableObjects = new ArrayList<MovableObject>();
	
	private Timer timer;
	
	public World(){
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
      this.setBackground(new Color(255,255,255));
      this.setFocusable(true);
      this.setFocusTraversalKeysEnabled(false);
		
		this.addObject(new CardGUI(this, "Two of Clubs", 200, 200, "card_two_c.png"));		
		this.addMouseListener(new MouseWhisperer());
		
	}
	
	public boolean isOnScreen(WorldObject obj){
		return obj.getWorldX() < SCREEN_WIDTH && obj.getWorldX() > 0 &&
				 obj.getWorldY() < SCREEN_HEIGHT && obj.getWorldY() > 0;
	}
	
	public void addObject(WorldObject obj){
   	if(obj instanceof MovableObject){
      	this.allMovableObjects.add((MovableObject)obj);
         if(isOnScreen(obj))
         	this.screenMovableObjects.add((MovableObject)obj);	
      }else{
         this.allStillObjects.add(obj);
         if(isOnScreen(obj))
         	this.screenStillObjects.add(obj);
      }
   }
	
	public void removeObject(WorldObject obj){
      if(obj instanceof MovableObject){
      	this.allMovableObjects.remove((MovableObject)obj);  
         if(isOnScreen(obj))
         	this.screenMovableObjects.remove((MovableObject)obj);         
      }else{
         this.allStillObjects.remove(obj);
         if(isOnScreen(obj))
            this.screenStillObjects.remove(obj);          
      }
   }
	
	public void paintComponent(Graphics g){
   	super.paintComponent(g);
      for(WorldObject obj : this.screenStillObjects)
      	obj.paintComponent(g);
      for(MovableObject obj : this.screenMovableObjects)
         obj.paintComponent(g);
   }
	
	private class ClickListener implements ActionListener{
        
   	public void actionPerformed(ActionEvent e){    
      	repaint();
      }
   }
	
	private class MouseWhisperer implements MouseListener{
		
		public void mouseClicked(MouseEvent e){
			repaint();
		}
		
		public void mousePressed(MouseEvent e){
			
		}
		
		public void mouseReleased(MouseEvent e){
			
		}
		
		public void mouseEntered(MouseEvent e){
			
		}
		
		public void mouseExited(MouseEvent e){
			
		}
	}
   
   public void addCard()
   {
      this.addObject(new CardGUI(this, "blah", 100, 300, "card_back.png"));
   }

}