/**
*Defines a Hand as an ArrayList of Cards.
*/

import java.util.ArrayList;

public class Hand
{
   private ArrayList<Card> hand = new ArrayList<Card>();
   private int multiplesRank1 = 0;
   private int multiplesRank2 = 0;
   private double preflopEV;
   
   public Hand(){}
   
   public Hand(Card c1, Card c2, double expected)
   {
      preflopEV = expected;
      hand.add(c1);
      hand.add(c2);
      orderByRank();
   }
   
   public Hand(Card c1, Card c2)
   {
      hand.add(c1);
      hand.add(c2);
      orderByRank();
   }

   
   public Hand(Card c1, Card c2, Card c3, Card c4, Card c5)
   {
      hand.add(c1);
      hand.add(c2);
      hand.add(c3);
      hand.add(c4);
      hand.add(c5);
      orderByRank();
   }
   
   /**
   *Adds specified Card to Hand.
   *
   *@param c Card to be added to Hand.
   *@throws Runtime Exception if Hand is already full.
   */
   public void addCard(Card c) throws RuntimeException
   {
      if(hand.size() == 5)
         throw new RuntimeException("Error: Hand is already full.");
         
      hand.add(c);
      orderByRank();
   }
   
   /**
   *Resets Hand.
   */
   public void clearHand()
   {
      hand.clear();
      multiplesRank1 = 0;
      multiplesRank2 = 0;
   }
   
   /**
   *Returns Card.
   *
   *@param i Index from which to retrieve Card.
   *@return Card at index.
   *@throws RntimeException if given invalid index.
   */
   public Card getCard(int i) throws RuntimeException
   {
   
      if(i > hand.size() || i <= -1)
         throw new RuntimeException("Error: Invalid Index, cannot get Card.");
         
      return hand.get(i);
   }
   
   /**
   *Returns size of Hand.
   *
   *@return Size of Hand.
   */
   public int getSize()
   {
      return hand.size();
   }
   
   /**
   *Orders Cards by Rank.
   */
   public void orderByRank()
   {
      Card c;
      
      for(int i = 0; i < hand.size(); i++)
         for(int j = hand.size() - 1; j > i; j--)
            if(hand.get(j).getValue() < hand.get(i).getValue())
            {
               c = hand.get(j);
               hand.set(j, hand.get(i));
               hand.set(i, c);
            }
            
   }
   
   /**
   *Compares cards at specified locations in Hand.
   *
   *@param i location of first Card.
   *@param j location of second Card.
   *@return true if Hands belond to same Suit.
   *@throws RuntimeException if passed invalid indecies.
   */
   public boolean compareSuit(int i, int j) throws RuntimeException
   {
      if(i < 0 && j < 0 && i == j)
         throw new RuntimeException("Error: Invalid indecies passed to compareSuit.");
      
      return (hand.get(i).getSuit()).equals(hand.get(j).getSuit());
   }
   
   /**
   *Compares cards at specified locations in Hand.
   *
   *@param i location of first Card.
   *@param j location of second Card.
   *@return true if Hands are of the same Rank.
   *@throws RuntimeException if passed invalid indecies.
   */
   public int compareRank(int i, int j) throws RuntimeException
   {
      if(i < 0 && j < 0 && i == j)
         throw new RuntimeException("Error: Invalid indecies passed to compareRank.");
         
      return hand.get(i).getValue() - hand.get(j).getValue();
   }
   
   /**
    * Returns number of pairs in hand, sets MR1 and MR2 as necessary.
    *
    * @return number of pairs in hand.
    */
   private int numPairs()
   {
      int numPairs = 0;
      
      for(int i = 0; i < hand.size() - 1; i++)
      {
         if(compareRank(i, i + 1) == 0)
         {
            numPairs++;
            if(multiplesRank1 == 0)
               multiplesRank1 = hand.get(i).getValue();
            else
               multiplesRank2 = hand.get(i).getValue();
               
            i++;
         }
      }
      return numPairs;
   }
   
   /**
   *Checks if hand has three of a kind, sets MR1.
   *
   *@return True if hand has three of a kind.
   */
   private boolean isThree()
   {
      if(hand.size() < 3) 
         return false;
      
      if(compareRank(0, 2) == 0 || compareRank(1,3) == 0  || compareRank(2,4) == 0)
      {
         multiplesRank1 = hand.get(2).getValue();
         return true;
      }
      
      return false;
   }
   
   /**
   *Checks if hand has a straight.
   *
   *@return True if hand has 5 Cards, each with rank one higher than the previous Card.
   */
   private boolean isStraight()
   {
      if(hand.size() < 5)
         return false;
      
      for(int i = 0; i < hand.size()- 1; i++)
      {
         if(i == 3)
            if(hand.get(3).getValue() == 5 && hand.get(4).getValue() == 14)
               return true;
      
         if(hand.get(i+1).getValue() != hand.get(i).getValue() + 1)
            return false;
      } 
      return true;    
   }
   
   /**
   *Checks if hand has four of a kind, sets MR1.
   *
   *@return True if hand has four of a kind.
   */
   private boolean isFour()
   {
      if(hand.size() < 4)
         return false;
      
      if(compareRank(0,3) == 0 || compareRank(1,4) == 0)
      {
         multiplesRank1 = hand.get(2).getValue();
         return true;
      }
     
      return false;
   }

   /**
   *Checks if hand is a flush.
   *
   *@return True if hand has 5 cards of the same Suit.
   */
   private boolean isFlush()
   {
      if(hand.size() < 5)
         return false;
      
      if(compareSuit(0,1) && compareSuit(0,2) && compareSuit(0,3) && compareSuit(0,4))
         return true;
   
      return false;
   }  
   
   /**
   *Checks if hand is a full house, sets MR1 and MR2.
   *
   *@return True if hand has three of a kind and a pair.
   */
   private boolean isFullHouse()
   {
      if(hand.get(0).getValue() == hand.get(2).getValue() && hand.get(3).getValue() == hand.get(4).getValue())
      {
         multiplesRank1 = hand.get(0).getValue();
         multiplesRank2 = hand.get(3).getValue();
         return true;
      }
      
      if(hand.get(0).getValue() == hand.get(1).getValue() && hand.get(2).getValue() == hand.get(4).getValue())
      {
         multiplesRank1 = hand.get(3).getValue();
         multiplesRank2 = hand.get(0).getValue();
         return true;
      }
    
      return false;
   }
   
   /**
   *Checks if hand is a straight flush.
   *
   *@return True if hand has a straight, with every card of the same suit.
   */
   private boolean isStraightFlush()
   {
      if(isStraight() && isFlush())
         return true;
      
      return false;
   }
   
   /**Checks if hand is a royal flush.
   *
   *@return True if hand has a striaght flush of of cards with ranks 10-Ace.
   */
   private boolean isRoyalFlush()
   {
      if(hand.get(0).getValue() == 10 && isFlush())
         return true;
      
      return false;
   }
   
   /**
   *Ranks hand on a scale of 0-9.
   *
   *@return Hand ranking.
   */ 
   public int classifyHand()
   {
      if(isRoyalFlush())
         return 9;
      
      else if(isStraightFlush())
         return 8;
       
      else if(isFour())
         return 7;
      
      else if(isFullHouse())
         return 6;
      
      else if(isFlush())
         return 5;
      
      else if(isStraight())
         return 4;
      
      else if(isThree())
         return 3;
      
      else if(numPairs() == 2)
         return 2;
      
      else if(numPairs() == 1)
         return 1;
      
      else
         return 0;
         
   }

   /**
   *Returns hand type.
   *
   *@return Hand type.
   */ 
   public String handToString()
   {
      if(isRoyalFlush())
         return "Royal Flush";
      
      else if(isStraightFlush())
         return "Straight Flush";
       
      else if(isFour())
         return "Four of a kind";
      
      else if(isFullHouse())
         return "Full House";
      
      else if(isFlush())
         return "Flush";
      
      else if(isStraight())
         return "Straight";
      
      else if(isThree())
         return "Three of a kind";
      
      else if(numPairs() == 2)
         return "Two pair";
      
      else if(numPairs() == 1)
         return "One pair";
      
      else
         return "High Card";
         
   }
   
   /**
   *Prints every Card in this Hand.
   */  
   public void printHand()
   {
      for(int i = 0; i < hand.size(); i++)
      {
         System.out.print(hand.get(i).toString() + " ");
      }
   }
   
   /**
   *Returns the rank of one set of multiples in hand.
   *
   *@return Rank of one set of multiples in hand.
   */
   public int getMR1()
   {
      return multiplesRank1;
   }
   
   /**
   *Returns the rank of one set of multiples in hand.
   *
   *@return Rank of one set of multiples in hand.
   */
   public int getMR2()
   {
      return multiplesRank2;
   }
   
   /**
   *Compares two hands for equality of ranking and suits of Cards.
   *
   *@return 2 if Cards are exactly the same, 1 if Hands have cards of equal values, but 1 or both 
   *is not suitedare not suited, 0 if Hands are not equal at all
   */
   public int equals(Hand h)
   {
      boolean sameSuit = true;
     
      if(getSize() != h.getSize())
         return 0;
     
      for(int i = 0; i < h.getSize(); i++)
      {
         if(hand.get(i).getValue() != h.getCard(i).getValue())
            return 0; 
         if(hand.get(i).getSuit() != h.getCard(i).getSuit())
            sameSuit = false;
      } 
      if(sameSuit == true)
         return 2;
      else if(h.getCard(0).getSuit() != h.getCard(1).getSuit())
         return 1; 
      else 
         return 0;  
   }
   
   /**
   *Returns expected return rate from bets based on cards in hand.
   *
   *@return Expected Value
   */
   public double getEV()
   {
      return preflopEV;
   }
}
