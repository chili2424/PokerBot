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
      private int highestRaise;
   
      public Table(ArrayList<Player> p, int sB, int bB)
      {
         players = p;
         smallBlind = sB;
         bigBlind = highestRaise = bB;
      
         deck = new Deck();
         dealer = 0;
         activeCount = players.size();
      }
      
      public int getBigBlind()
      {
         return bigBlind;
      }
   
      public Deck getDeck()
      {
         return deck;
      }
   
      public ArrayList<Card> getTableCards()
      {
         return tableCards;
      }
   
      public void addToTableCards(Card c)
      {
         tableCards.add(c);
         for(Player p : players)
            p.addToAllCards(c);         
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
         
        // System.out.println("\nh1:");
        // h1.printHand();
        // System.out.println("\nh2:");
        // h2.printHand();
      
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
                     int jStart = 4;
                     int kStart = 4;
                     int j;
                     int k;
                     for(int i = 4; i >= 0; i--)
                     {    
                        for(j = jStart; j > 0; j--)  
                        {
                           if(h1.getCard(j).getValue() != h1.getMR1() && h1.getCard(j).getValue() != h1.getMR2())
                           {
                              h1Kicker = h1.getCard(j).getValue();
                              break;
                           }
                        }
                  
                         for(k = kStart; k > 0; k--)  
                         {
                           if(h2.getCard(k).getValue() != h2.getMR1() && h2.getCard(k).getValue() != h2.getMR2())
                           {
                              h2Kicker = h2.getCard(k).getValue();
                              break;
                           }
                         }
                           
                        if(h1Kicker != h2Kicker && h1Kicker != 0 && h2Kicker != 0)
                           break;
                        
                        jStart = j;
                        kStart = k;
                               
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
         }
         curPlayer = firstToAct();
      }
   
      public void dealFlop()
      {
         highestBet = 0;
         highestRaise = bigBlind;
         
         Card c1 = deck.dealCard();
         Card c2 = deck.dealCard();
         Card c3 = deck.dealCard();
        
         tableCards.add(c1);
         tableCards.add(c2);
         tableCards.add(c3);
      
         for(Player p : players)
         {
             
            p.addToPotCont(p.getMoneyIn());   
            p.setMoneyIn(0);
            p.addToAllCards(c1);
            p.addToAllCards(c2);
            p.addToAllCards(c3);
            
         }
         curPlayer = firstToAct();
      }
   
      public void dealTurn()
      {
         highestBet = 0;
         highestRaise = bigBlind;
         
         Card c1 = deck.dealCard();
      //   System.out.println("Deck cards dealt");
      
         tableCards.add(c1);
     //    System.out.println("Table card added");
      
         for(Player p : players)
         {
            p.addToPotCont(p.getMoneyIn());   
            p.setMoneyIn(0);
            p.addToAllCards(c1);
       //     System.out.println("SHIT");
            findBestHand(p);
      //      System.out.println("FUCK");
         }
         curPlayer = firstToAct();
     //    System.out.println("First to act called");
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
         moveDealer();
         activeCount = players.size();
         tableCards.clear();
         highestRaise = bigBlind;
      }
   
   //handle not having enough for blinds
      public void handleBlinds()
      {  
         int sB, bB;
         if(dealer == players.size() - 1)    
         {
            pot += players.get(sB = 0).takeMoney(smallBlind);
            pot += players.get(bB = 1).takeMoney(bigBlind);
            
            //players.get(0).setMoneyIn(smallBlind);
            //players.get(1).setMoneyIn(bigBlind);
            
         }      
         else if(dealer == players.size() - 2)
         {
            pot += players.get(sB = dealer + 1).takeMoney(smallBlind);
            pot += players.get(bB = 0).takeMoney(bigBlind);
            
            //players.get(dealer + 1).setMoneyIn(smallBlind);
            //players.get(0).setMoneyIn(bigBlind);
         }
         else
         { 
            pot += players.get(sB = dealer + 1).takeMoney(smallBlind);
            pot += players.get(bB = dealer + 2).takeMoney(bigBlind);
            
           // players.get(dealer + 1).setMoneyIn(smallBlind);
            //players.get(dealer + 2).setMoneyIn(bigBlind);
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
            
            if(p.isActive() && p.getMoneyIn() != highestBet && p.getMoney() != 0)
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
         //int moneyTaken;
         
         if(decision < 0)
         {
            players.get(curPlayer).setActive(false);
            activeCount--;
         }
         else if(decision > 0)
         {
           //handle betting less than highest bet
            //if decision < highest bet, assume that player is going all in
            
            pot += players.get(curPlayer).takeMoney(decision);
            //pot += moneyTaken; // deal with this
            
              //added stuff
            if(decision != highestBet - players.get(curPlayer).getMoneyIn())
            {
               if(tableCards.size() == 0 && players.get(curPlayer).getMoneyIn() == 0)       
                  if(highestBet < bigBlind)
                     highestRaise = bigBlind;
                  else
                     highestRaise = decision - highestBet;
             //  System.out.println("Highest raise is now " + highestRaise);
            }
               
            //players.get(curPlayer).setMoneyIn(players.get(curPlayer).getMoneyIn() + moneyTaken);
            
          
            if(players.get(curPlayer).getMoneyIn() > highestBet)
               highestBet += players.get(curPlayer).getMoneyIn() - highestBet;
            //players.get(curPlayer).setMoneyIn(highestBet);
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
      
      public ArrayList<Player> getPlayers()
      {
         return players;
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
      
      public ArrayList<ArrayList<Integer>> getSortedPlayers()
      {
         //sort players by best hand, descending. If ties, sort players in columns by ascending contribution.
         //int in array is index of player 
         ArrayList<ArrayList<Integer>> sortedP = new ArrayList<ArrayList<Integer>>();
         sortedP.add(new ArrayList<Integer>());
         
         int bestHand;
         
         ArrayList<Integer> beenSorted = new ArrayList<Integer>();
         
         for(int i = 0; i < players.size(); i++)
         {
            bestHand = 0;
            while(beenSorted.contains(bestHand))
               bestHand++;
            for(int j = 0; j < players.size(); j++)
            {
               if(!beenSorted.contains(j) && compareHands(players.get(j).getBestHand(), players.get(bestHand).getBestHand()) > 0)
                  bestHand = j;
            }
            if(sortedP.get(0).size() == 0)
               sortedP.get(0).add(bestHand);
            else if(compareHands(players.get(bestHand).getBestHand(), players.get(sortedP.get(sortedP.size() - 1).get(0)).getBestHand()) == 0)
            {
               for(int k = 0; k < sortedP.get(sortedP.size() - 1).size(); k++)
                  if(players.get(sortedP.get(sortedP.size() - 1).get(k)).getPotCont() > players.get(bestHand).getPotCont())
                  {
                     sortedP.get(sortedP.size() - 1).add(k, bestHand);
                     k = sortedP.get(sortedP.size() - 1).size();;
                  }
               if(!sortedP.get(sortedP.size() - 1).contains(bestHand))
                  sortedP.get(sortedP.size() - 1).add(bestHand);
            }
            else
            {
               sortedP.add(new ArrayList<Integer>());
               sortedP.get(sortedP.size() - 1).add(bestHand);
            }
            beenSorted.add(bestHand);
         }
            
         int temp;
         int size = sortedP.size();
         int count = 0;
         for(int i = 0; i < size; i++)
         {
            for(int j = 0; j < sortedP.get(i).size() && count < players.size(); j++)
            {
               if(!players.get(sortedP.get(i).get(j)).isActive())
               {
               
                  temp = sortedP.get(i).get(j);
                  sortedP.get(i).remove(j);
                  if(sortedP.get(i).size() == 0)
                     sortedP.remove(i);
                  sortedP.add(new ArrayList<Integer>());
                  sortedP.get(sortedP.size() - 1).add(temp);
                  count++;
                  i=-1;// unsure, used to be i = 0
                  break;//unsure, added this, THINK ABOUT IT YO...
               }
            }
         }
         return sortedP;              
      }
      
      public void handleWinners()
      {
         ArrayList<ArrayList<Integer>> sortedP = getSortedPlayers();
         int potCont;
         
         for(int i = 0; i < sortedP.size(); i++)
            for(int j = 0; j < sortedP.get(i).size(); j++)
            {
               potCont = players.get(sortedP.get(i).get(j)).getPotCont();
               int totalWinnings = removePotConts(potCont);
               int indiWinnings = totalWinnings / (sortedP.get(i).size() - j);
               
               for(int k = j; k < sortedP.get(i).size(); k++)
                  players.get(sortedP.get(i).get(k)).addMoney(indiWinnings);
                  
               
               
               
            } 
            
            System.out.println("\n" + players.get(sortedP.get(0).get(0)).getName() + ": " + players.get(sortedP.get(0).get(0)).getMoney());       
            players.get(sortedP.get(0).get(0)).getBestHand().printHand();         
           
      }
    
      public void setAllBestHands()
      {
         for(Player p : players)
            findBestHand(p); 
      }
      
      
      private int removePotConts(int n)
      {
         int sum = 0;
         for(Player p : players)
            sum += p.takeFromPotCont(n);
         return sum;
      }
     
      public int getHighestRaise()
      {
         return highestRaise;
      }
     
      public void setHighestRaise(int r)
      {
         highestRaise = r;
      }
     
      public void raiseBlinds()
      {
         bigBlind *= 2;
         smallBlind *= 2;
      }
     
      public int numTableCards()
      {
         return tableCards.size();
      }
   
    
   
                        
   }     
          
