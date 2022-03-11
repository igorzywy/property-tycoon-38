import java.util.ArrayList;
import java.util.Random;

public class Board {
    int TILE_COUNT = 40;
    int PLAYER_COUNT = 5;
    int player_turn = 0;
    int rl_double = 0 ;
    ArrayList<Tile> board = null;
    ArrayList<Player> players = null;
    ArrayList<Card> cards = null;
    public Board(){

        //adding tiles to the board
        this.board = Tile.getXLSXData();
        this.players = new ArrayList<Player>();
        for (int i = 0; i < PLAYER_COUNT; i++) {
            this.players.add(new Player());
        }

    }

    public void setPLAYER_COUNT(int player_Count){
        PLAYER_COUNT = player_Count;
    }

    public void incPlayer(){
        player_turn++;
        if (this.player_turn > PLAYER_COUNT) {
            this.player_turn = 0;
        }

    }
    public void movePlayer(Board b, Player player){
        int roll = b.rollDice();
        for(int i = 1;  i < roll ; i++){
            player.incrPos();
        }
        if(this.rl_double ==3){
            player.setPl_pos(10);
            this.rl_double = 0;
            System.out.println(player);
            System.out.println("\n\n");
        }

        if(this.rl_double ==2){
            b.movePlayer( b,player);
            System.out.println(player);
            System.out.println("\n\n");
        }
        if(this.rl_double == 1){
            b.movePlayer( b,player);
            System.out.println(player);
            System.out.println("\n\n");
        }

    }
    public int rollDice(){
        Random rand = new Random();
        int dice = rand.nextInt(6);
        int dice2 = rand.nextInt(6);

        dice ++;
        dice2 ++;
        if(dice == dice2){
            this.rl_double++;
        }

        System.out.println("you rolled: \n " + dice + " and " + dice2);
        System.out.println("\n\n");

        return dice + dice2;
    }

    public Tile getTile(int i){
        return this.board.get(i);
    }

    public Player getPlayer(int i){
        return this.players.get(i);
    }

    public int bSize(){
        return this.board.size();
    }

    public int pSize(){
        return this.players.size();
    }

    public static void main(String[] args){

        Board b = new Board();

        System.out.println(b.bSize());

        for (int i = 0; i < b.bSize(); i++) {
            System.out.println(b.getTile(i));

        }
        for (int i = 0; i < b.pSize(); i++) {
            System.out.println(b.getPlayer(i));
        }

        b.movePlayer(b,b.getPlayer(b.player_turn));


//        b.rollDice();
//        b.rollDice();


    }
}
