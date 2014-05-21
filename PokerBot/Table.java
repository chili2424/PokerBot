/**
*Defines Table where Game is played, which has Players, a Deck of Cards, and the pot.
*/

import java.util.ArrayList;
import java.awt.*;

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
      bigBlind = highestRaise = bB;
   
      deck = new Deck();
      dealer = 0;
      activeCount = players.size();

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
      int h1Kicker, h2Kicker, jStart, kStart, j, k;
      
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
                  h1Kicker = h2Kicker = 0;
                  jStart = kStart = 4;
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
                      
                        if(compareHands(curHand, bestHand) > 0)
                           bestHand = curHand;
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
         
      }
      curPlayer = firstToAct();
   }

   /**
   *Deals turn Card to Players and Table.
   */
   public void dealTurn()
   {
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
      highestRaise = bigBlind;
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
      if(dealer == players.size() - 1)
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
      for(Player p : players)
         if(p.isActive() && p.getMoneyIn() != highestBet && p.getMoney() != 0)
            return false;
            
      return true;
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
      if(decision < 0)
      {
         players.get(curPlayer).setActive(false);
         activeCount--;
      }
      else if(decision > 0)
      {
         pot += players.get(curPlayer).takeMoney(decision);
         
         if(decision != highestBet - players.get(curPlayer).getMoneyIn())
         {
            if(tableCards.size() == 0 && players.get(curPlayer).getMoneyIn() == 0)       
               if(highestBet < bigBlind)
                  highestRaise = bigBlind;
               else
                  highestRaise = decision - highestBet;
         }
            
         if(players.get(curPlayer).getMoneyIn() > highestBet)
            highestBet += players.get(curPlayer).getMoneyIn() - highestBet;
      }           
      moveCurPlayer();
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
         System.out.println(c.toString());
       
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
   public void handleWinners()
   {
      ArrayList<ArrayList<Integer>> sortedP = getSortedPlayers();
      int potCont, totalWinnings, indiWinnings;
      
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
         
      System.out.println("\n" + players.get(sortedP.get(0).get(0)).getName() + ": " + players.get(sortedP.get(0).get(0)).getMoney());       
      players.get(sortedP.get(0).get(0)).getBestHand().printHand();      
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
   
   
   public void drawTable()
   {
           
      StdDraw.setFont();
      //Draw Table
      StdDraw.setPenColor(new Color(0, 120, 0));
      StdDraw.filledEllipse(50, 50, 40,30);
      
      //Draw Players
      StdDraw.setPenColor(new Color(0, 0, 0));
      StdDraw.circle(50, 12, 5);
      
      StdDraw.text(50, 12, "user");
   
      if(playerIn("Hotel")){
         StdDraw.circle(75, 18, 5);
         StdDraw.text(75, 18, "Hotel");
      }
      else{
         StdDraw.filledCircle(75, 18, 5);
      }
   
      if(playerIn("Alpha")){
         StdDraw.circle(25, 18, 5);
         StdDraw.text(25, 18, "Alpha");
      }
      else{
         StdDraw.filledCircle(25, 18, 5);
      }
   
      if(playerIn("Delta")){
         StdDraw.circle(50, 88, 5);
         StdDraw.text(50,88, "Delta");
      }
      else{
         StdDraw.filledCircle(50, 88, 5);
      }
   
      if(playerIn("Echo")){
         StdDraw.circle(75, 82, 5);
         StdDraw.text(75, 82, "Echo");
      }
      else{
         StdDraw.filledCircle(75, 82, 5);
      }
   
      if(playerIn("Charlie")){
         StdDraw.circle(25, 82, 5);
         StdDraw.text(25, 82, "Charlie");
      }
      else{
         StdDraw.filledCircle(25, 82, 5);
      }
   
      if(playerIn("Bravo")){
         StdDraw.circle(2, 50, 5);
         StdDraw.text(2,50, "Bravo");
      }
      else{
         StdDraw.filledCircle(2, 50, 5);
      }
   
      if(playerIn("Fox Trot")){
         StdDraw.circle(96, 58, 5);
         StdDraw.text(96, 58, "Fox Trot");
      }
      else{
         StdDraw.filledCircle(96, 58, 5);
      }
      
      if(playerIn("Golf")){
         StdDraw.circle(92, 30, 5);
         StdDraw.text(92, 30, "Golf");
      }
      else{
         StdDraw.filledCircle(92, 30, 5);
      }
   
      //Draw Table Cards
      for(int i = 0; i < tableCards.size(); i++)
      {
         StdDraw.picture(25 + i*12, 50, tableCards.get(i).getPath());
      }
      
      StdDraw.setFont(new Font("SanSerif", Font.BOLD, 30));
      StdDraw.text(50, 30, "Pot: " + pot);
   
   
   }
   
   public boolean playerIn(String s)
   {
      for(Player p: players)
         if(p.getName() == s)
            return true;
            
      return false;
   } 
}
       
