import game.Board;
import game.Card;
import game.Player;
import game.Tile;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Random;

import static org.junit.Assert.*;

public class PropertyTycoonTest {

    Board b;
    @Before
    public  void setup(){ b = new Board(5); }

    @Test
    public void tileTest(){
        Random random = new Random();
        int i = random.nextInt(39);
        int a = random.nextInt(39);
        System.out.println(b.getTile(i));
        System.out.println(b.getTile(a));
    }
    @Test
    public void diceTest(){

        assertTrue(b.rollDice()>0);
    }

    @Test
    public void getPlayersTest(){
        for (Player player:b.getPlayers()) {
            System.out.println(player);
        }
    }

    @Test
    public void movePlayerTest(){
        int intial=b.getPlayer(0).getPl_pos();
        b.movePlayer(b.rollDice());
        assertTrue(intial<b.getPlayer(0).getPl_pos());
    }
    @Test
    public void buyProperty(){
        //set player pos to 1 so they can buy old creek
        Player p = b.getPlayer(0);
        p.setPl_pos(1);
        b.buyingTile();
        Tile t = b.getTile(1);
        assertEquals((int) t.getOwnedBy(), p.getPlayer_id());
    }
    @Test
    public void shuffleOpportunityCard(){
        Board A = new Board(2);
        ArrayList<Card> check = b.getCardsOK();
        ArrayList<Card> test = A.getCardsOK();
        assertNotSame(check, test);
    }
    @Test
    public void shufflePotOfLuckCard(){
        Board A = new Board(2);
        ArrayList<Card> check = b.getCardsPL();
        ArrayList<Card> test = A.getCardsPL();
        assertNotSame(check, test);
    }
    @Test
    public void Jail(){
        Player p = b.getPlayer(0);
        b.goJail();
        assertEquals(p.getPl_pos(),9);
    }
    @Test
    public  void checkifTitlecanbeBought(){
        Player p = b.getPlayer(0);
        p.setPl_pos(3);
        System.out.println(b.getTile(3));
        System.out.println(b.checkCanBeBought());

        assertTrue(b.checkCanBeBought());
    }
    @Test
    public void check(){
        Player p = b.getPlayer(0);
        p.setPl_pos(3);
        System.out.println(b.getTile(3));
        b.buyingTile();
        System.out.println(b.getTile(3));
        System.out.println(b.checkCanBeBought());
        assertFalse(b.checkCanBeBought());
    }

}
