import java.util.ArrayList;
import java.util.Scanner;

public class Game
{
   
   
   
   public static void main(String[] args)
   {
      int bigBlind = 20, smallBlind = 10, initMoney = 1500;
      ArrayList<Player> players = new ArrayList<Player>();
      players.add(new Player("User", initMoney));
      players.add(new AI("Alpha", initMoney));
      players.add(new AI("Bravo", initMoney));
      players.add(new AI("Charlie", initMoney));
      players.add(new AI("Delta", initMoney));
   //   players.add(new Player("Echo", initMoney));
   //   players.add(new Player("FoxTrot", initMoney));
   //   players.add(new Player("Golf", initMoney));
   //   players.add(new Player("Hotel", initMoney));
      
      
      Table t = new Table(players, smallBlind, bigBlind); 
      t.handleBlinds();
      t.dealPreFlop();
      

      
      System.out.println("PreFlop:");
      players.get(0).getPreFlop().printHand();
            
      
      runTurn(t);
      
      t.dealFlop();
      System.out.println("======== FLOP ========");
      t.printTableCards();
      
      runTurn(t);  
 
                  
   }
   
   private static void runTurn(Table t)
   {
      int decision;
      AI bot;  
      Scanner sc = new Scanner(System.in);
      
      while(!t.isTurnOver())
      {   
         if(t.getCurPlayer() == 0)
         {
            
            System.out.println(" ");

            System.out.println("User: ");
            System.out.println("money in: " + t.getPlayer(0).getMoneyIn());
            System.out.println("money: " + t.getPlayer(0).getMoney());
            
            decision = sc.nextInt();
            
            System.out.println("User bet: " + decision);
            t.handleDecision(decision);
            
         }
         else
         {    
             
             System.out.println(" ");
             bot = (AI)t.getPlayer(t.getCurPlayer());
             System.out.println(bot.getName());
             decision = bot.makeDecision(t);
             t.handleDecision(decision);
          //   System.out.println("Highest Bet:" + t.getHighestBet());
             
             System.out.println("Bet: " + decision);
             System.out.println("Money: " + bot.getMoney());
             
             System.out.println("Pot size: " +  t.getPot());

         }
      }
    }

   
   
}
      
      
      