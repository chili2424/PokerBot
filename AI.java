public class AI extends Player
{
   public AI(String name, int m)
   {
      super(name, m);
   }   

   public int makeDecision(Table t)
   {
   
      return t.getHighestBet() - getMoneyIn();
    
  
   }

}