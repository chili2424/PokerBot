/**
*Defines Table where Game is played, which has Players, a Deck of Cards, and the pot.
*/


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
   private ArrayList<String> textBox = new ArrayList<String>();
   private final int MAX_LENGTH = 10;
   private int numRaises;
   private int lastRaiser = -1;

/**
*Initializes players and small/big blind amounts.
*
*@param p ArrayList of players at table.
*@param sB Amount of initial small blind.
*@param bB Amount of initial big blind.
*/
   public Table(ArrayList<Player> p, int sB, int bB)
   {
      players = p;
      smallBlind = sB;
      bigBlind = highestBet = highestRaise = bB;
   
      deck = new Deck();
      dealer = 0;
      activeCount = players.size();
   
   }

   public int getLastRaiser()
   {
      return lastRaiser;
   }

/**
*Returns current amount for Big Blind.
*
*@return Big Blind bet amount.
*/
   public int getBigBlind()
   {
      return bigBlind;
   }

/**
*Returns Deck.
*
*@return Deck.
*/
   public Deck getDeck()
   {
      return deck;
   }

/**
*Returns Cards currently dealt to the Table, and visible/accessible by all Players.
*
*@return ArrayList of table Cards.
*/
   public ArrayList<Card> getTableCards()
   {
      return tableCards;
   }

/**
*Adds secified Card to Table, and every Player's available Cards.
*
*@param c Card to be added.
*/
   public void addToTableCards(Card c)
   {
      tableCards.add(c);
      for(Player p : players)
         p.addToAllCards(c);         
   }

/**
*Compares two hands.
*
*@return Positive number, zero, or negative number if first Hand is greater than, equal to, or less than second hand.
*/
   public int compareHands(Hand h1, Hand h2)
   {
      int h1Type = h1.classifyHand();
      int h2Type = h2.classifyHand();
      int handDiff = h1Type - h2Type;
      int h1Kicker, h2Kicker, jStart, kStart, j = h1.getCard(4).getValue(), k = h2.getCard(4).getValue();
   
      if(handDiff == 0)
      {
         switch(h1Type)
         {
            case 0:
            case 5:
               for(int i = 4; i >= 0; i--)
               {
                  if(h1.getCard(i).getValue() != h2.getCard(i).getValue())
                  {
                     handDiff = h1.getCard(i).getValue() - h2.getCard(i).getValue();
                     break;
                  }
               }
               break;
            case 4:
            case 8:
            //If you have A-5 straight hand strength = 0
            //Default case, hand strength = highest card in hand
               if(h1.getCard(4).getValue() == 14 && h1.getCard(0).getValue() == 2)
                  j = 0;                  
               if(h2.getCard(4).getValue() == 14 && h2.getCard(0).getValue() == 2)
                  k = 0;
            
               handDiff = j - k;
            
                 
               break;
         
            default:
               //System.out.println("Got here");
               if(h1.getMR1() != h2.getMR1())
                  handDiff = h1.getMR1() - h2.getMR1();
               else if(h1.getMR2() != h2.getMR2())
                  handDiff = h1.getMR2() - h2.getMR2();
               else 
               {
                  h1Kicker = h2Kicker = 0;
                  jStart = kStart = 4;
                  for(int i = 4; i >= 0; i--)
                  {    
                     for(j = jStart; j >= 0; j--)  
                     {
                        if(h1.getCard(j).getValue() != h1.getMR1() && h1.getCard(j).getValue() != h1.getMR2())
                        {
                           h1Kicker = h1.getCard(j).getValue();
                           break;
                        }
                     }
                  
                                          
                     for(k = kStart; k >= 0; k--)  
                     {
                        if(h2.getCard(k).getValue() != h2.getMR1() && h2.getCard(k).getValue() != h2.getMR2())
                        {
                           h2Kicker = h2.getCard(k).getValue();
                           break;
                        }
                     }
                  
                     
                     if(h1Kicker != h2Kicker && h1Kicker != 0 && h2Kicker != 0)
                     {
                        break;
                     }
                  
                     jStart = j - 1;
                     kStart = k - 1;   
                     
                   
                  }                          
                  handDiff = h1Kicker - h2Kicker;
               }      
               break;
         }               
      }
      return handDiff;
   }

/**
*Finds best combination of specified Player's Cards.
*
*@param p Player who's best Hand is going to be calculated.
*/
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
                        
                        //System.out.print("CurHand: ");
                        //curHand.printHand();
                        //System.out.print("\nBestHand: ");
                        //bestHand.printHand();
                        
                     
                        if(compareHands(curHand, bestHand) > 0)
                        {
                           bestHand = curHand;
                           //System.out.println("\nCurHand won\n\n");
                        }
                        else
                        {
                           //System.out.println("\nBestHand won\n\n");
                        }
                     }
         p.setBestHand(bestHand);
      }
   }

/**
*Deals each player two Cards, and sets first player to act for this hand.
*/
   public void dealPreFlop()
   {
      for(Player p : players)
      {
         p.addToPreFlop(deck.dealCard());
         p.addToPreFlop(deck.dealCard());   
      }
      curPlayer = firstToAct();
   }

/**
*Deals flop Cards to Table and to every Player.
*/
   public void dealFlop()
   {
      lastRaiser = -1;
      numRaises = 0;
      Card c1, c2, c3;
   
      highestBet = 0;
      highestRaise = bigBlind;
   
      c1 = deck.dealCard();
      c2 = deck.dealCard();
      c3 = deck.dealCard();
   
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
         findBestHand(p);
      
      }
      curPlayer = firstToAct();
   }

/**
*Deals turn Card to Players and Table.
*/
   public void dealTurn()
   {
      lastRaiser = -1;
      numRaises = 0;
      Card c1;
   
      highestBet = 0;
      highestRaise = bigBlind;
   
      c1 = deck.dealCard();
   
      tableCards.add(c1);
   
      for(Player p : players)
      {
         p.addToPotCont(p.getMoneyIn());   
         p.setMoneyIn(0);
         p.addToAllCards(c1);
         findBestHand(p);
      }
      curPlayer = firstToAct();
   }

/**
*Deals river Card to Players and Table.
*/
   public void dealRiver()
   {
      dealTurn();
   }

/**
*Resets Table in preperation for next hand.
*/
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
      highestBet = highestRaise = bigBlind;
      numRaises = 0;
      lastRaiser = -1;
   }

/**
*Takes small blind and big blind amounts from appropriate players, and adds those to the pot.
*/
   public void handleBlinds()
   {  
      int sB, bB;
      if(dealer == players.size() - 1)    
      {
         pot += players.get(sB = 0).takeMoney(smallBlind);
         pot += players.get(bB = 1).takeMoney(bigBlind);
      }      
      else if(dealer == players.size() - 2)
      {
         pot += players.get(sB = dealer + 1).takeMoney(smallBlind);
         pot += players.get(bB = 0).takeMoney(bigBlind);
      }
      else
      { 
         pot += players.get(sB = dealer + 1).takeMoney(smallBlind);
         pot += players.get(bB = dealer + 2).takeMoney(bigBlind);
      }
      highestBet = bigBlind;
   }

/**
*Adjusts dealer position, wrapping to the start of the list if necessary.
*/
   public void moveDealer()
   {
      if(dealer >= players.size() - 1)
         dealer = 0;
      else
         dealer++;
   }

/**
*Sets first to act to Player in the third position after the Dealer, wrapping around to
*start of list if neccessary. If only two players left, first to act is NOT the dealer preFlop, is the dealer
*after the flop.
*After the flop, makes sure to skip over inactive players.
*/
   public int firstToAct()
   {
      if(tableCards.size() == 0 && players.size() > 2)
      {
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
         return dealer;     
      }
   
      return -1;
   }

/**
*Returns position of current Player.
*
*@reutrn Current Player's position.
*/
   public int getCurPlayer()
   {
      return curPlayer;
   }

/**
*Checks if current round of betting is over based on whether or not everyone has bet the correct amount, or folded.
*
*@return True if turn is over.
*/
   public boolean isTurnOver()
   {
      boolean over = true;
      int count = 0;
      
      for(Player p : players)
      {
         if(p.isActive() && p.getMoneyIn() != highestBet && p.getMoney() != 0)
         {
            System.out.println("Over is false-" + p.getName() + " is active, his money in is " + p.getMoneyIn() + " and the highest bet is" + highestBet + " his money is " + p.getMoney());
            over = false;
         }   
         if(p.isActive() && p.getMoney() > 0)
            count++;
      }
      
      if(count <= 1)
         return true;
      else
         return over;
   }

/**
*Checks if hand is over before the river has been dealt, by checking each Player's active status.
*
*@return True if more than one player is still Active.
*/
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

/**
*Adjusts position of current player, skipping inactive players.
*/
   public void moveCurPlayer()
   {
      do
      {
         if(++curPlayer == players.size())
            curPlayer = 0;
      }while(!players.get(curPlayer).isActive());
   }

/**
*Handles the current Player's decision, -1 means fold, 0 means check, and a positive number indicates 
*amount the player wants to bet/raise. Adjusts current Player.
*/
   public void handleDecision(int decision)
   {  
   
      String text = players.get(curPlayer).getName();
      if(decision < 0)
      {
         players.get(curPlayer).setActive(false);
         activeCount--;
         text += " folded!";
      }
      else if(decision > 0)
      {
      //SERIOUS ERRORS HERE
         decision = players.get(curPlayer).takeMoney(decision);
      
      
         if(players.get(curPlayer).getMoneyIn() == highestBet)
            text += " called.";
         else if(players.get(curPlayer).getMoneyIn() > highestBet && players.get(curPlayer).getMoney() > 0)
         {
            incrNumRaises();
            if(curPlayer == 0) //user
            {
               lastRaiser = 0;
            }
            else
            {
               lastRaiser = -1;
            }
            players.get(curPlayer).incrTimesBet();
            players.get(curPlayer).setAverageBet(decision);
         
            text += " raised by " + Integer.toString(decision - highestBet) + ".";
         }
         else
            text += " went all in!";
      
         pot += decision;
      
         if(tableCards.size() == 0 && players.get(curPlayer).getMoneyIn() == 0)       
            if(highestBet < bigBlind)
               highestRaise = bigBlind;
            else
               highestRaise = decision - highestBet;
         
            
                           
         if(players.get(curPlayer).getMoneyIn() > highestBet)
         {
            highestRaise = decision - highestBet;
            highestBet = decision;
         }
          
          
      }  
      else if(decision == 0)
         text += " checked."; 
              
      moveCurPlayer();
   
      textBox.add(text);
      if(textBox.size() > MAX_LENGTH)
         textBox.remove(0);
   }

/**
*Returns highest bet so far during this round of betting.
*
*@return Highest bet.
*/
   public int getHighestBet()
   {
      return highestBet;
   }

/**
*Returns Player at indicated position.
*
*@param i Player position.
*@return Player at position i.
*/
   public Player getPlayer(int i)
   {
      return players.get(i);
   }

/**
*Returns all Players.
*
*@return ArrayList of Players.
*/
   public ArrayList<Player> getPlayers()
   {
      return players;
   }

/**
*Returns amount in pot.
*
*@return Pot amount.
*/
   public int getPot()
   {
      return pot;
   }

/**
*Prints cards "on table".
*/
   public void printTableCards()
   {
      for(Card c : tableCards)
      {
         //System.out.println(c.toString());
      
      }
   }

/**
*Returns number of active players.
*
*@return Active count.
*/
   public int activeCount()
   {
      return activeCount;
   }

/**
*Removes all Players from Table who are out of money.
*/
   public void removePlayers()
   {
      for(int i = 0; i < players.size(); i++)
         if(players.get(i).getMoney() == 0)
         {
            players.remove(i);
            i = -1;
         }
   }

/**
*Returns postition of dealer.
*
*@return Dealer's position.
*/
   public int getDealer()
   {
      return dealer;
   }

/**********how much have we tested this?***********/
/**
*Sorts players by hand, if tied, players are sorted by amount contributed to the pot.
*
*@return Outer ArrayList is sorted by hand strength, inner ArrayLists are of Players with equally good hands, sorted
*by pot contribution. Int indicates position of Player in this Table's ArrayList of all Players.
*/
   public ArrayList<ArrayList<Integer>> getSortedPlayers()
   {
   
      ArrayList<ArrayList<Integer>> sortedP;
      ArrayList<Integer> beenSorted;
      int bestHand, temp, size, count;
   
      sortedP = new ArrayList<ArrayList<Integer>>();
      sortedP.add(new ArrayList<Integer>());
   
      beenSorted = new ArrayList<Integer>();
   
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
      
      size = sortedP.size();
      count = 0;
      for(int i = 0; i < size; i++)
      {
         for(int j = 0; j < sortedP.get(i).size() && count < players.size(); j++)
            if(!players.get(sortedP.get(i).get(j)).isActive())
            {
               temp = sortedP.get(i).get(j);
               sortedP.get(i).remove(j);
               if(sortedP.get(i).size() == 0)
                  sortedP.remove(i);
               sortedP.add(new ArrayList<Integer>());
               sortedP.get(sortedP.size() - 1).add(temp);
               count++;
               i=-1;
               break;
            }
      }
      return sortedP;              
   }

/**
*Distributes pot to winners. A Player can only win as much from each opponent as he contributed to the pot.
*/
   public void handleWinners(GUI gui, boolean display)
   {
      ArrayList<ArrayList<Integer>> sortedP = getSortedPlayers();
      int potCont, totalWinnings, indiWinnings;
      String s = "";
   
         
   //print out winners
      if(sortedP.get(0).size() == 1 && activeCount > 1)
      {
         addText(players.get(sortedP.get(0).get(0)).getName() + " won with");
         addText(players.get(sortedP.get(0).get(0)).getBestHand().toString() + "!");
      }
      else if(activeCount > 1)
      {
         int i;
         for(i = 0; i < sortedP.get(0).size() - 1; i++)
         {
            s+= players.get(sortedP.get(0).get(i)).getName() + ", ";
         }
         s += "and " + players.get(sortedP.get(0).get(i)).getName() + " tied";
         addText(s);
         addText("with a " + players.get(sortedP.get(0).get(0)).getBestHand().toString()); 
      
      }
   
      for(Player p: players)
      {
         p.addToPotCont(p.getMoneyIn());   
         p.setMoneyIn(0);
      }
   
      for(int i = 0; i < sortedP.size(); i++)
         for(int j = 0; j < sortedP.get(i).size(); j++)
         {
            potCont = players.get(sortedP.get(i).get(j)).getPotCont();
            totalWinnings = removePotConts(potCont);
            indiWinnings = totalWinnings / (sortedP.get(i).size() - j);
         
            for(int k = j; k < sortedP.get(i).size(); k++)
               players.get(sortedP.get(i).get(k)).addMoney(indiWinnings);
         } 
      
      //System.out.println("\n" + players.get(sortedP.get(0).get(0)).getName() + ": " + players.get(sortedP.get(0).get(0)).getMoney());       
      players.get(sortedP.get(0).get(0)).getBestHand().printHand(); 
   
      if(activeCount > 1)
      {
         addText("Click anywhere.");
         gui.drawTable(this, display, false);
         StdDraw.show(1000);
         while(!StdDraw.mousePressed());
      }
      else
      {
         gui.drawTable(this, display, false);     
         StdDraw.show(3000);
      }
   
   }

/**
*Find each Player's best Hand.
*/
   public void setAllBestHands()
   {
      for(Player p : players)
         findBestHand(p); 
   }

/**
*Lower each Player's pot contribution. Used to distribute winnings.
*/
   private int removePotConts(int n)
   {
      int sum = 0;
      for(Player p : players)
         sum += p.takeFromPotCont(n);
      return sum;
   }

/**
*Returns highest raise in current round of betting.
*
*@return Highest raise amount.
*/
   public int getHighestRaise()
   {
      return highestRaise;
   }

/**
*Sets highest raise in current round of betting.
*
*@param r Highest raise amount.
*/
   public void setHighestRaise(int r)
   {
      highestRaise = r;
   }

/**
*Doublees blind amounts.
*/
   public void raiseBlinds()
   {
      bigBlind *= 2;
      smallBlind *= 2;
   }

/**
*Returns number of cards on the table.
*
*@return number of table cards.
*/
   public int numTableCards()
   {
      return tableCards.size();
   }

/**
*Returns current player's position in relation to last player.
*
*@return disatnce from last player.
*/
   public int distanceFromLastToAct()
   {
      int activePos = 0, temp = 0, position;
      for (int i = 0; i < players.size(); i++)
      {
         if(i + firstToAct() > players.size() - 1)
            temp = i + firstToAct() - players.size();
         else 
            temp = i + firstToAct();
         if(players.get(temp).isActive() && temp != curPlayer)        
            activePos++;
         
         if(temp == curPlayer)
            break;
      }
   
      position = activeCount - activePos;
      if(position >= 0)
         return position - 1;
   
      return position + players.size() - 1;         
   }

   public Player playerIn(String s)
   {
      for(Player p: players)
         if(p.getName() == s)
            return p;
         
      return null;
   } 

   public ArrayList<String> textBox()
   {
      return textBox;
   }

   public void addText(String s)
   {
      textBox.add(s);
      if(textBox.size() > 10)
         textBox.remove(0);
   }

   public int getNumRaises()
   {
      return numRaises;
   }

   public int incrNumRaises()
   {
      return numRaises++;
   }
   
}
    
