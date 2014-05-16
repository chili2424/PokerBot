   import java.util.Random;

   public class AI extends Player
   {
      public AI(String name, int m)
      {
         super(name, m);
      }   
   
      public int makeDecision(Table t)
      {
         Random rand = new Random();
         Data data = new Data();
         double handStrength, returnRatio, potOdds;
         int toCall = t.getHighestBet() - super.getMoneyIn();
      
         if(t.numTableCards() == 0)
         {
            double EV = data.findEV(super.getPreFlop());
         
            if(EV < 0)
               return -1;
            else
               return toCall;
                   
         }
         
         handStrength = data.handStrength(t);
         potOdds = (double)toCall/(t.getPot() + toCall);
         if(toCall > 0) 
         {
            returnRatio = handStrength/potOdds;
            int randInt = rand.nextInt(100);
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
                  return -1;
            }
         
            return toCall;
         
         }  
         else 
         {
            double highMult = 1.3, lowMult = .8;
            int randInt = rand.nextInt(101); // 0 to a 100
            double critPercent = 1.0 / (double)t.activeCount();
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
   
      public int raiseAmount(double handStrength, Table t, int callAmount)
      {
         Random r = new Random();
         double mult = 1.5;   
         double critPercent = mult / (double)t.activeCount();
         double ratio = handStrength / critPercent;
         int bet;
         int rand = (int)((.10 * t.getPot()) * r.nextDouble());
         int toCall = callAmount;
         int potThreshold = 6 * t.getBigBlind();
         double estHandStrength = critPercent;
      
         int baseBet = (int)(.5 * t.getPot());
         if(t.getPot() < 3 * t.getBigBlind())
            return t.getBigBlind();
            
         if(t.getPot() > potThreshold)
         {
            if(toCall > .75 * t.getPot())
            {
               estHandStrength *= 1.5;
            }
         }
         
            if(ratio > 1)
               bet = (int)(baseBet + (ratio * rand));              
            else 
               bet = (int)(baseBet - (ratio * rand));
         
            System.out.println("Highest Bet: " + t.getHighestBet());
         
            if(bet < t.getHighestBet() * 2 - t.getPlayer(t.getCurPlayer()).getMoneyIn())
            {
               bet = 2 * t.getHighestBet() - t.getPlayer(t.getCurPlayer()).getMoneyIn();
            }
            return bet;
         
         }
      
      }
   
