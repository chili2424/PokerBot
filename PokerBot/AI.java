/**
*Uses various factors including calculated Hand Strength and current Money
*to determine best decision wether that may be fold, call, check, or bet/raise.
*/

import java.util.Random;

public class AI extends Player
{
   private final static int FOLD = -1;
   private final static int THRESHOLD = 6;
   private final static double e = 2.7182;
   private final static double a = 0.5;
   double handStrength;
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
      double returnRatio, potOdds, highMult, lowMult, critPercent, EV;
      int randInt, toCall;
      toCall = t.getHighestBet() - super.getMoneyIn();
      int averageBet = t.getBigBlind();
      double raiseAdjustment = 2.0 / (1 + Math.pow(e, -0.2 * Math.pow(t.getNumRaises(),1.625))) - 1;
      
       if(t.getLastRaiser() == 0)
         {
            averageBet = t.getPlayer(0).getAverageBet();
            System.out.println("LAST RAISER, Average Bet of User: " + averageBet);
         }
   
      if(t.numTableCards() == 0)
      {
         EV = data.findEV(super.getPreFlop());
         System.out.println("EV: " + EV);
         System.out.println("Distance from Last to Act: " + t.distanceFromLastToAct());
         EV -= t.distanceFromLastToAct() * (.01 + EV/100);
         System.out.println("EV: " + EV); 
         handStrength = EV;
         
         System.out.println("\nRaise adjustment: " + raiseAdjustment);
  
         
         System.out.println("EV: " + EV + " toCall / AverageBet * .01 = " + (toCall / averageBet * .01));
         
         
         if(t.getNumRaises() == 0 && EV >= .2)
         {
            return 3 * t.getBigBlind();
         }
         else if(EV - toCall / averageBet * .01 - raiseAdjustment >= .2)
         {
            return 3 * toCall;
         }
         else if(EV - toCall / averageBet * .01 - raiseAdjustment >= 0) //might be problematic for HUGE raises/all-ins
         {
            return toCall;
         }
         else if(EV < 0 && toCall == 0)
         {
            return toCall;
         }
         else
         {
            return FOLD;
         }
      }
      
      handStrength = data.handStrength(t);
      potOdds = (double)toCall/(t.getPot() + toCall);
      if(toCall > 0) 
      {
         returnRatio = handStrength/potOdds - toCall / averageBet * .01 - raiseAdjustment;
         randInt = rand.nextInt(100);
         System.out.println("returnRatio: " + returnRatio);
         System.out.println("Rand Int: " + randInt);
         
      
         if(returnRatio > 3.5)
         {
            if(randInt > 10)
            {
               return raiseAmount(handStrength, t, toCall);
            }
         }
            
         else if(returnRatio > 3.0)
         {
            if(randInt > 30){
               return raiseAmount(handStrength, t, toCall);
            }
         }
               
         else if(returnRatio > 2.0)
         {
            if(randInt > 80){
               return raiseAmount(handStrength, t, toCall);
            }
         }
                  
         else if(returnRatio > 1)
         {
            if(randInt > 95){
               return raiseAmount(handStrength, t, toCall);
            }
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
   
      double mult, critPercent, ratio, estHandStrength, baseBet;
      int bet, rand, toCall, potThreshold;
      Random r = new Random();
      
      rand = (int)((.10 * t.getPot()) * r.nextDouble());
      toCall = callAmount;
      potThreshold = THRESHOLD * t.getBigBlind();
      mult = 1.5;
      estHandStrength = critPercent = mult / (double)t.activeCount();
      ratio = handStrength / critPercent;
      baseBet = (int)(.5 * t.getPot());
      
      if(t.getPot() < 3 * t.getBigBlind())
         return t.getBigBlind();
         
      //if(t.getPot() > potThreshold)
        // if(toCall > .75 * t.getPot())
          //  estHandStrength *= 1.5;
      //should we add other levels?
      //over a certain estHS we should just fold, for a certain amount up to that threshold, use it as another factor to modify bet.
      //should that threshold be solid number, or ratio to our own hand strength
      //also, ratio of toCall to our own money
      
      if(ratio > 1)
         bet = (int)(baseBet + (ratio * rand));              
      else 
         bet = (int)(baseBet - (ratio * rand));
      
      System.out.println("Highest Bet: " + t.getHighestBet());
      
      if(bet < t.getHighestBet() + t.getHighestRaise())
         bet = t.getHighestBet() + t.getHighestRaise();
      
      return bet;
   } 
   
   public double handStrength()
   {
      return handStrength;
   }
}

