/**
*Does calculations to assist AI in making decisions.
*/

import java.util.ArrayList;
import java.util.Random;

public class Data
{
   private ArrayList<Hand> possPreflopHands = new ArrayList<Hand>();
   private ArrayList<Player> playerDataBase = new ArrayList<Player>();
   private final static int RUNS = 10000;

   /****************how the fuck do we describe this*****************/
   public Data()
   {
      possPreflopHands.add(new Hand(new Card(14, 0), new Card(14, 1), 2.32));//aces
      possPreflopHands.add(new Hand(new Card(13, 0), new Card(13, 1), 1.67));//kings
      possPreflopHands.add(new Hand(new Card(12, 0), new Card(12, 1), 1.22));//queens
      possPreflopHands.add(new Hand(new Card(11, 0), new Card(11, 1), .86));//jacks
      possPreflopHands.add(new Hand(new Card(14, 0), new Card(13, 0), .78));//AK s
      possPreflopHands.add(new Hand(new Card(14, 0), new Card(12, 0), .59));//AQ s
      possPreflopHands.add(new Hand(new Card(10, 0), new Card(10, 1), .58));//TT
      possPreflopHands.add(new Hand(new Card(14, 0), new Card(13, 1), .51));//AK
      possPreflopHands.add(new Hand(new Card(14, 0), new Card(11, 0), .44));//AJ s
      possPreflopHands.add(new Hand(new Card(13, 0), new Card(12, 0), .39));//KQ s
      possPreflopHands.add(new Hand(new Card(9, 0), new Card(9, 1), .38));//99
      possPreflopHands.add(new Hand(new Card(14, 0), new Card(10, 0), .32));//AT s
      possPreflopHands.add(new Hand(new Card(14, 0), new Card(12, 1), .31));//AQ
      possPreflopHands.add(new Hand(new Card(13, 0), new Card(11, 0), .29));//KJ s
      possPreflopHands.add(new Hand(new Card(14, 0), new Card(14, 1), .25));//88
      possPreflopHands.add(new Hand(new Card(12, 0), new Card(11, 0), .23));//QJ s
      possPreflopHands.add(new Hand(new Card(13, 0), new Card(10, 0), .20));//KT s
      possPreflopHands.add(new Hand(new Card(14, 0), new Card(9, 0), .19));//A9 s
      possPreflopHands.add(new Hand(new Card(14, 0), new Card(11, 1), .19));//AJ
      possPreflopHands.add(new Hand(new Card(12, 0), new Card(10, 0), .17));//QT s
      possPreflopHands.add(new Hand(new Card(13, 0), new Card(12, 1), .16));//KQ
      possPreflopHands.add(new Hand(new Card(7, 0), new Card(7, 1), .16));//77
      possPreflopHands.add(new Hand(new Card(11, 0), new Card(10, 0), .15));//JT s
      possPreflopHands.add(new Hand(new Card(14, 0), new Card(8, 0), .1));//A8 s
      possPreflopHands.add(new Hand(new Card(13, 0), new Card(9, 0), .09));//K9 s
      possPreflopHands.add(new Hand(new Card(14, 0), new Card(10, 1), .08));//AT
      possPreflopHands.add(new Hand(new Card(14, 0), new Card(5, 0), .08));//A5 s
      possPreflopHands.add(new Hand(new Card(14, 0), new Card(7, 0), .08));//A7 s
      possPreflopHands.add(new Hand(new Card(13, 0), new Card(11, 1), .08));//KJ
      possPreflopHands.add(new Hand(new Card(6, 0), new Card(6, 1), .07));//66
      possPreflopHands.add(new Hand(new Card(10, 0), new Card(9, 0), .05));//T9 s
      possPreflopHands.add(new Hand(new Card(14, 0), new Card(4, 0), .05));//A4 s
      possPreflopHands.add(new Hand(new Card(12, 0), new Card(9, 0), .05));//Q9 s
      possPreflopHands.add(new Hand(new Card(11, 0), new Card(9, 0), .04));//J9 s
      possPreflopHands.add(new Hand(new Card(12, 0), new Card(11, 1), .03));//QJ
      possPreflopHands.add(new Hand(new Card(14, 0), new Card(6, 0), .03));//A6 s
      possPreflopHands.add(new Hand(new Card(5, 0), new Card(5, 1), .02));//55
      possPreflopHands.add(new Hand(new Card(14, 0), new Card(3, 0), .02));//A3 s
      possPreflopHands.add(new Hand(new Card(13, 0), new Card(8, 0), .01));//K8 s
      possPreflopHands.add(new Hand(new Card(13, 0), new Card(10, 1), .01));//KT
      possPreflopHands.add(new Hand(new Card(9, 0), new Card(8, 0), 0));//98 s  
      possPreflopHands.add(new Hand(new Card(14, 0), new Card(2, 0), 0));//A2 s   
      possPreflopHands.add(new Hand(new Card(7, 0), new Card(8, 0), -.02));//78 s
      possPreflopHands.add(new Hand(new Card(12, 0), new Card(10, 1), -.02));//Q10 
      possPreflopHands.add(new Hand(new Card(12, 0), new Card(8, 0), -.02));//Q8 s
      possPreflopHands.add(new Hand(new Card(4, 0), new Card(4, 1), -.03));//44
      //hands we don't want to play
      possPreflopHands.add(new Hand(new Card(14, 0), new Card(9, 1), -.03));//A9
      possPreflopHands.add(new Hand(new Card(11, 0), new Card(8, 0), -.05));//J8 s
      possPreflopHands.add(new Hand(new Card(13, 0), new Card(6, 0), -.05));//K6 s
      possPreflopHands.add(new Hand(new Card(13, 0), new Card(5, 0), -.05));//K5 s
      possPreflopHands.add(new Hand(new Card(13, 0), new Card(4, 0), -.05));//K4 s
      possPreflopHands.add(new Hand(new Card(12, 0), new Card(7, 0), -.06));//Q7s
      possPreflopHands.add(new Hand(new Card(13, 0), new Card(9, 1), -.07));//K9  
      possPreflopHands.add(new Hand(new Card(14, 0), new Card(8, 1), -.07));//A8   
      possPreflopHands.add(new Hand(new Card(11, 0), new Card(7, 0), -.07));//J7 s
      possPreflopHands.add(new Hand(new Card(12, 0), new Card(6, 0), -.08));//Q6s 
      possPreflopHands.add(new Hand(new Card(13, 0), new Card(3, 0), -.08));//K3 s
      possPreflopHands.add(new Hand(new Card(12, 0), new Card(9, 1), -.08));//Q9
      possPreflopHands.add(new Hand(new Card(11, 0), new Card(9, 1), -.09));//J9
      possPreflopHands.add(new Hand(new Card(12, 0), new Card(5, 0), -.09));//Q5 s
      possPreflopHands.add(new Hand(new Card(13, 0), new Card(2, 0), -.09));//K2 s
      possPreflopHands.add(new Hand(new Card(12, 0), new Card(3, 0), -.1));//Q3 s
      possPreflopHands.add(new Hand(new Card(11, 0), new Card(8, 1), -.1));//J8
      possPreflopHands.add(new Hand(new Card(9, 0), new Card(8, 1), -.1));//89
      possPreflopHands.add(new Hand(new Card(10, 0), new Card(8, 1), -.1));//10 8  
      possPreflopHands.add(new Hand(new Card(9, 0), new Card(7, 1), -.1));//79   
      possPreflopHands.add(new Hand(new Card(14, 0), new Card(7, 1), -.1));//A7 s
      possPreflopHands.add(new Hand(new Card(10, 0), new Card(7, 1), -.1));//10 7 
      possPreflopHands.add(new Hand(new Card(12, 0), new Card(4, 0), -.1));//Q4 s
      possPreflopHands.add(new Hand(new Card(12, 0), new Card(8, 1), -.11));//Q8
      possPreflopHands.add(new Hand(new Card(11, 0), new Card(5, 1), -.11));//J5 s
      possPreflopHands.add(new Hand(new Card(10, 0), new Card(6, 1), -.11));//10 6
      possPreflopHands.add(new Hand(new Card(7, 0), new Card(5, 1), -.11));//75
      possPreflopHands.add(new Hand(new Card(11, 0), new Card(4, 0), -.11));//J4 s
      possPreflopHands.add(new Hand(new Card(13, 0), new Card(8, 1), -.11));//K8
      possPreflopHands.add(new Hand(new Card(6, 0), new Card(8, 1), -.11));//68
      possPreflopHands.add(new Hand(new Card(13, 0), new Card(7, 1), -.11));//K7
      possPreflopHands.add(new Hand(new Card(11, 0), new Card(6, 0), -.11));//J6 s
      possPreflopHands.add(new Hand(new Card(5, 0), new Card(8, 1), -.11));//58
      possPreflopHands.add(new Hand(new Card(10, 0), new Card(6, 1), -.11));//10 6
      possPreflopHands.add(new Hand(new Card(7, 0), new Card(6, 1), -.11));//67
      possPreflopHands.add(new Hand(new Card(14, 0), new Card(4, 1), -.12));//A4
      possPreflopHands.add(new Hand(new Card(14, 0), new Card(3, 1), -.13));//A3
      possPreflopHands.add(new Hand(new Card(14, 0), new Card(2, 1), -.15));//A2
      //end of bad hands
      
      possPreflopHands.add(new Hand(new Card(6, 0), new Card(7, 0), -.03));//67s
      possPreflopHands.add(new Hand(new Card(11, 0), new Card(10, 1), -.03));//J10
      possPreflopHands.add(new Hand(new Card(7, 0), new Card(9, 0), -.04));//79s
      possPreflopHands.add(new Hand(new Card(10, 0), new Card(7, 0), -.05));//107s
      possPreflopHands.add(new Hand(new Card(6, 0), new Card(5, 0), -.07));//56s
      possPreflopHands.add(new Hand(new Card(9, 0), new Card(10, 1), -.07));//109
      possPreflopHands.add(new Hand(new Card(6, 0), new Card(8, 0), -.07));//68 s  
      possPreflopHands.add(new Hand(new Card(3, 0), new Card(3, 1), -.07));//33
      possPreflopHands.add(new Hand(new Card(5, 0), new Card(4, 0), -.08));//54 s
      
      possPreflopHands.add(new Hand(new Card(5, 0), new Card(7, 0), -.09));//57s
      possPreflopHands.add(new Hand(new Card(2, 0), new Card(2, 1), -.09));//22
      possPreflopHands.add(new Hand(new Card(4, 0), new Card(6, 0), -.09));//64s
      possPreflopHands.add(new Hand(new Card(6, 0), new Card(9, 0), -.09));//69s
      
      possPreflopHands.add(new Hand(new Card(4, 0), new Card(7, 0), -.11));//74s
      possPreflopHands.add(new Hand(new Card(5, 0), new Card(3, 0), -.11));//53s
      possPreflopHands.add(new Hand(new Card(3, 0), new Card(6, 0), -.11));//63s
      possPreflopHands.add(new Hand(new Card(5, 0), new Card(8, 0), -.12));//58s
      possPreflopHands.add(new Hand(new Card(3, 0), new Card(4, 0), -.13));//43s
      possPreflopHands.add(new Hand(new Card(2, 0), new Card(3, 0), -.15));//23s    
   }
     
   public double findEV(Hand h)
   {
      Hand h1;
      Card c1, c2;
      
      c1 = h.getCard(0);
      c2 = h.getCard(1);
     
      if(c1.getSuit() == c2.getSuit())
      {
         c1 = new Card(h.getCard(0).getValue(), 0);
         c2 = new Card(h.getCard(1).getValue(), 0);
         h1 = new Hand(c1, c2); 
      
         for(Hand h2 : possPreflopHands)
         {
            if(h1.equals(h2) == 2)
               return h2.getEV();  
         }
      }
      else
      {   
         for(Hand h2 : possPreflopHands)
         {
            if(h.equals(h2) > 0)
               return h2.getEV();  
         }
      }     
      return -1;
   }
 
 //We can provide player data
 
// public void addToPlayerDataBase(

//STUFF TO ADD:
//position, moneyIn/potCont, player personality

   /**
   *Simulates RUNS number of possible outcomes from current game, returns
   *probability of current Player winning the hand.
   *
   *@param t Current table from which to run simulations.
   *@return Number between zero and one, indicating percentage chance of winning.
   */
   public double handStrength(Table t)
   {
      ArrayList<Player> activePlayers = new ArrayList<Player>();
      Table tSim;
      Card[] cards;
      int count = 0, j, i;
      Player curPlayer;
   
      curPlayer = new Player(t.getPlayer(t.getCurPlayer()).getName(), t.getPlayer(t.getCurPlayer()).getMoney());
      activePlayers.add(curPlayer);
   
      for(i = 0; i < t.getPlayers().size(); i++)
      {
         if(t.getPlayer(i).isActive() && i != t.getCurPlayer())
            activePlayers.add(new Player(t.getPlayer(i).getName(), t.getPlayer(i).getMoney()));
      }     
   
      tSim = new Table(activePlayers, 0, 0);
      for(i = 0; i < RUNS; i++)
      {
         curPlayer.addToPreFlop(t.getPlayer(t.getCurPlayer()).getPreFlop().getCard(0));
         curPlayer.addToPreFlop(t.getPlayer(t.getCurPlayer()).getPreFlop().getCard(1));
         tSim.getDeck().removeCard(curPlayer.getPreFlop().getCard(0));
         tSim.getDeck().removeCard(curPlayer.getPreFlop().getCard(1));
        
         for(j = 0; j < t.getTableCards().size(); j++)
         {
            tSim.addToTableCards(t.getTableCards().get(j));
            tSim.getDeck().removeCard(t.getTableCards().get(j));
         } 
         
         for(j = 1; j < activePlayers.size(); j++)
         {
            cards = randomPossibleHand(tSim);
            tSim.getPlayer(j).addToPreFlop(tSim.getDeck().removeCard(cards[0]));
            tSim.getPlayer(j).addToPreFlop(tSim.getDeck().removeCard(cards[1]));       
         }
         
         for(j = tSim.numTableCards(); j < 5; j++)
         {
            tSim.dealTurn();
         }
         
         tSim.findBestHand(curPlayer);
         for(j = 1; j < activePlayers.size(); j++)
         {
            tSim.findBestHand(activePlayers.get(j));
            if(tSim.compareHands(curPlayer.getBestHand(), activePlayers.get(j).getBestHand()) < 0)
            {
               break;
            }
         }
         
         if(j == activePlayers.size())
            count++;
            
         for(j = 1; j < activePlayers.size(); j++)
         {
            tSim.getDeck().addCard(activePlayers.get(j).getPreFlop().getCard(0));
            tSim.getDeck().addCard(activePlayers.get(j).getPreFlop().getCard(1));
            activePlayers.get(j).clearCards();
         }
         
         for(j = 4; j >= t.numTableCards(); j--)
         {
            tSim.getDeck().addCard(tSim.getTableCards().remove(j));
         }
      
         tSim.getTableCards().clear();
         curPlayer.clearCards();
      }
          
      return count/(double)RUNS;
   }
   
   /**
   *Looks for equivalent of specified Hand in table of possible Hands.
   *
   *@param hand Preflop hand to search for.
   *@return True if equivalent hand is found.
   */
   public boolean handInTable(Hand hand)
   {
      for(Hand h: possPreflopHands)
      {
         if(hand.equals(h) > 0)
            return true;
      }
      return false;
   }
   
   /**
   *Generates random Hand which can be found in table of possible hands.
   *
   *@param tSim Fake Table which matches the main game table exactly, used for Monte Carlo simulations.
   *@return Array of Cards found in table.
   */
   public Card[] randomPossibleHand(Table tSim)
   { 
      Random rand = new Random();
      ArrayList<Integer> nums = new ArrayList<Integer>();
      Card cards[] = {null, null};
      Hand check;
      
      for(int i = 0; i < possPreflopHands.size(); i++)
      {
         nums.add(i);
      }
      
      while(cards[0] == null)
      {
         int randInt = rand.nextInt(nums.size());
         check = possPreflopHands.get(nums.get(randInt));
         cards = tSim.getDeck().contains(check);
         nums.remove(randInt);
      }
      return cards;
   }
}