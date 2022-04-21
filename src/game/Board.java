package game;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Random;
import java.util.Scanner;


public class Board {


    private int TILE_COUNT = 40;
    private int PLAYER_COUNT = 5;
    private int player_turn = 0;
    private int rl_double = 0 ;
    private boolean isDouble = false;
    private ArrayList<Tile> board = null;
    private ArrayList<Player> players = null;
    //opportunity knocks cards
    private Deque<Card> cardsOK = null;
    //pot luck cards
    private Deque<Card> cardsPL = null;
    private int freeParkingSpace = 0;


    //player,bid
    Pair<Integer,Integer> highest_bid = new Pair<>(0,0);

    public boolean game_End = false;




    public Board(int p_count){

        //adding tiles to the board
        this.board = Tile.getXLSXData();
        //setting player count
        setPLAYER_COUNT(p_count);
        //adding players to board
        this.players = new ArrayList<Player>();
        for (int i = 0; i < PLAYER_COUNT; i++) {
            this.players.add(new Player());
        }
        //adding pot luck cards to board
        this.cardsPL = Card.getXLSXDataPotLuck();
        //adding opportunity knocks cards to board
        this.cardsOK = Card.getXLSXDataOpportunityKnocks();

    }

    public void addPublicSpace(int amount){
        this.freeParkingSpace += amount;
    }

    public int getFreeParkingSpace(){
        return this.freeParkingSpace;
    }

    public void setFreeParkingSpace(int amount){
        this.freeParkingSpace = amount;
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

    public void setRl_double(int d){
        rl_double = d;
    }
    public void setIsDouble(boolean b){
        isDouble = b;

    }


//    public void turn(){
//        Player p = getPlayer(player_turn);
//        for (int i = 0; i < board.size(); i++) {
//            System.out.println(getTile(i));
//        }
//        gameText.setText( "it's player  "+ p.getPlayer_id() +
//                " turn\n" + p.getPl_cash());
//        System.out.println("want to mortgage?");
//        Scanner input = new Scanner(System.in);
//        String s = input.nextLine();
//        if (s.equals("y") && p.getOwns().size() >= 1){
//            //mortgage();
//        }else if (s.equals("y") && p.getOwns().size() < 1){
//            System.out.println("you don't own any tiles!");
//        }
//
//        movePlayer();
//        canBeBought();
//        while(this.isDouble && this.rl_double<3) {  //check if is double is true and the amount of doubles rolled is less than 3
//            movePlayer();
//        }
//            this.isDouble = false; //reset values
//            this.rl_double = 0;
//            incPlayer();
//    }

    //lets players mortgage a property
    public boolean mortgage(int tileId){
        Player p = getPlayer(player_turn);
//        System.out.println("player " + p.getPlayer_id() + "owns: " + p.getOwns());
//        System.out.println("input tile Id to sell");
//        Scanner input = new Scanner(System.in);
//        int s = input.nextInt();
        ArrayList<Tile> tilesOfPlayer = p.getOwns();
        boolean removedTile = false;
//        while(!removedTile){
            for (int i = 0; i < tilesOfPlayer.size(); i++) {
                if (tilesOfPlayer.get(i).getTile_id() == tileId){
                    tilesOfPlayer.get(i).flipMortgaged();
//                    System.out.println("player: " + p.getPlayer_id() + " gains: " + tilesOfPlayer.get(i).getPrice()/2);
                    p.addPl_cash(tilesOfPlayer.get(i).getPrice()/2);
                    removedTile = true;
                    return removedTile;
                }else{
//                    System.out.println("you don't own that tile");
                    return false;
                }
            }
        //}


        /* can be used to trade properties
         while(removedTile == false){
            for (int i = 0; i < tilesOfPlayer.size(); i++) {
                if (tilesOfPlayer.get(i).getTile_id() == s){
                    tilesOfPlayer.get(i).setOwnedBy(null);
                    p.removeOwned(tilesOfPlayer.get(i));

                    removedTile = true;
                }else{
                    System.out.println("you don't own that tile");
                    s = input.nextInt();
                }
            }
        }
         */
        System.out.println(p.getOwns());
        return false;
    }
// need to add so that it also ask with the player wants to trade that it gets the other players id
    public String getPlayersTiles(){
        getPlayer(player_turn).getOwns();
        return null;
    }
    //regular buying of tiles(no auction)
//    public boolean canBeBought() {
//        Player p = getPlayer(player_turn);
//        Tile t = getTile(getPlayer(player_turn).getPl_pos());
//        String s = "";
//        if (t.getCanBeBought() && t.getOwnedBy() == null) { // check that the title can be bought and is not owned by any1
//            System.out.println(t.getTileName());
//            System.out.println("Price is " + t.getPrice());
//            System.out.println("Do you want to buy? Y/N \n");
//            if (s.equals("y")) { // checks if the input is y
//                int cash = p.getPl_cash();
//                int price = t.getPrice();
//                int total = cash - price;
//                if (total > 0) {
//                    p.setPl_cash(total);//yh
//                    t.setOwnedBy(p.getPlayer_id()); //yh
//                    p.addOwns(t);
//                    System.out.println("cash: " + p.getPl_cash());
//                    System.out.println("tile is owned by: " + t.getOwnedBy());
//                } else {
//                    System.out.println("you have insufficient funds");
//                    mortgage();
//                }
//            }
//            if (s.equals("n")) {
//                auction();
//            }
//        }
//        return true;
//    }

        public boolean checkCanBeBought() {
            Tile t = getTile(getPlayer(player_turn).getPl_pos());

            if (t.getCanBeBought() == false || t.getOwnedBy() == null) {
                return false;
            } else {
                return true;
            }
        }

        public boolean checkIsOwned(){
            Tile t = getTile(getPlayer(player_turn).getPl_pos());

            if (t.getOwnedBy() == null) {
                return false;
            } else {
                return true;
            }
        }


        //buying tiles with auction
        public void auctionBuy ( int t){
            // getTile(getPlayer(player_turn).getPl_pos());
            int cash = getPlayer(highest_bid.getKey()).getPl_cash();
            int price = highest_bid.getValue();
            int total = cash - price;
            if (total > 0) {
                getPlayer(highest_bid.getKey()).setPl_cash(total);//yh
                getTile(t).setOwnedBy(highest_bid.getKey()); //yh
                getPlayer(highest_bid.getKey()).addOwns(getTile(t));
                System.out.println("cash: " + getPlayer(highest_bid.getKey()).getPl_cash());
                System.out.println("tile is owned by: " + getTile(t).getOwnedBy());
            }
        }


    public void auction(){
        highest_bid = new Pair<>(0,0);
        for(int i = 0;i< pSize();i++){
            Scanner input = new Scanner(System.in);
            if(!getPlayer(i).isBankrupt()){
                System.out.println(getPlayer(i).getPlayer_id() + " would you like to place a bid?y/n \n");
                String s = input.nextLine();
                if(s.equals("y")){
                    System.out.println("place bid: ");
                    int bid = input.nextInt();//lets goo
                    boolean valid_bid = false;
                    while(valid_bid == false){
                        if (bid > getPlayer(i).getPl_cash()){
                            System.out.println("Cannot place bid you are too poor!");
                            bid = input.nextInt();
                        }else{
                            valid_bid = true;
                        }
                    }
                    bid(bid, i);
                }
            }
        }
        System.out.println(highest_bid);
        //getting the player pos to get current tile will need to change later to have auctions for bankruptcy
        auctionBuy(getPlayer(player_turn).getPl_pos());
    }

    public void bid(int player_bid, int player_id){
        if (player_bid > highest_bid.getValue()){
            highest_bid = new Pair<>(player_id,player_bid);
        }
    }

    public void jail(int p){
        getPlayer(p).setPl_pos(10);
    }

    public void movePlayer(){

        int roll = rollDice();
        if(this.rl_double == 3 ){
            this.isDouble = false;
            this.rl_double = 0;
            jail(player_turn);
            System.out.println("player is in jail");
            System.out.println("\n\n");
        }
        else {
            for (int i = 1; i < roll; i++) {
                getPlayer(player_turn).incrPos();
                System.out.println(getPlayer(player_turn).getPl_pos());
            }
        }


       System.out.println("******" + getPlayer(player_turn).getPl_pos()+"******\n\n");


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
    public Deque<Card> getCardsOK(){
        return cardsOK;   }
    public Deque<Card> getCardsPL(){
        return cardsPL;   }


    public int pSize(){
        return this.players.size();
    }

}
