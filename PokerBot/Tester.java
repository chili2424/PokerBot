import java.util.ArrayList;

public class Tester
{

   public static void main(String[] args)
   {
         Player p0 = new Player();
         Player p1 = new Player();
         
      /* p0.addToPreFlop(new Card(13,3));
       p0.addToPreFlop(new Card(12, 1));
       p1.addToPreFlop(new Card(13, 3));
       p1.addToPreFlop(new Card(2, 2));*/
         
         ArrayList<Player> players = new ArrayList<Player>();
         players.add(p0);
         players.add(p1);
         
       /* p0.addToAllCards(new Card(10,0));
        p0.addToAllCards(new Card(4,0));
        p0.addToAllCards(new Card(8,1));
        p0.addToAllCards(new Card(14,0));
        p0.addToAllCards(new Card(10,3));
        
        p1.addToAllCards(new Card(10,0));
        p1.addToAllCards(new Card(4,0));
        p1.addToAllCards(new Card(8,1));
        p1.addToAllCards(new Card(14,0));
        p1.addToAllCards(new Card(10,3));*/
        
        Hand h3 = new Hand(new Card(14,0), new Card(13, 1));
        
        System.out.println(h3.equals(new Hand(new Card(14, 0), new Card(13, 1))));
        
        Data data = new Data();
        
        System.out.println("EV " + data.findEV(h3));
      
      Hand h1 = new Hand(new Card(4,0), new Card(10,1), new Card(10,0), new Card(13,1), new Card(14,2));
      Hand h2 = new Hand(new Card(2,0), new Card(10,1), new Card(10,0), new Card(13,0), new Card(14,2));
      
     
         
       //System.out.print(p1.getBestHand().toString());
       
        Table t = new Table(players, 0, 0);
        
        //System.out.println(t.compareHands(p1.getBestHand(), p0.getBestHand()));
       /* t.findBestHand(p0);
        p0.getBestHand().printHand();
        
        t.findBestHand(p1);
        p1.getBestHand().printHand();*/
        System.out.println(t.compareHands(h1, h2));
        

       // t.getPlayer(0).getPreFlop().printHand();         
                
       // Data data = new Data();
       // System.out.println(data.handStrength(t));
   }
}
