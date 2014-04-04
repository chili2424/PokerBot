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