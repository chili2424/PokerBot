
import java.awt.*;
import java.util.ArrayList;

//To Do:
//not say "To Call: 0" ("your turn" instead? black border around boxes?)
//show all cards at end
//say who won
//tell user what type of hand they have
//make raise work
//make button EITHER Call OR Check
//FIX FLICKERING

public class GUI
{
   private int positions[][] = {{50, 20}, {25, 26}, {4, 48}, {18, 86}, {45, 96}, {75, 90}, {96, 66}, {91, 39}, {75, 26}};
   private String names[] =         {"User", "Alpha", "Bravo", "Charlie", "Delta", "Echo", "Fox Trot", "Golf", "Hotel"};
   private int cardPositions[][] = {{50, 20}, {31, 36}, {14, 53}, {25, 76}, {45, 83}, {71, 78}, {85, 64}, {81, 47}, {70, 37}};

   
   public void drawTable(Table t)
   {
           
      StdDraw.setFont();
      
      StdDraw.setPenColor(new Color(255, 255, 255));
      
      StdDraw.filledSquare(0, 0, 150);
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.rectangle(0, 10, 15, 20);
      printTextBox(t);
      StdDraw.setFont();
      
      //Draw Table
      StdDraw.setPenColor(new Color(0, 120, 0));
      StdDraw.filledEllipse(50, 58, 40, 30);
      
      StdDraw.setPenColor(0, 0, 0);
      for(int i = 0; i < 9; i++)
      {
         Player p = t.playerIn(names[i]);
         if(p != null && p.isActive())
         {
            StdDraw.circle(positions[i][0], positions[i][1], 6);
            StdDraw.text(positions[i][0], positions[i][1] + 2, names[i]);
            StdDraw.text(positions[i][0], positions[i][1] - 3, "$" + Integer.toString(p.getMoney()));
            if(i > 0)
            {
               StdDraw.picture(cardPositions[i][0], cardPositions[i][1], "cards/card_back.png", 3, 6);
               StdDraw.picture(cardPositions[i][0] + 1, cardPositions[i][1], "cards/card_back.png", 3, 6);
            } 
         }
         else if(p != null && !p.isActive())
         {
            StdDraw.setPenColor(new Color(100, 100, 100));
            StdDraw.filledCircle(positions[i][0], positions[i][1], 6);
            StdDraw.setPenColor(new Color(0, 0, 0));
            StdDraw.text(positions[i][0], positions[i][1] + 2, names[i]);
            StdDraw.text(positions[i][0], positions[i][1] - 3, "$" + Integer.toString(p.getMoney()));
         }
         else
         {
            StdDraw.setPenColor(new Color(0, 0, 0));
            StdDraw.filledCircle(positions[i][0], positions[i][1], 6);
         }
      }
      
      //Draw Player Cards 
      if(t.getPlayer(0).isActive())
      {
         StdDraw.picture(47, 36, t.getPlayer(0).getPreFlop().getCard(0).getPath(), 6, 12);
         StdDraw.picture(53, 36, t.getPlayer(0).getPreFlop().getCard(1).getPath(), 6, 12);
      }
      
      //Draw Table Cards
      for(int i = 0; i < t.getTableCards().size(); i++)
      {
         StdDraw.picture(29 + i*10, 58, t.getTableCards().get(i).getPath(), 8, 16);
      }
      
      StdDraw.setFont(new Font("SanSerif", Font.BOLD, 30));
      StdDraw.text(50, 70, "Pot: $" + t.getPot());
      
      //Draw buttons
      StdDraw.setPenColor(new Color(139,69,19));
      StdDraw.setFont(new Font("SanSerif", Font.BOLD, 20));
      StdDraw.filledRectangle(30,5,8,5); 
      StdDraw.filledRectangle(50,5,8,5);
      StdDraw.filledRectangle(70,5,8,5);
      StdDraw.setPenColor(new Color(0,0,0));
      StdDraw.text(30,5,"Fold");
      StdDraw.text(50,5,"Check/Call");
      StdDraw.text(70,5,"Raise");   
   }
   
   public int handleMouse(Table t)
   {
      boolean pressed = false;
      while(!pressed)
      {
         double x = StdDraw.mouseX();
         double y = StdDraw.mouseY();
             
         if(StdDraw.mousePressed() && y < 10 && y > 0)
         {
            //fold box
            if(x > 22 && x < 38)
               return -1;
            
            //check/call
            else if(x > 42 && x < 58)
               return -2;
            
            else if(x > 62 && x < 78)
            {
               //handle slider   
            }
         }
      }
      return 0;
   }
   
   public void printTextBox(Table t)
   {
      ArrayList<String> text = t.textBox();
      int height = 28;
      
      StdDraw.setFont(new Font("SanSerif", Font.PLAIN, 15));
      
      for(String s: text)
      {
         StdDraw.textLeft(-5, height, s);
         height -= 3;
      }
   }
}