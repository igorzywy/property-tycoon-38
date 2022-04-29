package game;

import javafx.util.Pair;

import java.util.*;

/**
 * Board is where all the components of the game like cards and players and tiles will be stored
 */
public class Board {


    private int TILE_COUNT = 40;
    private int playerCount = 5;
    private int player_turn = 0;
    private int rl_double = 0 ;
    private boolean isDouble = false;
    private ArrayList<Tile> board = null;
    private ArrayList<Player> players = null;
    //opportunity knocks cards
    private ArrayList<Card> cardsOK = null;
    //pot luck cards
    private ArrayList<Card> cardsPL = null;
    private int freeParkingSpace = 0;
    private Pair<Integer,Integer> highestBidOld = new Pair<>(0,0);

    private ArrayList<Player> auctionList = new ArrayList<Player>();
    private int highestBid = 0;
    private Player highestBidPlayer = null;


    /**
     * This method resets the highest bid and the player with the highest bid
     */
    public void resetHighestBid(){
        this.highestBid = 0;
        this.highestBidPlayer = null;
    }

    /**
     * Gets the player with the highest bid
     * @return Player This returns the highest bid player
     */
    public Player getHighestBidPlayer(){
        return this.highestBidPlayer;
    }

    public int numOfBidders(){
        return auctionList.size();
    }

    /**
     * Gets the highest bid amount
     * @return int Returns the amount in the highest bid
     */
    public int getHighestBid(){
        return this.highestBid;
    }

    //for when the player doesn't want to bid for the current property

    /**
     * This method removes the player who is currently bidding if they decided to
     * not place a bid
     */
    public void auctionNo(){
        this.auctionList.remove(0);
    }

    /**
     * Adds the amount the player currently bidding has selected
     * @param amount the amount to be added to the highest bid
     */
    public void auctionBid(int amount){
        Player ap = auctionList.get(0);
        this.highestBid += amount;
        this.highestBidPlayer = ap;
    }

    //assigns the tile owned by to the player that won the auctions id and subtracts the money from the players account

    /**
     * Assigns the player the tile that they won in the auction and adds it to their owned tiles.
     * Also changes the tiles owned by to be the same as the playerID of the winner of the auction. If the list of
     * players that were in the auction was 0 or all the players left and no one placed a bid then the bank still holds
     * that property.
     * @param tileI This is the index of the tile that was won at auction
     */
    public void auctionWinner(int tileI){
        if (highestBidPlayer == null){
            getTile(tileI).setOwnedBy(0);
        }else {
            getTile(tileI).setOwnedBy(highestBidPlayer.getPlayer_id());
            highestBidPlayer.addOwns(getTile(tileI));
            highestBidPlayer.setPl_cash(highestBidPlayer.getPl_cash() - highestBid);
        }

    }

    /**
     * Gets the current player that is given the option to bid or stop bidding
     * @return Player The player at the front of the queue for the auction
     */
    public Player getCurrentBiddingPlayer(){
        return auctionList.get(0);
    }

    /**
     * Changes the current player to be given the option to place a bid or not
     */
    public void incrIndexOfCurrentBidder(){
        Player temp = auctionList.get(0);
        auctionList.remove(0);
        auctionList.add(temp);
    }

    /**
     * Clears the auction arraylist and adds all players that are not bankrupt to an arraylist
     */
    public void addAllPlayersToAuction(){

        auctionList.clear();
        auctionList.addAll(players);
    }

    //need to add a remove from current player auction turn

    //player,bid





    /*
    * Bankrupt calls the player to be removed from the current players arraylist and also resets all of the tiles
    *  that they own to be their default state
    *
     */

    /**
     * Removes the player from the current players arraylist and also resets all the tiles
     * that they own to be their default state
     * @param playerId The player that is going to be bankrupt
     */
    public void bankrupt(Player playerId){
        ArrayList<Tile> plOwned = playerId.getOwns();
        for (int j = 0; j < plOwned.size(); j++) {
            plOwned.get(j).setOwnedBy(0);
            plOwned.get(j).resetTile();
        }
        players.remove(playerId);
    }






    /**
     * The constructor for the Board class. This assigns all the tiles that are on the csv file to the board variable.
     * This adds new players to the arraylist of players.
     * This also adds the pot luck cards and the opportunity knocks cards to the arraylists of the different types
     * of cards.
     * Also sets the player count
     * @param p_count Used to allow the user to input how many players there will be in the game.
     */
    public Board(int p_count){

        //adding tiles to the board
        this.board = Tile.getXLSXData();
        //setting player count
        setPlayerCount(p_count);
        //adding players to board
        this.players = new ArrayList<Player>();
        for (int i = 0; i < playerCount; i++) {
            this.players.add(new Player());
        }
        Collections.shuffle(this.players);
        //adding pot luck cards to board
        this.cardsPL = Card.getXLSXDataPotLuck();
        //adding opportunity knocks cards to board
        this.cardsOK = Card.getXLSXDataOpportunityKnocks();

    }



    /**
     * This method checks if the player owns the set of a certain tile group
     * @param tileGroup The name of the tile group to be checked
     * @return boolean Returns true if the player owns the set and false if the player doesn't own the set
     */
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

    /**
     * Calculates the amount the player that landed on the tile will have to pay for a station. Doubles the amount
     * the station costs depending on the number of stations owned by the player that owns the station tile that
     * was landed on.
     * @return The rent that the player will have to pay for landing on a station.
     */
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

    /**
     * Calculates the amount the player that landed on the tile will have to pay for a utility. Multiplies the
     * number on the dice roll by 4 if the player owns one utility and 10 if they own two utilities.
     * @param diceRoll The amount the player rolled on the dice to be multiplied
     * @return int The amount the player will have to pay for the rent
     */
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

    /**
     * Makes the player pay rent and subtracts the amount they have to pay from their bankroll and also
     * gives that money to the player that owns that tile
     * @param rentAmount The amount that the player will have to pay
     */
    public void payRent(int rentAmount){
        Player p = players.get(player_turn);
        int ownedBy = getTile(p.getPl_pos()).getOwnedBy();
        Player pOwn = getPlayer(ownedBy-1);
        p.setPl_cash(p.getPl_cash() - rentAmount);
        pOwn.setPl_cash(pOwn.getPl_cash() + rentAmount);
    }

    /**
     * Gets the rent of a tile depending on how many houses and hotels they have
     * @return int The amount that the rent for the tile will be
     */
    public int calcRentOfTile(){
        Player p = players.get(player_turn);
        Tile t = getTile(p.getPl_pos());
        int noHouses = t.getHouses();
        int noHotels = t.getHotels();

        if (noHotels > 0){
            return t.getRentHotel();
        }else if (noHouses > 0){
            if (noHouses == 1){
                return t.get1HRent();
            }else if (noHouses == 2){
                return t.get2HRent();
            }else if (noHouses == 3){
                return t.get3HRent();
            }else if (noHouses == 4){
                return t.get4HRent();
            }

        }else{
            return t.getRentUnimp();
        }
        return t.getRentUnimp();

    }

    /**
     * The method adds the tile that they have bought to the arraylist of the player owned tiles.
     * Also subtracts the amount the tile costs from the players bankroll
     */
    public void buyingTile(){
        Player p = getPlayer(player_turn);
        getTile(p.getPl_pos()).setOwnedBy(p.getPlayer_id());
        p.addOwns(getTile(p.getPl_pos()));
        p.setPl_cash(p.getPl_cash() - getTile(p.getPl_pos()).getPrice());
    }

    /**
     * Method for when the card is a bank pay player card adds the amount the card says to the players bankroll
     * @param amount The amount that the player will receive
     */
    public void Cardbpp(int amount){
        Player p = getPlayer(player_turn);
        p.setPl_cash(p.getPl_cash() + amount);
    }

    /**
     * Method for when the card is a player pay player card. Since there is only 1 card of this type the players all
     * get deducted the amount on the card. It is then added to the player who got the card bankroll.
     * @param amount The amount that the player will receive
     */
    public void Cardppp(int amount){
        Player p = getPlayer(player_turn);
        for (int i = 0; i < getPlayerCount(); i++) {
            getPlayer(i).setPl_cash(getPlayer(i).getPl_cash() - amount);
        }
        p.setPl_cash(p.getPl_cash() + (getPlayerCount()*amount) + amount);
    }

    /**
     * Method for when the card is a player move card the players position is set to the amount on the card
     * @param amount The position the player will move to
     */
    public void Cardpm(int amount){
        Player p = getPlayer(player_turn);
        if (amount == 10){
            p.setInJail();
        }
        p.setPl_pos(amount);
    }

    /**
     * Method for when the card is a player pay bank card. This will deduct how much the player will pay to the bank
     * @param amount The amount the player will have to pay
     */
    public void Cardppb(int amount) {
        Player p = getPlayer(player_turn);
        p.setPl_cash(p.getPl_cash() - amount);
    }

    /**
     * Method for when the card is a player pay free card. The player will pay the specified amount to free parking
     * @param amount The amount the player will have to pay
     */
    public void Cardppf(int amount){
        Player p = getPlayer(player_turn);
        p.setPl_cash(p.getPl_cash() - amount);
        addPublicSpace(amount);
    }

    /**
     * Method for when the card is a player move x amount backwards card. This will move the player back a
     * specified amount of spaces
     * @param amount The amount of spaces the player will move back
     */
    public void CardpmxBack(int amount){
        Player p = getPlayer(player_turn);
        amount = amount * -1;
        for (int i = 0; i < amount; i++) {
            p.decresePos();
        }
    }

    /**
     * Method for when the card is a player move x amount forwards card. This will move the player forwards a
     * specified amount of spaces
     * @param amount The amount of spaces the player will move forwards
     */
    public void CardpmxForward(int amount){
        Player p = getPlayer(player_turn);
        for (int i = 0; i < amount; i++) {
            p.incrPos();
        }
    }


    /**
     * Method for when the card is a player pay repair card. This will get the number of hotels and houses that the
     * player owns and then get multiplied by the amount on the card for houses and the amount for on the card for
     * hotel prices
     * @param houseAmount The amount a repair will cost for 1 house
     * @param hotelAmount The amount a repair will cost for 1 hotel
     */
    public void Cardppr(int houseAmount, int hotelAmount){
        Player p = getPlayer(player_turn);
        p.setPl_cash(p.getPl_cash() - (p.getHousesOwned() * houseAmount) +
                p.getHotelsOwned() * hotelAmount);
    }


    /**
     * Method for when the card is a player move forward. They will also collect 200 if they pass go. Checks if the
     * player will pass go and adds 200 to their bankroll and sets their new position to the amount on the card
     * otherwise just sets the players position to the amount on the card.
     * @param amount The position that the player will be moved to.
     */
    public void Cardpmf(int amount){
        Player p = getPlayer(player_turn);
        if(amount==0){
            while(p.getPl_pos()!=0){
                p.incrPos();
            }
        } else{
            while(p.getPl_pos()!=amount){
                p.incrPos();
            }
        }
    }

    /**
     * Method for when the card is a get out of jail free card. Player will be given a get out of jail free card to use
     */
    public void Cardjfc(){
        Player p = getPlayer(player_turn);
        p.incrNoJailFreeCard();
    }


    /**
     * Gets the amount of tiles that there are in a certain group
     * @param grp The group that you want to get the amount of tiles for
     * @return int The number of tiles in a group
     */
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

    /**
     * Checks if there is a more than 1 house difference on a given group
     * @param grp The group to be checked
     * @return boolean True if there is a greater difference than 1 on a group false if there isn't.
     */
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

    /**
     * Checks if there are 4 houses on each property in a colour set.
     * @param grp The group to be checked.
     * @return boolean True if there are 4 houses false if otherwise.
     */
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

    /**
     * Adds a card to the arraylist of opportunity knocks cards
     * @param c The card to be added to the list
     */
    public void addCardsOK(Card c){
        this.cardsOK.add(c);
    }
    /**
     * Adds a card to the arraylist of pot luck cards
     * @param c The card to be added to the list
     */
    public void addCardsPL(Card c){
        this.cardsPL.add(c);
    }
    /**
     * Removes the first card in the list of pot luck cards
     */
    public void removeCardsPL(){
        this.cardsPL.remove(0);
    }
    /**
     * Removes the top card in the list of opportunity knocks cards
     */
    public void removeCardsOK(){
        this.cardsOK.remove(0);
    }
    /**
     * Increases the amount of money in free parking
     * @param amount The amount to be added to the free parking tile
     */
    public void addPublicSpace(int amount){
        this.freeParkingSpace += amount;
    }
    /**
     * Gets the amount stored in free parking
     * @return int The amount of money in free parking
     */
    public int getFreeParkingSpace(){
        return this.freeParkingSpace;
    }
    /**
     * Sets the amount of money stored in free parking overwrites the old amount
     * @param amount The amount that free parking will be set to
     */
    public void setFreeParkingSpace(int amount){
        this.freeParkingSpace = amount;
    }
    /**
     * Sets the player count for the start of the game so that the correct amount of players is added
     * @param player_Count The amount of players that the user specifies
     */
    public void setPlayerCount(int player_Count){
        playerCount = player_Count;
    }





    /**
     * Changes whose turn it is out of the list of players
     */
    public void incPlayer(){

        Player temp = players.get(0);
        players.remove(0);
        players.add(temp);
        player_turn = 0;
    }




    //lets players mortgage a property or downgrade it by selling a house or hotel
    //check if tile has a hotel
    //check if tile set has 1 house diff
    //check if tile has a house
    //if no house mortgage
    /**
     * Allows the player to downgrade a property and gain money. It will first remove hotels then houses if there is no
     * greater than one difference between the amount of houses on each set. Then the property will be
     * mortgaged.
     * @param tileI The index of the tile to be mortgaged
     * @return boolean True if the tile has been downgraded in any way. False if nothing has been downgraded.
     */
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


    /**
     * Checks if the current tile that the player is on can be purchased
     * @return boolean True if the tile can be purchased and another player doesn't own it
     * and False if otherwise
     */
    public boolean checkCanBeBought() {
        Tile t = getTile(getPlayer(player_turn).getPl_pos());

        if (t.getCanBeBought() == false || t.getOwnedBy() != 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks if a certain tile is owned by another player
     * @return boolean True if the tile is owned by another player and false if the tile isn't owned by another player
     */
    public boolean checkIsOwned(){
        Tile t = getTile(getPlayer(player_turn).getPl_pos());

        if (t.getOwnedBy() == 0) {
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

    /**
     * Gets the list of players that are currently in the auction
     * @return ArrayList Player The list of players involved with the auction
     */
    public ArrayList<Player> getAuctionList(){
        return auctionList;
    }


    /**
     * Subtracts 50 from the current players bankroll if they are in jail
     */
    public void pay50Jail(){
        Player p = getPlayer(player_turn);
        p.setPl_cash(p.getPl_cash() - 50);
        addPublicSpace(50);
        p.setNotInJail();
    }

    /**
     * Puts the current player in jail and sets them as in jail
     */
    public void goJail(){
        Player p = players.get(player_turn);
        p.setInJail();
        p.setPl_pos(10);
    }

    /**
     * Moves the player a set amount of tiles.
     * @param roll The amount of tiles to move the player
     * @return int The player position
     */
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

    /**
     * Rolls 2 dice at random between 1-6 and adds them together
     * @return int The sum of the 2 dice
     */
    public int rollDice(){
        Random rand = new Random();
        int dice = rand.nextInt(6);
        int dice2 = rand.nextInt(6);
        dice ++;
        dice2 ++;
        if(dice == dice2){
            this.rl_double++;

        }
        else{
            this.isDouble = false;
        }
        System.out.println("you rolled: \n " + dice + " and " + dice2);
        System.out.println("\n\n");

        return dice + dice2;
    }

    /**
     * Gets a certain tile from the Board
     * @param i The index of the tile
     * @return Tile The tile that is at index i
     */
    public Tile getTile(int i){
        return this.board.get(i);
    }

    /**
     * Gets a player at a certain index
     * @param i The index of the desired player
     * @return Player The player at a certain index
     */
    public Player getPlayer(int i){
        return this.players.get(i);
    }

    /**
     * Gets the arraylist of players currently in the game
     * @return ArrayList Player The arraylist of players that are in the game
     */
    public ArrayList<Player> getPlayers(){return players;}

    /**
     * The number of players at the start of the game
     * @return int The number of players at the start of the game
     */
    public int getPlayerCount(){
        return playerCount;
    }

    /**
     * Gets the index of the current players turn
     * @return int The index of the current players turn
     */
    public int getPlayerTurn(){
        player_turn = players.indexOf(players.get(0));
        return player_turn;
    }


    /**
     * Gets the number of tiles that is on the board
     * @return int The number of tiles on the board
     */
    public int bSize(){
        return this.board.size();
    }

    /**
     * Gets the arraylist of the opportunity knocks cards
     * @return ArrayList Card The arraylist of opportunity knocks cards
     */
    public ArrayList<Card> getCardsOK(){
        return cardsOK;   }
    /**
     * Gets the arraylist of the pot luck cards
     * @return ArrayList Card The arraylist of pot luck cards
     */
    public ArrayList<Card> getCardsPL(){
        return cardsPL;   }

}
