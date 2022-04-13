import javafx.util.Pair;

import java.sql.SQLOutput;
import java.util.*;

public class Board {
    int TILE_COUNT = 40;
    int PLAYER_COUNT = 5;
    int player_turn = 0;
    int rl_double = 0 ;
    boolean isDouble = false;
    ArrayList<Tile> board = null;
    ArrayList<Player> players = null;

    //opportunity cards
    Deque opportunityCards;
    //pot of luck cards
    Deque potOfLuck;



    //player,bid
    Pair<Integer,Integer> highest_bid = new Pair<>(0,0);

    public boolean game_End = false;
    public Board(){

        //adding tiles to the board
        this.board = Tile.getXLSXData();
        //adding players to board
        this.players = new ArrayList<Player>();
        for (int i = 0; i < PLAYER_COUNT; i++) {
            this.players.add(new Player());
        }
        //adding pot luck cards to board
        this.potOfLuck = Card.getXLSXDataPotLuck();
        //adding opportunity knocks cards to board
        this.opportunityCards = Card.getXLSXDataOpportunityKnocks();





    }


    public void setPLAYER_COUNT(int player_Count){
        PLAYER_COUNT = player_Count;
    }
    //increment the player turn
    public void incPlayer(){
        if (this.player_turn == PLAYER_COUNT-1) {
            this.player_turn = 0;
        }else{
            this.player_turn++;
        }



    }


    public void turn(Player p){
        System.out.println( "it's player  "+ p.getPlayer_id() + " turn\n");
        movePlayer(p);
        Tile t = getTile(p.getPl_pos());
        canBeBrought(t,p);
        opportunity(p);



        while(this.isDouble && this.rl_double<3) {  //check if is double is true and the amount of doubles rolled is less than 3
            movePlayer(p);
        }
            this.isDouble = false; //reset values
            this.rl_double = 0;
            incPlayer();
        }

    //regular buying of tiles(no auction)
    public void canBeBrought(Tile t, Player p){
        Scanner input = new Scanner(System.in);

        if(t.tile_can_be_bought && t.owned_by ==null){ // check that the title can be bought and is not owned by any1
            System.out.println(t.tile_name);
            System.out.println("Price is " + t.price);
            System.out.println("Do you want to buy? Y/N \n");
            String s = input.nextLine(); //take the input
            if(s.equals("y")){ // checks if the input is y
                int cash = p.getPl_cash();
                int price = t.price;
                int total = cash - price;
                if(total>0){
                    p.setPl_cash(total);//yh
                    t.owned_by = p.player_id; //yh
                    System.out.println("cash: "+p.pl_cash);
                    System.out.println("tile is owned by: "+ t.owned_by);
                }else{
                    System.out.println("you have insufficient funds");
                }
            }
            if(s.equals("n")){
              auction();
            }
        }
    }
    //buying tiles with auction
    public void auctionBuy(Player p, Tile t) {
        int cash = p.getPl_cash();
        int price = highest_bid.getValue();
        int total = cash - price;
        if (total > 0) {
            p.setPl_cash(total);//yh
            t.owned_by = p.player_id; //yh
            System.out.println("cash: " + p.pl_cash);
            System.out.println("tile is owned by: " + t.owned_by);
        }
    }
     //opportunity knocks method
    public void opportunity(Player p){
        int pos = p.pl_pos;
        if( pos== 7| pos == 22|pos==36){
            System.out.println("inside opp");
        }
    }

    public void auction(){
        highest_bid = new Pair<>(0,0);
        for(int i = 0;i< pSize();i++){
            Player p = getPlayer(i);
            Scanner input = new Scanner(System.in);
            if(!p.isBankrupt()){
                System.out.println( p.player_id + " would you like to place a bid?y/n \n");
                String s = input.nextLine();
                if(s.equals("y")){
                    System.out.println("place bid: ");
                    int bid = input.nextInt();//lets goo
                    boolean valid_bid = false;
                    while(valid_bid == false){
                        if (bid > p.getPl_cash()){
                            System.out.println("Cannot place bid you are too poor!");
                            bid = input.nextInt();
                        }else{
                            valid_bid = true;
                        }
                    }

                    bid(bid, p);
                }
            }
        }
        System.out.println(highest_bid);
        auctionBuy(getPlayer(highest_bid.getKey()), getTile(getPlayer(player_turn).getPl_pos()));
    }

    public void bid(int player_bid, Player p){
        if (player_bid > highest_bid.getValue()){
            highest_bid = new Pair<>(p.player_id,player_bid);
        }
    }

    public void jail(Player p){
        p.setPl_pos(10);
    }

    public void movePlayer(Player player){

        int roll = rollDice();
        if(this.rl_double == 3 ){
            this.isDouble = false;
            this.rl_double = 0;
            jail(player);
            System.out.println("player is in jail");
            System.out.println("\n\n");
        }
        else {
            for (int i = 0; i < roll; i++) {
                player.incrPos();
            }
        }


       System.out.println("******" + player.getPl_pos()+"******\n\n");


    }
    public int rollDice(){
        Random rand = new Random();
        int dice = rand.nextInt(6);
        int dice2 = rand.nextInt(6);
        dice ++;
        dice2 ++;
        if(dice == dice2){
            this.rl_double++;
            this.isDouble = true;
        }
        else{
            this.isDouble = false;
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




    public boolean getGameEnd(){
        return game_End;
    }
    public int getPlayerTurn(){
        return player_turn;
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
        System.out.println("\n\n");
        System.out.println("pot of luck cards");
        System.out.println(b.potOfLuck+" \n");
        System.out.println("OpportunityCards");
        System.out.println(b.opportunityCards+"\n");

       while(!b.getGameEnd()) {
           for(int i =0; i< b.pSize();)


               if(!b.getPlayer(b.getPlayerTurn()).isBankrupt()){
                   b.turn(b.getPlayer(b.getPlayerTurn()));
               }

       }

//        b.rollDice();
//        b.rollDice();


    }
}
