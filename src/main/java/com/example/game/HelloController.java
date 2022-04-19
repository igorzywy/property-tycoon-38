package com.example.game;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.util.Pair;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;
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
    Boolean mortgOption = false;
    int bid = 0;
    boolean isBid = false;
    int currBidNo = 0;

    @FXML public TextArea gameText;
    @FXML public TextField playerNum;
    @FXML public TextField nums;
    @FXML public Button rollB;
    @FXML public Button mortB;
    @FXML public Button yesB;
    @FXML public Button noB;
    @FXML public Button lockB;

    ImageView pawn1;
    ImageView pawn2;
    ImageView pawn3;
    ImageView pawn4;
    ImageView pawn5;
    ImageView pawn6;
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
    protected void mortg(){
        mortgOption = true;
        rollB.setDisable(false);
    }

    //New Game button is pressed. We create a new Board object and set things to default values
    @FXML
    protected void NewGame() throws InterruptedException {

        if(playerNum !=null) {

            Board b = new Board(Integer.parseInt(playerNum.getText()));
            System.out.println(b.bSize());
            rollB.setDisable(false);
            mortB.setDisable(false);
            noB.setDisable(true);
            yesB.setDisable(true);
            lockB.setDisable(true);
            isBid = false;
            currBidNo = 0;
            Boolean mortgOption = false;
            AtomicInteger bid = new AtomicInteger();
            for (int i = 0; i < b.bSize(); i++) {
                System.out.println(b.getTile(i));
            }
            for (int i = 0; i < b.pSize(); i++) {
                System.out.println(b.getPlayer(i));
            }
            System.out.println(b.getCardsOK());
            System.out.println(b.getCardsPL());
            Player p = b.getPlayer(b.getPlayerTurn());
            mortB.setOnAction(e -> {
                if (b.getGameEnd()) {
                    gameText.setText("GAME FINISHED");
                } else if (mortgOption == false && p.getOwns().size() < 1 && !b.getPlayer(b.getPlayerTurn()).isBankrupt()) {
                    gameText.setText("you don't own any tiles!");
                }else if(mortgOption == true && p.getOwns().size() >= 1 && !b.getPlayer(b.getPlayerTurn()).isBankrupt()){
                    b.mortgage();
                    mortB.setDisable(true);
                }
            });

            //Rolls the dice and moves the player
            rollB.setOnAction(e ->{
                b.movePlayer();
                //Tile t does not get update, and always stays as the first tile to be assigned to it
                //i dont understand why it does than, it should change, but it doesn't
                Tile t = b.getTile(p.getPl_pos());
                System.out.println(t);
                if(t.getCanBeBought() && t.getOwnedBy() == null) { // check that the title can be bought and is not owned by any1
                    gameText.setText(t.getTileName());
                    gameText.appendText("\nPrice is " + t.getPrice());
                    System.out.println("\nDo you want to buy?");
                    mortB.setDisable(true);
                    rollB.setDisable(true);
                    yesB.setDisable(false);
                    noB.setDisable(false);
                    //check if tile belongs to someone else
                }else if(t.getOwnedBy() != null && t.getOwnedBy() != b.getPlayer(b.getPlayerTurn()).player_id){
                    gameText.setText(b.getPlayer(b.getPlayerTurn()) + " pays rent to " + b.getPlayer(t.owned_by) );
                }
            });


            yesB.setOnAction(e ->{
                if(isBid == false){
                    Tile t = b.getTile(p.getPl_pos());
                    int cash = p.getPl_cash();
                    int price = t.getPrice();
                    int total = cash - price;
                    if(total>0) {
                        p.setPl_cash(total);//yh
                        t.setOwnedBy(p.getPlayer_id()); //yh
                        p.addOwns(t);
                        gameText.setText("cash: " + p.getPl_cash());
                        gameText.appendText("\ntile is owned by: " + t.getOwnedBy());
                        //reset values
                        b.isDouble = false;
                        b.rl_double = 0;
                        b.incPlayer();
                        yesB.setDisable(true);
                        noB.setDisable(true);
                        b.incPlayer();
                        rollB.setDisable(false);
                        mortB.setDisable(false);
                        gameText.setText(b.getPlayer(b.getPlayerTurn()) + "'s turn");
                    }else{
                        gameText.setText("You have insufficient funds");
                        b.mortgage();
                    }
                }else{
                    System.out.println("place bid: ");
                    lockB.setDisable(false);
                    yesB.setDisable(true);
                    noB.setDisable(true);
                }
            });

            noB.setOnAction(e ->{
                if(isBid == false){
                    isBid = true;
                    currBidNo = 1;
                    b.highest_bid = new Pair<>(0,0);
                    gameText.setText(b.getPlayer(currBidNo) + "want to bid?");

                }else{
                    isBid = true;
                    yesB.setDisable(false);
                    if(currBidNo <= b.pSize() ){
                            gameText.setText("All bids have been placed");
                            isBid = false;
                            b.auctionBuy(b.highest_bid.getKey());
                            b.incPlayer();
                            yesB.setDisable(true);
                            noB.setDisable(true);
                            rollB.setDisable(false);
                            mortB.setDisable(false);
                            gameText.setText(b.getPlayer(b.getPlayerTurn()) + "'s turn");
                    }
                    currBidNo++;
                    gameText.setText(b.getPlayer(currBidNo) + " want to bid?");
                }

            });

            lockB.setOnAction(e ->{
                if(!nums.getText().isEmpty()) {
                    if(isBid == false){
                        this.bid =Integer.parseInt(nums.getText());
                        ArrayList<Tile> tilesOfPlayer = p.getOwns();
                        boolean removedTile = false;
                        for (int i = 0; i < tilesOfPlayer.size(); i++) {
                            if (tilesOfPlayer.get(i).getTile_id() == bid.get()) {
                                tilesOfPlayer.get(i).flipMortgaged();
                                System.out.println("player: " + p.getPlayer_id() + " gains: " + tilesOfPlayer.get(i).getPrice() / 2);
                                p.addPl_cash(tilesOfPlayer.get(i).getPrice() / 2);
                                removedTile = true;
                            }
                            }if(removedTile == true){
                                lockB.setDisable(true);
                            }else{
                                gameText.setText("You don't own that tile");
                            }
                    }else {
                        if(currBidNo <= b.pSize()){
                            gameText.setText("All bids have been placed");
                            b.auctionBuy(b.highest_bid.getKey());
                            b.incPlayer();
                            yesB.setDisable(true);
                            noB.setDisable(true);
                            rollB.setDisable(false);
                            mortB.setDisable(false);
                            gameText.setText(b.getPlayer(b.getPlayerTurn()) + "'s turn");
                        }
                        gameText.setText("Got it");
                        b.bid(Integer.parseInt(nums.getText()), currBidNo);
                        currBidNo++;
                        lockB.setDisable(true);
                        yesB.setDisable(false);
                        noB.setDisable(false);
                        gameText.appendText(b.getPlayer(currBidNo) + "want to bid");
                    }

                }else{
                        gameText.setText("Please enter a number");
                }
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