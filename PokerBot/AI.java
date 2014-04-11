import java.util.Random;

public class AI extends Player
{
   public AI(String name, int m)
   {
      super(name, m);
   }   

   public int makeDecision(Table t)
   {
      Random rand = new Random();
      int num = rand.nextInt(4);
      //if(num > 0)
        // return t.getHighestBet() - getMoneyIn();
      
      return -1;
    
  
   }

}