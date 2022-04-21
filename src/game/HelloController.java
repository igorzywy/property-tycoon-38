package game;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

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
    Field[] fields = HelloController.class.getDeclaredFields();
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

    ImageView pawn1;
    ImageView pawn2;
    ImageView pawn3;
    ImageView pawn4;
    ImageView pawn5;
    ImageView pawn6;

    Player p;


//    pawn1 = new ImageView("pawn-black.png");
//    pawn1.setFitHeight(40);
//    pawn1.setFitWidth(40);
//    pawn2 = new ImageView("pawn-red.png");
//    pawn2.setFitHeight(40);
//    pawn2.setFitWidth(40);
//    pawn3 = new ImageView("pawn-blue.png");
//    pawn3.setFitHeight(40);
//    pawn3.setFitWidth(40);
//    //add the pawns to the starting tile
//    pane1.getChildren().addAll(pawn1, pawn2, pawn3);
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

        mortB.setVisible(false);
        lockB.setVisible(false);
        buyBYes.setVisible(false);
        buyBNo.setVisible(false);
        nums.setVisible(false);

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

    }

    @FXML
    protected boolean checkIfFPS(){
        if(b.getTile(p.getPl_pos()).getGroup() == "free"){
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

    @FXML
    protected boolean checkIfCard(){
        return false;
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



    //New Game button is pressed. We create a new Board object and set things to default values
    @FXML
    protected void NewGame() throws InterruptedException {

        if(playerNum !=null) {

            b = new Board(Integer.parseInt(playerNum.getText()));
            System.out.println(b.bSize());
            rollB.setDisable(false);
            mortB.setVisible(true);
            lockB.setVisible(false);
            isAuction = false;



            mortgOption = false;
            AtomicInteger bid = new AtomicInteger();
            for (int i = 0; i < b.bSize(); i++) {
                System.out.println(b.getTile(i));
            }
            for (int i = 0; i < b.pSize(); i++) {
                System.out.println(b.getPlayer(i));
            }
            System.out.println(b.getCardsOK());
            System.out.println(b.getCardsPL());
            p = b.getPlayer(b.getPlayerTurn());

            buyBYes.setVisible(false);

            //Rolls the dice and moves the player
            rollB.setOnAction(e ->{
                b.movePlayer();
                gameText.setText(p.toString());
                rollB.setVisible(false);
                //check if tax,card,go,freepark,jail,gojail
                if (checkIfTax()){
                    taxation();
                }else if(checkIfCard()){

                }else if(checkIfFPS()){
                    getFPS();
                }
                else{
                    gameText.appendText("\n\nDo you want to buy " + b.getTile(p.getPl_pos()).getTileName() + "?");
                }




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


            lockB.setOnAction(e ->{
                if(mortgOption == true){
                    boolean removedTile = false;
                    nums.setVisible(true);
                    int tileToMorg = Integer.parseInt(nums.getText());

                    System.out.println("roler");
                    removedTile = b.mortgage(tileToMorg);
                    if (removedTile == true){
                        mortgOption = false;
                        nums.setVisible(false);
                        lockB.setVisible(false);
                    }else{
                        gameText.setText("You don't own this property");
                        mortgOption = true;
                        nums.setVisible(true);
                        lockB.setVisible(true);
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