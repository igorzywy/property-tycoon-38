package game;

import javafx.util.Pair;

import java.util.*;


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
    private Pair<Integer,Integer> highestBidOld = new Pair<>(0,0);

    private ArrayList<Player> auctionList = new ArrayList<Player>();
    private int highestBid = 0;
    private Player highestBidPlayer = null;




    public void resetHighestBid(){
        this.highestBid = 0;
        this.highestBidPlayer = null;
    }

    public Player getHighestBidPlayer(){
        return this.highestBidPlayer;
    }

    public int numOfBidders(){
        return auctionList.size();
    }

    public int getHighestBid(){
        return this.highestBid;
    }

    //for when the player doesn't want to bid for the current property
    public void auctionNo(){
        this.auctionList.remove(0);
    }

    public void auctionBid(int amount){
        Player ap = auctionList.get(0);
        this.highestBid += amount;
        this.highestBidPlayer = ap;
    }

    //assigns the tile owned by to the player that won the auctions id and subtracts the money from the players account
    public void auctionWinner(int tileI){
        getTile(tileI).setOwnedBy(highestBidPlayer.getPlayer_id());
        highestBidPlayer.addOwns(getTile(tileI));
        highestBidPlayer.setPl_cash(highestBidPlayer.getPl_cash() - highestBid);
    }

    public Player getCurrentBiddingPlayer(){
        return auctionList.get(0);
    }

    public void incrIndexOfCurrentBidder(){
        Player temp = auctionList.get(0);
        auctionList.remove(0);
        auctionList.add(temp);
    }

    public void addAllPlayersToAuction(){

        auctionList.clear();
        auctionList.addAll(players);
    }

    //need to add a remove from current player auction turn

    //player,bid


    public boolean game_End = false;


    /*
    * Bankrupt calls the player to be removed from the current players arraylist and also resets all of the tiles
    *  that they own to be their default state
    *
     */

    public void bankrupt(int playerId){
        for (int i = 0; i < getPlayerCount(); i++) {
            if (getPlayer(i).getPlayer_id() == playerId){
                ArrayList<Tile> plOwned = getPlayer(i).getOwns();
                for (int j = 0; j < plOwned.size(); j++) {
                    plOwned.get(j).setOwnedBy(0);
                    plOwned.get(j).resetTile();
                }
                players.remove(getPlayer(i));
            }

        }

    }


    public Board(int p_count){

        //adding tiles to the board
        this.board = Tile.getXLSXData();
        //setting player count
        setPlayerCount(p_count);
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

    public boolean playerOwnSet(String tileGroup) {
        Player p = players.get(player_turn);
        int counter = 0;
        for (int i = 0; i < bSize(); i++) {
            if (getTile(i).getGroup().equals(tileGroup) && (getTile(i).getOwnedBy() == p.getPlayer_id())) {
                counter++;
            }
        }
        if (counter == getGroupSize(tileGroup)) {
            return true;
        } else {
            return false;
        }
    }
    //getting the rent amount for landing on an owned station
    public int getStationRentAmount(){
        Player p = players.get(player_turn);
        int ownedBy = getTile(p.getPl_pos()).getOwnedBy();
        int numOfStations = getPlayer(ownedBy).getNumberOfGroupOwned(getTile(p.getPl_pos()).getGroup());
        int rentAmount = getTile(p.getPl_pos()).getPrice();
        for (int i = 1; i <= numOfStations; i++) {
            rentAmount *= i;
        }
        return rentAmount;
    }

    //getting the rent amount for landing on an owned utility
    public int getUtilRentAmount(int diceRoll){
        Player p = players.get(player_turn);
        int ownedBy = getTile(p.getPl_pos()).getOwnedBy();
        int numOfUtils = getPlayer(ownedBy).getNumberOfGroupOwned(getTile(p.getPl_pos()).getGroup());
        int rentAmount = 0;
        if (numOfUtils == 1){
            rentAmount = 4 * diceRoll;
        }else if(numOfUtils == 2){
            rentAmount = 10 * diceRoll;
        }

        return rentAmount;
    }

    public void payRent(int rentAmount){
        Player p = players.get(player_turn);
        int ownedBy = getTile(p.getPl_pos()).getOwnedBy();
        Player pOwn = getPlayer(ownedBy);
        p.setPl_cash(p.getPl_cash() - rentAmount);
        pOwn.setPl_cash(pOwn.getPl_cash() + rentAmount);
        getTile(p.getPl_pos()).setOwnedBy(p.getPlayer_id());
    }

    public void buyingTile(){
        Player p = getPlayer(player_turn);
        getTile(p.getPl_pos()).setOwnedBy(p.getPlayer_id());
        p.addOwns(getTile(p.getPl_pos()));
        p.setPl_cash(p.getPl_cash() - getTile(p.getPl_pos()).getPrice());
    }

    public void Cardbpp(int amount){
        Player p = getPlayer(player_turn);
        p.setPl_cash(p.getPl_cash() + amount);
    }

    public void Cardppp(int amount){
        Player p = getPlayer(player_turn);
        for (int i = 0; i < getPlayerCount(); i++) {
            getPlayer(i).setPl_cash(getPlayer(i).getPl_cash() - amount);
        }
        p.setPl_cash(p.getPl_cash() + (getPlayerCount()*amount) + amount);
    }

    public void Cardpm(int amount){
        Player p = getPlayer(player_turn);
        p.setPl_pos(amount);
    }

    public void Cardppb(int amount) {
        Player p = getPlayer(player_turn);
        p.setPl_cash(p.getPl_cash() - amount);
    }

    public void Cardppf(int amount){
        Player p = getPlayer(player_turn);
        p.setPl_cash(p.getPl_cash() - amount);
        addPublicSpace(amount);
    }

    public void CardpmxBack(int amount){
        Player p = getPlayer(player_turn);
        for (int i = 0; i < amount; i++) {
            p.decresePos();
        }
    }

    public void CardpmxForward(int amount){
        Player p = getPlayer(player_turn);
        for (int i = 0; i < amount; i++) {
            p.incrPos();
        }
    }

    public void Cardppr(int amount){
        Player p = getPlayer(player_turn);
        p.setPl_cash(p.getPl_cash() - (p.getHousesOwned() * amount) +
                p.getHotelsOwned() * amount);
    }

    public void Cardpmf(int amount){
        Player p = getPlayer(player_turn);
        if (amount - p.getPl_pos() > bSize()-1){
            p.setPl_cash(p.getPl_cash() + 200);
            p.setPl_pos(amount);
        }else{
            p.setPl_pos(amount);
        }
    }

    public void Cardjfc(){
        Player p = getPlayer(player_turn);
        p.incrNoJailFreeCard();
    }



    public int getGroupSize(String grp){
        int counter = 0;
        for (int i = 0; i < bSize(); i++) {
            if (board.get(i).getGroup().equals(grp)){
                counter++;
            }
        }
        return counter;
    }
    //checks if there is a difference of more than 1 house on each property
    public boolean checkForTwoHouseDiff(String grp){
        ArrayList<Tile> tilesDiff = new ArrayList<Tile>();
        for (int i = 0; i < bSize(); i++) {
            if (board.get(i).getGroup().equals(grp)){
                tilesDiff.add(board.get(i));
            }
        }
        for (int i = 0; i < tilesDiff.size(); i++) {
            for (int j = 0; j < tilesDiff.size(); j++) {
                if (Math.abs(tilesDiff.get(i).getHouses() - tilesDiff.get(j).getHouses()) > 1){
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }

    //checks if there are 4 houses on each property in a colour set
    public boolean checkForFourHouses(String grp){
        ArrayList<Tile> tilesOfSet = new ArrayList<Tile>();
        for (int i = 0; i < bSize(); i++) {
            if (board.get(i).getGroup().equals(grp)){
                tilesOfSet.add(board.get(i));
            }
        }
        for (int i = 0; i < tilesOfSet.size(); i++) {
            if (tilesOfSet.get(i).getHouses() == 4){
                return true;
            }else{
                return false;
            }
        }

        return false;
    }

    public void addCardsOK(Card c){
        this.cardsOK.addLast(c);
    }

    public void addCardsPL(Card c){
        this.cardsPL.addLast(c);
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

    public void setPlayerCount(int player_Count){
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

    public boolean checkIfOwnProperty(int tileId){
        Player p = getPlayer(player_turn);
        if (getTile(p.getPl_pos()).getOwnedBy() == p.getPlayer_id()){
            return true;
        }else {
            return false;
        }
    }
    //lets players mortgage a property or downgrade it by selling a house or hotel
    //check if tile has a hotel
    //check if tile set has 1 house diff
    //check if tile has a house
    //if no house mortgage
    public boolean mortgage(int tileI) {
        Player p = getPlayer(player_turn);
        Tile t = getTile(tileI);
        //checks if the tile is station or util
        if (t.getGroup().equals("Station") || t.getGroup().equals("Utilities")) {
            return false;
        } else {
            //check if player owns tile
            if (t.getOwnedBy() == p.getPlayer_id()) {
                //checks if the tile has a hotel
                if (t.getHotels() > 0) {
                    p.setPl_cash(p.getPl_cash() + ((t.getHousePrice() * 5) / 2));
                    t.decrHotels();
                    return true;
                } else {
                    //checks if there is a more than 1 house difference on tile set and if the tile
                    // has houses
                    if (!checkForTwoHouseDiff(t.getGroup()) && t.getHouses() > 0) {
                        p.setPl_cash(p.getPl_cash() + (t.getHousePrice() / 2));
                        t.decrHouses();
                        return true;
                    } else {
                        //checks if the property has been mortgaged if not then it mortgages it so players
                        // don't collect rent from it
                        if (!t.getMortgaged()){
                            p.setPl_cash(p.getPl_cash() + (t.getPrice()/2));
                            t.flipMortgaged();
                            return true;
                        }else {
                            return false;
                        }
                    }
                }
            } else {
                return false;
            }
        }

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


    //auction
    //should let all players decide if they want to place a bit at an auction
    //players only add 50 to the current highest bid
    //if the player doesn't want to add to the bid they won't be asked if they want to add to it again
    //
    //at start add all players to auction list
    //if player doesn't want to buy a tile they get removed from the list so that they don't get asked again
    //

// adds all the players to the list of players to the auction


    public ArrayList<Player> getAuctionList(){
        return auctionList;
    }

    //buying tiles with auction
    public void auctionBuy (int t){
        // getTile(getPlayer(player_turn).getPl_pos());
        int cash = getPlayer(highestBidOld.getKey()).getPl_cash();
        int price = highestBidOld.getValue();
        int total = cash - price;
        if (total > 0) {
            getPlayer(highestBidOld.getKey()).setPl_cash(total);//yh
            getTile(t).setOwnedBy(highestBidOld.getKey()); //yh
            getPlayer(highestBidOld.getKey()).addOwns(getTile(t));
            System.out.println("cash: " + getPlayer(highestBidOld.getKey()).getPl_cash());
            System.out.println("tile is owned by: " + getTile(t).getOwnedBy());
        }
    }


    public void auction(){
        highestBidOld = new Pair<>(0,0);
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
        System.out.println(highestBidOld);
        //getting the player pos to get current tile will need to change later to have auctions for bankruptcy
        auctionBuy(getPlayer(player_turn).getPl_pos());
    }

    public void bid(int player_bid, int player_id){
        if (player_bid > highestBidOld.getValue()){
            highestBidOld = new Pair<>(player_id,player_bid);
        }
    }

    public void goJail(){
        Player p = players.get(player_turn);
        p.setInJail();
        p.setPl_pos(9);
    }

    public int movePlayer(int roll){


        if(this.rl_double == 3 ){
            this.isDouble = false;
            this.rl_double = 0;
            goJail();
            System.out.println("player is in jail");
            System.out.println("\n\n");
            return 10;
        }
        else {
            for (int i = 1; i < roll; i++) {
                getPlayer(player_turn).incrPos();
                System.out.println(getPlayer(player_turn).getPl_pos());
            }
            System.out.println("******" + getPlayer(player_turn).getPl_pos()+"******\n\n");
            return getPlayer(player_turn).getPl_pos();
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

    public ArrayList<Player> getPlayers(){return players;}

    public int getPlayerCount(){
        return PLAYER_COUNT;
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
