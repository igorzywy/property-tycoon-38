package game;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.ArrayList;

public class HelloController {

    //game tiles-panes
    @FXML FlowPane pane1;
    @FXML FlowPane pane2;
    @FXML FlowPane pane3;
    @FXML FlowPane pane4;
    @FXML FlowPane pane5;
    @FXML FlowPane pane6;
    @FXML FlowPane pane7;
    @FXML FlowPane pane8;
    @FXML FlowPane pane9;
    @FXML FlowPane pane10;
    @FXML FlowPane pane11;
    @FXML FlowPane pane12;
    @FXML FlowPane pane13;
    @FXML FlowPane pane14;
    @FXML FlowPane pane15;
    @FXML FlowPane pane16;
    @FXML FlowPane pane17;
    @FXML FlowPane pane18;
    @FXML FlowPane pane19;
    @FXML FlowPane pane20;
    @FXML FlowPane pane21;
    @FXML FlowPane pane22;
    @FXML FlowPane pane23;
    @FXML FlowPane pane24;
    @FXML FlowPane pane25;
    @FXML FlowPane pane26;
    @FXML FlowPane pane27;
    @FXML FlowPane pane28;
    @FXML FlowPane pane29;
    @FXML FlowPane pane30;
    @FXML FlowPane pane31;
    @FXML FlowPane pane32;
    @FXML FlowPane pane33;
    @FXML FlowPane pane34;
    @FXML FlowPane pane35;
    @FXML FlowPane pane36;
    @FXML FlowPane pane37;
    @FXML FlowPane pane38;
    @FXML FlowPane pane39;
    @FXML FlowPane pane40;

    FlowPane[] tiles = new FlowPane[40];
    Shape[] pawnsStorage = new Shape[5];
    Shape[] pawns;
    boolean mortgOption = false;
    boolean upgradeOption = false;
    boolean bidOption = false;
    int bid = 0;
    boolean isAuction = false;
    int currBidNo = 0;
    private Board b;

    @FXML public TextArea gameText;
    @FXML public TextField playerNum;
    @FXML public TextField nums;
    @FXML public Button newGameB;
    @FXML public Button rollB;
    @FXML public Button mortB;
    @FXML public Button yesB;
    @FXML public Button noB;
    @FXML public Button lockB;
    //button to buy or not properties after roll
    @FXML public Button buyBYes;
    @FXML public Button buyBNo;
    //End Mortgage Button
    @FXML public Button endMorgB;
    //Button to end turn
    @FXML public Button nextTurnB;
    //button to upgrade
    @FXML public Button upgradeB;
    //button to leave bid
    @FXML public Button leaveBidB;
    //buttons to place bids
    @FXML public Button plusTenB;
    @FXML public Button plusFiftyB;
    @FXML public Button plusHundredB;
    //buttons for jail
    @FXML public Button pay50B;
    @FXML public Button useJfcB;
    //button to go bankrupt
    @FXML public Button goBankruptB;

    Player p;

    Circle pawn1 = new Circle(20,Color.BLUE);
    Rectangle pawn2 = new Rectangle(15,15,Color.RED);
    Ellipse pawn3 = new Ellipse(20,10);

    Polygon pawn4 = new Polygon(
            0.0, 0.0,
            10.0, 20.0,
            20.0, 10.0
    );
    Polygon pawn5 = new Polygon(
            30.0, 20.0,
            30.0, 50.0,
            0,0
    );


    //Setting objects up


    /**
     * Method that initialises at the start of the application.
     * Sets up tiles, players and buttons to their initial state
     */
    @FXML
    protected void initialize(){
        System.out.println("Initialising");

        //setting up tiles array
        //manually because java doesn't let me cast Field objects to FlowPane
        tiles[0]=pane1;tiles[1]=pane2;tiles[2]=pane3;tiles[3]=pane4;tiles[4]=pane5;tiles[5]=pane6;tiles[6]=pane7;
        tiles[7]=pane8;tiles[8]=pane9;tiles[9]=pane10;tiles[10]=pane11;tiles[11]=pane12;tiles[12]=pane13;tiles[13]=pane14;
        tiles[14]=pane15;tiles[15]=pane16;tiles[16]=pane17;tiles[17]=pane18;tiles[18]=pane19;tiles[19]=pane20;tiles[20]=pane21;
        tiles[21]=pane22;tiles[22]=pane23;tiles[23]=pane24;tiles[24]=pane25;tiles[25]=pane26;tiles[26]=pane27;tiles[27]=pane28;
        tiles[28]=pane29;tiles[29]=pane30;tiles[30]=pane31;tiles[31]=pane32;tiles[32]=pane33;tiles[33]=pane34;tiles[34]=pane35;
        tiles[35]=pane36;tiles[36]=pane37;tiles[37]=pane38;tiles[38]=pane39;tiles[39]=pane40;

        //assign all pawns to storage array
        pawnsStorage[0]=pawn1;pawnsStorage[1]=pawn2;pawnsStorage[2]=pawn3;pawnsStorage[3]=pawn4;pawnsStorage[4]=pawn5;
        pawnsStorage[2].setFill(Color.BROWN);
        pawnsStorage[3].setFill(Color.DARKGOLDENROD);

        //set opacity of all tiles
        for(int i = 0; i < tiles.length; i++) {
            tiles[i].setStyle("-fx-background-color: rgba(255, 255, 255, 0.2);");
        }


        mortB.setVisible(false);
        lockB.setVisible(false);
        buyBYes.setVisible(false);
        buyBNo.setVisible(false);
        nums.setVisible(false);
        upgradeB.setVisible(false);
        endMorgB.setVisible(false);
        nextTurnB.setVisible(false);
        leaveBidB.setVisible(false);
        plusTenB.setVisible(false);
        plusFiftyB.setVisible(false);
        plusHundredB.setVisible(false);
        pay50B.setVisible(false);
        useJfcB.setVisible(false);
        goBankruptB.setVisible(false);
    }



    @FXML
    protected boolean checkIfTax(){
        if(b.getTile(p.getPl_pos()).getGroup().equals("Tax")){
            return true;
        }else{return false;}




    }

    @FXML
    protected void taxation(){
        if(p.getPl_cash() >= b.getTile(p.getPl_pos()).getPrice()) {
            gameText.appendText("\n\nYou payed tax: " + b.getTile(p.getPl_pos()).getPrice());
            p.setPl_cash(p.getPl_cash() - b.getTile(p.getPl_pos()).getPrice());
            b.addPublicSpace(b.getTile(p.getPl_pos()).getPrice());
            afterBuy();
        }else {
            p.setPl_cash(p.getPl_cash() - b.getTile(p.getPl_pos()).getPrice());
            b.addPublicSpace(b.getTile(p.getPl_pos()).getPrice());
            cannotAffordRent();


        }
    }



    @FXML
    protected void getFPS(){
        gameText.appendText("\n\nPlayer " + p.getPlayer_id() + " has receive the Free Parking Space money (" + b.getFreeParkingSpace() + ")");
        p.addPl_cash(b.getFreeParkingSpace());
        b.setFreeParkingSpace(0);
        afterBuy();
    }


    @FXML
    protected boolean checkIfFPS(){
        if(b.getTile(p.getPl_pos()).getGroup().equals("Free")){
            return true;
        }else{
            return false;
        }
    }

    @FXML
    protected boolean checkifGoJail(){
        if(b.getTile(p.getPl_pos()).getGroup().equals("GoJail")){
            return true;
        }else{
            return false;
        }
    }
    //checking if the tile is a card tile pot luck
    @FXML
    protected boolean checkIfCardPL(){
        if (b.getTile(p.getPl_pos()).getGroup().equals("CardPL")){
            return true;
        }else{
            return false;
        }
    }

    @FXML
    protected void cardPL(){
        Card c = b.getCardsPL().get(0);
        b.removeCardsPL();
        if (c.getType() == CardType.bpp){
            gameText.appendText("\n\n"+p.getPlayer_id() + " receives: " + c.getAmount() + " from the bank!");
            afterBuy();
            b.Cardbpp(c.getAmount());
        }else if (c.getType() == CardType.ppp){
            //need to check if the player has enough money else let them mortgage or bankrupt
            gameText.appendText("\n\n"+p.getPlayer_id() + " receives " + c.getAmount() +
                    " from each player since it's their birthday!");
            b.Cardppp(c.getAmount());
            afterBuy();
            // get all players and sub 10 from all add players.size()*10 + 10 to the current player
        }else if (c.getType() == CardType.pm){
            gameText.appendText("\n\n"+p.getPlayer_id() + " moves to tile " + c.getAmount());
            b.Cardpm(c.getAmount());
            updatePos();
            afterBuy();

        }else if (c.getType() == CardType.ppb){
            //need to check if the player has enough money else let them mortgage or bankrupt
            gameText.appendText("\n\n"+p.getPlayer_id() + " pays the bank " + c.getAmount());
            if (p.getPl_cash() < c.getAmount()){
                b.Cardppb(c.getAmount());
                cannotAffordRent();
            }else {
                b.Cardppb(c.getAmount());
                afterBuy();
            }


        }else if (c.getType() == CardType.ppf){
            //need to check if the player has enough money else let them mortgage or bankrupt
            gameText.appendText("\n\n"+p.getPlayer_id() + " puts " + c.getAmount() + " into free parking!");
            if (p.getPl_cash() < c.getAmount()) {
                b.Cardppf(c.getAmount());
                cannotAffordRent();
            }else {
                b.Cardppf(c.getAmount());
                afterBuy();
            }

        }else if (c.getType() == CardType.pmx){
            if (c.getAmount() < 0){
                gameText.appendText("\n\n"+p.getPlayer_id() + " moves back " + c.getAmount());
                b.CardpmxBack(c.getAmount());
                updatePos();
                afterBuy();
            }else if(c.getAmount() > 0){
                gameText.appendText("\n\n"+p.getPlayer_id() + " moves forwards " + c.getAmount());
                b.CardpmxForward(c.getAmount());
                updatePos();
                afterBuy();
            }
        }else if (c.getType() == CardType.ppr){
            //need to check if the player has enough money else let them mortgage or bankrupt
            gameText.appendText("\n\n"+p.getPlayer_id() + " pays for repairs " +
                    c.getAmount() + " per house " + c.getHotelPrice() + " per hotel ");
            b.Cardppr(c.getAmount(), c.getHotelPrice());
            afterBuy();

        }else if (c.getType() == CardType.pmf){
            gameText.appendText("\n\n"+p.getPlayer_id() + " advances to " + b.getTile(c.getAmount()).getTileName());
            b.Cardpmf(c.getAmount());
            updatePos();
            afterBuy();
        }else if(c.getType() == CardType.jfc){
            gameText.appendText("\n\n"+p.getPlayer_id() + " gets a get out of jail free card!\n");
            b.Cardjfc();
            afterBuy();
        }
        b.addCardsPL(c);
    }

    //opportunity knocks
    @FXML
    protected boolean checkIfCardOK(){
        if (b.getTile(p.getPl_pos()).getGroup().equals("CardOK")){
            return true;
        }else{
            return false;
        }
    }
    @FXML protected void cardOK(){
        Card c = b.getCardsOK().get(0);
        b.removeCardsOK();
        if (c.getType() == CardType.bpp){
            gameText.appendText("\n\n"+p.getPlayer_id() + " receives: " + c.getAmount() + " from the bank!");
            afterBuy();
            b.Cardbpp(c.getAmount());
        }else if (c.getType() == CardType.ppp){
            //need to check if the player has enough money else let them mortgage or bankrupt
            gameText.appendText("\n\n"+p.getPlayer_id() + " receives " + c.getAmount() +
                    " from each player since it's their birthday!");
            b.Cardppp(c.getAmount());
            afterBuy();
            // get all players and sub 10 from all add players.size()*10 + 10 to the current player
        }else if (c.getType() == CardType.pm){
            gameText.appendText("\n\n"+p.getPlayer_id() + " moves to tile " + c.getAmount());
            b.Cardpm(c.getAmount());
            updatePos();
            afterBuy();

        }else if (c.getType() == CardType.ppb){
            //need to check if the player has enough money else let them mortgage or bankrupt
            gameText.appendText("\n\n"+p.getPlayer_id() + " pays the bank " + c.getAmount());
            if (p.getPl_cash() < c.getAmount()){
                b.Cardppb(c.getAmount());
                cannotAffordRent();
            }else {
                b.Cardppb(c.getAmount());
                afterBuy();
            }


        }else if (c.getType() == CardType.ppf){
            //need to check if the player has enough money else let them mortgage or bankrupt
            gameText.appendText("\n\n"+p.getPlayer_id() + " puts " + c.getAmount() + " into free parking!");
            if (p.getPl_cash() < c.getAmount()) {
                b.Cardppf(c.getAmount());
                cannotAffordRent();
            }else {
                b.Cardppf(c.getAmount());
                afterBuy();
            }

        }else if (c.getType() == CardType.pmx){
            if (c.getAmount() < 0){
                gameText.appendText("\n\n"+p.getPlayer_id() + " moves back " + c.getAmount());
                b.CardpmxBack(c.getAmount());
                updatePos();
                afterBuy();
            }else if(c.getAmount() > 0){
                gameText.appendText("\n\n"+p.getPlayer_id() + " moves forwards " + c.getAmount());
                b.CardpmxForward(c.getAmount());
                updatePos();
                afterBuy();
            }
        }else if (c.getType() == CardType.ppr){
            //need to check if the player has enough money else let them mortgage or bankrupt
            gameText.appendText("\n\n"+p.getPlayer_id() + " pays for repairs " +
                    c.getAmount() + " per house " + c.getHotelPrice() + " per hotel ");
            b.Cardppr(c.getAmount(), c.getHotelPrice());
            afterBuy();

        }else if (c.getType() == CardType.pmf){
            gameText.appendText("\n\n"+p.getPlayer_id() + " advances to " + b.getTile(c.getAmount()).getTileName());
            b.Cardpmf(c.getAmount());
            updatePos();
            afterBuy();
        }else if(c.getType() == CardType.jfc){
            gameText.appendText("\n\n"+p.getPlayer_id() + " gets a get out of jail free card!\n");
            b.Cardjfc();
            afterBuy();
        }
        b.addCardsOK(c);
    }

    @FXML
    protected void mortg(){
        mortgOption = true;
        rollB.setDisable(false);
        nums.setVisible(true);
    }

    /**
     * Calls bankrupt removing the player from the list of players and go straight to the next players turn.
     */
    @FXML
    protected void bankrupt(){
        gameText.appendText("\nPlayer " + p.getPlayer_id() + " is bankrupt");
        b.bankrupt(p);
        goBankruptB.setVisible(false);
        p = b.getPlayer(b.getPlayerTurn());
        rollB.setVisible(true);
        buyBYes.setVisible(false);
        buyBNo.setVisible(false);
        mortB.setVisible(false);
        upgradeB.setVisible(false);
        nextTurnB.setVisible(false);
        if (b.getPlayers().size() == 1) {
            gameText.appendText("\nPlayer " + p.getPlayer_id() + " is the WINNER!!!");
            rollB.setVisible(false);
            buyBYes.setVisible(false);
            buyBNo.setVisible(false);
            mortB.setVisible(false);
            upgradeB.setVisible(false);
            nextTurnB.setVisible(false);
        }


    }
    // checking if the player is in jail and if they are just visiting
    @FXML
    protected boolean checkIfJail(){
        if ((b.getTile(p.getPl_pos()).getGroup().equals("Jail"))){
            return true;
        }else{
            return false;
        }
    }
    @FXML
    protected boolean checkIfStation() {
        if (b.getTile(p.getPl_pos()).getGroup().equals("Station")){
            return true;
        }else {
            return false;
        }
    }

    @FXML
    protected boolean checkIfUtility() {
        if (b.getTile(p.getPl_pos()).getGroup().equals("Utilities")){
            return true;
        }else {
            return false;
        }
    }
    @FXML
    protected boolean checkIfJustVisit(){
        if (!p.getInJail()){
            return true;
        }else{
            return false;
        }
    }
    @FXML
    protected void updatePos(){
        tiles[b.getPlayer(b.getPlayerTurn()).getPl_pos()].getChildren().add(pawns[b.getPlayer(b.getPlayerTurn())
                .getPlayer_id()-1]);
    }

    @FXML
    protected void justVisiting(){
        gameText.appendText("\n\n"+p.getPlayer_id() + " is just visiting!");
        afterBuy();
    }

    //buying a house on a property
    //button press to upgrade
    //all houses are then displayed that they own
    //they pick a house that they own
    //house can only be bought if they own the set
    //or if there isn't more than 1 difference in amount of houses between all the properties
    //can only get a hotel if they have 4 houses on each property in a colour group
    @FXML
    protected void upgradeTile(int tileI){
        if (b.getTile(tileI).getGroup().equals("Station") || b.getTile(tileI).getGroup().equals("Utilities")){
            gameText.appendText("\n\nYou cannot upgrade a " + b.getTile(tileI).getGroup());
        }else {
            if (p.getPl_cash() >= b.getTile(tileI).getHousePrice()){
                if (b.getTile(tileI).getOwnedBy() == p.getPlayer_id()){
                    if (b.playerOwnSet(b.getTile(tileI).getGroup())){
                        if (!b.checkForTwoHouseDiff(b.getTile(tileI).getGroup())){
                            if (!b.checkForFourHouses(b.getTile(tileI).getGroup())) {
                                p.setPl_cash(p.getPl_cash() - b.getTile(tileI).getHousePrice());
                                b.getTile(tileI).incrHouses();
                                p.incrHousesOwned();
                                gameText.appendText("\n\n"+p.getPlayer_id() + " has bought a house on " +
                                        b.getTile(tileI).getTileName());
                            }else if (b.getTile(tileI).getHotels() >= 1){
                                gameText.appendText("\n\n"+b.getTile(tileI).getTileName() + " already has a hotel!");
                            }else if (p.getPl_cash() < (b.getTile(tileI).getHousePrice()*5)){
                                gameText.appendText("\n\nYou cannot afford a hotel! " +
                                        (b.getTile(tileI).getHousePrice()*5) + " is needed");
                            }else{
                                p.setPl_cash(p.getPl_cash() - (b.getTile(tileI).getHousePrice()*5));
                                b.getTile(tileI).incrHotels();
                                p.incrHotelsOwned();
                                gameText.appendText("\n\n"+p.getPlayer_id() + " has bought a hotel on " +
                                        b.getTile(tileI).getTileName());
                            }
                        }else{
                            gameText.appendText("\n\nYou have more than 1 house difference between your properties");
                        }
                    }else{
                        gameText.appendText("\n\nYou don't own the set");
                    }
                }else{
                    gameText.appendText("\n\nYou don't own that tile");
                }
            }else {
                gameText.appendText("\n\nYou don't have enough money to buy a house! " +
                        b.getTile(tileI).getHousePrice() + " is needed. You have " + p.getPl_cash());
            }
        }
    }



    @FXML
    protected void endTurn(){
        b.incPlayer();
        p = b.getPlayer(b.getPlayerTurn());
        rollB.setVisible(true);
        buyBYes.setVisible(false);
        buyBNo.setVisible(false);
        mortB.setVisible(false);
        upgradeB.setVisible(false);
        nextTurnB.setVisible(false);
        goBankruptB.setVisible(false);
    }

    @FXML
    protected void addBuilding(int option, int tileNo){
        if(option == 1){
            tiles[tileNo].setStyle("-fx-background-color: rgba(255, 255, 255, 0.2);");
        }else if(option == 2){
            tiles[tileNo].setStyle("-fx-background-color: rgba(255, 0, 255, 0.2);");
        }
    }

    @FXML
    protected void doYouWantToBuy(){
        gameText.appendText("\n\nDo you want to buy " + b.getTile(p.getPl_pos()).getTileName() + "?\nFor " +
                b.getTile(p.getPl_pos()).getPrice());
        buyBYes.setVisible(true);
        buyBNo.setVisible(true);
        rollB.setVisible(false);
        mortB.setVisible(false);
        goBankruptB.setVisible(false);
    }

    /**
     * After the player has made a decision on whether to buy a tile mortgage, upgrade, next turn will be
     * visible buttons and the roll button will be disabled and not visible
     */
    @FXML protected void afterBuy(){
        mortB.setVisible(true);
        upgradeB.setVisible(true);
        nextTurnB.setVisible(true);
        rollB.setVisible(false);
        goBankruptB.setVisible(true);
    }

    @FXML
    protected void cannotAffordRent(){
        gameText.appendText("\n\nyou cannot afford the rent/tax");
        //enable bankruptcy button and mortgage button
        gameText.appendText("\nYou need to mortgage " + Math.abs(p.getPl_cash()));
        mortB.setVisible(true);
        goBankruptB.setVisible(true);

        //add a bankruptcy button
    }

    /*
     * auction starts
     * add all players to the list of players for the auction
     *
     * pass the tile that we are putting on auction to auction method in this class this makes it easier to pass
     *   tiles to auction that the player doesn't current stand on for instance when a player goes bankrupt
     *
     * Show buttons no, 10, 50, 100 - buttons will call auctionBid(int amount) to increase the current highest bid
     *   no button will remove player from the list, so they won't be asked to bid again the method for this
     *   takes the index of the current player in that list
     *
     * start with the first player in the list and go to the last player we keep calling auction or not change the
     *   scene of the gui until the array of auctionList isn't 1 and at that point we assign the last player in the
     *   list to be the winner of the auction and assign them the ownership of the tile
     *
     * after they press a button we increment the index of current bidder but before that we check if the player has
     *   enough money if they don't have enough money then we don't increment the player, and we make them either
     *   choose a lower amount to bid or they can only press no
     *
     */
    private int auctionTileI = 0;
    @FXML
    protected void auction(int tileI){
        b.resetHighestBid();
        auctionTileI = tileI;
        gameText.appendText("\n\nAuction for tile " + b.getTile(auctionTileI).getTileName() + "\nOriginal value " +
                b.getTile(auctionTileI).getPrice());
        b.addAllPlayersToAuction();
        //display buttons for no and bid amounts each button should pass a different bid amount to bid in this class

    }

    //for bidding pass amount depending on what button was pressed if they cannot afford the bid then they have to
    //press something else
    @FXML
    protected void bid(int amount){
        if (b.getCurrentBiddingPlayer().getPl_cash() >= (b.getHighestBid() + amount)){
            b.auctionBid(amount);
            gameText.appendText("\n\n\n" +b.getCurrentBiddingPlayer().getPlayer_id() + " has bid £" + amount + " the current " +
                    "highest bid is " + b.getHighestBid());
            b.incrIndexOfCurrentBidder();
        }else{
            gameText.appendText("\n\nYou cannot afford to bid £" + amount);
        }
    }

    //for when the player doesn't want to place a bid i don't think we need to incr the index of current bidder
    //since removing from the array all elements get shifted to the left by 1
    @FXML protected void bidNo(){
        gameText.appendText("\nPlayer " + b.getCurrentBiddingPlayer().getPlayer_id() + " has left the auction");
        b.auctionNo();

        //b.incrIndexOfCurrentBidder();

        //checking if only 1 player left in the auctionList of players
        if (b.getAuctionList().size() == 1){
            b.auctionWinner(auctionTileI);

            if (b.getHighestBidPlayer() == null){
                gameText.appendText("\n\nNo one placed a bid so the bank still owns the tile!");
            }else {
                gameText.appendText("\n\nWinner of auction - player " + b.getHighestBidPlayer().getPlayer_id());
            }
            plusTenB.setVisible(false);
            plusFiftyB.setVisible(false);
            plusHundredB.setVisible(false);
            leaveBidB.setVisible(false);
            rollB.setVisible(false);
            mortB.setVisible(true);
            upgradeB.setVisible(true);
            nextTurnB.setVisible(true);
            goBankruptB.setVisible(true);

        }

    }
    @FXML
    protected  void checkIfPassGo(int lap){
        if (p.getLap()==lap+1){
            gameText.appendText("\n\nYou have been given £200");
        }
    }
    @FXML
    protected void rentMsg(int rentAmount){
        gameText.appendText("\nPlayer " + p.getPlayer_id() + " has paid " + rentAmount +
                " to Player " + b.getTile(p.getPl_pos()).getOwnedBy());


    }

    @FXML
    protected void payJail50(){
        gameText.appendText("\nPlayer " + p.getPlayer_id() + " has paid 50 to get out of jail!");
        b.pay50Jail();
        mortB.setVisible(false);
        upgradeB.setVisible(false);
        rollB.setVisible(false);
        nextTurnB.setVisible(true);
        useJfcB.setVisible(false);
        pay50B.setVisible(false);
    }

    @FXML
    protected void jailJFC(){
        gameText.appendText("\nPlayer " + p.getPlayer_id() + " has used a get out of jail free card!");
        p.useJFC();
        mortB.setVisible(false);
        upgradeB.setVisible(false);
        rollB.setVisible(false);
        nextTurnB.setVisible(true);
        useJfcB.setVisible(false);
        pay50B.setVisible(false);
    }

    //New Game button is pressed. We create a new Board object and set things to default values
    @FXML
    protected void NewGame() throws InterruptedException {

        if(Integer.parseInt(playerNum.getText()) >0 && Integer.parseInt(playerNum.getText())<6){
            newGameB.setVisible(false);
            b = new Board(Integer.parseInt(playerNum.getText()));
            System.out.println(b.bSize());
            rollB.setVisible(true);
            mortB.setVisible(false);
            lockB.setVisible(false);
            isAuction = false;
            gameText.appendText("\nOrder of players");
            for (int i = 0; i < b.getPlayerCount(); i++) {
                gameText.appendText("\n" + b.getPlayer(i).getPlayer_id());
            }



            mortgOption = false;
            pawns = new Shape[b.getPlayerCount()];
            //setting up array of pawns
            for(int i = 0; i < b.getPlayerCount(); i++){
                pawns[i] = pawnsStorage[i];
            }


            //setting up starting position of all pawns
            tiles[0].getChildren().addAll(pawns);


            p = b.getPlayer(b.getPlayerTurn());

            buyBYes.setVisible(false);

            //Rolls the dice and moves the player
            rollB.setOnAction(e ->{


                if (p.getInJail()){
                    pay50B.setVisible(true);
                    goBankruptB.setVisible(true);
                    if (p.getNoJailFreeCard()>0){
                        //show get out of jail free card button
                        useJfcB.setVisible(true);
                    }
                }else{
                    int lap = p.getLap();
                    int diceRollAmount = b.rollDice();
                    int move = b.movePlayer(diceRollAmount);
                    diceRollAmount = diceRollAmount-1;
                    gameText.appendText("\nYou rolled a " + diceRollAmount);
                    diceRollAmount = diceRollAmount+1;
                    updatePos();
                    gameText.appendText("\n\nPlayer " +p.getPlayer_id() + "\nMoney " + p.getPl_cash() + "\nPosition " +
                            p.getPl_pos());
                    rollB.setVisible(false);
                    checkIfPassGo(lap);
                    //check if tax,cardPl,cardOK,go,freepark,jail,gojail,util,station
                    if (checkIfTax()){
                        taxation();

                    }else if(checkIfCardPL()){

                        cardPL();

                    }else if (checkIfCardOK()){

                        cardOK();


                    }else if(checkIfFPS()){
                        getFPS();
                    }else if(checkifGoJail()){
                        b.goJail();
                        nextTurnB.setVisible(true);
                        gameText.appendText("\nPlayer " + p.getPlayer_id() + " is in jail!");
                    }else if (checkIfJail()){
                        if (checkIfJustVisit()){
                            justVisiting();
                        }
                        nextTurnB.setVisible(true);

                    }else if (checkIfStation()){
                        if (b.checkCanBeBought()){
                            doYouWantToBuy();
                        }else{
                            int rentAmount = b.getStationRentAmount();
                            if (p.getPl_cash() < rentAmount){
                                b.payRent(rentAmount);
                                rentMsg(rentAmount);
                                cannotAffordRent();
                            }else{
                                b.payRent(rentAmount);
                                rentMsg(rentAmount);
                                afterBuy();
                            }
                        }

                    }else if (checkIfUtility()) {
                        if (b.checkCanBeBought()){
                            doYouWantToBuy();
                        }else{
                            int rentAmount = b.getUtilRentAmount(diceRollAmount);
                            if (p.getPl_cash() < rentAmount){
                                b.payRent(rentAmount);
                                rentMsg(rentAmount);
                                cannotAffordRent();
                            }else{
                                b.payRent(rentAmount);
                                rentMsg(rentAmount);
                                afterBuy();
                            }
                        }
                    }
                    else{
                        if (b.checkCanBeBought()){
                            doYouWantToBuy();
                        }else if (b.checkIsOwned()){
                            int rentAmount = b.calcRentOfTile();
                            if (p.getPl_cash() < rentAmount){
                                b.payRent(rentAmount);
                                rentMsg(rentAmount);
                                cannotAffordRent();
                            }else{
                                b.payRent(rentAmount);
                                rentMsg(rentAmount);
                                afterBuy();
                            }

                        }

                    }

                }

            });

            buyBYes.setOnAction(e ->{
                if(b.getPlayer(b.getPlayerTurn()).getPl_cash() >= b.getTile(p.getPl_pos()).getPrice()){
                    b.buyingTile();
                    gameText.appendText("\n\nProperty has be bought.\nPlayer " + p.getPlayer_id() + " has " +
                            p.getPl_cash());
                    buyBYes.setVisible(false);
                    buyBNo.setVisible(false);
                    mortB.setVisible(true);
                    upgradeB.setVisible(true);
                    nextTurnB.setVisible(true);
                    goBankruptB.setVisible(true);
                }else{
                    gameText.appendText("\n\nYou don't have enough money.");
                    buyBYes.setVisible(false);
                    buyBNo.setVisible(true);
                    mortB.setVisible(false);
                    goBankruptB.setVisible(false);
                }
            });

            buyBNo.setOnAction(e ->{
                bidOption = true;
                endMorgB.setVisible(false);
                mortB.setVisible(false);
                nums.setVisible(false);
                lockB.setVisible(false);
                buyBYes.setVisible(false);
                buyBNo.setVisible(false);
                plusTenB.setVisible(true);
                plusFiftyB.setVisible(true);
                plusHundredB.setVisible(true);
                leaveBidB.setVisible(true);
                goBankruptB.setVisible(false);
                auction(b.getTile(p.getPl_pos()).getTile_id());

            });

            goBankruptB.setOnAction(e->{
                bankrupt();
            });

            pay50B.setOnAction(e ->{
                payJail50();
            });

            useJfcB.setOnAction(e->{
                jailJFC();
            });

            plusTenB.setOnAction(e ->{
                bid(10);
            });

            plusFiftyB.setOnAction(e ->{
                bid(50);
            });

            plusHundredB.setOnAction(e ->{
                bid(100);
            });

            leaveBidB.setOnAction(e ->{
                bidNo();
            });

            nextTurnB.setOnAction(e ->{
                endTurn();
            });


            mortB.setOnAction(e -> {
                nums.setVisible(true);
                lockB.setVisible(true);
                mortgOption = true;
                ArrayList<Tile> tempOwns = p.getOwns();
                if (!tempOwns.isEmpty()){
                    for (int i = 0; i < tempOwns.size(); i++) {
                        if (!tempOwns.get(i).getMortgaged()){
                            gameText.appendText("\n\nTile "+tempOwns.get(i).getTile_id() + " "
                                    + tempOwns.get(i).getTileName()+ "\n");
                        }
                    }
                }else{
                    gameText.appendText("\nYou don't own any tiles!");
                    nums.setVisible(false);
                    lockB.setVisible(false);
                }


            });

            endMorgB.setOnAction(e ->{
                mortgOption = false;
                if (p.getPl_cash() < 0){
                    gameText.appendText("\n\nPlayer needs " + Math.abs(p.getPl_cash()));
                    mortB.setVisible(true);
                    //bankruptcy button true
                    endMorgB.setVisible(false);
                    nums.setVisible(false);
                    nextTurnB.setVisible(false);
                    rollB.setVisible(false);
                    lockB.setVisible(false);
                }
                mortB.setVisible(true);
                upgradeB.setVisible(true);
                endMorgB.setVisible(false);
                nums.setVisible(false);
                nextTurnB.setVisible(true);
                rollB.setVisible(false);
                lockB.setVisible(false);
            });

            upgradeB.setOnAction(e ->{
                nums.setVisible(true);
                lockB.setVisible(true);
                upgradeOption = true;
                ArrayList<Tile> tempOwns = p.getOwns();
                if (!tempOwns.isEmpty()){
                    for (int i = 0; i < tempOwns.size(); i++) {
                        if (!tempOwns.get(i).getMortgaged()){
                            gameText.appendText("\n\nTile "+tempOwns.get(i).getTile_id() + " "
                                    + tempOwns.get(i).getTileName()+ "\n");
                        }
                    }
                }else{
                    gameText.appendText("\nYou don't own any tiles!");
                }
            });

            lockB.setOnAction(e ->{
                if(mortgOption == true){
                    boolean removedTile = false;
                    nums.setVisible(true);
                    endMorgB.setVisible(true);
                    int tileToMorg = Integer.parseInt(nums.getText());
                    removedTile = b.mortgage(tileToMorg);
                    if (removedTile == true){
                        mortgOption = false;
                        nums.setVisible(false);
                        lockB.setVisible(false);
                        endMorgB.setVisible(false);
                        gameText.appendText("\n\n" + b.getTile(tileToMorg).getTileName() + " has been mortgaged!");
                        gameText.appendText("\n\nPlayer " + p.getPlayer_id() + " has " + p.getPl_cash());

                    }else{
                        gameText.appendText("\n\nYou don't own this property or it has already been mortgaged, " +
                                "has a greater than 1 house difference, or is a Station or Utility\n");
                        mortgOption = true;
                        nums.setVisible(true);
                        lockB.setVisible(true);
                        endMorgB.setVisible(true);

                    }

                }else if(upgradeOption = true){
                    upgradeTile(Integer.parseInt(nums.getText()));
                    nums.setVisible(false);
                    lockB.setVisible(false);
                }

            });
        }
    }
}