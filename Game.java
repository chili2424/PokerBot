import java.util.ArrayList;

public class Game
{
   public static void main(String[] args)
   {
      int bigBlind = 20, smallBlind = 10, initMoney = 1500;
      ArrayList<Player> players = new ArrayList<Player>();
      players.add(new Player("User", initMoney));
      players.add(new Player("Alpha", initMoney));
   //   players.add(new Player("Bravo", initMoney));
   //   players.add(new Player("Charlie", initMoney));
   //   players.add(new Player("Delta", initMoney));
   //   players.add(new Player("Echo", initMoney));
   //   players.add(new Player("FoxTrot", initMoney));
   //   players.add(new Player("Golf", initMoney));
   //   players.add(new Player("Hotel", initMoney));
      
      Table t = new Table(players, smallBlind, bigBlind); 
      t.handleBlinds();
  
      t.dealPreFlop();
      

      
      System.out.println("PreFlop:");
      players.get(0).getPreFlop().printHand();
      
      System.out.println("First Better Position:");
      System.out.println(t.firstToAct());
      
         
         if(t.getCurrentBetter() == 0)
         {
            //prompt user for decision
         }
         else
         {
            //AI makes decision. t.handleDecision(AI's decision)
         
         }
      
      
      /*handle first round of bets
    
      t.handleDecision(currentBetter Decision):
         if fold: 
            remove player, move currentBetter forward
         if call:
            set player's money-in to be t.highest-bet
            take t.highest_bet - moneyIn from player
            move current Better Forward
         if raise: 
            * must be certain amount
            * take take t.highest_bet - moneyIn from player
            * set highest Bet to player's new bet amount
            * move currentBetter Forward
            
         

  
      */
      
      
      
     
      
      
      
      
      
         
      
   }
   
   
   
}
      
      
      