import java.util.ArrayList;

public class Tester
{

   public static void main(String[] args)
   {
         Player p0 = new Player();
         Player p1 = new Player();
         
       //  p0.addToPreFlop(new Card(14,0));
       //  p0.addToPreFlop(new Card(14, 1));
       //  p1.addToPreFlop(new Card(12, 0));
       //  p1.addToPreFlop(new Card(11, 0));
         
         ArrayList<Player> players = new ArrayList<Player>();
         players.add(p0);
         players.add(p1);
         
         p0.addToAllCards(new Card(8,0));
         p0.addToAllCards(new Card(13,0));
         p0.addToAllCards(new Card(8,1));
     //    p0.addToAllCards(new Card(5,0));
         p0.addToAllCards(new Card(3,0));
         p0.addToAllCards(new Card(3,3));
      //   p0.addToAllCards(new Card(5,3));
      
      Hand h1 = new Hand(new Card(3,0), new Card(1,0), new Card(1,1), new Card(7,1), new Card(7,2));
      Hand h2 = new Hand(new Card(1,0), new Card(6,2), new Card(1,1), new Card(7,3), new Card(7,4));
      
     
         
       //  System.out.print(p1.getBestHand().handToString());
       
        Table t = new Table(players, 0, 0);
        System.out.println(t.compareHands(h1,h2));
      //  t.findBestHand(p0);
      //  p0.getBestHand().printHand();
        

        t.getPlayer(0).getPreFlop().printHand();         
                
       // Data data = new Data();
       // System.out.println(data.handStrength(t));
   }
}
