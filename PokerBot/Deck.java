/**
*Defines a Deck as an ArrayList of Cards.
*/

import java.util.ArrayList;
import java.util.Random;

public class Deck
{
   private ArrayList<Card> deck = new ArrayList<Card>();

   /**
   *Initializes entire 52 card deck.
   */
   public Deck()
   {
      for(int i = 2; i < 15; i++)
         for(int j = 0; j < 4; j++)
            deck.add(new Card(i, j));
            
   }

   /**
   *Removes the specified Card from the Deck.
   *
   *@param card Card to be removed.
   *@return Card which was removed, or null if Card could not be found.
   */
   public Card removeCard(Card card)
   {
      Card c = null;
      for(int i = 0; i < deck.size(); i++)
      {
         if(deck.get(i).equals(card))
         {
            c = new Card(deck.get(i).getValue(), deck.get(i).getSuit());
            deck.remove(i);
         }
      }
      return c;
   }

   /**
   *Add Card to Deck.
   *
   *@param c Card to be added.
   */
   public void addCard(Card c)
   {
      deck.add(c);
   }
         
   /**
   *Randomizes order of Cards in ArrayList.
   */
   public void shuffleDeck()
   {
      Card[] tempDeck; 
      tempDeck = new Card[52];
      Random rand = new Random();
   
      for(int i = 0; i < 52; i++)
      {
         int r = rand.nextInt(52 - i);
         tempDeck[i] = deck.get(r);
         deck.remove(r);
      }
   
      for(int i = 0; i < 52; i++)
         deck.add(tempDeck[i]);
   }

   /**
   *Deals a Card from the Deck.
   *
   *@return Card which was randomly removed from Deck.
   */
   public Card dealCard()
   {
      Random rand = new Random();
      int i = rand.nextInt(deck.size());
      Card temp = deck.get(i);
   
      deck.remove(i);
   
      return temp;
   }

   /**
   *Prints entire Deck.
   */
//************CAN PROBABLY BE REMOVED***********//
   public void printDeck()
   {
      for(Card c : deck)
      {
         System.out.println(c.toString());
      }
   }

   /**
   *Searches Deck for any combination of Cards which would have the same ranking in the game 
   *as the Hand which was passed in (i.e. suit is not of importance).
   *
   *@param h Hand to search for.
   *@return Array conatining Cards found, or all null values.
   */
   public Card[] contains(Hand h)
   {
      Card[] nums = {null, null};
      for(int i = 0; i < deck.size(); i++)
         if(deck.get(i).getValue() == h.getCard(0).getValue())
            for(int j = 0; j < deck.size(); j++)
               if(deck.get(j).getValue() == h.getCard(1).getValue() && i != j)
                  if(h.getCard(0).getSuit() != h.getCard(1).getSuit() || deck.get(j).getSuit() == deck.get(i).getSuit())
                  {
                     //System.out.println("Contains: " + deck.get(i).toString() + " " + deck.get(j).toString());
                     nums[0] = deck.get(i);
                     nums[1] = deck.get(j);
                     return nums;
                  }
      return nums;
   }
   
   /**
   *Allows user to retrieve Card from a specific location in Deck.
   *
   *@param i Int indicating location in ArrayList.
   *@return Card from specified location.
   */
   public Card getCard(int i)
   {
      Card temp = deck.get(i);
      deck.remove(i);
      return temp;
   }
}
