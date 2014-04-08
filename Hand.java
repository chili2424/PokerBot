import java.util.ArrayList;


public class Hand
{
   private ArrayList<Card> hand = new ArrayList<Card>();
   private int multiplesRank1 = 0;
   private int multiplesRank2 = 0;
   
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
      multiplesRank1 = 0;
      multiplesRank2 = 0;
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
   
   private boolean isStraight()
   {
      if(hand.size() < 5)
         return false;
      
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
      return true;    
   }
   
   
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

   
   private boolean isFlush()
   {
      if(hand.size() < 5)
         return false;
      
      if(compareSuit(0,1) && compareSuit(0,2) && compareSuit(0,3) && compareSuit(0,4))
         return true;
   
      return false;
   }  
   
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
   
   private boolean isStraightFlush()
   {
      if(isStraight() && isFlush())
         return true;
      
      return false;
   }
   
   private boolean isRoyalFlush()
   {
      if(hand.get(0).getValue() == 10 && isFlush())
         return true;
      
      return false;
   }
   
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
     
   public void printHand()
   {
      for(int i = 0; i < hand.size(); i++)
      {
         System.out.println(hand.get(i).toString() + " ");
      }
   }
   
   public int getMR1()
   {
      return multiplesRank1;
   }
   
   public int getMR2()
   {
      return multiplesRank2;
   }
}
