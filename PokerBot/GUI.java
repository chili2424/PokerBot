
import java.awt.*;
import java.util.ArrayList;
import java.text.*;

//To Do:
//tell user what type of hand they have


public class GUI
{
   private int positions[][] = {{50, 20}, {25, 26}, {4, 48}, {18, 86}, {45, 96}, {75, 90}, {96, 66}, {91, 39}, {75, 26}};
   private String names[] = {"User", "Alpha", "Bravo", "Charlie", "Delta", "Echo", "Fox Trot", "Golf", "Hotel"};
   private int cardPositions[][] = {{50, 20}, {31, 36}, {14, 53}, {25, 76}, {45, 83}, {71, 78}, {85, 64}, {81, 47}, {70, 37}};
   private double sliderY;
   
   /**
   *This function draws the entire screen including table, players, their cards, and a box for text updates.
   *
   */
   public void drawTable(Table t, boolean showCards, boolean playersTurn)
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
            StdDraw.text(positions[i][0], positions[i][1] + 3, names[i]);
            StdDraw.text(positions[i][0], positions[i][1] - 3, "$" + Integer.toString(p.getMoney()));
            if(i > 0 && !showCards)
            {
               StdDraw.picture(cardPositions[i][0], cardPositions[i][1], "cards/card_back.png", 3, 6);
               StdDraw.picture(cardPositions[i][0] + 1, cardPositions[i][1], "cards/card_back.png", 3, 6);
            }
            else if(i > 0 && showCards)
            {
               StdDraw.picture(cardPositions[i][0], cardPositions[i][1],p.getPreFlop().getCard(0).getPath() , 6, 12);
               StdDraw.picture(cardPositions[i][0] + 3, cardPositions[i][1], p.getPreFlop().getCard(1).getPath(), 6, 12);
               //print hand strengths
               double hs = ((AI)p).handStrength();
               if(cardPositions[i][1] > positions[i][1] && t.numTableCards() > 0 && hs >= 0)
                  StdDraw.text(positions[i][0], positions[i][1] - 8, "HS: " + (int)(hs * 100) + "%");
               else if(t.numTableCards() > 0 && hs >= 0)
                  StdDraw.text(positions[i][0], positions[i][1] + 8, "HS: " + (int)(hs * 100) + "%");
            
             //  if(t.numTableCards() >= 3)
             //     StdDraw.text(positions[i][0], positions[i][1] - 10, p.getBestHand().toString());
              
            }
         }
         else if(p != null && !p.isActive())
         {
            StdDraw.setPenColor(new Color(100, 100, 100));
            StdDraw.filledCircle(positions[i][0], positions[i][1], 6);
            StdDraw.setPenColor(new Color(0, 0, 0));
            StdDraw.text(positions[i][0], positions[i][1] + 3, names[i]);
            StdDraw.text(positions[i][0], positions[i][1] - 3, "$" + Integer.toString(p.getMoney()));
         }
         else
         {
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.filledCircle(positions[i][0], positions[i][1], 6);
            StdDraw.setPenColor(StdDraw.BLACK);
         }
      }
      
      
      //Draw Dealer Button
      StdDraw.setPenColor(StdDraw.WHITE);
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.setFont(new Font("SanSerif", Font.BOLD, 20));
      StdDraw.text(positions[t.getDealer()][0],positions[t.getDealer()][1], "D");
      
      
      //Draw Player Cards 
      if(t.playerIn("User") != null && t.playerIn("User").isActive())
      {
         StdDraw.picture(49, 36, t.getPlayer(0).getPreFlop().getCard(0).getPath(), 8, 16);
         StdDraw.picture(52, 36, t.getPlayer(0).getPreFlop().getCard(1).getPath(), 8, 16);
      }
      
      //Draw Table Cards
      if(t.activeCount() > 1)
         for(int i = 0; i < t.getTableCards().size(); i++)
         {
            StdDraw.picture(34 + i*8, 58, t.getTableCards().get(i).getPath(), 8, 16);
         }
      
      StdDraw.setFont(new Font("SanSerif", Font.BOLD, 30));
      StdDraw.text(50, 70, "Pot: $" + t.getPot());
      
      //Draw buttons
      if(playersTurn && t.getPlayer(0).getMoney() > 0){
         StdDraw.setPenColor(new Color(139,69,19));
         StdDraw.setFont(new Font("SanSerif", Font.BOLD, 20));
         StdDraw.filledRectangle(30,5,8,5); 
         StdDraw.filledRectangle(70,5,8,5);
         StdDraw.setPenColor(new Color(0,0,0));
         StdDraw.text(30,5,"Fold");
         
         int sliderMin = t.getHighestRaise() + t.getHighestBet() > t.getHighestBet() + t.getHighestRaise() ? 
                  t.getHighestRaise() + t.getHighestBet() : t.getHighestBet() + t.getHighestRaise();
      
         int sliderMax = t.getPlayer(0).getMoney();
         
         if(t.getPlayer(t.getCurPlayer()).getMoney() > t.getHighestBet() - t.getPlayer(t.getCurPlayer()).getMoneyIn()){
            
            StdDraw.setPenColor(new Color(139,69,19));
            StdDraw.filledRectangle(50,5,8,5);
            
            StdDraw.setPenColor(new Color(0,0,0));
            if(t.getHighestBet() - t.getPlayer(t.getCurPlayer()).getMoneyIn() > 0)
               StdDraw.text(50,5,"Call: $" + (t.getHighestBet() - t.getPlayer(t.getCurPlayer()).getMoneyIn()));
            else
               StdDraw.text(50,5,"Check");
         }
      
         if(sliderMin < sliderMax)
            StdDraw.text(70,5,"Raise");   
         else
            StdDraw.text(70,5,"All-In");
      }
      
   }
   
   public int handleMouse(Table t, boolean showCards)
   {
      sliderY = 0;
      drawSlider(t);
      boolean pressed = false;
      while(!pressed)
      {
         double x = StdDraw.mouseX();
         double y = StdDraw.mouseY();
         
         drawSlider(t);
             
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
               int sliderMin = t.getHighestRaise() + t.getHighestBet() > t.getHighestBet() + t.getHighestRaise() ? 
                  t.getHighestRaise() + t.getHighestBet() : t.getHighestBet() + t.getHighestRaise();
               int sliderMax = t.getPlayer(0).getMoney();
               if(sliderMin < sliderMax)
                  return (int)((sliderY/25)*(sliderMax - sliderMin) + sliderMin);
               else
                  return t.getPlayer(0).getMoney();
            }
         }
        
      
         if(StdDraw.mousePressed() && x >= 88 && x <= 92 && y <= sliderY + 25 && y < 25.3 && y > -.3)
         {
            if(y > 25)   
               sliderY = 25;
            else if(y < 0)
               sliderY = 0;
            else
               sliderY = y;
               
            drawTable(t, showCards, true);
         
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
   
   public void drawSlider(Table t)
   {
      int sliderMin = t.getHighestRaise() + t.getHighestBet();
   
      int sliderMax = t.getPlayer(0).getMoney();
      
      if(sliderMin < sliderMax)
      {
      
         StdDraw.setPenColor(StdDraw.BLACK);
         StdDraw.setPenRadius(.006);
         StdDraw.line(90, 0, 90, 25);
         
         StdDraw.filledRectangle(90, sliderY, 2, 1);
         
         
         int raise = (int)((sliderY/25)*(sliderMax - sliderMin) + sliderMin);
         if(sliderY > 24.9 && sliderY < 25.5)
            raise = t.getPlayer(0).getMoney();
            
         StdDraw.text(95, sliderY, Integer.toString(raise));
         
         StdDraw.show(10);
         StdDraw.setPenRadius(.002);
      }
      
   }
   
   public void endGame(String s)
   {
      StdDraw.filledSquare(0, 0, 150);
      StdDraw.setPenColor(StdDraw.WHITE);
      StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 180));
      StdDraw.text(50, 50, s + " won!!");
      StdDraw.show(10000);
   }
}