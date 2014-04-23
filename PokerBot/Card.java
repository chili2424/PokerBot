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
}
         
   
   
   