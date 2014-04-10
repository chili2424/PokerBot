public class Card
{
   private String suit;
   private int value;
   
   public Card(int value, int s)
   {
      switch(s){
         case 0:
            suit = "Spades";
            break;
         case 1:
            suit = "Hearts";
            break;
         case 2:
            suit = "Clubs";
            break;
         case 3:
            suit = "Diamonds";
            break;
      }
      
      this.value = value;
   }
   
   public String getSuit()
   {
      return suit;
   }
   
   public int getValue()
   {
      return value;
   }
   
   //Convert the |value| of a card to the correct string.
   public String toString()
   {
      if(value == 11)
      {
         return "Jack of " + suit;
      }
      else if(value == 12)
      {
         return "Queen of " + suit;
      }
      else if(value == 13)
      {
         return "King of " + suit;
      }
      else if(value == 14)
      {
         return "Ace of " + suit;
      }
      else
      {
         return Integer.toString(value) + " of " + suit;
      }
   }
}
         
   
   
   