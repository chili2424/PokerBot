/**
*Defines a Player, which has a name, Cards, and money.
*/

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
   private int averageBet;
   private int sumBet = 20;
   private int timesBet = 0;
   
   public Player(){}
   
   /**
   *Initializes Player's name and money.
   *
   *@param name Player's name.
   *@param money Player's money.
   */
   public Player(String name, int money)
   {
      this.name = name;
      this.money = money;
      this.active = true;
   }
   public int getAverageBet()
   {
      return averageBet;
   }
   public void setAverageBet(int moneyBet)
   {
      averageBet = (40 + sumBet) /(1 + timesBet);
      System.out.println("averageBet:" + averageBet + "= (40 + " + sumBet + ")/(1 + " + timesBet);
      sumBet += moneyBet;
   }
   public void incrTimesBet()
   {
      timesBet++;
   }
   /**
   *Adds card to this Player's ArrayList of all Cards.
   *
   *@param c Card to be added.
   */
   public void addToAllCards(Card c)
   {
      allCards.add(c);
   }
   
   /**
   *Sets this Player's best Hand.
   *
   *@param h Player's new best Hand.
   */
   public void setBestHand(Hand h)
   {
      bestHand = h;
   }
   
   /**
   *Adds card to this Player's PreFlop cards and ArrayList of all Cards.
   *
   *@param c Card to be added.
   */
   public void addToPreFlop(Card c)
   {
      preFlop.addCard(c);
      addToAllCards(c);
   }
   
   /**
   *Returns this Player's PreFlop Hand.
   *
   *@return PreFlop cards.
   */
   public Hand getPreFlop()
   {
      return preFlop;
   }
   
   /**
   *Returns name of Player.
   *
   *@return Player's name.
   */
   public String getName()
   {
      return name;
   }
   
   /**
   *Returns all of the Cards this Player has access to, including PreFlop hand and the Cards on the Table.
   *
   *@return ArrayList of Cards.
   */
   public ArrayList<Card> getAllCards()
   {
      return allCards;
   }
   
   /**
   *Returns 5 Cards from Player's AllCards which give this Player the best chance of winning the current round.
   *
   *@return Hand containing Player's best 5 cards.
   */
   public Hand getBestHand()
   {
      return bestHand;
   }
   
   /**
   *Returns this Player's money.
   *
   *@return Player's money.
   */
   public int getMoney()
   {
      return money;
   }
   
   /**
   *Sets this Player's money.
   *
   *@param m New amount of Money.
   */
   public void setMoney(int m)
   {
      money = m;
   }
   
   /**
   *Modifiess this Player's money.
   *
   *@param m amount to be added, or subtracted if m is negative.
   */
   public void addMoney(int m)
   {
       money += m;
   }
   
   /**
   *Takes money from this Player, and returns amount taken. If more is "taken" than this Player
   *has, this Player's money is set to 0 and function returns only how much this Player actually had.
   *
   *@param m Amount to be taken.
   *@return Amount actually taken.
   */
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
      moneyIn += paid;
      return paid;
   }
   
   /**
   *Sets status of Player.
   *
   *@param a New active status.
   */
   public void setActive(boolean a)
   {
      active = a; 
   }
   
   /**
   *Returns status of this Player.
   *
   *@return True if Player is active in current round, false otherwise.
   */
   public boolean isActive()
   {
      return active;
   }
   
   /**
   *Clears all of this player's Cards.
   */
   public void clearCards()
   {
      allCards.clear();
      bestHand.clearHand();
      preFlop.clearHand();    
   }
   
   /**
   *Adjusts how much Player has bet in this round.
   *
   *@param m Amount Player has bet.
   */
   public void setMoneyIn(int m)
   {
      moneyIn = m;
   }  
   
   /**
   *Returns amount this Player has bet in current round.
   *
   *@return How much Player has in for the current round of betting.
   */
   public int getMoneyIn()
   {
      return moneyIn;
   }
   
   /**
   *Returns total amount Player has in current pot, for all rounds of betting.
   *
   *@return Amount Player has in pot.   
   */
   public int getPotCont()
   {
      return potContribution;
   }
   
   /**
   *Adds to Player's total pot contribution.
   *
   *@param n Amount to be added.
   */
   public void addToPotCont(int n)
   {
      potContribution += n;
   }
   
   /**
   *Reduce amount Player has in pot.
   *
   *@param n How much this Player's contribution will be reduced by.
   */
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
   
   /**
   *Set Player's pot contribution.
   *
   *@param n Player's new pot contribution.
   */
   public void setPotCont(int n)
   {
      potContribution = n;
   }
}