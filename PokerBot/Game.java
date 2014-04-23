   import java.util.ArrayList;
   import java.util.Scanner;

   public class Game
   {
   
   
   
      public static void main(String[] args)
      {
         int bigBlind = 20, smallBlind = 10, initMoney = 200;
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
      
         int handsPlayed = 1;
         Table t = new Table(players, smallBlind, bigBlind);
         while(players.size() > 1)
         {
            if(handsPlayed % 15 == 0)
               t.raiseBlinds();
            
            System.out.println("\nNew Hand\n");
            System.out.println("Dealer: " + t.getPlayer(t.getDealer()).getName());
            t.handleBlinds();
            t.dealPreFlop();
                       
            runTurn(t);
         
            t.dealFlop();
            System.out.println("======== FLOP ========");
            t.printTableCards();         
            runTurn(t);  
         
            t.dealTurn(); 
            System.out.println("======== TURN ========");
            t.printTableCards();         
         
            runTurn(t);
         
            t.dealRiver(); 
            System.out.println("======== RIVER ========");
            t.printTableCards();         
         
            runTurn(t);
            
            t.handleWinners();  
            t.removePlayers();
            handsPlayed++;
            t.resetTable();
         }
      }
   
      private static void runTurn(Table t)
      {
         int decision;
         AI bot;  
         Scanner sc = new Scanner(System.in);
         int numPlayed = 0;
         int initActive = t.activeCount();
      
         while(t.activeCount() > 1 && (numPlayed < initActive || !t.isTurnOver()))
         {   
            if(t.getPlayer(t.getCurPlayer()).getName() == "User" && t.getPlayer(0).getMoney() > 0)
            {
            
               System.out.println(" ");
            
               System.out.println("User: ");
               System.out.println("Preflop Hand: ");
               t.getPlayer(0).getPreFlop().printHand();
           //    System.out.println("\nBest hand: ");
          //     t.getPlayer(0).getBestHand().printHand();
            //   System.out.println("money in: " + t.getPlayer(0).getMoneyIn());
            
               System.out.println("\nmoney: " + t.getPlayer(0).getMoney());
            
               decision = sc.nextInt();
               while(decision >= 0 && decision != t.getHighestBet() - t.getPlayer(t.getCurPlayer()).getMoneyIn() &&
                decision < t.getHighestRaise() + t.getHighestBet())
               {
                  
                  System.out.println("Invalid bet");
                  decision = sc.nextInt();
               }
              // System.out.println("User bet: " + decision);
               t.handleDecision(decision);
            
            }
            else if(t.getPlayer(t.getCurPlayer()).getName() != "User" && t.getPlayer(t.getCurPlayer()).getMoney() > 0)
            {    
             
               System.out.println(" ");
               bot = (AI)t.getPlayer(t.getCurPlayer());
               System.out.println(bot.getName());
           //    System.out.println("Preflop Hand: ");
          //     t.getPlayer(t.getCurPlayer()).getPreFlop().printHand();
          //     System.out.println("\nBest hand: ");
           //    t.getPlayer(t.getCurPlayer()).getBestHand().printHand();
               decision = bot.makeDecision(t);
               t.handleDecision(decision);
            //   System.out.println("Highest Bet:" + t.getHighestBet());
             
               System.out.println("Bet: " + decision);
               System.out.println("Money: " + bot.getMoney());
             
               System.out.println("Pot size: " +  t.getPot());
            
            }
            else
               t.moveCurPlayer();
         
            numPlayed++;
         }
      }
   
   
   
   }
      
      
