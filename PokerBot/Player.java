import java.util.ArrayList;

public class Player
{
   private ArrayList<Card> allCards = new ArrayList<Card>();
   private Hand bestHand = new Hand(); //Best 5 card-hand
   private Hand preFlop = new Hand(); 
   private String name;
   private int money;
   private boolean active = true;
   private int moneyIn;
   private int potContribution;
   
   public Player(){}
   
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
   
   public void addToPreFlop(Card c)
   {
      preFlop.addCard(c);
      addToAllCards(c);
   }
   
   public Hand getPreFlop()
   {
      return preFlop;
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
   
   public void addMoney(int m)
   {
       money += m;
   }
   
   public int takeMoney(int m)
   {
      int paid = m;
      if(money < m)
      {
         paid = money;
         money = 0;
      }
      else
      {
         money -= m;
      }
      return paid;
   }
   
   public void setActive(boolean a)
   {
      active = a; 
   }
   
   public boolean isActive()
   {
      return active;
   }
   
   public void clearCards()
   {
      allCards.clear();
      bestHand.clearHand();
      preFlop.clearHand();    
   }
   
   public void setMoneyIn(int m)
   {
      moneyIn = m;
   }  
   
   public int getMoneyIn()
   {
      return moneyIn;
   }
      
   public int getPotCont()
   {
      return potContribution;
   }
   
   public void addToPotCont(int n)
   {
      potContribution += n;
   }
   
   public int takeFromPotCont(int n)
   {
      if(n > potContribution)
      {
         int temp = potContribution;
         potContribution = 0;
         return temp;
      }
      else
      {
         potContribution -= n;
         return n;
      }
   }
   
   public void setPotCont(int n)
   {
      potContribution = n;
   }
  
}