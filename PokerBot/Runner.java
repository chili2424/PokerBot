/**
* Runner.java by Peter Olson
*
* Runs the game, using a JApplet
*
*@author Peter Olson
*@version 1.0
*/

import javax.swing.JApplet;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Runner extends JApplet{

	public void init(){
		World world = new World();
		Container screen = this.getContentPane();
		screen.add(world);
		setSize(1200, 800);
	}
	
	// Using JFrame*:				*would have to extend JFrame and comment out above init method
	/*
	public Runner() {
	   World world = new World();
	   getContentPane().add(world);
	   addWindowListener(new WindowAdapter(){
	  		public void windowClosing(WindowEvent e) {
	      	System.exit(0);
	      }
	   });
	   pack();
		setSize(1200, 800);
	   setVisible(true);
   }
  
   public static void main(String[] args){
  		Runner runner = new Runner();
   }
	*/
}