   import java.util.ArrayList;

   public class Table
   {
      private ArrayList<Player> players;
      private Deck deck;
      private int smallBlind;
      private int bigBlind;
      private int dealer;
      private int highestBet;
      private ArrayList<Card> tableCards = new ArrayList<Card>();
      private int curPlayer;
      private int activeCount;
      private int pot;
   
      public Table(ArrayList<Player> p, int sB, int bB)
      {
         players = p;
         smallBlind = sB;
         bigBlind = bB;
      
         deck = new Deck();
         dealer = 0;
         activeCount = players.size();
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
                         
                           if(compareHands(curHand, bestHand) > 0)
                              bestHand = curHand;
                        }
            p.setBestHand(bestHand);
         }
      }
   
      public void dealPreFlop()
      {
         for(Player p : players)
         {
            p.addToPreFlop(deck.dealCard());
            p.addToPreFlop(deck.dealCard());  
            p.addToPotCont(p.getMoneyIn());   
         }
         curPlayer = firstToAct();
      }
   
      public void dealFlop()
      {
         highestBet = 0;
         
         Card c1 = deck.dealCard();
         Card c2 = deck.dealCard();
         Card c3 = deck.dealCard();
      
         tableCards.add(c1);
         tableCards.add(c2);
         tableCards.add(c3);
      
         for(Player p : players)
         {
            if(p.isActive())
            {  
               p.addToPotCont(p.getMoneyIn());   
               p.setMoneyIn(0);
               p.addToAllCards(c1);
               p.addToAllCards(c2);
               p.addToAllCards(c3);
            }
         }
         curPlayer = firstToAct();
      }
   
      public void dealTurn()
      {
         highestBet = 0;
         
         Card c1 = deck.dealCard();
      
         tableCards.add(c1);
      
         for(Player p : players)
         {
            if(p.isActive())
            { 
               p.addToPotCont(p.getMoneyIn());   
               p.setMoneyIn(0);
               p.addToAllCards(c1);
               findBestHand(p);
            }
         }
         curPlayer = firstToAct();
      }
   
      public void dealRiver()
      {
         dealTurn();
      }
      
      public void resetTable()
      {
         deck = new Deck();
         pot = 0;
         for(Player p : players)
         {
            p.setMoneyIn(0);
            p.clearCards();
            p.setActive(true);
         }
        // moveDealer();
         activeCount = players.size();
         tableCards.clear();
      }
   
   //handle not having enough for blinds
      public void handleBlinds()
      {  
         int sB, bB;
         if(dealer == players.size() - 1)    
         {
            pot += players.get(sB = 0).takeMoney(smallBlind);
            pot += players.get(bB = 1).takeMoney(bigBlind);
            
            players.get(0).setMoneyIn(smallBlind);
            players.get(1).setMoneyIn(bigBlind);
            
         }      
         else if(dealer == players.size() - 2)
         {
            pot += players.get(sB = dealer + 1).takeMoney(smallBlind);
            pot += players.get(bB = 0).takeMoney(bigBlind);
            
            players.get(dealer + 1).setMoneyIn(smallBlind);
            players.get(0).setMoneyIn(bigBlind);
         }
         else
         { 
            pot += players.get(sB = dealer + 1).takeMoney(smallBlind);
            pot += players.get(bB = dealer + 2).takeMoney(bigBlind);
            
            players.get(dealer + 1).setMoneyIn(smallBlind);
            players.get(dealer + 2).setMoneyIn(bigBlind);
         }
         highestBet = bigBlind;
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
        if(tableCards.size() == 0 && players.size() > 2)
        {
            //first to act is 3 after dealer
            if(dealer == players.size() - 1)
               return 2;
            
            if(dealer == players.size() - 2)
               return 1;
               
            if(dealer == players.size() - 3)
               return 0;
            
            return dealer + 3;     
        }
        
        if(tableCards.size() == 0 && players.size() == 2)
        {
            if(dealer == 0)
               return 1;
            
            if(dealer == 1)
               return 0;
        }
        
        
        if(tableCards.size() > 0 && players.size() > 2)
        {
            //first to act is 3 after dealer
               for(int i = dealer + 1; i <= players.size() + dealer; i++)
               {
                  if(i == players.size()) 
                     i = 0;
               
                  if(players.get(i).isActive())
                     return i;
               }
    
        }
        
        
        if(tableCards.size() > 0 && players.size() == 2)
        {
            //first to act is 3 after dealer
            return dealer;     
        }
        
        return -1;

      }
   
      public int getCurPlayer()
      {
         return curPlayer;
      }
   
      public boolean isTurnOver()
      {
         for(Player p : players)
         {
            if(p.isActive() && p.getMoneyIn() != highestBet)
               return false;
         }
         return true;
      }
   
      public boolean handOver()
      {
         int i = 0;
         for(Player p : players)
         {
            if (p.isActive())
               i++;
            if (i > 1)
               return false;
         }
         return true;
      }
   
      public void moveCurPlayer()
      {
         do
         {
            if(++curPlayer == players.size())
               curPlayer = 0;
         }while(!players.get(curPlayer).isActive());
      }
   
      //-1 means fold, 0 means check, a positive number means player wants to bet that amount
      public void handleDecision(int decision)
      {
         int moneyTaken;
         
         if(decision < 0)
         {
            players.get(curPlayer).setActive(false);
            activeCount--;
         }
         else if(decision > 0)
         {
            //handle betting less than highest bet
            //if decision < highest bet, assume that player is going all in
            
            moneyTaken = players.get(curPlayer).takeMoney(decision);
            pot += moneyTaken; // deal with this
            players.get(curPlayer).setMoneyIn(players.get(curPlayer).getMoneyIn() + moneyTaken);
            
            highestBet += players.get(curPlayer).getMoneyIn() - highestBet;
            players.get(curPlayer).setMoneyIn(highestBet);
         }           
         moveCurPlayer();
      }
      

      public int getHighestBet()
      {
         return highestBet;
      }
      
      public Player getPlayer(int i)
      {
         return players.get(i);
      }
      
      public int getPot()
      {
         return pot;
      }
      
      public void printTableCards()
      {
         for(Card c : tableCards)
         {
            System.out.println(c.toString());
          
         }
      }

      public int activeCount()
      {
         return activeCount;
      }
      
      public void removePlayers()
      {
         for(int i = 0; i < players.size(); i++)
         {
            if(players.get(i).getMoney() == 0)
            {
               players.remove(i);
               i = -1;
            }
         }
      }
      
      public int getDealer()
      {
         return dealer;
      }
      
      public ArrayList<Player> sortPlayersByHands()
      {
         ArrayList<Player> sortedP = new ArrayList<Player>();
         Player p;
         
         Player bestPlayer = players.get(0);
         
         for(int i = 0; i < players.size(); i++)
         {
            for(int j = 1; j < players.size(); j++)
               if(!sortedP.contains(players.get(i))  &&  compareHands(players.get(i).getBestHand(), bestPlayer.getBestHand()) > 0 &&
               players.get(i).isActive()) 
                  bestPlayer = players.get(i); 
               else if(!sortedP.contains(players.get(i))  &&  compareHands(players.get(i).getBestHand(), bestPlayer.getBestHand()) == 0 &&
               players.get(i).isActive()) 
                  if(players.get(i).getPotCont() > bestPlayer.getPotCont())
                     bestPlayer = players.get(i);
                     
            sortedP.add(bestPlayer);
            bestPlayer = players.get(0);
         }    
         for(int i = 0; i < players.size(); i++)
         {
            if(!players.get(i).isActive())
            {
               sortedP.add(players.get(i));
            }
         }
               
         return sortedP; 
      }
      
      
      public void handleWinners()
      {
         ArrayList<Player> sortedP = sortPlayersByHands();
         
         for(int i = 0; i < sortedP.size(); i++)
         {
            for(Player p : players)
            {
               sortedP.get(i).addMoney(p.takeFromPotCont(sortedP.get(i).getPotCont()));
            }
         } 
      }
      
      
      
      
}
