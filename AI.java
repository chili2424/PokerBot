public class AI extends Player
{
   public AI(String name, int m)
   {
      super(name, m);
   }   

   public int makeDecision(Table t)
   {
       System.out.println("Money In: " + getMoneyIn());
      //for now, return call
      return t.getHighestBet() - getMoneyIn();
    
  
   }

}