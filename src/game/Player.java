package game;


import java.util.ArrayList;

/**
 * Stores all the information about a single player
 */
public class Player {


    private static int pl_num = 1;
    private int player_id = 0;
    private int pl_pos = 0;
    private int pl_cash = 1500; // cash that the player starts with
    private boolean bankrupt = false; // manages if player is bankrupt
    private ArrayList<Tile> owns = new ArrayList<>();
    private boolean inJail = false;
    private int housesOwned = 0;
    private int hotelsOwned = 0;
    private int noJailFreeCard = 0;
    private int lap = 0;
    private boolean passedGo = false;

    /**
     * Gets the number of get out of jail free cards a player owns
     * @return int The amount of get out of jail free cards
     */
    public int getNoJailFreeCard() {
        return noJailFreeCard;
    }

    /**
     * Increases the amount of get out of jail free cards by 1
     */
    public void incrNoJailFreeCard() {
        this.noJailFreeCard ++;
    }

    /**
     * Constructor for Player class sets the player id of the player
     */
    public Player(){
        this.player_id = this.pl_num++;
    }

    /**
     * Gets the number of houses a player owns
     * @return int The number of houses a player owns
     */
    public int getHousesOwned(){
        return this.housesOwned;
    }

    /**
     * Gets the number of hotels that a player owns
     * @return int The number of hotels that a player owns
     */
    public int getHotelsOwned() {
        return hotelsOwned;
    }

    /**
     * Increases the amount of houses a player owns by 1
     */
    public void incrHousesOwned(){
        this.housesOwned++;
    }

    /**
     * Increases the amount of hotels a player owns by 1
     */
    public void incrHotelsOwned(){
        this.hotelsOwned++;
    }

    /**
     * Gets the players ID
     * @return int The player ID
     */
    public int getPlayer_id() {
        return this.player_id;
    }

    /**
     * Sets the players position on the board
     * @param pl_pos The index that the player should be on the board
     */
    public void setPl_pos(int pl_pos) {
        this.pl_pos = pl_pos;
    }

    /**
     * Gets the amount of times that a player has gone past GO
     * @return int The amount of times that a player has gone past GO
     */
    public int getLap(){return this.lap;}


    /**
     * Gets the current players position on the board
     * @return int The index of the board array that the player is currently at
     */
    public int getPl_pos() {

        return this.pl_pos;

    }

    /**
     * Uses the players get out of jail free card and removes them from jail
     */
    public void useJFC(){
        this.noJailFreeCard--;
        this.inJail = false;
    }

    /**
     * Gets the number of tiles that the player owns from a certain group
     * @param group The group to be checked
     * @return int The amount of tiles from a group that the player owns
     */
    public int getNumberOfGroupOwned(String group) {
        int counter = 0;
        if (owns.isEmpty()){
            return 0;
        }else{
            for (int i = 0; i < owns.size(); i++) {
                if (owns.get(i).getGroup().equals(group)){
                    counter++;
                }
            }
        }
        return counter;
    }


    /**
     * Gets if the player is in jail
     * @return boolean True if the player is in jail false otherwise
     */
    public boolean getInJail(){
        return this.inJail;
    }

    /**
     * Sets the players current status as in jail or True
     */
    public void setInJail(){
        this.inJail = true;
    }

    /**
     * Sets the players current status as not in jail or False
     */
    public void setNotInJail(){
        this.inJail = false;
    }

    /**
     * Gets the amount of money the player has
     * @return int The amount of money the player has
     */
    public int getPl_cash(){
        return this.pl_cash;
    }

    /**
     * Sets the amount of money a player has to a new amount
     * @param cash The amount of money to set the player to have
     */
    public void setPl_cash(int cash){
        this.pl_cash = cash;
    }

    /**
     * Adds an amount to the players current bankroll
     * @param cash The amount of money to be added to the bankroll
     */
    public void addPl_cash(int cash) { this.pl_cash+=cash; }





    //get and set the tiles that the player owns

    /**
     * Adds to the list of tiles that the player owns
     * @param t The tile to be added to the list
     */
    public void addOwns(Tile t){
        this.owns.add(t);
    }

    /**
     * Gets the list of tiles that the player owns
     * @return ArrayList Tile The list of tiles that the player owns
     */
    public ArrayList<Tile> getOwns(){
        return this.owns;
    }


    /**
     * Increases the players position on the board and adds £200 if they pass go
     */
    public void incrPos(){
        if (this.pl_pos == 39){
            this.pl_pos = 0;
            pl_cash+=200;
            this.lap++;
        } else {

            this.pl_pos ++;
            this.passedGo =false;
        }

    }

    /**
     * Decreases the players position on the board
     */
    public void decresePos(){
        if (this.pl_pos == 0){
            this.pl_pos = 39;
        } else {
            this.pl_pos --;
        }

    }
    @Override
    public String toString(){
        String a = "-----------------";
        a+=  "\nplayer id: " + this.player_id + "\npos: " + this.pl_pos + "\nCash: " + this.pl_cash;
        a+= "\n-----------------";
        return a;
    }
}
