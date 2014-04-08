import java.util.ArrayList;

public class Table
{
   private ArrayList<Player> players = new ArrayList<Player>();
   private int pot;
   private Deck deck;
   private int smallBlind;
   private int bigBlind;
   private int dealer;
   
   /*
    * Returns positive int if hand1 is better than hand2
    * Returns 0 if hand1 and hand2 are the same
    * Return negative if hand1 is less than hand2
    */
   public int compareHands(Hand h1, Hand h2)
   {
      int h1Type = h1.classifyHand();
      int h2Type = h2.classifyHand();
      int handDiff = h1Type - h2Type;
      
      if(handDiff == 0)
      {
         switch(h1Type)
         {
            case 0:
            case 4:
            case 5:
            case 8:
               for(int i = 4; i >= 0; i--)
               {
                  if(h1.getCard(i).getValue() != h2.getCard(i).getValue())
                  {
                     handDiff = h1.getCard(i).getValue() - h2.getCard(i).getValue();
                     break;
                  }
               }
               break;
            default:
               if(h1.getMR1() != h2.getMR1())
                  handDiff = h1.getMR1() - h2.getMR1();
               else if(h1.getMR2() != h2.getMR2())
                  handDiff = h1.getMR2() - h2.getMR2();
               else 
               {
                  int h1Kicker = 0;
                  int h2Kicker = 0;
                  for(int i = 0; i < 5; i = i + 2)
                  {  
                     if(h1.getCard(i).getValue() != h1.getMR1() && h1.getCard(i).getValue() != h1.getMR2())
                        h1Kicker = h1.getCard(i).getValue();
                     
                     if(h2.getCard(i).getValue() != h2.getMR1() && h2.getCard(i).getValue() != h2.getMR2())
                        h2Kicker = h2.getCard(i).getValue();
                  }              
                  handDiff = h1Kicker - h2Kicker;
               }      
                      
               break;
         }               
      }
      
      return handDiff;
   }
   
   public Hand bestHand(ArrayList<Card> allCards)
   {
      ArrayList<Hand> hands = possibleHands(allCards); 
      int bestHandIndex = 0;
      for(int i = 0; i < hands.size() - 1; i++)
      {
         if(compareHands(hands.get(bestHandIndex), hands.get(i+1)) < 0)
            bestHandIndex = i + 1;    
      }    
      return hands.get(bestHandIndex);    
   }
   
   public ArrayList<Hand> possibleHands(ArrayList<Card> allCards)
   {    
      ArrayList<Hand> hands = new ArrayList<Hand>();
      for(int i = 0; i < allCards.size(); i++){
         for(int j = i + 1; j < allCards.size(); j++){
            for(int k = j + 1; k < allCards.size(); k++){ 
               for(int l = k + 1; l < allCards.size(); l++){
                  for(int m = l + 1; m < allCards.size(); m++){ 
                     Hand h = new Hand();
                     h.addCard(allCards.get(i));
                     h.addCard(allCards.get(j));
                     h.addCard(allCards.get(k));
                     h.addCard(allCards.get(l));
                     h.addCard(allCards.get(m));
                     hands.add(h); }}}}} 
      return hands;
   }
  
}