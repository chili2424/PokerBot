import java.util.ArrayList;

public class Table
{
   private ArrayList<Player> players;
   private int pot;
   private Deck deck;
   private int smallBlind;
   private int bigBlind;
   private int dealer;
   private int highestBet;
   private ArrayList<Card> tableCards = new ArrayList<Card>();
   
   public Table(ArrayList<Player> p, int sB, int bB)
   {
      players = p;
      smallBlind = sB;
      bigBlind = bB;
      
      deck = new Deck();
      pot = dealer = 0;
   }
   
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
   
   //Takes in a hand of arbitrary size
   public void findBestHand(Player p)
   {
      int size = p.getAllCards().size();
      Hand curHand;
      Hand bestHand = new Hand(p.getAllCards().get(0), p.getAllCards().get(1), p.getAllCards().get(2),
       p.getAllCards().get(3), p.getAllCards().get(4));
       
      if(size > 5)
      {
         for(int i = 0; i < size; i++)
            for(int j = i + 1; j < size; j++)
               for(int k = j + 1; k < size; k++)
                  for(int l = k + 1; l < size; l++)
                     for(int m = l + 1; m < size; m++)
                     {
                        curHand = new Hand(p.getAllCards().get(i), p.getAllCards().get(j), p.getAllCards().get(k),
                         p.getAllCards().get(l), p.getAllCards().get(m));
                         
                      /*   System.out.println("Best: ");
                         bestHand.printHand();
                         System.out.println("Cur: ");
                         curHand.printHand();  
                         */
                         
                        if(compareHands(curHand, bestHand) > 0){
                       //    System.out.println("changed");
                           bestHand = curHand;
                        }
                     }
          p.setBestHand(bestHand);
      }
   }
   
   public void dealPreFlop()
   {
      for(Player p : players)
      {
         p.setMoneyIn(0);
         p.addToPreFlop(deck.dealCard());
         p.addToPreFlop(deck.dealCard());     
      }
   }
   
   public void dealFlop()
   {
      Card c1 = deck.dealCard();
      Card c2 = deck.dealCard();
      Card c3 = deck.dealCard();
      
      tableCards.add(c1);
      tableCards.add(c2);
      tableCards.add(c3);
      
      for(Player p : players)
      {
         p.setMoneyIn(0);
         p.addToAllCards(c1);
         p.addToAllCards(c2);
         p.addToAllCards(c3);
      }
   }
   
   public void dealTurn()
   {
      Card c1 = deck.dealCard();
      
      tableCards.add(c1);
      
      for(Player p : players)
      {
         p.setMoneyIn(0);
         p.addToAllCards(c1);
      }
   }
   
   public void dealRiver()
   {
      dealTurn();
   }
      
   public void resetTable()
   {
      deck = new Deck();
      for(Player p : players)
      {
         p.clearCards();
         p.setActive(true);
      }
      moveDealer();
   }
   
   //handle side pots
   public void handleBlinds()
   {  
      if(dealer == players.size() - 1)    
      {
         pot += players.get(0).takeMoney(smallBlind);
         pot += players.get(1).takeMoney(bigBlind);
      }      
      else if(dealer == players.size() - 2)
      {
         pot += players.get(dealer + 1).takeMoney(smallBlind);
         pot += players.get(0).takeMoney(bigBlind);
      }
      else
      { 
         pot += players.get(dealer + 1).takeMoney(smallBlind);
         pot += players.get(dealer + 2).takeMoney(bigBlind);
      }
   }
   
   
   public void moveDealer()
   {
      if(dealer == players.size() - 1)
         dealer = 0;
      else
         dealer++;
   }
   
   public int firstToAct()
   {
      int pos;
      int n;
      
      if(tableCards.size() == 0)
         n = 3;
      else
         if(players.size() == 2)
            n = 0;
         else
            n = 1;
         
      pos = dealer + n;
      if(pos >= players.size())
         return pos - players.size();
      else
         return pos;
   }
   
   
   
}
