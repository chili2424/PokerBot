import java.util.ArrayList;

public class Tester
{

   public static void main(String[] args)
   {
         Player p0 = new Player();
         Player p1 = new Player();
         Player p2 = new Player();
         Player p3 = new Player();
         
      
         
         Hand h0 = new Hand(new Card(1,1), new Card(2,3), new Card(3, 2), new Card(4,1), new Card(5,2));
         Hand h1 = new Hand(new Card(1,1), new Card(2,3), new Card(3, 2), new Card(4,1), new Card(5,2));
         Hand h2 = new Hand(new Card(5,1), new Card(2,3), new Card(5, 2), new Card(5,3), new Card(10,4));
         Hand h3 = new Hand(new Card(7,1), new Card(7,3), new Card(7, 2), new Card(5,3), new Card(10,4));
         
         p0.setBestHand(h0);
         p1.setBestHand(h1);
         p2.setBestHand(h2);
         p3.setBestHand(h3);
         
         p0.setPotCont(10);
         p1.setPotCont(80);
         
         p0.setActive(false);
         p2.setActive(false);
       
         
         ArrayList<Player> players = new ArrayList<Player>();
         players.add(p0);
         players.add(p1);
         players.add(p2);
         players.add(p3);
        
         
       //  System.out.print(p1.getBestHand().handToString());
       
        Table t = new Table(players, 0, 0);
        
        int[][] sortedP = t.getSortedPlayers();
        
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
               System.out.print(sortedP[i][j] + " ");
            System.out.println();
        }
         
        
      
    
    
      
   }
}
