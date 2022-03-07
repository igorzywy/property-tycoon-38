import java.util.ArrayList;
import java.util.Random;

public class Board {
    int TILE_COUNT = 40;
    int PLAYER_COUNT = 5;
    int player_turn = 0;
    ArrayList<Tile> board = null;
    ArrayList<Player> players = null;

    public Board(){

        //adding tiles to the board
        this.board = Tile.getXLSXData();
        this.players = new ArrayList<Player>();
        for (int i = 0; i < PLAYER_COUNT; i++) {
            this.players.add(new Player());
        }

    }

    public void incPlayer(){
        if (this.player_turn >= PLAYER_COUNT){
            this.player_turn=0;
        }else{
            this.player_turn++;
        }

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

//        b.getPlayer(0).incrPos( b.Roll());
//        System.out.println("pos:" + b.getPlayer(0).getPl_pos());
//        b.getPlayer(0).incrPos(b.Roll());
//        System.out.println("pos:" + b.getPlayer(0).getPl_pos());



    }
}
