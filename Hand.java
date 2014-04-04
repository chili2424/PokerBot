import java.util.ArrayList;


public class Hand
{
   ArrayList<Card> hand = new ArrayList<Card>();
   
   public void addCard(Card c)
   {
      if(hand.size() == 7)
      {
         throw new RuntimeException();
      }
      hand.add(c);
      orderByRank();
   }
   
   public void clearHand()
   {
      hand.clear();
   }
   
   public Card getCard(int i)
   {
      /*Pugene added && i > -1 because you shouldn't be able to index less than 0*/
      if(i < hand.size() && i > -1)
      {
         return hand.get(i);
      }
      else
      {
         throw new RuntimeException();
      }
   }
   
   public int getSize()
   {
      return hand.size();
   }
   
   public void orderByRank()
   {
      Card c;
      
      for(int i = 0; i < hand.size(); i++)
      {
         for(int j = hand.size() - 1; j > i; j--)
         {
            if(hand.get(j).getValue() < hand.get(i).getValue())
            {
               c = hand.get(j);
               hand.set(j, hand.get(i));
               hand.set(i, c);
            }
         }
      }
   }
   
   /**
   *Compares cards i & j in hand.
   */
   public boolean compareSuit(int i, int j)
   {
      /*Pugene: Make sure that i and j are not negative and the same*/
      if(i < 0 && j < 0 && i == j)
      {
         System.out.println("compareSuit: You passed me bad parameters");
      }
      return (hand.get(i).getSuit()).equals(hand.get(j).getSuit());
   }
   
   public int compareRank(int i, int j)
   {
      /*Pugene: Make sure that i and j are not negative and the same*/
      if(i < 0 && j < 0 && i == j)
      {
         System.out.println("compareRank: You passed me bad parameters");
      }
      return hand.get(i).getValue() - hand.get(j).getValue();
   }
   
   /**
    * Assume orderByRank has been called
    * Returns number of pairs in hand
    */
   public int numPairs()
   {
      int numPairs = 0;
      
      for(int i = 0; i < hand.size() - 1; i++)
      {
         if(compareRank(i, i + 1) == 0)
         {
            numPairs++;
            i += 1;
         }
      }
      return numPairs;
   }
   
   public boolean isThree()
   {
      for(int i = 0; i < hand.size() - 2; i++)
      {
         if(compareRank(i, i + 1) == 0 && compareRank(i, i + 2) == 0)
         {
            return true;
         }
      }
      return false;
   }
   
   public boolean isStraight()
   {
      if(hand.size() < 5)
         return false;
      else
      {
         for(int i = 0; i < hand.size()- 1; i++)
         {
            if(i == 3)
            {
               if(hand.get(3).getValue() == 5 && hand.get(4).getValue() == 14)
               {
                  return true;
               }
            }
            if(hand.get(i+1).getValue() != hand.get(i).getValue() + 1)
               return false;
         } 
       }
       return true;
   }
   
   
   public boolean isFour()
   {
      for(int i = 0; i < hand.size() - 3; i++)
      {
         if(compareRank(i, i + 1) == 0 && compareRank(i, i + 2) == 0 && compareRank(i, i+3) == 0)
         {
            return true;
         }
      }
      return false;
   }

   
   public boolean isFlush()
   {
      if(hand.size() < 5)
         return false;
      else
      {
         if(compareSuit(0,1) && compareSuit(0,2) && compareSuit(0,3) && compareSuit(0,4))
            return true;
      }
      return false;
   }  
   
   public boolean isFullHouse()
   {
      if(isThree() && numPairs() == 2)
         return true;
      
      return false;
   }
   
   public boolean isStraightFlush()
   {
      if(isStraight() && isFlush())
         return true;
      
      return false;
   }
   
   public boolean isRoyalFlush()
   {
      if(isStraightFlush() && hand.get(0).getValue() == 10)
         return true;
      
      return false;
   }
   
   public String classifyHand()
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
   
   
   public void printHand()
   {
      for(int i = 0; i < hand.size(); i++)
      {
         System.out.print(hand.get(i).toString() + " ");
      }
   }
}