import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Board {
    int TILE_COUNT = 40;
    int PLAYER_COUNT = 5;
    int player_turn = 0;
    int rl_double = 0 ;
    boolean isDouble = false;
    ArrayList<Tile> board = null;
    ArrayList<Player> players = null;
    //opportunity knocks cards
    ArrayList<Card> cardsOK = null;
    //pot luck cards
    ArrayList<Card> cardsPL = null;
    private boolean game_End = false;
    public Board(){

        //adding tiles to the board
        this.board = Tile.getXLSXData();
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

    public void setPLAYER_COUNT(int player_Count){
        PLAYER_COUNT = player_Count;
    }

    public void incPlayer(){
        player_turn++;
        if (this.player_turn > PLAYER_COUNT) {
            this.player_turn = 0;
        }

    }
    public void incrplayerTurn(Board b){
        if (this.player_turn > b.PLAYER_COUNT){
            this.player_turn = 0;
        } else {
            this.player_turn ++;
        }
    }

        public void turn(Board b, Player p){
        System.out.println( "it's player  "+ p.getPlayer_id() + " turn\n");
        movePlayer(b,p);
        Tile t = b.getTile(p.getPl_pos());
        canbeBrought(t,p,b);


        while(this.isDouble && this.rl_double<3) {  //check if is double is true and the amount of doubles rolled is less than 3
            movePlayer(b, p);
        }

            this.isDouble = false; //reset values
            this.rl_double = 0;
            incrplayerTurn(b);
            
    }

    private void canbeBrought(Tile t, Player p, Board b){
        Scanner input = new Scanner(System.in);

        if(t.tile_can_be_bought&& t.owened_by==null){ // check that the title can be bought and is not owned by any1
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
                    t.owened_by = p.player_id;
                    System.out.println("cash: "+p.pl_cash);
                    System.out.println("tile is owned by: "+ t.owened_by);
                }else{
                    System.out.println("you have insufficient funds");
                }
            }
            if(s.equals("n")){
              auction(b);
            }

        }

    }

    private void  auction(Board b){
        for(int i = 0;i< b.pSize();i++){
            Player p = b.getPlayer(i);
            Scanner input = new Scanner(System.in);
            if(!p.isBankrupt()){
                System.out.println( p.player_id + " would you like to place a bid?y/n \n");
                String s = input.nextLine();
                if(s.equals("y")){
                    System.out.println("place bid: ");
                    int bid = input.nextInt();

                }
            }
        }
    }

    private void bid(){

    }

    public void jail(Player p){
        p.setPl_pos(10);
    }

    public void movePlayer(Board b, Player player){

        int roll = b.rollDice();
        if(this.rl_double == 3 ){
            this.isDouble = false;
            this.rl_double = 0;
            b.jail(player);
            System.out.println("player is in jail");
            System.out.println("\n\n");
        }
        else {
            for (int i = 1; i < roll; i++) {
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


    public Card getPLCard(int i) { return this.cardsPL.get(i); }
    public Card getOKCard(int i) { return this.cardsOK.get(i); }

    public int bSize(){
        return this.board.size();
    }


    public int plCardsize() { return this.cardsPL.size(); }
    public int okCardsize() { return this.cardsOK.size(); }

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

        for (int i = 0; i < b.plCardsize(); i++) {
            System.out.println(b.getPLCard(i));
        }

        for (int i = 0; i < b.okCardsize(); i++) {
            System.out.println(b.getOKCard(i));
        }

       while(!b.game_End) {
           for(int i =0; i< b.pSize();)


               if(!b.getPlayer(b.player_turn).isBankrupt()){
                   b.turn(b, b.getPlayer(b.player_turn));
               }

       }

//        b.rollDice();
//        b.rollDice();


    }
}
