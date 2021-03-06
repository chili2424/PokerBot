/**
*Runs the entire Poker Game, including Players and Table.
*/

//they'll keep raising even if the only other person is already all-in
//ties are definitely not being handled correctly
//

import java.util.ArrayList;
import java.util.Scanner;
import java.awt.*;

public class Game
{ 
   public static void main(String[] args)
   {
      final int RAISE_BLINDS = 10;
      int bigBlind = 20, smallBlind = 10, initMoney = 200, handsPlayed = 1;
      Table t;
      boolean display = true;
      ArrayList<Player> players;
      GUI gui = new GUI();
      
      players = new ArrayList<Player>();
      players.add(new Player("User", initMoney));
      players.add(new AI("Alpha", initMoney));
      players.add(new AI("Bravo", initMoney));
      players.add(new AI("Charlie", initMoney));
      players.add(new AI("Delta", initMoney));
      players.add(new AI("Echo", initMoney));
      players.add(new AI("Fox Trot", initMoney));
      players.add(new AI("Golf", initMoney));
      players.add(new AI("Hotel", initMoney));
      
      StdDraw.setCanvasSize(900,700);
      StdDraw.setXscale(0, 100);
      StdDraw.setYscale(0, 102);
      
   
      t = new Table(players, smallBlind, bigBlind);
       
      while(players.size() > 1)
      {
         if(handsPlayed % RAISE_BLINDS == 0)
            t.raiseBlinds();
         
         System.out.println("\nNew Hand\n");
         System.out.println("Dealer: " + t.getPlayer(t.getDealer()).getName());
         t.handleBlinds();
         t.dealPreFlop();
         
         gui.drawTable(t, display, false);         
         runTurn(t, gui, display);
      
        
         t.dealFlop();
         if(t.activeCount() > 1)
            gui.drawTable(t, display, false);
            
         System.out.println("======== FLOP ========");
         t.printTableCards();         
         runTurn(t, gui, display);  
      
         t.dealTurn(); 
         if(t.activeCount() > 1)
            gui.drawTable(t, display, false);
         
         System.out.println("======== TURN ========");
         t.printTableCards();         
      
         runTurn(t, gui, display);
      
         t.dealRiver(); 
         
         if(t.activeCount() > 1)
            gui.drawTable(t, display, false);
         System.out.println("======== RIVER ========");
         t.printTableCards();         
      
         runTurn(t, gui, display);
         
         
         t.handleWinners(gui, display);  
         t.removePlayers();
         handsPlayed++;
         t.resetTable();
      }
      gui.endGame(players.get(0).getName());
   }

   private static void runTurn(Table t, GUI gui, boolean display)
   {
      final int CALL = -2;
      final int FLOP = 3;
      int decision;
      AI bot;  
      Scanner sc = new Scanner(System.in);
      int numPlayed = 0;
      int initActive = t.activeCount();
      Data data = new Data();
      
      while(t.activeCount() > 1 && !t.isTurnOver(initActive, numPlayed))
      {   
         StdDraw.show(1000);
         
         System.out.println(t.getPlayer(t.getCurPlayer()).getName());
         System.out.println("Money: " + t.getPlayer(t.getCurPlayer()).getMoney());
         
         if(t.getPlayer(t.getCurPlayer()).getName() == "User" && t.getPlayer(0).getMoney() > 0)
         {
            System.out.println(" ");
            System.out.println("h raise "  + t.getHighestRaise());
            System.out.println("h bet " + t.getHighestBet());
            System.out.println("User: ");
            System.out.println("Preflop Hand: ");
            t.getPlayer(0).getPreFlop().printHand();
            if(t.numTableCards() >= FLOP)
               System.out.println("\nHand Strength: " + data.handStrength(t));
         
            System.out.println("\nmoney: " + t.getPlayer(0).getMoney());
           
            //t.addText("To Call: $" + Integer.toString(t.getHighestBet() - t.getPlayer(t.getCurPlayer()).getMoneyIn()) +".");
            gui.drawTable(t, display, true);
            StdDraw.show(100);
            decision = gui.handleMouse(t, display);
            
            
            if(decision == CALL)
               decision = t.getHighestBet() - t.getPlayer(t.getCurPlayer()).getMoneyIn();
            
            
            //irrelevant now we have gui???   
            while(decision >= 0 && decision != t.getHighestBet() - t.getPlayer(t.getCurPlayer()).getMoneyIn() &&
             decision < t.getHighestRaise() + t.getHighestBet() && decision != t.getPlayer(t.getCurPlayer()).getMoney())
            {     
               System.out.println("Invalid bet");
            }
            
            System.out.println("Bet: " + decision);
            
            t.handleDecision(decision);
         
         }
         else if(t.getPlayer(t.getCurPlayer()).getName() != "User" && t.getPlayer(t.getCurPlayer()).getMoney() > 0)
         {    
          
            System.out.println(" ");
            bot = (AI)t.getPlayer(t.getCurPlayer());
            System.out.println(bot.getName());
            System.out.println("Preflop Hand: ");
            t.getPlayer(t.getCurPlayer()).getPreFlop().printHand();
            if(t.numTableCards() >= FLOP)
               System.out.println("\nHand Strength: " + data.handStrength(t));
            
            decision = bot.makeDecision(t);
            t.handleDecision(decision);
          
            System.out.println("Bet: " + decision);
            System.out.println("Money: " + bot.getMoney());
          
            System.out.println("Pot size: " +  t.getPot());
         
         }
         else{
            System.out.println("moved");
            t.moveCurPlayer();
         }
      
         numPlayed++;
         gui.drawTable(t, display, false);
         StdDraw.show(10);
      }
   }
   
            
}
   
   
