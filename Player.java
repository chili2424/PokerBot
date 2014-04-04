public class Player
{
   private Hand allCards;
   private Hand bestHand; //Best 5 card-hand 
   private String name;
   private int money;
   private boolean active;
   
   public Player(String name, int money)
   {
      this.name = name;
      this.money = money;
      this.active = true;
   }
   
   public void addToAllCards(Card c)
   {
      allCards.addCard(c);
   }
   
   public void setBestHand(Hand h)
   {
      bestHand = h;
   }
   
   public String getName()
   {
      return name;
   }
   
   public Hand getAllCards()
   {
      return allCards;
   }
   
   public Hand getBestHand()
   {
      return bestHand;
   }
   
   public int getMoney()
   {
      return money;
   }
   
   public void setMoney(int m)
   {
      money = m;
   }
   
   public void setActive(boolean a)
   {
      active = a; 
   }
}