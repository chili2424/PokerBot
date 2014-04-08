import java.util.ArrayList;

public class Player
{
   private ArrayList<Card> allCards = new ArrayList<Card>();
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
      allCards.add(c);
   }
   
   public void setBestHand(Hand h)
   {
      bestHand = h;
   }
   
   public String getName()
   {
      return name;
   }
   
   public ArrayList<Card> getAllCards()
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