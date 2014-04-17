public class Pot
{
   private static final int SIZE = 9;

   private int totalMoney;
   private int moneyPerPlayer;
   private int[] playerAmounts = new int[SIZE];
   private int activePlayers;
   
   public Pot()
   {
   }
   
   public int checkAmount(int player)
   {
      return playerAmounts[player];
   }
   
   public int setAmount(int player)
   {
      int diff;
      diff = playerAmounts[player] - limit;
      playerAmounts = limit;
      return diff;
   }
   
   public void getLimit()
   {
      return limit;
   }
   
   public void setLimit(int l)
   {
      limit = l;
   }
     
   
}