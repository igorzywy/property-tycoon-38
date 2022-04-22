package game;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.*;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

public class HelloController {

    @FXML private ImageView dice1;
    @FXML private ImageView dice2;
    @FXML private Label diceOut;

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
    int bid = 0;
    boolean isAuction = false;
    int currBidNo = 0;
    private Board b;

    @FXML public TextArea gameText;
    @FXML public TextField playerNum;
    @FXML public TextField nums;
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

    Player p;

    Circle pawn1 = new Circle(20);
    Rectangle pawn2 = new Rectangle(10,10);
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

        //set opacity of all tiles
        for(int i = 0; i < tiles.length; i++) {
            tiles[i].setStyle("-fx-background-color: rgba(255, 255, 255, 0.2);");
        }


        mortB.setVisible(false);
        lockB.setVisible(false);
        buyBYes.setVisible(false);
        buyBNo.setVisible(false);
        nums.setVisible(false);
        endMorgB.setVisible(false);
        nextTurnB.setVisible(false);

    }

//    @FXML
//    protected void setDiceImage(int diceNum, ImageView dice){
//        String num = new String(String.valueOf(diceNum));
//        dice.setImage(new Image("Alea_" + num + ".png"));
//    }

//    @FXML
//    protected int diceRoll() throws InterruptedException {
//        Random rng = new Random();
//        int temp1 = rng.nextInt(1,7);
//        int temp2 = rng.nextInt(1,7);
//        setDiceImage(temp1, dice1);
//        setDiceImage(temp2, dice2);
//        String diceSum = String.valueOf(temp1+temp2);
//        diceOut.setText(diceSum);
//        return temp1+temp2;
//    }

    @FXML
    protected boolean checkIfTax(){
        if(b.getTile(p.getPl_pos()).getGroup().equals("Tax")){
                return true;
            }else{return false;}

//                boolean removedTile = false;
//                nums.setVisible(true);
//                int tileToMorg = Integer.parseInt(nums.getText());
//                while(!removedTile){
//                    removedTile = b.mortgage(tileToMorg);


    }

    @FXML
    protected void taxation(){
        if(p.getPl_cash() >= b.getTile(p.getPl_pos()).getPrice()) {
            gameText.setText("You payed tax: " + b.getTile(p.getPl_pos()).getPrice());
            p.setPl_cash(p.getPl_cash() - b.getTile(p.getPl_pos()).getPrice());
            b.addPublicSpace(b.getTile(p.getPl_pos()).getPrice());
        }else if (p.getOwns().size() >= 1){
            //checks if all the properties the player owns are mortgaged
            boolean allMortgaged = true;
            for (int i = 0; i < p.getOwns().size(); i++) {
                if (p.getOwns().get(i).getMortgaged() == false){
                    allMortgaged = false;
                }
            }
            //if they are mortgaged then the player goes bankrupt
            if (allMortgaged == true){
                bankrupt();
            }
            mortgOption = true;
            nums.setVisible(true);
            lockB.setVisible(true);
            endMorgB.setVisible(true);
        }else{
            bankrupt();
        }
    }

    @FXML
    protected void getFPS(){
        gameText.setText("Player " + p.getPlayer_id() + " has receive the Free Parking Space money (" + b.getFreeParkingSpace() + ")");
        p.addPl_cash(b.getFreeParkingSpace());
        b.setFreeParkingSpace(0);
    }

    @FXML
    protected void goJail(){
        p.setInJail();
        p.setPl_pos(11);
    }

    @FXML
    protected boolean checkIfFPS(){
        if(b.getTile(p.getPl_pos()).getGroup() == "Free"){
            return true;
        }else{
            return false;
        }
    }

    @FXML
    protected boolean checkifGoJail(){
        if(b.getTile(p.getPl_pos()).getGroup() == "GoJail"){
            return true;
        }else{
            return false;
        }
    }
    //checking if the tile is a card tile pot luck
    @FXML
    protected boolean checkIfCardPL(){
        if (b.getTile(p.getPl_pos()).getGroup() == "CardPL"){
            return true;
        }else{
            return false;
        }
    }

    @FXML
    protected void cardPL(){
        Card c = b.getCardsPL().pollFirst();
        if (c.getType() == CardType.bpp){
            gameText.setText(p.getPlayer_id() + " receives: " + c.getAmount() + " from the bank!");
            p.setPl_cash(p.getPl_cash() + c.getAmount());
        }else if (c.getType() == CardType.ppp){
            gameText.setText(p.getPlayer_id() + " receives " + c.getAmount() +
                    " from each player since it's their birthday!");
            for (int i = 0; i < b.getPLAYER_COUNT(); i++) {
                b.getPlayer(i).setPl_cash(b.getPlayer(i).getPl_cash() - c.getAmount());
            }
            p.setPl_cash(p.getPl_cash() + (b.getPLAYER_COUNT()*c.getAmount()) + c.getAmount());
            // get all players and sub 10 from all add players.size()*10 + 10 to the current player
        }else if (c.getType() == CardType.pm){
            gameText.setText(p.getPlayer_id() + " moves to tile " + c.getAmount());
            p.setPl_pos(c.getAmount());
        }else if (c.getType() == CardType.ppb){
            gameText.setText(p.getPlayer_id() + " pays the bank " + c.getAmount());
            p.setPl_cash(p.getPl_cash() - c.getAmount());
        }else if (c.getType() == CardType.ppf){
            gameText.setText(p.getPlayer_id() + " puts " + c.getAmount() + " into free parking!");
            p.setPl_cash(p.getPl_cash() - c.getAmount());
            b.addPublicSpace(c.getAmount());
        }else if (c.getType() == CardType.pmx){
            if (c.getAmount() < 0){
                gameText.setText(p.getPlayer_id() + " moves back " + c.getAmount());
                for (int i = 0; i < c.getAmount(); i++) {
                    p.decresePos();
                }
            }else if(c.getAmount() > 0){
                gameText.setText(p.getPlayer_id() + " moves forwards " + c.getAmount());
                for (int i = 0; i < c.getAmount(); i++) {
                    p.incrPos();
                }
            }
        }else if (c.getType() == CardType.ppr){
            gameText.setText(p.getPlayer_id() + " pays for repairs " +
                    c.getAmount() + " per house " + c.getHotelPrice() + " per hotel ");
            p.setPl_cash(p.getPl_cash() - (p.getHousesOwned() * c.getAmount()) +
                    p.getHotelsOwned() * c.getHotelPrice());
        }else if (c.getType() == CardType.pmf){
            gameText.setText(p.getPlayer_id() + " advances to " + b.getTile(c.getAmount()).getTileName());
            if (c.getAmount() - p.getPl_pos() > b.bSize()-1){
                p.setPl_cash(p.getPl_cash() + 200);
                p.setPl_pos(c.getAmount());
            }else{
                p.setPl_pos(c.getAmount());
            }
        }else if(c.getType() == CardType.jfc){
            p.incrNoJailFreeCard();
        }
        b.addCardsPL(c);
    }
    //opportunity knocks
    @FXML
    protected boolean checkIfCardOK(){
        if (b.getTile(p.getPl_pos()).getGroup() == "CardOK"){
            return true;
        }else{
            return false;
        }
    }
    @FXML protected void cardOK(){
        Card c = b.getCardsOK().pollFirst();
        if (c.getType() == CardType.bpp){
            gameText.setText(p.getPlayer_id() + " receives: " + c.getAmount() + " from the bank!");
            p.setPl_cash(p.getPl_cash() + c.getAmount());
        }else if (c.getType() == CardType.ppp){
            gameText.setText(p.getPlayer_id() + " receives " + c.getAmount() +
                    " from each player since it's their birthday!");
            for (int i = 0; i < b.getPLAYER_COUNT(); i++) {
                b.getPlayer(i).setPl_cash(b.getPlayer(i).getPl_cash() - c.getAmount());
            }
            p.setPl_cash(p.getPl_cash() + (b.getPLAYER_COUNT()*c.getAmount()) + c.getAmount());
            // get all players and sub 10 from all add players.size()*10 + 10 to the current player
        }else if (c.getType() == CardType.pm){
            gameText.setText(p.getPlayer_id() + " moves to tile " + c.getAmount());
            p.setPl_pos(c.getAmount());
        }else if (c.getType() == CardType.ppb){
            gameText.setText(p.getPlayer_id() + " pays the bank " + c.getAmount());
            p.setPl_cash(p.getPl_cash() - c.getAmount());
        }else if (c.getType() == CardType.ppf){
            gameText.setText(p.getPlayer_id() + " puts " + c.getAmount() + " into free parking!");
            p.setPl_cash(p.getPl_cash() - c.getAmount());
            b.addPublicSpace(c.getAmount());
        }else if (c.getType() == CardType.pmx){
            if (c.getAmount() < 0){
                gameText.setText(p.getPlayer_id() + " moves back " + c.getAmount());
                for (int i = 0; i < c.getAmount(); i++) {
                    p.decresePos();
                }
            }else if(c.getAmount() > 0){
                gameText.setText(p.getPlayer_id() + " moves forwards " + c.getAmount());
                for (int i = 0; i < c.getAmount(); i++) {
                    p.incrPos();
                }
            }
        }else if (c.getType() == CardType.ppr){
            gameText.setText(p.getPlayer_id() + " pays for repairs " +
                    c.getAmount() + " per house " + c.getHotelPrice() + " per hotel ");
            p.setPl_cash(p.getPl_cash() - (p.getHousesOwned() * c.getAmount()) +
                    p.getHotelsOwned() * c.getHotelPrice());
        }else if (c.getType() == CardType.pmf){
            gameText.setText(p.getPlayer_id() + " advances to " + b.getTile(c.getAmount()).getTileName());
            if (c.getAmount() - p.getPl_pos() > b.bSize()-1){
                p.setPl_cash(p.getPl_cash() + 200);
                p.setPl_pos(c.getAmount());
            }else{
                p.setPl_pos(c.getAmount());
            }
        }else if(c.getType() == CardType.jfc){
            p.incrNoJailFreeCard();
        }
        b.addCardsOK(c);
    }

    @FXML protected void getCard(){

    }


    @FXML
    protected void mortg(){
        mortgOption = true;
        rollB.setDisable(false);
    }

    @FXML
    protected void bankrupt(){
        p.setBankrupt(true);
        System.out.println(p.getPlayer_id() + " is bankrupt");
        //call for all the players properties to be auctioned
        //call to increment player
    }
    // checking if the player is in jail and if they are just visiting
    @FXML
    protected boolean checkIfJail(){
        if ((b.getTile(p.getPl_pos()).getGroup() == "Jail")){
            return true;
        }else{
            return false;
        }
    }

    @FXML protected boolean checkIfJustVisit(){
        if (p.getInJail()){
            return false;
        }else{
            return true;
        }
    }

    @FXML
    protected void justVisiting(){
        gameText.setText(p.getPlayer_id() + " is just visiting!");
    }

    //buying a house on a property
    //button press to upgrade
    //all houses are then displayed that they own
    //they pick a house that they own
    //house can only be bought if they own the set
    //or if there isn't more than 1 difference in amount of houses between all the properties
    @FXML
    protected void buyingHouse(int tileI){
        if (b.getTile(tileI).getOwnedBy() == p.getPlayer_id()){
             if (playerOwnSet(b.getTile(tileI).getGroup())){
                 if (!b.checkForTwoHouseDiff(b.getTile(tileI).getGroup())){
                     b.getTile(tileI).incrHouses();
                 }else {
                     gameText.setText("you have more than 1 house difference between your properties");
                 }
             }else {
                 gameText.setText("you don't own the set");
             }
        }else{
            gameText.setText("you don't own that tile");
        }
    }

    //checking if the player owns the set
    @FXML
    protected boolean playerOwnSet(String tileGroup){
        int counter = 0;
        for (int i = 0; i < b.bSize(); i++) {
            if (b.getTile(i).getGroup().equals(tileGroup) && (b.getTile(i).getOwnedBy() == p.getPlayer_id())){
                counter++;
            }
        }
        if (counter == b.getGroupSize(tileGroup)){
            return true;
        }else{return false;}


    }
    @FXML
    protected void endTurn(){
        b.incPlayer();
        p = b.getPlayer(b.getPlayerTurn());
        rollB.setVisible(true);
        buyBYes.setVisible(false);
        buyBNo.setVisible(false);
    }

    @FXML
    protected void addBuilding(int option, int tileNo){
        if(option == 1){
            tiles[tileNo].setStyle("-fx-background-color: rgba(255, 255, 255, 0.2);");
        }else if(option == 2){
            tiles[tileNo].setStyle("-fx-background-color: rgba(255, 255, 255, 0.2);");
        }
    }



    //New Game button is pressed. We create a new Board object and set things to default values
    @FXML
    protected void NewGame() throws InterruptedException {

        if(Integer.parseInt(playerNum.getText()) >0 && Integer.parseInt(playerNum.getText())<6){

            b = new Board(Integer.parseInt(playerNum.getText()));
            System.out.println(b.bSize());
            rollB.setDisable(false);
            mortB.setVisible(true);
            lockB.setVisible(false);
            isAuction = false;

            mortgOption = false;
            pawns = new Shape[b.getPLAYER_COUNT()];
            //setting up array of pawns
            for(int i = 0; i < b.getPLAYER_COUNT(); i++){
                pawns[i] = pawnsStorage[i];
            }

            //setting up starting position of all pawns
            tiles[0].getChildren().addAll(pawns);


            p = b.getPlayer(b.getPlayerTurn());

            buyBYes.setVisible(false);

            //Rolls the dice and moves the player
            rollB.setOnAction(e ->{
                int move = b.movePlayer();
                tiles[b.getPlayer(b.getPlayerTurn()).getPl_pos()].getChildren().add(pawns[b.getPlayer(b.getPlayerTurn()).getPlayer_id()-1]);
                gameText.setText(p.toString());
                rollB.setVisible(false);
                //check if tax,cardPl,cardOK,go,freepark,jail,gojail
                if (checkIfTax()){
                    taxation();
                }else if(checkIfCardPL()){
                    cardPL();
                }else if (checkIfCardOK()){
                    cardOK();

                }else if(checkIfFPS()){
                    getFPS();
                }else if(checkifGoJail()){
                    goJail();
                }else if (checkIfJail()){
                    if (checkIfJustVisit()){
                        justVisiting();
                    }

                }
                else{
                    gameText.appendText("\n\nDo you want to buy " + b.getTile(p.getPl_pos()).getTileName() + "?");
                    buyBYes.setVisible(true);
                    buyBNo.setVisible(true);
                    rollB.setVisible(false);
                    mortB.setVisible(false);
                }

            });

            buyBYes.setOnAction(e ->{
                if(b.getPlayer(b.getPlayerTurn()).getPl_cash() >= b.getTile(p.getPl_pos()).getPrice()){
                    b.getTile(p.getPl_pos()).setOwnedBy(p.getPlayer_id());
                    p.addOwns(b.getTile(p.getPl_pos()));
                    p.setPl_cash(p.getPl_cash() - b.getTile(p.getPl_pos()).getPrice());
                    gameText.setText("Property has be bought.");
                    buyBYes.setVisible(false);
                    buyBNo.setVisible(false);
                    mortB.setVisible(true);
                    nextTurnB.setVisible(true);
                }else{
                    gameText.setText("You don't have enough money.");
                    buyBYes.setVisible(false);
                    buyBNo.setVisible(false);
                    mortB.setVisible(true);
                }
            });

            buyBNo.setOnAction(e ->{

            });

            nextTurnB.setOnAction(e ->{
                endTurn();
            });


            mortB.setOnAction(e -> {
//                if (b.getGameEnd()) {
//                    gameText.setText("GAME FINISHED");
//                } else if (mortgOption == false && p.getOwns().size() < 1 && !b.getPlayer(b.getPlayerTurn()).isBankrupt()) {
//                    gameText.setText("you don't own any tiles!");
//                }else if(mortgOption == true && p.getOwns().size() >= 1 && !b.getPlayer(b.getPlayerTurn()).isBankrupt()){
//                    b.mortgage();
//                    mortB.setDisable(true);
//                }
                System.out.println("Morg");
            });

            endMorgB.setOnAction(e ->{
                mortgOption = false;
                mortB.setVisible(false);
            });

            lockB.setOnAction(e ->{
                if(mortgOption == true){
                    boolean removedTile = false;
                    nums.setVisible(true);
                    endMorgB.setVisible(true);
                    int tileToMorg = Integer.parseInt(nums.getText());

                    System.out.println("roler");
                    removedTile = b.mortgage(tileToMorg);
                    if (removedTile == true){
                        mortgOption = false;
                        nums.setVisible(false);
                        lockB.setVisible(false);
                        endMorgB.setVisible(false);

                    }else{
                        gameText.setText("You don't own this property");
                        mortgOption = true;
                        nums.setVisible(true);
                        lockB.setVisible(true);
                        endMorgB.setVisible(true);

                    }

                }




//                if(!nums.getText().isEmpty()) {
//                    if(isAuction == false){
//
//                         this.bid =Integer.parseInt(nums.getText());
//                        ArrayList<Tile> tilesOfPlayer = p.getOwns();
//                        boolean removedTile = false;
//                        for (int i = 0; i < tilesOfPlayer.size(); i++) {
//                            if (tilesOfPlayer.get(i).getTile_id() == bid.get()) {
//                                tilesOfPlayer.get(i).flipMortgaged();
//                                System.out.println("player: " + p.getPlayer_id() + " gains: " + tilesOfPlayer.get(i).getPrice() / 2);
//                                p.addPl_cash(tilesOfPlayer.get(i).getPrice() / 2);
//                                removedTile = true;
//                            }
//                            }if(removedTile == true){
//                                lockB.setDisable(true);
//                            }else{
//                                gameText.setText("You don't own that tile");
//                            }
//                    }else {
//                        if(currBidNo <= b.pSize()){
//                            gameText.setText("All bids have been placed");
//                            b.auctionBuy(b.highest_bid.getKey());
//                            b.incPlayer();
//                            yesB.setDisable(true);
//                            noB.setDisable(true);
//                            rollB.setDisable(false);
//                            mortB.setDisable(false);
//                            gameText.setText(b.getPlayer(b.getPlayerTurn()) + "'s turn");
//                        }
//                        gameText.setText("Got it");
//                        b.bid(Integer.parseInt(nums.getText()), currBidNo);
//                        currBidNo++;
//                        lockB.setDisable(true);
//                        yesB.setDisable(false);
//                        noB.setDisable(false);
//                        gameText.appendText(b.getPlayer(currBidNo) + "want to bid");
//                    }
//
//                }else{
//                        gameText.setText("Please enter a number");
//                }
            });



//            while(!b.getGameEnd()) {
//                for(int i =0; i< b.pSize();)
//                    if(!b.getPlayer(b.getPlayerTurn()).isBankrupt()){
//
//                        b.movePlayer();
//                        b.canBeBought();
//                        while(b.isDouble && b.rl_double<3) {  //check if is double is true and the amount of doubles rolled is less than 3
//                            b.movePlayer();
//                        }
//                        b.isDouble = false; //reset values
//                        b.rl_double = 0;
//                        b.incPlayer();


//            }
//
//            b.rollDice();
//            b.rollDice();
//        }
            }
    }
}