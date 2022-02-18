import java.util.ArrayList;

public class Board {
    int TILE_COUNT = 40;
    int PLAYER_COUNT = 1;
    int player_turn = 0;
    ArrayList<Tile> board = null;
    ArrayList<Player> players = null;
    public Board(){

        //adding tiles to the board
        this.board = new ArrayList<Tile>();
        for (int i = 0; i < TILE_COUNT; i++) {
            this.board.add(new Tile());
        }
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

        /*
        for (int i = 0; i < b.bSize(); i++) {
            System.out.println(b.getTile(i).getTile_id());

        }
        for (int i = 0; i < b.pSize(); i++) {
            System.out.println(b.getPlayer(i).getPlayer_id());
        }
        */

    }
}
