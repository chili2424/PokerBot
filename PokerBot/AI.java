/**
*Uses various factors including calculated Hand Strength and current Money
*to determine best decision wether that may be fold, call, check, or bet/raise.
*/

import java.util.Random;

public class AI extends Player
{
   private final static int FOLD = -1;
   private final static int THRESHOLD = 6;
   /**
   *Initializes player.
   */
   public AI(String name, int m)
   {
      super(name, m);
   }   

   /**
   *Uses factors like hand strength and current money to decide next move.
   *
   *@param t Current table setting.
   *@return An int indicating decision (-1 to fold, 0 to check, positive number to indicate bet amount.
   */
   public int makeDecision(Table t)
   {
      Random rand = new Random();
      Data data = new Data();
      double handStrength, returnRatio, potOdds, highMult, lowMult, critPercent, EV;
      int randInt, toCall;
      toCall = t.getHighestBet() - super.getMoneyIn();
   
      if(t.numTableCards() == 0)
      {
         EV = data.findEV(super.getPreFlop());
      
         if(EV < 0)
            return FOLD;
         else
            return toCall;    
      }
      
      handStrength = data.handStrength(t);
      potOdds = (double)toCall/(t.getPot() + toCall);
      if(toCall > 0) 
      {
         returnRatio = handStrength/potOdds;
         randInt = rand.nextInt(100);
         System.out.println("returnRatio: " + returnRatio);
         System.out.println("Rand Int: " + randInt);
      
         if(returnRatio > 3)
         {
            if(randInt > 10)
               return raiseAmount(handStrength, t, toCall);
         }
            
         else if(returnRatio > 2.5)
         {
            if(randInt > 30)
               return raiseAmount(handStrength, t, toCall);
         }
               
         else if(returnRatio > 1.5)
         {
            if(randInt > 80)
               return raiseAmount(handStrength, t, toCall);
         }
                  
         else if(returnRatio > 1)
         {
            if(randInt > 95)
               return raiseAmount(handStrength, t, toCall);
         }
                     
         else if(returnRatio <= 1)
         {
            if(randInt > 10)
               return FOLD;
         }
      
         return toCall;
      }  
      else 
      {
         highMult = 1.3;
         lowMult = .8;
         randInt = rand.nextInt(100);
         critPercent = 1.0 / (double)t.activeCount();
         System.out.println("Radom Int: " + randInt);
      
         if(handStrength >= critPercent * highMult)
         {
            if(randInt > 10) 
               return raiseAmount(handStrength, t, toCall);          
         }
         else if(handStrength >= critPercent * lowMult)
         {
            if(randInt > 65)
               return raiseAmount(handStrength, t, toCall);         
         }
         else
         {
            if(randInt > 95)
               return raiseAmount(handStrength, t, toCall);
         }
         return 0;     
      }
   }

   /**
   *Uses factors such as hand strength to determine ideal amount to raise by.
   *
   *@param handStrength Previously calculated hand strength.
   *@param t Current table.
   *@param callAmount Minimum bet amount it would take to stay in the game.
   *@return Calculated bet amount.
   */
   public int raiseAmount(double handStrength, Table t, int callAmount)
   {
      Random r = new Random();
      double mult, critPercent, ratio, estHandStrength, baseBet;
      int bet, rand, toCall, potThreshold;
      
      rand = (int)((.10 * t.getPot()) * r.nextDouble());
      toCall = callAmount;
      potThreshold = THRESHOLD * t.getBigBlind();
      mult = 1.5;
      estHandStrength = critPercent = mult / (double)t.activeCount();
      ratio = handStrength / critPercent;
      baseBet = (int)(.5 * t.getPot());
      
      if(t.getPot() < 3 * t.getBigBlind())
         return t.getBigBlind();
         
      if(t.getPot() > potThreshold)
         if(toCall > .75 * t.getPot())
            estHandStrength *= 1.5;
      
      if(ratio > 1)
         bet = (int)(baseBet + (ratio * rand));              
      else 
         bet = (int)(baseBet - (ratio * rand));
      
      System.out.println("Highest Bet: " + t.getHighestBet());
      
      if(bet < t.getHighestBet() * 2 - t.getPlayer(t.getCurPlayer()).getMoneyIn())
         bet = 2 * t.getHighestBet() - t.getPlayer(t.getCurPlayer()).getMoneyIn();
      
      return bet;
   } 
}

