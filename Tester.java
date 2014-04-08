import java.util.ArrayList;

public class Tester
{
static int onePair, twoPair, three, fullHouse, straight, flush, four, straightFlush, royal, highCard;
   public static void main(String[] args)
   {
         Table t = new Table();
       /*  
         Hand h1 = new Hand();
         Hand h2 = new Hand();
       
         h1.addCard(new Card(10,1));
         h1.addCard(new Card(11,1));
         h1.addCard(new Card(12,1));
         h1.addCard(new Card(13,1));
         h1.addCard(new Card(14,1));
         
         h2.addCard(new Card(10,1));
         h2.addCard(new Card(11,1));
         h2.addCard(new Card(12,1));
         h2.addCard(new Card(13,1));
         h2.addCard(new Card(14,1));
         
         System.out.println(t.compareHands(h1,h2));
         //System.out.println(h.classifyHand());
           
    
      double runs = 5;
      for(int j = 0; j < runs; j++)
      {
         Deck d = new Deck();
               
         for(int i = 0; i < 5; i++){
            h1.addCard(d.dealCard());
            h2.addCard(d.dealCard());
         }
         
         //if(h.isFlush())
           // flush++;
                  
        String handType = h.handToString();
         if(handType == "Royal Flush")
            royal++;
         else if(handType == "Straight Flush")
            straightFlush++;
         else if(handType == "Flush")
            flush++;
         else if(handType == "Straight")
            straight++;
         else if(handType == "Four of a kind")
            four++;
         else if(handType == "Full House")
            fullHouse++;
         else if(handType == "Three of a kind")
            three++;
         else if(handType == "Two pair")
            twoPair++;
         else if(handType == "One pair")
            onePair++;
         else if(handType == "High Card")
            highCard++;
         
        
        h1.printHand();
        System.out.println();
        h2.printHand();
        System.out.println(t.compareHands(h1, h2) + "\n");            
        h1.clearHand();
        h2.clearHand();
        
      }*/
      
      
      /*System.out.println("High card error: " + (100*(highCard*100/runs - 50.1253)/50.1253));
      System.out.println("One pair error: " + (100*(onePair*100/runs - 42.3728)/42.3728));
      System.out.println("Two pair error: " + (100*(twoPair*100/runs - 4.7619)/4.7619));
      System.out.println("Triple error: " + (100*(three*100/runs - 2.11)/2.11));
      System.out.println("Straight error: " + (100*(straight*100/runs - .392)/.392));
      System.out.println("Flush error: " + (100*(flush*100/runs - .197)/.197));
      System.out.println("Flush: " + flush);
      System.out.println("Full house error " + (100*(fullHouse*100/runs - .144)/.144));
      System.out.println("Four error: " + (100*(four*100/runs - .0240)/.0240));
      System.out.println("Straight flush error: " + (100*(straightFlush*100/runs - .00139)/.00139));
      System.out.println("Royal error: " + (100*(royal*100/runs - .000154)/.000154));
      */
     
      
      
      
      
      //test posssibleHands()
      
      ArrayList<Card> cards = new ArrayList<Card>();
      cards.add(new Card(7,1));
      cards.add(new Card(6,1));
      cards.add(new Card(5,1));
      cards.add(new Card(4,1));
      cards.add(new Card(3,1));
      cards.add(new Card(2,1));
      cards.add(new Card(8,1));
      
      ArrayList<Hand> hands = t.possibleHands(cards);
      
      for(int i = 0; i < hands.size(); i++)
      {
         hands.get(i).printHand();
         System.out.println(" ");
      }
       
      
   }
}
