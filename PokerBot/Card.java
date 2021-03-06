/**
*Defines a Card, which has a rank (2-14) and a suit(s,h,d,c).
*/

public class Card
{
   private String suit;
   private int value;
   private static final int SPADE = 0;
   private static final int HEART = 1;
   private static final int CLUB = 2;
   private static final int DIAMOND = 3;
   
   
   /**
   *Initializes suit and rank of Card, takes in suit
   *as number between 0-3.
   *
   *@param value Indicates ranking of card.
   *@param s Indicates suit of card.
   */
   public Card(int value, int s)
   {
      switch(s){
         case SPADE:
            suit = "s";
            break;
         case HEART:
            suit = "h";
            break;
         case CLUB:
            suit = "c";
            break;
         case DIAMOND:
            suit = "d";
            break;
      }
      
      this.value = value;
   }
   
   /**
   *Initializes rank and suit of Card, takes in string to indicate suit.
   *
   *@param value Indicates rank of card.
   *@param s Indicates suit of card.
   */
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
   
   public String getPath()
   {
     String path = "cards/card_";
      switch(value){
         case 1:
            path += "one";
            break;
         case 2:
            path += "two";
            break;
         case 3:
            path += "three";
            break;
         case 4:
            path += "four";
            break;
         case 5:
            path += "five";
            break;
         case 6:
            path += "six";
            break;
         case 7:
            path += "seven";
            break;
         case 8:
            path += "eight";
            break;
         case 9:
            path += "nine";
            break;
         case 10:
            path += "ten";
            break;
         case 11:
            path += "jack";
            break;
         case 12:
            path += "queen";
            break;
         case 13:
            path += "king";
            break;
         case 14:
            path += "ace";
            break;
      }
            
      path += "_"+suit+".png";
      
      return path;
  }
}
         
   
   
   