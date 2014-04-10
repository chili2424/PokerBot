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
      t.dealFlop();
  
      System.out.println("PreFlop:");
      players.get(0).getPreFlop().printHand();
  //    System.out.println(t.firstToAct());
  
      
      
      
      
     
      
      
      
      
      
         
      
   }
   
   
   
}
      
      
      