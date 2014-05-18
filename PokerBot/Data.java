import java.util.ArrayList;
import java.util.Random;

public class Data
{
   private ArrayList<Hand> preflopHands = new ArrayList<Hand>();
   private ArrayList<Player> playerDataBase = new ArrayList<Player>();
   private final static int RUNS = 100000;

   public Data()
   {
      preflopHands.add(new Hand(new Card(14, 0), new Card(14, 1), 2.32));//aces
      preflopHands.add(new Hand(new Card(13, 0), new Card(13, 1), 1.67));//kings
      preflopHands.add(new Hand(new Card(12, 0), new Card(12, 1), 1.22));//queens
      preflopHands.add(new Hand(new Card(11, 0), new Card(11, 1), .86));//jacks
      preflopHands.add(new Hand(new Card(14, 0), new Card(13, 0), .78));//AK s
      preflopHands.add(new Hand(new Card(14, 0), new Card(12, 0), .59));//AQ s
      preflopHands.add(new Hand(new Card(10, 0), new Card(10, 1), .58));//TT
      preflopHands.add(new Hand(new Card(14, 0), new Card(13, 1), .51));//AK
      preflopHands.add(new Hand(new Card(14, 0), new Card(11, 0), .44));//AJ s
      preflopHands.add(new Hand(new Card(13, 0), new Card(12, 0), .39));//KQ s
      preflopHands.add(new Hand(new Card(9, 0), new Card(9, 1), .38));//99
      preflopHands.add(new Hand(new Card(14, 0), new Card(10, 0), .32));//AT s
      preflopHands.add(new Hand(new Card(14, 0), new Card(12, 1), .31));//AQ
      preflopHands.add(new Hand(new Card(13, 0), new Card(11, 0), .29));//KJ s
      preflopHands.add(new Hand(new Card(14, 0), new Card(14, 1), .25));//88
      preflopHands.add(new Hand(new Card(12, 0), new Card(11, 0), .23));//QJ s
      preflopHands.add(new Hand(new Card(13, 0), new Card(10, 0), .20));//KT s
      preflopHands.add(new Hand(new Card(14, 0), new Card(9, 0), .19));//A9 s
      preflopHands.add(new Hand(new Card(14, 0), new Card(11, 1), .19));//AJ
      preflopHands.add(new Hand(new Card(12, 0), new Card(10, 0), .17));//QT s
      preflopHands.add(new Hand(new Card(13, 0), new Card(12, 1), .16));//KQ
      preflopHands.add(new Hand(new Card(7, 0), new Card(7, 1), .16));//77
      preflopHands.add(new Hand(new Card(11, 0), new Card(10, 0), .15));//JT s
      preflopHands.add(new Hand(new Card(14, 0), new Card(8, 0), .1));//A8 s
      preflopHands.add(new Hand(new Card(13, 0), new Card(9, 0), .09));//K9 s
      preflopHands.add(new Hand(new Card(14, 0), new Card(10, 1), .08));//AT
      preflopHands.add(new Hand(new Card(14, 0), new Card(5, 0), .08));//A5 s
      preflopHands.add(new Hand(new Card(14, 0), new Card(7, 0), .08));//A7 s
      preflopHands.add(new Hand(new Card(13, 0), new Card(11, 1), .08));//KJ
      preflopHands.add(new Hand(new Card(6, 0), new Card(6, 1), .07));//66
      preflopHands.add(new Hand(new Card(10, 0), new Card(9, 0), .05));//T9 s
      preflopHands.add(new Hand(new Card(14, 0), new Card(4, 0), .05));//A4 s
      preflopHands.add(new Hand(new Card(12, 0), new Card(9, 0), .05));//Q9 s
      preflopHands.add(new Hand(new Card(11, 0), new Card(9, 0), .04));//J9 s
      preflopHands.add(new Hand(new Card(12, 0), new Card(11, 1), .03));//QJ
      preflopHands.add(new Hand(new Card(14, 0), new Card(6, 0), .03));//A6 s
      preflopHands.add(new Hand(new Card(5, 0), new Card(5, 1), .02));//55
      preflopHands.add(new Hand(new Card(14, 0), new Card(3, 0), .02));//A3 s
      preflopHands.add(new Hand(new Card(13, 0), new Card(8, 0), .01));//K8 s
      preflopHands.add(new Hand(new Card(13, 0), new Card(10, 1), .01));//KT
      preflopHands.add(new Hand(new Card(9, 0), new Card(8, 0), 0));//98 s  
      preflopHands.add(new Hand(new Card(14, 0), new Card(2, 0), 0));//A2 s
      
      preflopHands.add(new Hand(new Card(7, 0), new Card(8, 0), -.02));//78 s
      preflopHands.add(new Hand(new Card(12, 0), new Card(10, 1), -.02));//Q10 
      preflopHands.add(new Hand(new Card(12, 0), new Card(8, 0), -.02));//Q8 s
      preflopHands.add(new Hand(new Card(4, 0), new Card(4, 1), -.03));//44
      //mental note for A9 offsuit -.03
      //J8 s -.03
      //K6s-K4s -.05
      //Q7s -.06
      //K9 -.07
      //A8 -.07
      //J7s -.07
     //Q6 s -.08
     //K3s -.08
     //Q9 -.08
     //J9 -.09
     //Q5s K2s -.09
     //Q3s -.1
     //J8 89 108 79 A7 107 Q4s -.1
     //Q8 J5s 106 75 J4s K8 68 K7 J6s 58 106 67  -.11
     //A4 -.12
     //A3 -.13
     //A2 -.15
      preflopHands.add(new Hand(new Card(6, 0), new Card(7, 0), -.03));//67s
      preflopHands.add(new Hand(new Card(11, 0), new Card(10, 1), -.03));//J10
      preflopHands.add(new Hand(new Card(7, 0), new Card(9, 0), -.04));//79s
      preflopHands.add(new Hand(new Card(10, 0), new Card(7, 0), -.05));//107s
      preflopHands.add(new Hand(new Card(6, 0), new Card(5, 0), -.07));//56s
      preflopHands.add(new Hand(new Card(9, 0), new Card(10, 1), -.07));//109
      preflopHands.add(new Hand(new Card(6, 0), new Card(8, 0), -.07));//68 s  
      preflopHands.add(new Hand(new Card(3, 0), new Card(3, 1), -.07));//33
      preflopHands.add(new Hand(new Card(5, 0), new Card(4, 0), -.08));//54 s
      
      preflopHands.add(new Hand(new Card(5, 0), new Card(7, 0), -.09));//57s
      preflopHands.add(new Hand(new Card(2, 0), new Card(2, 1), -.09));//22
      preflopHands.add(new Hand(new Card(4, 0), new Card(6, 0), -.09));//64s
      preflopHands.add(new Hand(new Card(6, 0), new Card(9, 0), -.09));//69s
      
      preflopHands.add(new Hand(new Card(4, 0), new Card(7, 0), -.11));//74s
      preflopHands.add(new Hand(new Card(5, 0), new Card(3, 0), -.11));//53s
      preflopHands.add(new Hand(new Card(3, 0), new Card(6, 0), -.11));//63s
      preflopHands.add(new Hand(new Card(5, 0), new Card(8, 0), -.12));//58s
      preflopHands.add(new Hand(new Card(3, 0), new Card(4, 0), -.13));//43s
      preflopHands.add(new Hand(new Card(2, 0), new Card(3, 0), -.15));//23s    
   }
     
   public double findEV(Hand h)
   {
      Card c1 = h.getCard(0);
      Card c2 = h.getCard(1);
     
      if(c1.getSuit() == c2.getSuit())
      {
         c1 = new Card(h.getCard(0).getValue(), 0);
         c2 = new Card(h.getCard(1).getValue(), 0);
         Hand h1 = new Hand(c1, c2); 
      
         for(Hand h2 : preflopHands)
         {
            if(h1.equals(h2) == 2)
               return h2.getEV();  
         }
      }
      else
      {   
         for(Hand h2 : preflopHands)
         {
            if(h.equals(h2) == 1)
               return h2.getEV();  
         }
      }     
      return -1;
   }
 
 //We can provide player data
 
// public void addToPlayerDataBase(


//returns a double from 0 to 1, representing a percentage chance of winningon
//STUFF TO ADD:
//position, moneyIn/potCont, player personality
   public double handStrength(Table t)
   {
      ArrayList<Player> activePlayers = new ArrayList<Player>();
      ArrayList<Card> tieCards;
      Table tSim;
      Card[] cards;
      int count, j;
   
      Player curPlayer = new Player(t.getPlayer(t.getCurPlayer()).getName(), t.getPlayer(t.getCurPlayer()).getMoney());
    
              
      activePlayers.add(curPlayer);
   
      for(int i = 0; i < t.getPlayers().size(); i++)
      {
         if(t.getPlayer(i).isActive() && i != t.getCurPlayer())
            activePlayers.add(new Player(t.getPlayer(i).getName(), t.getPlayer(i).getMoney()));
      }     
   
      tSim = new Table(activePlayers, 0, 0);
   
   //do ties
      
      count = 0;
      for(int i = 0; i < RUNS; i++)
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
         {
            count++;
         }
            
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
      for(Hand h: preflopHands)
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
      
      for(int i = 0; i < preflopHands.size(); i++)
      {
         nums.add(i);
      }
      
      while(cards[0] == null)
      {
         int randInt = rand.nextInt(nums.size());
         check = preflopHands.get(nums.get(randInt));
         cards = tSim.getDeck().contains(check);
         nums.remove(randInt);
      }
      return cards;
   }
}