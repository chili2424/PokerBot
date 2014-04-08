import java.util.ArrayList;
import java.util.Random;

public class Deck
{
   private ArrayList<Card> deck = new ArrayList<Card>();
   
   public Deck()
   {
      for(int i = 2; i < 15; i++)
      {
         for(int j = 0; j < 4; j++)
         {
            deck.add(new Card(i, j));
         }
      }
      shuffleDeck();
   }
   
   public void shuffleDeck()
   {
      Card[] tempDeck; 
      tempDeck = new Card[52];
     
      for(int i = 0; i < 52; i++)
      {
         int r = rand.nextInt(52 - i);
         tempDeck[i] = deck.get(r);
         deck.remove(r);
      }
      
      for(int i = 0; i < 52; i++)
      {
         deck.add(tempDeck[i]);
      }
   }

   public Card dealCard()
   {
      Random rand = new Random();
      int i = rand.nextInt(deck.size());
      Card temp = deck.get(i);
      
      deck.remove(i);
      
      return temp;
   }
   
   public void printDeck()
   {
      for(Card c : deck)
      {
         System.out.println(c.toString());
      }
   }
}
