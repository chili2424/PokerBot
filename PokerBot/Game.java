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
      // players.add(new AI("Delta", initMoney));
      //   players.add(new Player("Echo", initMoney));
      //   players.add(new Player("FoxTrot", initMoney));
      //   players.add(new Player("Golf", initMoney));
      //   players.add(new Player("Hotel", initMoney));
      
      
         Table t = new Table(players, smallBlind, bigBlind);
         while(players.size() > 1)
         {
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
            
            Player winner = t.getPlayer(t.getCurPlayer());
            //Handle winner, allocate the monies.
            //NEED TO IMPLEMENT TIE LATER.
            if(t.activeCount() > 1)
            {
               for(Player p: players)
               {
                  if(p.isActive() && t.compareHands(p.getBestHand(), winner.getBestHand()) > 0)
                     winner = p;
               }
            }
            else
               for(Player p: players)
                  if(p.isActive())
                     winner = p;  
                     
            System.out.println("\nWinner of current hand is " + winner.getName());
            System.out.println("The winning hand is: ");
            winner.getBestHand().printHand();
            winner.addMoney(t.getPot());
           
            if(t.getPlayer(t.getDealer()).getMoney() > 0)
               t.moveDealer();
            
            t.removePlayers();
            System.out.println("Still Active: ");
            for(Player p: players)
            {
               System.out.println(p.getName());
               System.out.println(p.getMoney());

            }
            //go through and determine who wins pots
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
               System.out.println("Best hand: ");
               t.getPlayer(0).getBestHand().printHand();
               System.out.println("money in: " + t.getPlayer(0).getMoneyIn());
            
               System.out.println("money: " + t.getPlayer(0).getMoney());
            
               decision = sc.nextInt();
            
               System.out.println("User bet: " + decision);
               t.handleDecision(decision);
            
            }
            else if(t.getPlayer(t.getCurPlayer()).getName() != "User" && t.getPlayer(t.getCurPlayer()).getMoney() > 0)
            {    
             
               System.out.println(" ");
               bot = (AI)t.getPlayer(t.getCurPlayer());
               System.out.println(bot.getName());
               System.out.println("Preflop Hand: ");
               t.getPlayer(t.getCurPlayer()).getPreFlop().printHand();
               System.out.println("Best hand: ");
               t.getPlayer(t.getCurPlayer()).getBestHand().printHand();
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
      
      
