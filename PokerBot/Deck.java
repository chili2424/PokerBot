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
      //shuffleDeck();
      }
   
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
   
      public void addCard(Card c)
      {
         deck.add(c);
      }
            
   
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
      
      public Card getCard(int i)
      {
         Card temp = deck.get(i);
         deck.remove(i);
         return temp;
      }
   }
