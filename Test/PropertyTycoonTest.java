import org.junit.Test;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PropertyTycoonTest {

    private Board b;
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
        int intial=b.getPlayer(1).getPl_pos();
        b.turn(b, b.getPlayer(1));
        assertTrue(intial<b.getPlayer(1).getPl_pos());

    }
}
