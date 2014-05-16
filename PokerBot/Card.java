/**
*Defines a Card, which has a rank (2-14) and a suit(s,h,d,c).
*/

public class Card
{
   private String suit;
   private int value;
   
   public Card(int value, int s)
   {
      switch(s){
         case 0:
            suit = "s";
            break;
         case 1:
            suit = "h";
            break;
         case 2:
            suit = "c";
            break;
         case 3:
            suit = "d";
            break;
      }
      
      this.value = value;
   }
   
   public Card(int value, String s)
   {
      this.value = value;
      suit = s;
   }
   
   /**
   *Returns suit of card.
   *
   *@return suit as a String.
   */
   public String getSuit()
   {
      return suit;
   }
   
   /**
   *Returns rank of card.
   *
   *@return rank of card as integer value.
   */
   public int getValue()
   {
      return value;
   }
   
   /**
   *Overrides Object's toString method.
   *
   *@return suit and rank as a letter/character pair.
   */
   public String toString()
   {
      if(value == 11)
      {
         return "J" + suit;
      }
      else if(value == 12)
      {
         return "Q" + suit;
      }
      else if(value == 13)
      {
         return "K" + suit;
      }
      else if(value == 14)
      {
         return "A" + suit;
      }
      else
      {
         return Integer.toString(value) + suit;
      }
   }
   
   /**
   *Overrides Object's equals method.
   *
   *@return true if and only if rank and suit both match.
   */
   public boolean equals(Card c)
   {
      if(c.getValue() == value && c.getSuit() == suit)
         return true;
         
      return false;
   }
}
         
   
   
   