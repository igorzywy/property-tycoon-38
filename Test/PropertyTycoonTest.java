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

    /**
     * F1
     * tests if the tiles are generated when the board is created
     */
    @Test
    public void tileTest(){
        Random random = new Random();
        int i = random.nextInt(39);
        int a = random.nextInt(39);
        System.out.println(b.getTile(i));
        System.out.println(b.getTile(a));
    }

    /**
     * F4
     * test if the dice is rolled and returns the sum of the rolls added
     */
    @Test
    public void diceTest(){

        assertTrue(b.rollDice()>0);
    }
    /**
     *f2
     * test if players are intailised on the board
     */
    @Test
    public void getPlayersTest(){
        for (Player player:b.getPlayers()) {
            System.out.println(player);
        }
    }

    /***
     * F5
     * test if the player pos is change when movePlayer is used
     */
    @Test
    public void movePlayerTest(){
        int intial=b.getPlayer(0).getPl_pos();
        b.movePlayer(b.rollDice());
        assertTrue(intial<b.getPlayer(0).getPl_pos());
    }
    /**
     * F12
     * test if when the buyProperty is used it sets the owned by value to the players id.
     */
    @Test
    public void buyProperty(){
        //set player pos to 1 so they can buy old creek
        Player p = b.getPlayer(0);
        p.setPl_pos(1);
        b.buyingTile();
        Tile t = b.getTile(1);
        assertEquals((int) t.getOwnedBy(), p.getPlayer_id());
    }

    /**
     * F8
     * test if the opportunity knock cards are generated and shuffled
     */
    @Test
    public void shuffleOpportunityCard(){
        Board A = new Board(2);
        ArrayList<Card> check = b.getCardsOK();
        ArrayList<Card> test = A.getCardsOK();
        assertNotSame(check, test);
    }
    /**
     * F7
     * test if the pot of luck cards are generated and shuffled
     */
    @Test
    public void shufflePotOfLuckCard(){
        Board A = new Board(2);
        ArrayList<Card> check = b.getCardsPL();
        ArrayList<Card> test = A.getCardsPL();
        assertNotSame(check, test);
    }
    /**
     * F26
     * test if player gets sent to jail
     */
    @Test
    public void Jail(){
        Player p = b.getPlayer(0);
        b.goJail();
        assertEquals(p.getPl_pos(),9);
    }

    /**
     * F12
     * test if the property can be purchased
     */
    @Test
    public  void checkifTitlecanbeBought(){
        Player p = b.getPlayer(0);
        p.setPl_pos(3);
        System.out.println(b.getTile(3));
        System.out.println(b.checkCanBeBought());

        assertTrue(b.checkCanBeBought());
    }
    /**
     * f12
     * test if that the  money is deducted frm player when the tile is bought and if the tile can be bought after.
     */
    @Test
    public void check(){
        Player p = b.getPlayer(0);
        p.setPl_pos(3);
        int iCash = p.getPl_cash();
        System.out.println(b.getTile(3));
        b.buyingTile();
        System.out.println(b.getTile(3));
        assertFalse(b.checkCanBeBought());
        assertTrue(iCash>p.getPl_cash());
    }
    /**
     * F8
     * test if the player lap increase once it done a full cycle of the board and that they get 200 when they passed go
     */
    @Test
    public void playertest(){

        Player p = b.getPlayer(0);
        int iCash = p.getPl_cash();
        int iLap = p.getLap();
        for (int i = 0; i < 40 ; i++) {
            p.incrPos();
        }
        assertTrue(200==p.getPl_cash()-iCash);
        assertTrue(iLap<p.getLap());
    }

    /***
     * F11
     * checks if card(opportunity knocks and pot of luck) gets added to the bottom of list when the card gets taken out
     *
     */
    @Test
    public void cardPutbottom(){
        Card c = b.getCardsOK().get(0);
        b.removeCardsOK();
        b.addCardsOK(c);
        Card f = b.getCardsPL().get(0);
        b.removeCardsPL();
        b.addCardsPL(f);

        assertSame(f,b.getCardsPL().get(16));
        assertSame(c,b.getCardsOK().get(15));
    }

    /***
     * F31
     * test if half the tile price has been added to player cash
     */
    @Test
    public void mortgageTest(){
        Player p = b.getPlayer(b.getPlayerTurn());
        p.setPl_pos(1);
        b.buyingTile();
        p.setPl_cash(p.getPl_cash() - b.getTile(1).getHousePrice());
        b.getTile(1).incrHouses();
        p.incrHousesOwned();
        Tile t = b.getTile(1);
        int tilePrice = t.getPrice();
        int cash = p.getPl_cash();
        b.mortgage(1);
        assertTrue(p.getPl_cash()-cash==tilePrice/2);
    }
    /**
     * f34
     * test if mortgage is sell house each time it is used before it mortgages a tile
     *
     */
    @Test
    public void mortageHouse(){
        Player p = b.getPlayer(b.getPlayerTurn());
        p.setPl_pos(1);
        b.buyingTile();
        int cash = p.getPl_cash();
        p.setPl_cash(p.getPl_cash() - b.getTile(1).getHousePrice());
        b.getTile(1).incrHouses();
        p.incrHousesOwned();
        Tile t = b.getTile(1);
        int tilehouse = t.getHouses();
        b.mortgage(1);
        assertTrue(t.getHouses()==tilehouse-1);
    }



}
