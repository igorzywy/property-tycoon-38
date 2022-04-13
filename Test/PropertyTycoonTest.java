import org.junit.Test;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PropertyTycoonTest {

     Board b;
    @Before
    public  void setup(){ b = new Board(); }

    @Test
    public void tileTest(){

    }
    @Test
    public void diceTest(){
         b.rollDice();
    }
    @Test
    public void movePlayerTest(){
        int intial=b.getPlayer(0).getPl_pos();
        b.turn(b.getPlayer(0));
        assertTrue(intial<b.getPlayer(0).getPl_pos());
    }
    @Test
    public void buyProperty(){
        //set player pos to 1 so they can buy old creek
        Player p = b.getPlayer(0);
        p.setPl_pos(1);
        Tile t = b.getTile(1);
        b.canBeBrought(t,p);


    }
}
